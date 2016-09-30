package kz.greetgo.sexpr;

import java.util.Map;

/**
 * Created by den on 30.09.16.
 */
public class RtdmInterpreter {

  private static Object evalSwitch(ExprList sw, Map<String, Object> scope) {
    for (LispParser.Expr caseExpr : sw.subList(1, sw.size() - 1)) {
      ExprList casePair = (ExprList) caseExpr;
      if (evalCond(casePair.get(0), scope))
        return eval(casePair.get(1), scope);
    }
    return eval(sw.get(sw.size() - 1), scope);
  }

  public static Object eval(LispParser.Expr sexpr, Map<String, Object> scope) {
    if (sexpr instanceof ExprList) {
      ExprList exprList = (ExprList) sexpr;
      LispParser.Expr tag = exprList.get(0);
      if (tag instanceof Atom && ((Atom)tag).name.equals("switch")) return evalSwitch(exprList, scope);
    }
    return sexpr;
  }

  private static boolean evalCond(LispParser.Expr conds, Map<String, Object> scope) {
    ExprList condList = (ExprList) conds;
    String name = ((Atom) condList.get(0)).name;
    switch (name) {
      case "or": {
        for (LispParser.Expr cond : condList.subList(1, condList.size())) {
          if (evalCond(cond, scope)) return true;
        }
        return false;
      }
      case "=": {
        String var = ((Atom)condList.get(1)).name;
        String val = ((Atom)condList.get(2)).name;
        return true;//val.equals(scope.get(var)); // TODO cast to types
      }
      case ">": {
        // TODO
      }
    }
    return false; // TODO
  }
}
