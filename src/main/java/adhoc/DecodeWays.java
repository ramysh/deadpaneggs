package adhoc;

import java.util.*;

/**
 * https://leetcode.com/problems/decode-ways/
 * @author rpurigella
 */
public class DecodeWays {

    public int numDecodings(String s) {
        return numD(s, 0);
    }

    private int numD(String s, int i) {
        if (i == s.length()) return 1;
        int a = 0;
        int b = 0;
        if (s.charAt(i) == '0') return 0;
        a = numD(s, i+1);
        if (i <= s.length() - 2) {
            if (Integer.parseInt(s.substring(i, i+2)) <= 26) {
                b = numD(s, i+2);
            }
        }
        return a + b;
    }

    public static void main(String[] args) {

    }
}

