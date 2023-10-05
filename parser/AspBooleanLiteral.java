package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspBooleanLiteral extends AspAtom {
  boolean bool = false;

  AspBooleanLiteral(int n) {
    super(n);
  }

  static AspBooleanLiteral parse(Scanner s) {
    enterParser("boolean");
    AspBooleanLiteral abl = new AspBooleanLiteral(s.curLineNum());
    if (s.curToken().kind == trueToken) {
      abl.bool = true;
    } else if (s.curToken().kind == falseToken) {
      abl.bool = false;
    }
    leaveParser("boolean");
    return abl;
  }

  @Override
  public void prettyPrint() {
    if (this.bool = true) prettyWrite(" true ");
    else if(this.bool = false) prettyWrite(" false ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
