package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspPassStmt extends AspSyntax {

  AspPassStmt(int n) {
    super(n);
  }

  static AspPassStmt parse(Scanner s) {
    enterParser("pass");
    AspPassStmt aps = new AspPassStmt(s.curLineNum());
    leaveParser("pass");
    return aps;
  }

  @Override
  public void prettyPrint() {
    prettyWrite(" pass ");
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
