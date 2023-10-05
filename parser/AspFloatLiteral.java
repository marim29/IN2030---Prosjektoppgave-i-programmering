package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFloatLiteral extends AspAtom {
  double flLit;

  AspFloatLiteral(int n) {
    super(n);
  }

  static AspFloatLiteral parse(Scanner s) {
    enterParser("float literal");

    AspFloatLiteral afl = new AspFloatLiteral(s.curLineNum());
    afl.flLit = s.curToken().floatLit;
    // afl.intLit.add(AspIntegerLiteral.parse(s));
    // skip(s, dotToken);
    // while (s.curToken.kind() == integerToken) {
    //   afl.int.add();
    // }

    leaveParser("float literal");
    return afl;
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
