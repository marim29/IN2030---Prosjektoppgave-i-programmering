package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspStringLiteral extends AspAtom {
  String stringLit;

  AspStringLiteral(int n) {
    super(n);
  }

  static AspStringLiteral parse(Scanner s) {
    enterParser("string literal");

    AspStringLiteral asl = new AspStringLiteral(s.curLineNum());
    asl.stringLit = s.curToken().stringLit;

    leaveParser("string literal");
    return asl;
  }

  @Override
  public void prettyPrint() {
    //
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }


}
