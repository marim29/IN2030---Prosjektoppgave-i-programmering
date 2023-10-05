package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspCompOpr extends AspSyntax {
  TokenKind kind;

  AspCompOpr(int n) {
    super(n);
  }

  static AspCompOpr parse(Scanner s) {
    enterParser("comp opr");

    AspCompOpr aco = new AspCompOpr(s.curLineNum());
    aco.kind = s.curToken().kind;

    leaveParser("comp opr");
    return aco;
  }


  @Override
  public void prettyPrint() {
    if (kind == lessToken) prettyWrite(" < ");
    else if (kind == greaterToken) prettyWrite(" > ");
    else if (kind == doubleEqualToken) prettyWrite(" == ");
    else if (kind == greaterEqualToken) prettyWrite(" >= ");
    else if (kind == lessEqualToken) prettyWrite(" <= ");
    else if (kind == notEqualToken) prettyWrite(" != ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
