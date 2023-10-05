package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspPrimarySuffix extends AspSyntax {

  AspPrimarySuffix(int n) {
    super(n);
  }


  static AspPrimarySuffix parse(Scanner s) {
    enterParser("primary suffix");

    AspPrimarySuffix aps = new AspPrimarySuffix(s.curLineNum());
    switch(s.curToken().kind) {
      case leftParToken:
          AspArguments.parse(s);
          break;
      case leftBracketToken:
          AspSubscription.parse(s);
          break;
      default:
          s.parserError("Token not found", s.curLineNum());
          break;
    }

    leaveParser("primary suffix");
    return aps;
  }


  @Override
  void prettyPrint() {
    //
  }

}
