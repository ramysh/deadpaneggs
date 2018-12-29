package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rpurigella
 */
public class Wildcard {
    public static void main(String[] args) {
        find_all_possibilities("1?0?");
    }

    static String[] find_all_possibilities(String s) {
        List<String> result = new ArrayList<>();
        solve(s.toCharArray(), 0, result);
        return result.toArray(new String[result.size()]);
    }

    static void solve(char[] s, int i, List<String> result) {
        if (i == s.length) {
            result.add(new String(s));
            return;
        }
        char c = s[i];
        if (c == '0' || c == '1') {
            solve(s, i + 1, result);
        } else {
            s[i] = '0';
            solve(s, i + 1, result);
            s[i] = '1';
            solve(s, i + 1, result);
            s[i] = '?';
        }
    }
}

