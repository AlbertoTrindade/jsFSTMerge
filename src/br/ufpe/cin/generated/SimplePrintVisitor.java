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
		if (nonTerminal.getType().equals("FuncDeclaration")) {
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
		if (nonTerminal.getType().equals("StmtList")) {
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
		if (type.equals("TryStatementInternal2") && expectedType.equals("TryStatementInternal")) return true;
		if (type.equals("PropertyName1") && expectedType.equals("PropertyName")) return true;
		if (type.equals("UnaryExpression1") && expectedType.equals("UnaryExpression")) return true;
		if (type.equals("UnaryOperator7") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("StmtList") && expectedType.equals("SourceElement")) return true;
		if (type.equals("AssignmentOperator12") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("RelationalOperator3") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("AdditiveOperator1") && expectedType.equals("AdditiveOperator")) return true;
		if (type.equals("Statement4") && expectedType.equals("Statement")) return true;
		if (type.equals("PropertyNameAndValueListInternal2") && expectedType.equals("PropertyNameAndValueListInternal")) return true;
		if (type.equals("Literal6") && expectedType.equals("Literal")) return true;
		if (type.equals("PrimaryExpression2") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("PostfixOperator1") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("AssignmentOperator7") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AssignmentExpression3") && expectedType.equals("AssignmentExpression")) return true;
		if (type.equals("MemberExpressionPart2") && expectedType.equals("MemberExpressionPart")) return true;
		if (type.equals("PropertyName2") && expectedType.equals("PropertyName")) return true;
		if (type.equals("Statement14") && expectedType.equals("Statement")) return true;
		if (type.equals("CaseBlockInternal2") && expectedType.equals("CaseBlockInternal")) return true;
		if (type.equals("ReturnExpression2") && expectedType.equals("ReturnExpression")) return true;
		if (type.equals("IterationStatement6") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("UnaryOperator6") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("AssignmentOperator11") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("RelationalOperator2") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("Statement5") && expectedType.equals("Statement")) return true;
		if (type.equals("PrimaryExpression1") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("AssignmentOperator8") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("InitializerExpression1") && expectedType.equals("InitializerExpression")) return true;
		if (type.equals("PostfixOperator2") && expectedType.equals("PostfixOperator")) return true;
		if (type.equals("CallExpressionInternal1") && expectedType.equals("CallExpressionInternal")) return true;
		if (type.equals("Statement13") && expectedType.equals("Statement")) return true;
		if (type.equals("Argument1") && expectedType.equals("Argument")) return true;
		if (type.equals("ShiftOperator3") && expectedType.equals("ShiftOperator")) return true;
		if (type.equals("LogicalORExpression2") && expectedType.equals("LogicalORExpression")) return true;
		if (type.equals("ReturnExpression1") && expectedType.equals("ReturnExpression")) return true;
		if (type.equals("CaseBlockInternal1") && expectedType.equals("CaseBlockInternal")) return true;
		if (type.equals("AssignmentOperator4") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("UnaryOperator5") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("RelationalOperator5") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("Statement6") && expectedType.equals("Statement")) return true;
		if (type.equals("IterationStatement1") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("MultiplicativeOperator3") && expectedType.equals("MultiplicativeOperator")) return true;
		if (type.equals("AssignmentOperator10") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("InitializerExpression2") && expectedType.equals("InitializerExpression")) return true;
		if (type.equals("FuncDeclaration") && expectedType.equals("SourceElement")) return true;
		if (type.equals("CallExpressionInternal2") && expectedType.equals("CallExpressionInternal")) return true;
		if (type.equals("Statement12") && expectedType.equals("Statement")) return true;
		if (type.equals("MultiplicativeOperator2") && expectedType.equals("MultiplicativeOperator")) return true;
		if (type.equals("FunctionExpression1") && expectedType.equals("FunctionExpression")) return true;
		if (type.equals("AssignmentOperator5") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("UnaryOperator4") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("Statement7") && expectedType.equals("Statement")) return true;
		if (type.equals("RelationalOperator4") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("Literal1") && expectedType.equals("Literal")) return true;
		if (type.equals("UnaryOperator3") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("AssignmentOperator6") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("Argument2") && expectedType.equals("Argument")) return true;
		if (type.equals("PropertyNameAndValueListInternal1") && expectedType.equals("PropertyNameAndValueListInternal")) return true;
		if (type.equals("ShiftOperator1") && expectedType.equals("ShiftOperator")) return true;
		if (type.equals("Statement8") && expectedType.equals("Statement")) return true;
		if (type.equals("MemberExpression1") && expectedType.equals("MemberExpression")) return true;
		if (type.equals("Statement11") && expectedType.equals("Statement")) return true;
		if (type.equals("EqualityOperator2") && expectedType.equals("EqualityOperator")) return true;
		if (type.equals("CallExpressionPart3") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("FunctionExpression2") && expectedType.equals("FunctionExpression")) return true;
		if (type.equals("MultiplicativeOperator1") && expectedType.equals("MultiplicativeOperator")) return true;
		if (type.equals("AssignmentOperator2") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("IterationStatement3") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("ArrayLiteralInternal1") && expectedType.equals("ArrayLiteralInternal")) return true;
		if (type.equals("Literal2") && expectedType.equals("Literal")) return true;
		if (type.equals("UnaryOperator2") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("PrimaryExpression6") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("Statement9") && expectedType.equals("Statement")) return true;
		if (type.equals("ShiftOperator2") && expectedType.equals("ShiftOperator")) return true;
		if (type.equals("LogicalORExpression1") && expectedType.equals("LogicalORExpression")) return true;
		if (type.equals("CallExpressionPart2") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("Statement10") && expectedType.equals("Statement")) return true;
		if (type.equals("EqualityOperator1") && expectedType.equals("EqualityOperator")) return true;
		if (type.equals("Statement1") && expectedType.equals("Statement")) return true;
		if (type.equals("AssignmentOperator3") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("ArrayLiteralInternal2") && expectedType.equals("ArrayLiteralInternal")) return true;
		if (type.equals("IterationStatement2") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("RelationalOperator6") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("Literal3") && expectedType.equals("Literal")) return true;
		if (type.equals("UnaryOperator1") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("PrimaryExpression5") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("Element1") && expectedType.equals("Element")) return true;
		if (type.equals("CallExpressionPart1") && expectedType.equals("CallExpressionPart")) return true;
		if (type.equals("LeftHandSideExpression2") && expectedType.equals("LeftHandSideExpression")) return true;
		if (type.equals("RelationalOperator1") && expectedType.equals("RelationalOperator")) return true;
		if (type.equals("Statement2") && expectedType.equals("Statement")) return true;
		if (type.equals("IterationStatement5") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("ArrayLiteralInternal3") && expectedType.equals("ArrayLiteralInternal")) return true;
		if (type.equals("UnaryOperator9") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("AssignmentExpression1") && expectedType.equals("AssignmentExpression")) return true;
		if (type.equals("AssignmentOperator9") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("Literal4") && expectedType.equals("Literal")) return true;
		if (type.equals("EqualityOperator4") && expectedType.equals("EqualityOperator")) return true;
		if (type.equals("PrimaryExpression4") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("Element2") && expectedType.equals("Element")) return true;
		if (type.equals("UnaryExpression2") && expectedType.equals("UnaryExpression")) return true;
		if (type.equals("MemberExpression2") && expectedType.equals("MemberExpression")) return true;
		if (type.equals("TryStatementInternal1") && expectedType.equals("TryStatementInternal")) return true;
		if (type.equals("Statement3") && expectedType.equals("Statement")) return true;
		if (type.equals("LeftHandSideExpression1") && expectedType.equals("LeftHandSideExpression")) return true;
		if (type.equals("IterationStatement4") && expectedType.equals("IterationStatement")) return true;
		if (type.equals("AssignmentOperator1") && expectedType.equals("AssignmentOperator")) return true;
		if (type.equals("AdditiveOperator2") && expectedType.equals("AdditiveOperator")) return true;
		if (type.equals("UnaryOperator8") && expectedType.equals("UnaryOperator")) return true;
		if (type.equals("MemberExpressionPart1") && expectedType.equals("MemberExpressionPart")) return true;
		if (type.equals("Literal5") && expectedType.equals("Literal")) return true;
		if (type.equals("EqualityOperator3") && expectedType.equals("EqualityOperator")) return true;
		if (type.equals("PrimaryExpression3") && expectedType.equals("PrimaryExpression")) return true;
		if (type.equals("AssignmentExpression2") && expectedType.equals("AssignmentExpression")) return true;
		return false;
	}
}
