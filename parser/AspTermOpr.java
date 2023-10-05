package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspTermOpr extends AspSyntax {
  TokenKind kind;

  AspTermOpr(int n) {
    super(n);
  }

  static AspTermOpr parse(Scanner s) {
    enterParser("term opr");

    AspTermOpr ato = new AspTermOpr(s.curLineNum());
    TokenKind k = s.curToken().kind;  ato.kind = k;

    leaveParser("term opr");
    return ato;
  }


  @Override
  public void prettyPrint() {
    if (this.kind == plusToken) prettyWrite(" + ");
    else if (this.kind == minusToken) prettyWrite(" - ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
