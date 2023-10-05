package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspSmallStmt extends AspSyntax {

  AspSmallStmt(int n) {
    super(n);
  }

  static AspSmallStmt parse(Scanner s) {
    enterParser("small stmt");

    switch(s.curToken().kind) {
      case nameToken:
          AspAssignment.parse(s);
          break;
      case notToken:
          AspExprStmt.parse(s);
          break;
      case passToken:
          AspPassStmt.parse(s);
          break;
      case returnToken:
          AspReturnStmt.parse(s);
          break;
      default:
          parserError("Token not found", s.curLineNum());
          break;
    }

    leaveParser("small stmt");

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
