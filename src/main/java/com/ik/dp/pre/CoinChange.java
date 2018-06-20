package com.ik.dp.pre;

import java.util.HashMap;

/**
 * @author rpurigella
 */
public class CoinChange {

    public static void main(String[] args) {
        int value = 67;
        int d[] = {3,8};
        System.out.println(ccRMain(value, d));
        System.out.println(ccMMain(value, d));
    }

    private static int ccRMain(int value, int[] d) {
        int result = ccRecursion(value, d);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int ccRecursion(int value, int[] d) {
        if (value == 0) return 0;
        if (value < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= d.length - 1; j++) {
            min = Math.min(min, ccRecursion(value-d[j], d));
        }
        if (min != Integer.MAX_VALUE) {
            return 1 + min;
        }
        return min;
    }

    private static int ccMMain(int value, int[] d) { // TODO run time? O(value * d.len)?
        HashMap<Integer, Integer> cache = new HashMap<>();
        int result = ccMemo(value, d, cache);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static int ccMemo(int value, int[] d, HashMap<Integer, Integer> cache) {
        if (value == 0) return 0;
        if (value < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= d.length - 1; j++) {
            int newValue = value - d[j];
            if (cache.get(newValue) != null) {
                min = cache.get(newValue);
            } else {
                cache.put(newValue, Math.min(min, ccMemo(newValue, d, cache)));
                min = cache.get(newValue);
            }
        }
        if (min != Integer.MAX_VALUE) {
            return 1 + min;
        }
        return min;
    }

    private static int ccDP(int value, int[] d) {
        int[] table = new int[value + 1];
        for(int i = 0; i < table.length; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        return table[value];
    }

}
