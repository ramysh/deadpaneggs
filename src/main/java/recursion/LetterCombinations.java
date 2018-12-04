package recursion;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * @author rpurigella
 */
public class LetterCombinations {
    public static void main(String[] args) {
        letterCombinations("237");
    }

    static List<String> letterCombinations(String digits) {
        String[] mapping = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        List<String> result = new ArrayList<>();
        solve(digits.toCharArray(), 0, result, mapping);
        return result;
    }

    static void solve(char[] s, int i, List<String> result, String[] mapping) {
        if (i == s.length) {
            result.add(new String(s));
            return;
        }

        char d = s[i];
        int digit = d -'0';
        for (int j = 0; j < mapping[digit].length(); j++) {
            s[i] = mapping[digit].charAt(j);
            solve(s, i+1, result, mapping);
        }
        s[i] = d;
    }
}

