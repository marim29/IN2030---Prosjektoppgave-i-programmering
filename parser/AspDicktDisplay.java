package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspDictDisplay extends AspAtom {
  ArrayList<AspStringLiteral> stLit = new ArrayList<>();
  ArrayList<AspExpr> exprs = new ArrayList<>();

  AspDictDisplay(int n) {
    super(n);
  }

  static AspDictDisplay parse(Scanner s) {
    enterParser("dict display");

    AspDictDisplay add = new AspDictDisplay(s.curLineNum());
    skip(s, leftBraceToken);
    if (s.curToken().kind == rightBraceToken) {
      skip(s, rightBraceToken);
    } else {
      while (true) {
        add.stLit.add(AspStringLiteral.parse(s));
        skip(s, colonToken);
        add.exprs.add(AspExpr.parse(s));
        if (s.curToken().kind != commaToken)  break;
        skip(s, commaToken);
      }
    }

    leaveParser("dick display");
    return add;
  }


  @Override
  public void prettyPrint() {
    prettyWrite(" { ");
    for (AspStringLiteral s : stLit) {
      for (AspExpr e : exprs) {
        s.prettyPrint();
        prettyWrite(" : ");
        e.prettyPrint();
        prettyWrite(" , ");
      }
    }
    prettyWrite(" } ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
