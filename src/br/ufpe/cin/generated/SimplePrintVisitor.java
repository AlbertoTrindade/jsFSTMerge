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
		if (type.equals("CallExpressionInternal1") && expectedType.equals("CallExpressionInternal")) return true;
		if (type.equals("CallExpressionPart1") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("MemberExpression1") && expectedType.equals("MemberExpression")) return true;
		if (type.equals("Argument1") && expectedType.equals("Argument")) return true;
		if (type.equals("LeftHandSideExpression2") && expectedType.equals("LeftHandSideExpression")) return true;
		if (type.equals("ReturnExpression1") && expectedType.equals("ReturnExpression")) return true;
		if (type.equals("CallExpressionPart3") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("FunctionExpression2") && expectedType.equals("FunctionExpression")) return true;
		if (type.equals("AssignmentOperator4") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("Statement2") && expectedType.equals("Statement")) return true;
		if (type.equals("AssignmentOperator2") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentOperator12") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AdditiveOperator1") && expectedType.equals("AdditiveOperator")) return true;
		if (type.equals("Statement4") && expectedType.equals("Statement")) return true;
		if (type.equals("SourceElement2") && expectedType.equals("SourceElement")) return true;
		if (type.equals("AssignmentExpression1") && expectedType.equals("AssignmentExpression")) return true;
		if (type.equals("AssignmentOperator10") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentOperator9") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("InitializerExpression2") && expectedType.equals("InitializerExpression")) return true;
		if (type.equals("PostfixOperator1") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("AssignmentOperator7") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("MemberExpressionPart2") && expectedType.equals("MemberExpressionPart")) return true;
		if (type.equals("CallExpressionInternal2") && expectedType.equals("CallExpressionInternal")) return true;
		if (type.equals("MemberExpression2") && expectedType.equals("MemberExpression")) return true;
		if (type.equals("CallExpressionPart2") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("ReturnExpression2") && expectedType.equals("ReturnExpression")) return true;
		if (type.equals("Statement3") && expectedType.equals("Statement")) return true;
		if (type.equals("FunctionExpression1") && expectedType.equals("FunctionExpression")) return true;
		if (type.equals("LeftHandSideExpression1") && expectedType.equals("LeftHandSideExpression")) return true;
		if (type.equals("AssignmentOperator5") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("Statement1") && expectedType.equals("Statement")) return true;
		if (type.equals("AssignmentOperator3") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentOperator11") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentOperator1") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("SourceElement1") && expectedType.equals("SourceElement")) return true;
		if (type.equals("AdditiveOperator2") && expectedType.equals("AdditiveOperator")) return true;
		if (type.equals("MemberExpressionPart1") && expectedType.equals("MemberExpressionPart")) return true;
		if (type.equals("AssignmentOperator8") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentOperator6") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("InitializerExpression1") && expectedType.equals("InitializerExpression")) return true;
		if (type.equals("PostfixOperator2") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("Argument2") && expectedType.equals("Argument")) return true;
		if (type.equals("AssignmentExpression2") && expectedType.equals("AssignmentExpression")) return true;
		return false;
	}
}
