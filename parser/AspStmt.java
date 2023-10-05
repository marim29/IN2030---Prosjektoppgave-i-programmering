package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspStmt extends AspSyntax {

  AspStmt(int n) {
    super(n);
  }

  static AspStmt parse(Scanner s) {
    enterParser("stmt");

    switch(s.curToken().kind) {
      case nameToken:
          AspSmallStmtList.parse(s);
          break;
      case notToken:
          AspSmallStmtList.parse(s);
          break;
      case passToken:
          AspSmallStmtList.parse(s);
          break;
      case returnToken:
          AspSmallStmtList.parse(s);
          break;
      case forToken:
          AspCompoundStmt.parse(s);
          break;
      case ifToken:
          AspCompoundStmt.parse(s);
          break;
      case whileToken:
          AspCompoundStmt.parse(s);
          break;
      case defToken:
          AspCompoundStmt.parse(s);
          break;
      default:
          parserError("Token not found", s.curLineNum());
          break;

    }
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
