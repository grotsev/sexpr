package kz.greetgo.sexpr;

import kz.greetgo.sexpr.LispParser.Expr;

public class Atom implements Expr
{
  String name;
  public String toString()
  {
    return name;
  }
  public Atom(String text)
  {
    name = text;
  }

}