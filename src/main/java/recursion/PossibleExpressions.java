package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rpurigella
 */
public class PossibleExpressions {

    public static void main(String[] args) {
        generate_all_expressions("23456", 40L);
        solver("222", 24L);
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

    static String[] solver(String num, long target) {
        List<String> ret = new ArrayList<>();
        if (num.length() == 0) return ret.toArray(new String[0]);
        char[] path = new char[num.length() * 2 - 1];
        char[] digits = num.toCharArray();
        long n = 0;
        for (int i = 0; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            path[i] = digits[i];
            compute(ret, path, i + 1, 0, n, digits, i + 1, target);
        }
        return ret.toArray(new String[0]);

    }
    static void compute(List<String> resultList, char[] charArr, int length, long left, long cur, char[] digits, int position, long target) {
        if (position == digits.length) {
            if (left + cur == target) resultList.add(new String(charArr, 0, length));
            return;
        }
        long n = 0;
        int j = length + 1;
        for (int i = position; i < digits.length; i++) {
            n = n * 10 + digits[i] - '0';
            charArr[j++] = digits[i];
            charArr[length] = '*';
            compute(resultList, charArr, j, left, cur * n, digits, i + 1, target);
            charArr[length] = '+';
            compute(resultList, charArr, j, left + cur, n, digits, i + 1, target);

        }
    }
}
