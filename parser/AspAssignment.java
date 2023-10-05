package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspAssignment extends AspSmallStmt {
  AspName name;
  ArrayList<AspSubscription> sub = new ArrayList<>();
  AspExpr expr;

  AspAssignment(int n) {
    super(n);
  }

  static AspAssignment parse(Scanner s) {
    enterParser("assignment");
    AspAssignment aa = new AspAssignment(s.curLineNum());
    aa.name = AspName.parse(s);
    if (s.curToken().kind == equalToken) {
      skip(s, equalToken);
      aa.expr = AspExpr.parse(s);
    } else {
      while(true) {
        aa.sub.add(AspSubscription.parse(s));
        if (s.curToken().kind == equalToken) break;
      }
      skip(s, equalToken);
      aa.expr = AspExpr.parse(s);
    }

    leaveParser("assignment");
    return aa;
  }


  @Override
  public void prettyPrint() {
    name.prettyPrint();
    for(AspSubscription su : sub) {
      su.prettyPrint();
    }
    prettyWrite(" = ");
    expr.prettyPrint();
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
