package recursion;

import java.util.*;

/**
 * @author rpurigella
 */
public class PossibleExpressions {

    public static void main(String[] args) {
        generate_all_expressions("234", 68L);
    }

    static String[] generate_all_expressions(String s, long target) {
        List<String> result = new ArrayList<>();
        StringBuilder expr = new StringBuilder();
        expr.append(s.charAt(0));
        solve(s, 1, expr, target, result);
        return result.toArray(new String[result.size()]);
    }

    static void solve(String s, int i, StringBuilder expr, long target, List<String> result) {
        if (i == s.length()) {
            if (eval(expr) == target) {
                result.add(expr.toString());
            }
            return;
        }

        expr.append(s.charAt(i));
        solve(s, i+1, expr, target, result);
        expr.deleteCharAt(expr.length()-1);

        expr.append('*');
        expr.append(s.charAt(i));
        solve(s, i+1, expr, target, result);
        expr.deleteCharAt(expr.length()-1);
        expr.deleteCharAt(expr.length()-1);

        expr.append('+');
        expr.append(s.charAt(i));
        solve(s, i+1, expr, target, result);
        expr.deleteCharAt(expr.length()-1);
        expr.deleteCharAt(expr.length()-1);

    }

    static long eval(StringBuilder expr) {
        long val = 0L;
        String[] eplus = expr.toString().split("\\+");
        for (String ep : eplus) {
            String[] estar = ep.split("\\*");
            long tmp = 1L;
            for (String es : estar) {
                tmp = tmp * Long.valueOf(es);
            }
            val = val + tmp;
        }
        return val;
    }
}
