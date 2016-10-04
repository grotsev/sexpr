package kz.greetgo.sexpr;

import java.io.StreamTokenizer;

public class Token
{
  public static final int SYMBOL = '*';
  public int type;
  public String text;

  public Token(int type, String text)
  {
    this.type = type;
    this.text = text;
  }

  public String toString()
  {
    switch(this.type)
    {
      case SYMBOL:
      case '"':
        return this.text;
      default:
        return String.valueOf((char)this.type);
    }
  }
}