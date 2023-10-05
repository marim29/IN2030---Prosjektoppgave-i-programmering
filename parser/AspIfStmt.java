package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspIfStmt extends AspSyntax {
  ArrayList<AspExpr> exprs = new ArrayList<>();
  ArrayList<AspSuite> s1 = new ArrayList<>();
  AspSuite s2;

  AspIfStmt(int n) {
    super(n);
  }

  static AspIfStmt parse(Scanner s) {
    enterParser("if stmt");

    AspIfStmt ais = new AspIfStmt(s.curLineNum());
    skip(s, ifToken);
    while(true) {
      ais.exprs.add(AspExpr.parse(s));
      skip(s, colonToken);
      ais.s1.add(AspSuite.parse(s));
      if (s.curToken().kind != elifToken)  break;
      skip (s, elifToken);
    }
    if (s.curToken().kind == elseToken) {
      skip(s, elseToken);
      skip(s, colonToken);
      ais.s2 = AspSuite.parse(s);
    }

    leaveParser("if stmt");
    return ais;
  }

  @Override
  public void prettyPrint() {
    prettyWrite(" if ");
    int nPrinted = 0;
    for (AspExpr ex : exprs) {
      for (AspSuite su : s1) {
        ex.prettyPrint();
        prettyWrite(" : ");
        su.prettyPrint();
        if (nPrinted == exprs.size()-1) break;
        prettyWrite(" elif ");
      }
    }
    if (s2 != null) {
      prettyWrite(" else ");
      prettyWrite(" : ");
      s2.prettyPrint();
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
