/* Generated By:JavaCC: Do not edit this line. JavaScriptMergeParser.java */
package br.ufpe.cin.generated;

import java.io.*;
import java.util.*;
import cide.gast.*;
import cide.gparser.*;
import de.ovgu.cide.fstgen.ast.*;

public class JavaScriptMergeParser extends AbstractFSTParser implements JavaScriptMergeParserConstants {
        public JavaScriptMergeParser(){}

  final public FSTInfo CompilationUnit(boolean inTerminal) throws ParseException {
                                                Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      n = SourceElement(inTerminal);
                                      replaceName(n);
    }
    jj_consume_token(0);
                                                                {if (true) return productionEndNonTerminal("CompilationUnit","-","-");}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo Id(boolean inTerminal) throws ParseException {
                                   Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    t = jj_consume_token(IDENTIFIER);
                        replaceName(new FSTInfo("<IDENTIFIER>",t.image));
                                                                            {if (true) return productionEndTerminal("Id","{<IDENTIFIER>}","{<IDENTIFIER>}","Replacement","Default",first,token);}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo SourceElement(boolean inTerminal) throws ParseException {
                                              Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    n = Function(inTerminal);
                                replaceName(n);
    n = Id(inTerminal);
                                                                   replaceName("Id", n);
                                                                                           replaceName(n);
    jj_consume_token(LPAREN);
    n = FormalParameters(inTerminal);
                                                                                                                                                replaceName(n);
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    n = FunctionBody(inTerminal);
                                                                                                                                                                                                     replaceName(n);
    jj_consume_token(RBRACE);
                                                                                                                                                                                                                           {if (true) return productionEndNonTerminal("SourceElement","{Id}","{Id}");}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo FunctionDeclaration(boolean inTerminal) throws ParseException {
                                                    Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    n = Function(true);
                          replaceName(n);
    n = Id(true);
                                                       replaceName(n);
    jj_consume_token(LPAREN);
    n = FormalParameters(true);
                                                                                                      replaceName(n);
    jj_consume_token(RPAREN);
    jj_consume_token(LBRACE);
    n = FunctionBody(true);
                                                                                                                                                     replaceName(n);
    jj_consume_token(RBRACE);
                                                                                                                                                                           {if (true) return productionEndTerminal("FunctionDeclaration","-","-","Replacement","Default",first,token);}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo Function(boolean inTerminal) throws ParseException {
                                         Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    jj_consume_token(FUNCTION);
                     {if (true) return productionEndTerminal("Function","-","-","Replacement","LineBased",first,token);}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo FormalParameters(boolean inTerminal) throws ParseException {
                                                 Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    n = Id(true);
                    replaceName(n);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(COMMA);
      n = Id(true);
                                                      replaceName(n);
    }
                                                                          {if (true) return productionEndTerminal("FormalParameters","-","-","Replacement","LineBased",first,token);}
    throw new Error("Missing return statement in function");
  }

  final public FSTInfo FunctionBody(boolean inTerminal) throws ParseException {
                                             Token first=null,t;FSTInfo n;
     first=getToken(1); productionStart(inTerminal);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case FUNCTION:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_3;
      }
      n = SourceElement(inTerminal);
                                      replaceName(n);
    }
                                                          {if (true) return productionEndNonTerminal("FunctionBody","-","-");}
    throw new Error("Missing return statement in function");
  }

  public JavaScriptMergeParserTokenManager token_source;
  public Token token, jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[3];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static private int[] jj_la1_3;
  static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
      jj_la1_3();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x0,0x0,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0x8,0x0,0x8,};
   }
   private static void jj_la1_2() {
      jj_la1_2 = new int[] {0x0,0x400000,0x0,};
   }
   private static void jj_la1_3() {
      jj_la1_3 = new int[] {0x0,0x0,0x0,};
   }

  public JavaScriptMergeParser(CharStream stream) {
    token_source = new JavaScriptMergeParserTokenManager(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  public void ReInit(CharStream stream) {
    token_source.ReInit(stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  public JavaScriptMergeParser(JavaScriptMergeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  public void ReInit(JavaScriptMergeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 3; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[127];
    for (int i = 0; i < 127; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 3; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 127; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}
