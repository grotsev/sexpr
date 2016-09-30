package kz.greetgo.sexpr;

import java.util.Map;

/**
 * Created by den on 30.09.16.
 */
public class RtdmInterpreter {
  public static Object evalSwitch(ExprList sw, Map<String, Object> scope) {
    for (LispParser.Expr caseExpr : sw.subList(1, sw.size() - 1)) {
      ExprList casePair = (ExprList) caseExpr;
      if (evalConds(casePair.get(0), scope))
        return eval(casePair.get(0), scope);
    }
    return sw.get(sw.size() - 1);
  }

  public static Object eval(LispParser.Expr sw, Map<String, Object> scope) {
    return null; // TODO
  }

  public static boolean evalConds(LispParser.Expr sw, Map<String, Object> scope) {
    if (sw instanceof ExprList) {
      ExprList list = (ExprList)sw;

    }
    return false; // TODO
  }
}
