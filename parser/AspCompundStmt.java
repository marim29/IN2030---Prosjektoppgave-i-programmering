package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspCompoundStmt extends AspStmt {

  AspCompoundStmt(int n) {
    super(n);
  }

  static AspCompoundStmt parse(Scanner s) {
    enterParser("compound stmt");

    switch(s.curToken().kind) {
      case forToken:
          AspForStmt.parse(s);
          break;
      case ifToken:
          AspIfStmt.parse(s);
          break;
      case whileToken:
          AspWhileStmt.parse(s);
          break;
      case defToken:
          AspFuncDef.parse(s);
          break;
      default:
          parserError("Token not found", s.curLineNum());
          break;

    }

    leaveParser("compound stmt");
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
