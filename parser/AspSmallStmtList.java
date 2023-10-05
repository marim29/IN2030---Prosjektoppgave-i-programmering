package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspSmallStmtList extends AspStmt {
  ArrayList<AspSmallStmt> smallStmts = new ArrayList<>();

  AspSmallStmtList(int n) {
    super(n);
  }

  static AspSmallStmtList parse(Scanner s) {
    enterParser("small stmt list");
    AspSmallStmtList li = new AspSmallStmtList(s.curLineNum());
    while (true) {
      li.smallStmts.add(AspSmallStmt.parse(s));
      if (s.curToken().kind == newLineToken) break;
      skip(s, semicolonToken);
    }
    leaveParser("small stmt list");
    return li;
  }


  @Override
  public void prettyPrint() {
    int nPrinted = 0;
    for (AspSmallStmt stmt : smallStmts) {
      if (nPrinted > 0 && smallStmts.size() > 1) prettyWrite(" ; ");
      stmt.prettyPrint();
      ++nPrinted;
    }
  }

  @Override
  public RuntimeValue eval(RuntimeScope curScope) throws RuntimeReturnValue {
//-- Must be changed in part 4:
return null;
  }

}
