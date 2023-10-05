package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspIntegerLiteral extends AspAtom {
  long intLit;

  AspIntegerLiteral(int n) {
    super(n);
  }

  static AspIntegerLiteral parse(Scanner s) {
    enterParser("integer literal");

    AspIntegerLiteral ail = new AspIntegerLiteral(s.curLineNum());
    ail.intLit = s.curToken().integerLit;

    // else {
    //   ail.int += s.curToken().integerLit;
    //   while (s.isDigit()) {
    //     ail.int += s.curToken().integerLit;
    //   }
    // }

    leaveParser("integer literal");
    return ail;
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
