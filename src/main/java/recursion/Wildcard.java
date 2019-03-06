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
        solve(s, result, new char[s.length()], 0);
        return result.toArray(new String[result.size()]);
    }

    static void solve(String s, List<String> result, char[] curr, int i) {
        if (i == s.length()) {
            result.add(new String(curr));
            return;
        }

        if (s.charAt(i) != '?') {
            curr[i] = s.charAt(i);
            solve(s, result, curr, i + 1);
        } else {
            curr[i] = '0';
            solve(s, result, curr, i + 1);

            curr[i] = '1';
            solve(s, result, curr, i + 1);
        }
    }
}

