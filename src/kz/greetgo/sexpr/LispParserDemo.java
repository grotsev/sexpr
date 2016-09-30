package kz.greetgo.sexpr;

import kz.greetgo.sexpr.ExprList;
import kz.greetgo.sexpr.LispParser;
import kz.greetgo.sexpr.LispParser.ParseException;
import kz.greetgo.sexpr.LispTokenizer;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class LispParserDemo
{
  public static void main(String args[])
  {
    Map<String, Object> scope = new HashMap<>();

    Reader r = new InputStreamReader(LispParserDemo.class.getResourceAsStream("sexpr.txt"));
    LispTokenizer tzr = new LispTokenizer(r);
    LispParser parser = new LispParser(tzr);

    try
    {
      LispParser.Expr sexpr = parser.parseExpr();
      Object result = RtdmInterpreter.eval(sexpr, scope);
      System.out.println(result);
    }
    catch (ParseException e1)
    {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }
}