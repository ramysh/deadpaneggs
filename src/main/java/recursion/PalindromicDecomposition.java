package recursion;

import java.util.*;

/**
 * @author rpurigella
 */
public class PalindromicDecomposition {

    public static void main(String[] args) {
        generate_palindromic_decompositions("aabb");
    }

    static String[] generate_palindromic_decompositions(String s) {
        List<String> result = new ArrayList<>();
        solve(s, 0, "", result);
        return result.toArray(new String[result.size()]);
    }

    static void solve(String s, int i, String expr, List<String> result) {
        if (i == s.length()) {
            result.add(expr);
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                String tmp;
                if (expr.length() > 0) {
                    tmp = expr + "|" + s.substring(i, j+1);
                } else {
                    tmp = expr + s.substring(i, j+1);
                }
                solve(s, j+1, tmp, result);
            }
        }
    }

    // Using StringBuilder
    static void solve2(String s, int i, StringBuilder expr, List<String> result) {
        if (i == s.length()) {
            result.add(expr.toString());
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (isPalindrome(s, i, j)) {
                int k = j+1-i;
                if (expr.length() > 0) {
                    expr.append('|');
                    expr.append(s.substring(i, j+1));
                    k++;
                } else {
                    expr.append(s.substring(i, j+1));
                }
                solve2(s, j+1, expr, result);
                expr.delete(expr.length() - k, expr.length());
            }
        }
    }

    static boolean isPalindrome(String s, int i , int j) {
        while (j > i) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}

