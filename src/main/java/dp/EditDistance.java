package dp;

import java.util.*;

/**
 * https://leetcode.com/problems/edit-distance/
 * @author rpurigella
 */
public class EditDistance {
    public static void main(String[] args) {
        String s1 = "horse";
        String s2 = "ros";
        System.out.println(editDistanceRecursiveMain(s1, s2));
        System.out.println(edDP(s1, s2));
    }

    static int editDistanceRecursiveMain(String s1, String s2) {
        return ed(s1, s2, 0, 0);
    }

    static int ed(String s1, String s2, int i, int j) {
        if (i == s1.length() && j == s2.length()) return 0;
        if (i == s1.length()) return s2.length() - j;
        if (j == s2.length()) return s1.length() - i;

        if (s1.charAt(i) == s2.charAt(j)) {
            return ed(s1, s2, i + 1, j + 1);
        } else {
            return Math.min(ed(s1, s2, i + 1, j + 1),
                    Math.min(ed(s1, s2, i, j + 1), ed(s1, s2, i + 1, j))) + 1;
        }
    }

    static int edDP(String strWord1, String strWord2) {
        int[][] table = new int[strWord1.length() + 1][strWord2.length() + 1];
        for (int i = 0; i < table.length; i++) {
            table[i][0] = i;
        }
        for (int i = 0; i < table[0].length; i++) {
            table[0][i] = i;
        }
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[0].length; j++) {
                if (strWord1.charAt(i - 1) == strWord2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    table[i][j] = Math.min(table[i - 1][j - 1],
                            Math.min(table[i][j - 1], table[i - 1][j])) + 1;
                }
            }
        }
        return table[strWord1.length()][strWord2.length()];
    }

}

