package kz.greetgo.sexpr;

import kz.greetgo.sexpr.ExprList;
import kz.greetgo.sexpr.LispParser;
import kz.greetgo.sexpr.LispParser.ParseException;
import kz.greetgo.sexpr.LispTokenizer;

public class LispParserDemo
{
  public static void main(String args[])
  {

    LispTokenizer tzr = new LispTokenizer(
        "(a (b1  (b2 \"b3 \")) c (d e123))");
    LispParser parser = new LispParser(tzr);

    try
    {
      LispParser.Expr result = parser.parseExpr();
      System.out.println(result);
    }
    catch (ParseException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}