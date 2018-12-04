package dp;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * @author rpurigella
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] values = {6, 1, 2, 7};
        StdOut.println(maxStolenValue(values));
    }

    static int maxStolenValue(int[] values) {
        int n = values.length;
        int[] table = new int[n];
        table[n-1] = values[n-1];
        if (values[n-1] > values[n-2]) {
            table[n-2] = values[n-1];
        } else {
            table[n-2] = values[n-2];
        }
        for (int i = n-3; i >= 0; i--) {
            table[i] = Math.max(values[i] + table[i+2], table[i+1]);
        }
        return table[0];
    }
}

