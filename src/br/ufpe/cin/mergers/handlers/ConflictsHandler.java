package br.ufpe.cin.mergers.handlers;

import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

import br.ufpe.cin.exceptions.TextualMergeException;
import br.ufpe.cin.mergers.util.MergeContext;
import br.ufpe.cin.printers.Prettyprinter;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

/**
 * Class responsbile for detecting with language specific conflicts that
 * just superimposition of trees is not able to detect/resolve.
 * 
 * In the future, we might extend these handlers to, in addition to detecting conflicts, dealing with them
 * @author Alberto
 */
final public class ConflictsHandler {

	public static void handle(MergeContext context) throws TextualMergeException{
		context.semistructuredOutput = Prettyprinter.print(context.superImposedTree); //partial result of semistructured merge is necessary for further processing
		
		findRenamingConflicts(context);
		findFunctionConversionConflicts(context);
		findDuplicatedDeclarationsErrors(context);
		removeDeletedDeclarations(context);
	}

	private static void findRenamingConflicts(MergeContext context) {
		RenamingHandler.handle(context);
	}
	
	private static void findFunctionConversionConflicts(MergeContext context) {
		FunctionConversionHandler.handle(context);
	}
	
	private static void findDuplicatedDeclarationsErrors(MergeContext context) {
		DuplicatedDeclarationHandler.handle(context);
	}
	
	private static void removeDeletedDeclarations(MergeContext context) {
		DeletionsHandler.handle(context);
	}
}
