package br.ufpe.cin.mergers.handlers;

import java.util.List;

import br.ufpe.cin.files.FilesManager;
import br.ufpe.cin.mergers.util.MergeContext;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

/**
 * This handler detects if a deleted function declaration from left or right was actually just renamed by
 * one of the developers while it was edited by the other, generating a renaming conflict (FP by Semistructured merge)
 * 
 * This handler does not avoid the conflict. It's only for statistical purpose.
 * @author Alberto
 */
public class RenamingHandler {

	private final static double DEFAULT_SIMILARITY_THRESHOLD = 0.9;

	public static void handle(MergeContext context) {
		long t0 = System.nanoTime();
		
		normalizeDeletions(context);
		
		for (FSTNode deletedLeft : context.nodesDeletedByLeft) {
			checkDeletions(context, deletedLeft, context.rightTree, true);
		}
		
		for (FSTNode deletedRight : context.nodesDeletedByRight) {
			checkDeletions(context, deletedRight, context.leftTree, false);
		}
		
		//excluding handler execution time to not bias performance evaluation as it is not required to original semistructured merge time
		long tf = System.nanoTime();
		long tt = tf - t0;
		context.semistructuredMergeTime = context.semistructuredMergeTime - tt;
	}
	
	private static void normalizeDeletions(MergeContext context) {
		//Getting original deleted nodes from its source
		normalize(context,context.nodesDeletedByLeft);
		normalize(context,context.nodesDeletedByRight);
	}
	
	private static void normalize(MergeContext context, List<FSTNode> nodes) {
		for (int i = 0; i<nodes.size();i++) {
			FSTNode deleted = nodes.get(i);
			if (deleted instanceof FSTNonTerminal) {
				FSTNode id = getId(deleted);
				if(id!=null){
					id = FilesManager.findNodeByID(context.baseTree, ((FSTTerminal) id).getBody());
					FSTNode normal_declaration = id.getParent();
					nodes.set(i, normal_declaration);
				}
			}
		}
	}

	private static void checkDeletions(MergeContext context, FSTNode deletedNode, FSTNode source, boolean isLeftDeletion) {
		if (deletedNode instanceof FSTNonTerminal) { // function declarations elements are FSTNonTerminal nodes
			//1. search node's ID
			FSTNode identifier = getId(deletedNode);

			if(identifier != null){
				FSTNode correspondingIdInSource = FilesManager.findNodeByID(source, ((FSTTerminal) identifier).getBody());
				if(correspondingIdInSource != null && hasChanges(deletedNode, correspondingIdInSource.getParent())){
					checkDeclarations(context, deletedNode,((FSTTerminal) identifier).getBody(), isLeftDeletion);
				}
			}
		}
	}

	private static FSTNode getId(FSTNode node) {
		for (FSTNode child : ((FSTNonTerminal)node).getChildren()) {
			if(child.getType().equals("Id")){
				return child;
			}
		}
		return null;
	}

	private static void checkDeclarations(MergeContext context, FSTNode deletedNode, String identifier, boolean isLeftDeletion) {
		FSTNode correspondingInMerged = FilesManager.findNodeByID(context.superImposedTree, identifier);
		
		if (correspondingInMerged == null) return;
		
		FSTNonTerminal declarationInMerged = correspondingInMerged.getParent();
		
		if (declarationInMerged != null){
			List<FSTNode> candidates = (isLeftDeletion)? context.addedLeftNodes : context.addedRightNodes;
			
			for(FSTNode renamingCandidate : candidates){
				if(renamingCandidate instanceof FSTNonTerminal){
					if(hasSameShape(renamingCandidate,deletedNode) && hasSimilarContent(renamingCandidate,deletedNode)){
						context.renamingConflicts++;
						break;
					}
				}
			}
		}
	}

	private static boolean hasChanges(FSTNode deletedNode, FSTNonTerminal declarationInSource) {
		return !(hasSameShape(declarationInSource, deletedNode) && hasSimilarContent(declarationInSource, deletedNode, 1));
	}

	private static boolean hasSimilarContent(FSTNode candidate, FSTNode deletedNode) {
		return hasSimilarContent(candidate, deletedNode, DEFAULT_SIMILARITY_THRESHOLD);
	}

	private static boolean hasSimilarContent(FSTNode candidate, FSTNode deletedNode, double similarityThreshold) {
		String printRenamedCandidate = FilesManager.getStringContentIntoSingleLineNoSpacing(FilesManager.prettyPrint((FSTNonTerminal) candidate));
		String printDeletedNode = FilesManager.getStringContentIntoSingleLineNoSpacing(FilesManager.prettyPrint((FSTNonTerminal) deletedNode));
		double similarity = FilesManager.computeStringSimilarity(printRenamedCandidate, printDeletedNode);
		return similarity >= similarityThreshold;
	}

	private static boolean hasSameShape(FSTNode renamingCadidate, FSTNode deletedNode) {
		String typesA = getTypes(renamingCadidate);
		String typesB = getTypes(deletedNode);
		return typesA.equals(typesB);
	}

	private static String getTypes(FSTNode node) {
		String types = node.getType();
		if (node instanceof FSTNonTerminal) {
			for (FSTNode child : ((FSTNonTerminal) node).getChildren()) { 
				types+= getTypes(child);
			}
		}
		return types;
	}
}