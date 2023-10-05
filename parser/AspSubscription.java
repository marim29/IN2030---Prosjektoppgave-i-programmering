package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspSubscription extends AspSyntax {
  AspExpr expr;

  AspSubscription(int n) {
    super(n);
  }

  static AspSubscription parse(Scanner s) {
    enterParser("subscription");

    AspSubscription su = new AspSubscription(s.curLineNum());
    skip(s, leftBracketToken);
    su.expr = AspExpr.parse(s);
    skip(s, rightBracketToken);

    leaveParser("subscription");
    return su;
  }


  @Override
  void prettyPrint() {
    prettyWrite(" [ ");
    expr.prettyPrint();
    prettyWrite(" ] ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
