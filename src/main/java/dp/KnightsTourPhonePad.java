package dp;

import java.util.*;

/**
 * @author rpurigella
 */
public class KnightsTourPhonePad {

    public static void main(String[] args) {
        for (String s : listNumbersMain(1, 4)) {
            System.out.println(s);
        }
        System.out.println(countNumbersMain(1, 4));
        System.out.println(numPhoneNumbers(1, 4));
    }

    static int[][] a = {{4,6}, {8,6}, {7,9}, {4,8}, {3, 9, 0}, {}, {7, 1, 0}, {2, 6}, {1, 3}, {4, 2}};

    static List<String> listNumbersMain(int start, int length) {
        List<String> result = new ArrayList<>();
        if (length == 0) return result;
        StringBuilder sb = new StringBuilder();
        listNumbers(start, length, result, sb);
        return result;
    }

    static void listNumbers(int start, int length, List<String> result, StringBuilder sb) {
        sb.append(start);
        if (length == 1) {
            result.add(sb.toString());
        } else {
            for (int next : a[start]) {
                listNumbers(next, length - 1, result, sb);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
    }

    static long countNumbersMain(int start, int length) {
        if (length == 0) return 0;
        if (length == 1) return 1;
        long val = 0;
        for (int next : a[start]) {
            val += countNumbersMain(next, length - 1);
        }
        return val;
    }

    static long numPhoneNumbers(int startdigit, int phonenumberlength) {
        long[][] table = new long[10][phonenumberlength];
        for (int i = 0; i < 10 ; i++) {
            table[i][0] = 1;
        }

        for (int c = 1; c < phonenumberlength; c++) {
            for (int r = 0; r < 10; r++) {
                long val = 0;
                for (int n : a[r]) {
                    val += table[n][c - 1];
                }
                table[r][c] = val;
            }
        }

        return table[startdigit][phonenumberlength - 1];
    }

}

