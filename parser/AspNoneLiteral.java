package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspNoneLiteral extends AspAtom {

  AspNoneLiteral(int n) {
    super(n);
  }

  static AspNoneLiteral parse(Scanner s) {
    enterParser("None");
    AspNoneLiteral anl = new AspNoneLiteral(s.curLineNum());
    leaveParser("None");
    return anl;
  }

  @Override
  public void prettyPrint() {
    prettyWrite(" None ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
