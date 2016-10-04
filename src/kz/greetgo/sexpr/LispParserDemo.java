package kz.greetgo.sexpr;

import kz.greetgo.sexpr.ExprList;
import kz.greetgo.sexpr.LispParser;
import kz.greetgo.sexpr.LispParser.ParseException;
import kz.greetgo.sexpr.LispTokenizer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LispParserDemo {
  public static void main(String args[]) throws URISyntaxException, IOException {
    java.net.URL url = LispParserDemo.class.getResource("sexpr.txt");
    java.nio.file.Path resPath = java.nio.file.Paths.get(url.toURI());
    String str = new String(java.nio.file.Files.readAllBytes(resPath), "UTF8");
    LispTokenizer tzr = new LispTokenizer(str);

//    for (int i = 0; i < 1000; i++) {
//      System.out.println(tzr.next());
//    }
//    System.exit(0);

    LispParser parser = new LispParser(tzr);

    Map<String, Object> scope = new HashMap<>();
    try {
      LispParser.Expr sexpr = parser.parseExpr();
      Object result = RtdmInterpreter.eval(sexpr, scope);
      System.out.println(result);

      System.out.println(example());
    } catch (ParseException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

  public static LispParser.Expr example() {
    return l(a("switch"), l(l(a("="), a("name"), s("Peter")), s("SMS to client")));
  }

  public static ExprList l(LispParser.Expr... exprs) {
    ExprList expr = new ExprList();
    expr.addAll(Arrays.asList(exprs));
    return expr;
  }

  public static Atom a(String name) {
    return new Atom(name);
  }

  public static StringAtom s(String text) {
    return new StringAtom(text);
  }

}