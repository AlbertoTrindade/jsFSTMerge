package br.ufpe.cin.generator;

/**
 * Class reponsible for dealing with the automatic generation 
 * of parsers trough <i>featureBNF</i> grammars. See the 
 * text files in the folder <i>/documentation</i> for more details.
 * @author Guilherme
 *
 */
public class Generator {

	public static void main(String[] args) {
		try{
			/* 1. read the featurebnf grammar in a .gcide file and converts into a javaCC grammar (.jj)
			   with all associated artefacts (pretty printer,etc) */
			new FSTgenTask().generate(
					"grammars/javascript_merge_fst.gcide", 
					"src/br/ufpe/cin/generated/javascript_merge.jj",
					"br.ufpe.cin.generated"
					);

			//2. generate a javaCC parser based on the .jj file
			new JavaCCTask().generate(
					"src/br/ufpe/cin/generated/javascript_merge.jj", 
					"src/br/ufpe/cin/generated/"
					);

			//3. test the generated artefacts
			new GeneratedParserTest().test(
					"br.ufpe.cin.generated.JavaScriptMergeParser", 
					"CompilationUnit", 
					"grammars/javascript_merge_fst_test.js"
					);
		
		}catch(Exception e){
			System.err.println("Refresh the root project folder (F5). Try again.");
			e.printStackTrace();
		}
	}
}
