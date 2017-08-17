package br.ufpe.cin.generated;

import java.util.*;
import cide.gast.*;

import java.io.PrintStream;

import cide.languages.*;

import de.ovgu.cide.fstgen.ast.*;

public class SimplePrintVisitor extends AbstractFSTPrintVisitor  {
	public SimplePrintVisitor(PrintStream out) {
		super(out); generateSpaces=true;
	}
	public SimplePrintVisitor() {
		super(); generateSpaces=true;
	}
	public boolean visit(FSTNonTerminal nonTerminal) {
		if (nonTerminal.getType().equals("CompilationUnit")) {
			printFeatures(nonTerminal,true);
			for (FSTNode v : getChildren(nonTerminal,"SourceElement")) {
				v.accept(this);
			}
			printFeatures(nonTerminal,false);
			return false;
		}
		if (nonTerminal.getType().equals("SourceElement1")) {
			printFeatures(nonTerminal,true);
			{
				FSTNode v=getChild(nonTerminal, "Function");
				if (v!=null) {
					v.accept(this);
				}
			}
			{
				FSTNode v=getChild(nonTerminal, "Id");
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("(");
			{
				FSTNode v=getChild(nonTerminal, "FormalParameters");
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken(")");
			printToken("{");
			{
				FSTNode v=getChild(nonTerminal, "FunctionBody");
				if (v!=null) {
					v.accept(this);
				}
			}
			printToken("}");
			printFeatures(nonTerminal,false);
			return false;
		}
		if (nonTerminal.getType().equals("SourceElement2")) {
			printFeatures(nonTerminal,true);
			{
				FSTNode v=getChild(nonTerminal, "StatementList");
				if (v!=null) {
					v.accept(this);
				}
			}
			printFeatures(nonTerminal,false);
			return false;
		}
		if (nonTerminal.getType().equals("FunctionBody")) {
			printFeatures(nonTerminal,true);
			for (FSTNode v : getChildren(nonTerminal,"SourceElement")) {
				v.accept(this);
			}
			printFeatures(nonTerminal,false);
			return false;
		}
		throw new RuntimeException("Unknown Non Terminal in FST "+nonTerminal);
	}
	protected boolean isSubtype(String type, String expectedType) {
		if (type.equals(expectedType)) return true;
		if (type.equals("SourceElement2") && expectedType.equals("SourceElement")) return true;
		if (type.equals("PostfixOperator1") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("Statement2") && expectedType.equals("Statement")) return true;
		if (type.equals("PostfixOperator2") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("Statement1") && expectedType.equals("Statement")) return true;
		if (type.equals("AdditiveOperator1") && expectedType.equals("AdditiveOperator")) return true;
		if (type.equals("SourceElement1") && expectedType.equals("SourceElement")) return true;
		if (type.equals("AdditiveOperator2") && expectedType.equals("AdditiveOperator")) return true;
		return false;
	}
}
