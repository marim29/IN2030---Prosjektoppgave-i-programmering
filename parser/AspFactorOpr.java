package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFactorOpr extends AspSyntax {
  TokenKind kind;

  AspFactorOpr(int n) {
    super(n);
  }

  static AspFactorOpr parse(Scanner s) {
    enterParser("factor opr");

    AspFactorOpr afo = new AspFactorOpr(s.curLineNum());
    TokenKind k = s.curToken().kind; afo.kind = k;

    leaveParser("factor opr");
    return afo;
  }


  @Override
  public void prettyPrint() {
    if (this.kind == astToken) prettyWrite(" * ");
    else if (this.kind == slashToken) prettyWrite(" / ");
    else if (this.kind == percentToken) prettyWrite(" % ");
    else if (this.kind == doubleSlashToken) prettyWrite(" // ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
