package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspTerm extends AspSyntax {
  ArrayList<AspFactor> factor = new ArrayList<>();
  ArrayList<AspTermOpr> termOpr = new ArrayList<>();

  AspTerm(int n) {
    super(n);
  }

  static AspTerm parse(Scanner s) {
    enterParser("term");

    AspTerm at = new AspTerm(s.curLineNum());
    while(true) {
      at.factor.add(AspFactor.parse(s));
      if (! s.isTermOpr())  break;
      at.termOpr.add(AspTermOpr.parse(s));
    }

    leaveParser("term");
    return at;
  }


  @Override
  void prettyPrint() {
    for (AspFactor af : factor) {
      for (AspTermOpr ao : termOpr) {
        af.prettyPrint();
        ao.prettyPrint();
      }
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
