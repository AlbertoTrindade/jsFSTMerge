package br.ufpe.cin.mergers.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.EvaluatorException;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.ast.AstNode;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.NodeVisitor;
import org.mozilla.javascript.ast.VariableInitializer;

import br.ufpe.cin.files.FilesManager;
import br.ufpe.cin.mergers.util.MergeConflict;
import br.ufpe.cin.mergers.util.MergeContext;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

/**
 * This handler detects if a Function Expression was converted into a Function Declaration (added node) by one of the developers
 * while it was edited by the other one, generating a function conversion conflict (FP by semistructured merge). Also, it
 * detects the other way around, the conversion of a Function Declaration (removed node) into a Function Expression
 * 
 * This handler does not avoid the conflict. It's only for statistical purpose.
 * @author Alberto
 */
public class FunctionConversionHandler {

	private final static int RHINO_VAR_TYPE = 123; // https://github.com/mozilla/rhino/blob/master/src/org/mozilla/javascript/Token.java#L180
	private final static int RHINO_FUNCTION_TYPE = 110; // https://github.com/mozilla/rhino/blob/master/src/org/mozilla/javascript/Token.java#L167

	public static void handle(MergeContext context) {
		long t0 = System.nanoTime();
		
		List<MergeConflict> semistructuredMergeConflicts = FilesManager.extractMergeConflicts(context.semistructuredOutput);

		for (FSTNode addedLeft : context.addedLeftNodes) {
			if (addedLeft.getType().equals("FuncDeclaration")) {
				checkAddedFunctionDeclaration(context, (FSTNonTerminal) addedLeft, semistructuredMergeConflicts, true);
			}
		}
		
		for (FSTNode addedRight : context.addedRightNodes) {
			if (addedRight.getType().equals("FuncDeclaration")) {
				checkAddedFunctionDeclaration(context, (FSTNonTerminal) addedRight, semistructuredMergeConflicts, false);
			}
		}
		
		for (FSTNode deletedLeft : context.nodesDeletedByLeft) {
			if (deletedLeft.getType().equals("FuncDeclaration")) {
				checkDeletedFunctionDeclaration(context, (FSTNonTerminal) deletedLeft, context.rightTree, semistructuredMergeConflicts, true);
			}
		}
		
		for (FSTNode deletedRight : context.nodesDeletedByRight) {
			if (deletedRight.getType().equals("FuncDeclaration")) {
				checkDeletedFunctionDeclaration(context, (FSTNonTerminal) deletedRight, context.leftTree, semistructuredMergeConflicts, false);
			}
		}
		
		//excluding handler execution time to not bias performance evaluation as it is not required to original semistructured merge time
		long tf = System.nanoTime();
		long tt = tf - t0;
		context.semistructuredMergeTime = context.semistructuredMergeTime - tt;
	}

	private static void checkAddedFunctionDeclaration(MergeContext context, FSTNode addedFunctionDeclaration, List<MergeConflict> semistructuredMergeConflicts, boolean isLeftAddition) {
		String functionId = ((FSTTerminal) getId(addedFunctionDeclaration)).getBody();
		
		for (MergeConflict mergeConflict : semistructuredMergeConflicts) {
			String deletedCandidate = isLeftAddition ? mergeConflict.left : mergeConflict.right;
			String editedCandidate = isLeftAddition ? mergeConflict.right : mergeConflict.left;
			
			try {
				if (!hasFunctionExpressionAssignedToId(deletedCandidate, functionId) &&
					hasFunctionExpressionAssignedToId(editedCandidate, functionId)) {
					context.functionConversionConflict++;
					break;
				}
			} catch (IOException | EvaluatorException e) {
				continue;
			}
		}
	}
	
	private static void checkDeletedFunctionDeclaration(MergeContext context, FSTNode deletedFunctionDeclaration, FSTNode source, List<MergeConflict> semistructuredMergeConflicts, boolean isLeftAddition) {
		String functionId = ((FSTTerminal) getId(deletedFunctionDeclaration)).getBody();
		
		String content = isLeftAddition ? context.getLeftContent() : context.getRightContent();
		FSTNode correspondingInSource = FilesManager.findNodeByID(source, functionId);
		
		if (correspondingInSource == null) return;
		
		FSTNonTerminal editedFunctionDeclaration = correspondingInSource.getParent();
		if (!editedFunctionDeclaration.getType().equals("FuncDeclaration")) return;
		
		FSTNode editedFunctionBody = editedFunctionDeclaration.getChildren().get(3);
		String editedFunctionBodyContent = FilesManager.prettyPrint((FSTNonTerminal) editedFunctionBody);
		
		for (MergeConflict mergeConflict : semistructuredMergeConflicts) {
			String deletedCandidate = isLeftAddition ? mergeConflict.left : mergeConflict.right;
			String editedCandidate = isLeftAddition ? mergeConflict.right : mergeConflict.left;
			
			try {
				if (deletedCandidate.isEmpty() &&
					editedCandidate.replaceAll("\\s+","").equals(editedFunctionBodyContent.replaceAll("\\s+","")) &&
					hasFunctionExpressionAssignedToId(content, functionId)) {
					context.functionConversionConflict++;
					break;
				}
			} catch (IOException | EvaluatorException e) {
				continue;
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
	
	private static boolean hasFunctionExpressionAssignedToId(String nodeBody, String id) throws IOException {
		CompilerEnvirons env = new CompilerEnvirons();
		env.setRecoverFromErrors(true);
		IRFactory factory = new IRFactory(env);
		AstRoot rootNode = factory.parse(nodeBody, null, 0);

		List<AstNode> instances = new ArrayList<AstNode>();
		
		rootNode.visit(new NodeVisitor() {
			@Override
			public boolean visit(AstNode node) {
				if (node.toSource().equals(id) && 
					node.getParent().getType() == RHINO_VAR_TYPE &&
					((VariableInitializer) node.getParent()).getInitializer().getType() == RHINO_FUNCTION_TYPE) {
					instances.add(node);
				}
				
				return true;
			}
		});

		return instances.size() > 0;
	}
}