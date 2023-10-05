package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspNotTest extends AspSyntax {
  boolean notTok = false;
  AspComparison comp;

  AspNotTest(int n) {
    super(n);
  }

  static AspNotTest parse(Scanner s) {
    enterParser("not test");

    AspNotTest ant = new AspNotTest(s.curLineNum());
    if (s.curToken().kind != notToken) {
      ant.comp = AspComparison.parse(s);
    } else {
      ant.notTok = true;
      skip(s, notToken);
      ant.comp = AspComparison.parse(s);
    }

    leaveParser("not test");
    return ant;
  }

  @Override
  public void prettyPrint() {
    if (notTok == true) {
      prettyWrite(" not ");
      comp.prettyPrint();
    }
    else {
      comp.prettyPrint();
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
