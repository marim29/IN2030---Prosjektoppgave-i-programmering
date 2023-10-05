package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFactorPrefix extends AspAtom {
  TokenKind kind;

  AspFactorPrefix(int n) {
    super(n);
  }

  static AspFactorPrefix parse(Scanner s) {
    enterParser("factor prefix");

    AspFactorPrefix afp = new AspFactorPrefix(s.curLineNum());
    TokenKind k = s.curToken().kind; afp.kind = k;
    if (s.isFactorPrefix()) {
      skip(s, k);
    }

    leaveParser("factor prefix");
    return afp;
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
