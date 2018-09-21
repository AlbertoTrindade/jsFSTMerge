package br.ufpe.cin.mergers.handlers;

import br.ufpe.cin.files.FilesManager;
import br.ufpe.cin.mergers.util.MergeContext;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

/**
 * This handler simply cleans up the remaining of deleted function declarations (when there's no conflicts)
 * @author Alberto
 */
public class DeletionsHandler {

	public static void handle(MergeContext context) {
		for(FSTNode deletedLeft : context.nodesDeletedByLeft){
			manageDeletions(context, deletedLeft);
		}
		for(FSTNode deletedRight : context.nodesDeletedByRight){
			manageDeletions(context, deletedRight);
		}
	}

	private static void manageDeletions(MergeContext context, FSTNode deletedNode) {
		if (deletedNode instanceof FSTNonTerminal) {
			if (!deletedNode.getType().equals("FuncDeclaration")) return;

			String identifier = ((FSTTerminal) getId(deletedNode)).getBody();
			FSTNode correspondingInMerged = FilesManager.findNodeByID(context.superImposedTree, identifier);
			
			if (correspondingInMerged == null) return;
			
			FSTNonTerminal functionDeclarationInMerged = (FSTNonTerminal) (correspondingInMerged.getParent());
 			FSTNode functionDeclarationInMergedBody = functionDeclarationInMerged.getChildren().size() > 3 ?
 					functionDeclarationInMerged.getChildren().get(3) : functionDeclarationInMerged.getChildren().get(2);
			String functionDeclarationInMergedBodyContent = FilesManager.prettyPrint((FSTNonTerminal) functionDeclarationInMergedBody);

			if (functionDeclarationInMergedBodyContent.trim().isEmpty()){
				deleteNode(functionDeclarationInMerged);
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

	private static void deleteNode(FSTNonTerminal node){
		FSTNonTerminal parent = node.getParent();
		parent.removeChild(node);
	}
}