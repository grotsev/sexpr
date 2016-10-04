package kz.greetgo.sexpr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.Iterator;

public class LispTokenizer {
  private final String src;
  private int pos = 0;

  /**
   * Constructs a tokenizer that scans input from the given string.
   *
   * @param src A string containing S-expressions.
   */
  public LispTokenizer(String src) {
    this.src = src;
  }

  /*  m_tokenizer.whitespaceChars(0, ' ');
    m_tokenizer.wordChars(' '+1,255);
    m_tokenizer.ordinaryChar('(');
    m_tokenizer.ordinaryChar(')');
    m_tokenizer.ordinaryChar('\'');
    m_tokenizer.commentChar(';');
    m_tokenizer.quoteChar('"');
  }*/

  public boolean isRightParenthesis() {
    while (src.charAt(pos) <= ' ') pos++;
    return src.charAt(pos) == ')';
  }

  public Token next() {
    int start = pos;
    int state = 0; // 0 out of token, 1 in atom, 2 in string, 3 in string after'
    for (; pos < src.length(); pos++) {
      char ch = src.charAt(pos);
      switch (state) {
        case 0: {
          if (ch <= ' ') continue; // skip spaces
          if (ch == '(') {
            pos++;
            return new Token('(', "(");
          }
          if (ch == ')') {
            pos++;
            return new Token(')', ")");
          }
          if (ch == '"') {
            start = pos;
            state = 2; // in string
            continue;
          }
          start = pos;
          state = 1; // in atom
          continue;
        }
        case 1: {
          if (ch <= ' ' || ch == '(' || ch == ')' || ch == '"') {
            return new Token('*', src.substring(start, pos));
          }
          continue;
        }
        case 2: {
          if (ch == '"') {
            return new Token('"', src.substring(start + 1, pos++));
          }
          if (ch == '\'') {
            state = 3;
            continue;
          }
          continue;
        }
        case 3: {
          continue;
        }
      }
    }
    throw new IllegalStateException();
  }

}