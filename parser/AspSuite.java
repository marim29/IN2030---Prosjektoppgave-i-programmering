package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

abstract class AspSuite extends AspSyntax {
  AspSmallStmtList smallStmtList;
  ArrayList<AspStmt> stmts = new ArrayList<>();

  AspSuite(int n) {
    super(n);
  }

  static AspSuite parse(Scanner s) {
    enterParser("suite");

    AspSuite as = new AspSuite(s.curLineNum());
    switch(s.curToken().kind) {
      case nameToken:
          as.smallStmtList = AspSmallStmtList.parse(s);
          break;
      case notToken:
          as.smallStmtList = AspSmallStmtList.parse(s);
          break;
      case passToken:
          as.smallStmtList = AspSmallStmtList.parse(s);
          break;
      case returnToken:
          as.smallStmtList = AspSmallStmtList.parse(s);
          break;
      case newLineToken:
          skip(s, newLineToken);  skip(s, indentToken);
          while (true) {
            as.stmts.add(AspStmt.parse(s));
            if (s.curToken().kind == dedentToken) break;
          }
          skip(s, dedentToken);
          break;
      default:
          s.parserError("Token not found", s.curLineNum());
          break;
    }

    leaveParser("suite");
  }


  @Override
  public void prettyPrint() {
    if (smallStmtList != null) {
      smallStmtList.prettyPrint();
    } else {
      prettyWrite(" NEWLINE ");
      prettyWrite(" INDENT ");
      for (AspStmt s : stmts) {
        s.prettyPrint();
      }
      prettyWrite(" DEDENT ");
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
