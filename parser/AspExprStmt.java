package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspExprStmt extends AspSmallStmt {
  AspExpr expr;

  AspExprStmt(int n) {
    super(n);
  }

  static AspExprStmt parse(Scanner s) {
    enterParser("expr stmt");
    AspExprStmt ex = new AspExprStmt(s.curLineNum());
    ex.expr = AspExpr.parse(s);
    leaveParser("expr stmt");
    return ex;
  }

  @Override
  public void prettyPrint() {
    expr.prettyPrint();
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
