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

import br.ufpe.cin.files.FilesManager;
import br.ufpe.cin.mergers.util.MergeConflict;
import br.ufpe.cin.mergers.util.MergeContext;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

/**
 * Unstructured merge added false negatives are mostly caused by failing to detect that the contributions to be merged add duplicated function declarations. 
 * Unstructured merge reports no conflict when merging developers contributions that add function declarations with the same name to different areas of the same program.
 * This handler detects such situations. 
 * 
 * Note that semistructured merge detects such false negatives without handlers, so this handler serves only for statistics purpose.
 * 
 * @author Alberto
 */
public class DuplicatedDeclarationHandler {
	private final static int RHINO_FUNCTION_TYPE = 110;
	
	public static void handle(MergeContext context) {
		long t0 = System.nanoTime();
		
		List<MergeConflict> semistructuredMergeConflicts = FilesManager.extractMergeConflicts(context.semistructuredOutput);

		for (FSTNode addedLeft : context.addedLeftNodes) {
			for (FSTNode addedRight : context.addedRightNodes) {
				if (addedLeft.getType().equals("FuncDeclaration") && addedRight.getType().equals("FuncDeclaration")) {
					checkAddedFunctionDeclarations(context, (FSTNonTerminal) addedLeft, (FSTNonTerminal) addedRight, semistructuredMergeConflicts);
				}
			}
		}
		
		//excluding handler execution time to not bias performance evaluation as it is not required to original semistructured merge time
		long tf = System.nanoTime();
		long tt = tf - t0;
		context.semistructuredMergeTime = context.semistructuredMergeTime - tt;
	}
	
	private static void checkAddedFunctionDeclarations(MergeContext context, FSTNonTerminal leftFunctionDeclaration, FSTNonTerminal rightFunctionDeclaration, List<MergeConflict> semistructuredMergeConflicts) {
		String leftFunctionId = ((FSTTerminal) getId(leftFunctionDeclaration)).getBody();
		String rightFunctionId = ((FSTTerminal) getId(rightFunctionDeclaration)).getBody(); 
		
		if (!leftFunctionId.equals(rightFunctionId)) return;
		
		try {
			if (countInstances(context.unstructuredOutput, leftFunctionId) == 2) {
				context.duplicatedDeclarationErrors++;
			}
		} catch (IOException | EvaluatorException e) {
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
	
	private static int countInstances(String nodeBody, String id) throws IOException{
		CompilerEnvirons env = new CompilerEnvirons();
		env.setRecoverFromErrors(true);
		IRFactory factory = new IRFactory(env);
		AstRoot rootNode = factory.parse(nodeBody, null, 0);

		List<AstNode> instances = new ArrayList<AstNode>();
		
		rootNode.visit(new NodeVisitor() {
			@Override
			public boolean visit(AstNode node) {
				if(node.toSource().equals(id) && node.getParent().getType() == RHINO_FUNCTION_TYPE) {
					instances.add(node);
				}
				
				return true;
			}
		});

		return instances.size();
	}
}