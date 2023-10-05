package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspComparison extends AspSyntax {
  ArrayList<AspTerm> term = new ArrayList<>();
  ArrayList<AspCompOpr> comp = new ArrayList<>();

  AspComparison(int n) {
    super(n);
  }

  static AspComparison parse(Scanner s) {
    enterParser("comparison");

    AspComparison ac = new AspComparison(s.curLineNum());
    while(true) {
      ac.term.add(AspTerm.parse(s));
      if (! s.isCompOpr())  break;
      ac.comp.add(AspCompOpr.parse(s));
    }

    leaveParser("comparison");
    return ac;
  }


  @Override
  public void prettyPrint() {
    for (AspTerm t : term) {
      for (AspCompOpr c : comp) {
        t.prettyPrint();
        c.prettyPrint();
      }
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
