package no.uio.ifi.asp.scanner;

import java.io.*;
import java.util.*;

import no.uio.ifi.asp.main.*;
import static no.uio.ifi.asp.scanner.TokenKind.*;

public class Scanner {
    private LineNumberReader sourceFile = null;
    private String curFileName;
    private ArrayList<Token> curLineTokens = new ArrayList<>();
    private Stack<Integer> indents = new Stack<>();
    private final int TABDIST = 4;


    public Scanner(String fileName) {
	curFileName = fileName;
	indents.push(0);

	try {
	    sourceFile = new LineNumberReader(
			    new InputStreamReader(
				new FileInputStream(fileName),
				"UTF-8"));
	} catch (IOException e) {
	    scannerError("Cannot read " + fileName + "!");
	}
    }


    private void scannerError(String message) {
	String m = "Asp scanner error";
	if (curLineNum() > 0)
	    m += " on line " + curLineNum();
	m += ": " + message;

	Main.error(m);
    }


    public Token curToken() {
	while (curLineTokens.isEmpty()) {
	    readNextLine();
	}
	return curLineTokens.get(0);
    }


    public void readNextToken() {
	if (! curLineTokens.isEmpty())
	    curLineTokens.remove(0);
    }


    private void readNextLine() {
	curLineTokens.clear();

	// Read the next line:
	String line = null;
	try {
	    line = sourceFile.readLine();
	    if (line == null) {
        while (indents.size() > 1) {
          curLineTokens.add(new Token(TokenKind.dedentToken, curLineNum()));
          indents.pop();
        }
        Token t = new Token(TokenKind.eofToken, curLineNum());
        curLineTokens.add(t);
        for (Token to: curLineTokens)
        Main.log.noteToken(to);
		sourceFile.close();
		sourceFile = null;
    return;
	    } else {
		Main.log.noteSourceLine(curLineNum(), line);
	    }
	} catch (IOException e) {
	    sourceFile = null;
	    scannerError("Unspecified I/O error!");
	}

	// Part 1 - Scanner:

  int pos = 0;
  String str = "";
  String digit = "";
  boolean newline = true;

  if (line.length() == 0) {
    newline = false;
  }

  while (pos < line.length()) {

    String newString = expandLeadingTabs(line);
    int n = findIndent(newString);

    if (n > indents.peek()) {
      indents.push(n);
      curLineTokens.add(new Token(TokenKind.indentToken, curLineNum()));
    } else if (n < indents.peek()) {
      indents.pop();
      curLineTokens.add(new Token(TokenKind.dedentToken, curLineNum()));
    } else if (n != indents.peek()) {
      scannerError("Indent error");
    }


    char c = line.charAt(pos);


    if (c == '#') {
      // Ignore comments.
      newline = false;
      break;

  // # Tall og symboler
    } else if (isDigit(c)) {
      boolean isFloat = false;
      while (isDigit(c) || c == '.') {
        if (c == '.') {
          if(isFloat) {
            scannerError("Illegal float");
          }
          else {
            isFloat = true;
          }
        }
        digit += c;
        pos++;
        if (!(pos < line.length())) {
          break;
        }
        c = line.charAt(pos);
      }
      if (isFloat) {
        Token fl = new Token(TokenKind.floatToken, curLineNum());
        fl.floatLit = Double.parseDouble(digit);
        curLineTokens.add(fl);
      } else {
      Token in = new Token(TokenKind.integerToken, curLineNum());
      in.integerLit = Integer.parseInt(digit);
      curLineTokens.add(in);
      }
      digit = "";
      continue;
    }

    // # Operatører:
    else if (c == '*') {
      curLineTokens.add(new Token(TokenKind.astToken, curLineNum()));

      // > & >=
    } else if (c == '>') {
      if ((pos == 0) && (line.length() > 1) && (line.charAt(pos+1) == '=')) {
        curLineTokens.add(new Token(TokenKind.greaterEqualToken, curLineNum()));
      } else if ((pos == 0) && (line.length() == 1)) {
        curLineTokens.add(new Token(TokenKind.greaterToken, curLineNum()));
      } else if ((pos == line.length()-1)) {
        curLineTokens.add(new Token(TokenKind.greaterToken, curLineNum()));
      } else if ((pos > 0) && (pos < line.length()-1) && (line.charAt(pos+1) == '=')) {
        curLineTokens.add(new Token(TokenKind.greaterEqualToken, curLineNum()));
      } else if ((pos > 0) && (pos < line.length()-1) && (line.charAt(pos+1) != '=')) {
        curLineTokens.add(new Token(TokenKind.greaterToken, curLineNum()));
      }

      // < & <=
    } else if (c == '<') {
      if ((pos == 0) && (line.length() > 1) && (line.charAt(pos+1) == '=')) {
        curLineTokens.add(new Token(TokenKind.lessEqualToken, curLineNum()));
      } else if ((pos == 0) && (line.length() == 1)) {
        curLineTokens.add(new Token(TokenKind.lessToken, curLineNum()));
      } else if ((pos == line.length()-1)) {
        curLineTokens.add(new Token(TokenKind.lessToken, curLineNum()));
      } else if ((pos > 0) && (pos < line.length()-1) && (line.charAt(pos+1) == '=')) {
        curLineTokens.add(new Token(TokenKind.lessEqualToken, curLineNum()));
      } else if ((pos > 0) && (pos < line.length()-1) && (line.charAt(pos+1) != '=')) {
        curLineTokens.add(new Token(TokenKind.lessToken, curLineNum()));
      }

    } else if (c == '-') {
      curLineTokens.add(new Token(TokenKind.minusToken, curLineNum()));
    } else if (c == '%') {
      curLineTokens.add(new Token(TokenKind.percentToken, curLineNum()));
    } else if (c == '+') {
      curLineTokens.add(new Token(TokenKind.plusToken, curLineNum()));

      // / og //
    } else if ((c == '/') && (pos == 0) && (line.length() > 1)) {
      if (line.charAt(pos+1) == '/') {
        curLineTokens.add(new Token(TokenKind.doubleSlashToken, curLineNum()));
      } else {
        curLineTokens.add(new Token(TokenKind.slashToken, curLineNum()));
      }
    } else if ((c == '/') && (pos == 0) && (line.length() == 1)) {
      curLineTokens.add(new Token(TokenKind.slashToken, curLineNum()));
    } else if ((c == '/') && (pos == line.length()-1) && (line.length() > 1)) {
      if (line.charAt(pos-1) == '/') {
        // Ignorer
      } else if (line.charAt(pos-1) != '/') {
        curLineTokens.add(new Token(TokenKind.slashToken, curLineNum()));
      }
    } else if ((c == '/') && (pos > 0) && (pos < line.length()-1)) {
      if (line.charAt(pos+1) == '/') {
        curLineTokens.add(new Token(TokenKind.doubleSlashToken, curLineNum()));
      } else if (line.charAt(pos-1) == '/') {
        // Ignorer
      } else if ((line.charAt(pos+1) == '/') && (line.charAt(pos-1) == '/')) {
        // Ignorer
      } else {
        curLineTokens.add(new Token(TokenKind.slashToken, curLineNum()));
      }

    // # 'Delimiters':
    } else if (c == ':') {
      curLineTokens.add(new Token(TokenKind.colonToken, curLineNum()));
    } else if (c == ',') {
      curLineTokens.add(new Token(TokenKind.commaToken, curLineNum()));

    // "="
  } else if ((c == '=') && (pos > 0) && (pos < line.length()-1) && !(line.charAt(pos+1) == '=') && !(line.charAt(pos-1) == '=') && !(line.charAt(pos-1) == '>') && !(line.charAt(pos-1) == '<')) {
      curLineTokens.add(new Token(TokenKind.equalToken, curLineNum()));
    } else if ((c == '=') && (pos == 0) && (line.length() > 1) && !(line.charAt(pos+1) == '=')) {
      curLineTokens.add(new Token(TokenKind.equalToken, curLineNum()));
    } else if ((c == '=') && (pos == 0) && (line.length() == 1)) {
      curLineTokens.add(new Token(TokenKind.equalToken, curLineNum()));
    } else if ((c == '=') && (pos == line.length()-1) && !(line.charAt(pos-1) == '=') && !(line.charAt(pos-1) == '>') && !(line.charAt(pos-1) == '<')) {
      curLineTokens.add(new Token(TokenKind.equalToken, curLineNum()));
    } else if ((c == '=') && (pos > 0) && (line.charAt(pos-1) == '>')) {
      // Ignorer
    } else if ((c == '=') && (pos > 0) && (line.charAt(pos-1) == '<')) {
      // Ignorer
    //  "=="
    } else if ((c == '=') && (pos < line.length()-1) && (line.charAt(pos+1) == '=')) {
      curLineTokens.add(new Token(TokenKind.doubleEqualToken, curLineNum()));

    } else if ((c == '=') && (pos > 0) && (line.charAt(pos-1) == '=')) {
      // Gjør ingenting
    } else if ((c == '=') && (pos == line.length()-1) && (line.charAt(pos-1) == '=')) {
      curLineTokens.add(new Token(TokenKind.doubleEqualToken, curLineNum()));
    } else if ((c == '=') && (pos > 0) && (pos < line.length()-1) && (line.charAt(pos+1)) == '=') {
      curLineTokens.add(new Token(TokenKind.doubleEqualToken, curLineNum()));
    } else if ((c == '=') && (pos > 0) && (pos < line.length()-1) && (line.charAt(pos-1) == '=')) {
      // Ignoreres

    } else if ((c == '=') && (line.charAt(pos-1) == '!')) {
      curLineTokens.add(new Token(TokenKind.notEqualToken, curLineNum()));

    }
    if (c == '{') {
      curLineTokens.add(new Token(TokenKind.leftBraceToken, curLineNum()));
    } else if (c == '[') {
      curLineTokens.add(new Token(TokenKind.leftBracketToken, curLineNum()));
    } else if (c == '(') {
      curLineTokens.add(new Token(TokenKind.leftParToken, curLineNum()));
    } else if (c == '}') {
      curLineTokens.add(new Token(TokenKind.rightBraceToken, curLineNum()));
    } else if (c == ']') {
      curLineTokens.add(new Token(TokenKind.rightBracketToken, curLineNum()));
    } else if (c == ')') {
      curLineTokens.add(new Token(TokenKind.rightParToken, curLineNum()));
    } else if (c == ';') {
      curLineTokens.add(new Token(TokenKind.semicolonToken, curLineNum()));
    }

    // # Navn
    else if (isLetterAZ(c)) {

      while (isLetterAZ(c) || isDigit(c)) {
        str += c;
        pos++;

        if (!(pos < line.length())) {
          break;
        }
        c = line.charAt(pos);
      } Token na = new Token(TokenKind.nameToken, curLineNum());
        na.name = str;
        na.checkResWords();
        curLineTokens.add(na);
        str = "";
        continue;
    }

    // " og '
    else if (c == '\"' || c == '\'') {
      pos++;
      if (!(pos < line.length()-1)) {
        break;
      }
      c = line.charAt(pos);
      if (c == '\"' || c == '\'') {
        Token s = new Token(TokenKind.stringToken, curLineNum());
        s.stringLit = str;
        curLineTokens.add(s);
        pos++;
        str = "";
        continue;
      }

      while (c != '\"' && c != '\'') {
        str += c;
        pos++;
        if (!(pos < line.length()-1)) {
          break;
        }
        c = line.charAt(pos);
      }
      Token st = new Token(TokenKind.stringToken, curLineNum());
      st.stringLit = str;
      curLineTokens.add(st);
      str = "";
    }
    pos++;

  }
  if (newline) {
    System.out.println(line.length() + "token" + line);
    curLineTokens.add(new Token(newLineToken,curLineNum()));
  }

  for (Token t: curLineTokens)
  Main.log.noteToken(t);
  }

    public int curLineNum() {
	return sourceFile!=null ? sourceFile.getLineNumber() : 0;
    }

    private int findIndent(String s) {
	int indent = 0;

	while (indent<s.length() && s.charAt(indent)==' ') indent++;
	return indent;
    }

    private String expandLeadingTabs(String s) {
	String newS = "";
	for (int i = 0;  i < s.length();  i++) {
	    char c = s.charAt(i);
	    if (c == '\t') {
		do {
		    newS += " ";
		} while (newS.length()%TABDIST > 0);
	    } else if (c == ' ') {
		newS += " ";
	    } else {
		newS += s.substring(i);
		break;
	    }
	}
	return newS;
    }


    private boolean isLetterAZ(char c) {
	return ('A'<=c && c<='Z') || ('a'<=c && c<='z') || (c=='_');
    }


    private boolean isDigit(char c) {
	return '0'<=c && c<='9';
    }


    public boolean isCompOpr() {
      TokenKind k = curToken().kind;
  //-- Must be changed in part 2:
      if (k == greaterToken) return true;
      else if (k == lessToken) return true;
      else if (k == doubleEqualToken) return true;
      else if (k == greaterEqualToken) return true;
      else if (k == lessEqualToken) return true;
      else if (k == notEqualToken) return true;
      else {
        return false;
      }
    }


    public boolean isFactorPrefix() {
      TokenKind k = curToken().kind;
      //-- Must be changed in part 2:
      if (k == plusToken) return true;
      else if (k == minusToken) return true;
      else {
        return false;
      }
    }


    public boolean isFactorOpr() {
      TokenKind k = curToken().kind;
	//-- Must be changed in part 2:
      if (k == astToken) return true;
      else if (k == slashToken) return true;
      else if (k == percentToken) return true;
      else if (k == doubleSlashToken) return true;
      else {
        return false;
      }
    }


    public boolean isTermOpr() {
      TokenKind k = curToken().kind;
  //-- Must be changed in part 2:
      if (k == plusToken) return true;
      else if (k == minusToken) return true;
      else {
        return false;
      }
    }


    public boolean anyEqualToken() {
	for (Token t: curLineTokens) {
	    if (t.kind == equalToken) return true;
	    if (t.kind == semicolonToken) return false;
	}
	return false;
  }
}
