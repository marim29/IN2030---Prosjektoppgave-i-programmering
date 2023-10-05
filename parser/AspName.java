package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspName extends AspAtom {
  String name;

  AspName(int n) {
    super(n);
  }

  static AspName parse(Scanner s) {
    enterParser("name");

    AspName na = new AspName(s.curLineNum());
    na.name = s.curToken().name;

    leaveParser("name");
    return na;
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
