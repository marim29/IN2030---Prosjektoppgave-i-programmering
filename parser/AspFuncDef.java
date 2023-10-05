package no.uio.ifi.asp.parser;

import java.util.ArrayList;

import no.uio.ifi.asp.main.*;
import no.uio.ifi.asp.runtime.*;
import no.uio.ifi.asp.scanner.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

class AspFuncDef extends AspSyntax {
  ArrayList<AspName> names = new ArrayList<>();
  AspSuite suite;

  AspFuncDef(int n) {
    super(n);
  }

  static AspFuncDef parse(Scanner s) {
    enterParser("func def");
    AspFuncDef afd = new AspFuncDef(s.curLineNum());
    skip(s, defToken);
    afd.names.add(AspName.parse(s));
    skip(s, leftParToken);
    if (s.curToken().kind == rightParToken) {
      skip(s, colonToken);
      afd.suite = AspSuite.parse(s);
      break;
    }
    while (true) {
      afd.names.add(AspName.parse(s));
      if (s.curToken().kind == rightParToken) {
        skip(s, colonToken);
        afd.suite = AspSuite.parse(s);
        break;
      }
      skip(s, commaToken);
    }

    leaveParser("func def");
    return ;
  }


  @Override
  void prettyPrint() {
    prettyWrite(" def ");
    names.get(0).prettyPrint();
    prettyWrite(" ( ");

    int nPrinted = 0;
    for (AspName name : names) {
      if (nPrinted == 0)  continue;  nPrinted++;
      if (nPrinted > 1) prettyWrite(" , ");
      name.prettyPrint();
      nPrinted++;
    }
    prettyWrite(" ) "); prettyWrite(" : ");
    suite.prettyPrint();
  }

}
