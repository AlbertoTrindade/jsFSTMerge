package br.ufpe.cin.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import br.ufpe.cin.app.JFSTMerge;
import br.ufpe.cin.files.FilesManager;
import br.ufpe.cin.generated.Java18MergeParser;
import cide.gparser.OffsetCharStream;
import cide.gparser.ParseException;
import cide.gparser.TokenMgrError;
import de.ovgu.cide.fstgen.ast.FSTFeatureNode;
import de.ovgu.cide.fstgen.ast.FSTNode;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;

/**
 * Class responsible for parsing javascript files, based on a 
 * <i>featurebnf</i> JavaScript annotated grammar: 
 * {@link http://tinyurl.com/java18featurebnf TODO: update this when grammar is done }
 * For more information, see the documents in <i>guides</i> package.
 * @author Guilherme and Alberto
 */
public class JSParser {

	/**
	 * Parses a given .js file
	 * @param javaScriptFile
	 * @return ast representing the java file
	 * @throws ParseException 
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public FSTNode parse(File javaScriptFile) throws FileNotFoundException, UnsupportedEncodingException, ParseException, TokenMgrError  {
		FSTFeatureNode generatedAst = new FSTFeatureNode("");//root node
		if(isValidFile(javaScriptFile)){
			if(!JFSTMerge.isGit){
				System.out.println("Parsing: " + javaScriptFile.getAbsolutePath());
			}
			Java18MergeParser parser = new Java18MergeParser(new OffsetCharStream(new InputStreamReader(new FileInputStream(javaScriptFile),"UTF8")));
			parser.CompilationUnit(false);
			generatedAst.addChild(new FSTNonTerminal("JavaScript-File", javaScriptFile.getName()));
			generatedAst.addChild(parser.getRoot());
		}
		return generatedAst;
	}

	/**
	 * Checks if the given file is adequate for parsing.
	 * @param file to be parsed
	 * @return true if the file is appropriated, or false
	 * @throws FileNotFoundException 
	 * @throws ParseException 
	 */
	private boolean isValidFile(File file) throws FileNotFoundException, ParseException 
	{
		if(FilesManager.readFileContent(file).isEmpty()){
			throw new FileNotFoundException();
		} else if(file != null && (isJavaFile(file) || JFSTMerge.isGit)){
			return true;
		} else if(file != null && !isJavaFile(file)){
			throw new ParseException("The file " + file.getName() + " is not a valid .js file.");
		} else {
			return false;
		}
	}
	

	/**
	 * Checks if a given file is a .js file.
	 * @param file
	 * @return true in case file extension is <i>javascript</i>, or false
	 */
	private boolean isJavaFile(File file){
		//return FilenameUtils.getExtension(file.getAbsolutePath()).equalsIgnoreCase("java");
		return file.getName().toLowerCase().contains(".js");
	}
}