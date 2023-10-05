package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspListDisplay extends AspAtom {
  ArrayList<AspExpr> expr = new ArrayList<>();

  AspListDisplay(int n) {
    super(n);
  }

  static AspListDisplay parse(Scanner s) {
    enterParser("list display");

    AspListDisplay ald = new AspListDisplay(s.curLineNum());
    skip(s, leftBracketToken);
    while (s.curToken().kind != rightBracketToken) {
      ald.expr.add(AspExpr.parse(s));
      if (s.curToken().kind != commaToken)  break;
      skip(s, commaToken);
    }
    skip(s, rightBracketToken);

    leaveParser("list display");
    return ald;
  }

  @Override
  public void prettyPrint() {
    prettyWrite(" [ ");
    for (AspExpr ex : expr) {
      ex.prettyPrint();  prettyWrite(" , ");
    }
    prettyWrite(" ] ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
