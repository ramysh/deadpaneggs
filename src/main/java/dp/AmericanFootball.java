package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Find num ways to reach a score
 * @author rpurigella
 */
public class AmericanFootball {

    public static void main(String[] args) {
        int n = 8;
        int[] k = {2, 3, 6};
        System.out.println(waysRecursionMemoMain(n, k));
        System.out.println(waysDP(n, k));
    }

    static long waysRecursionMemoMain(int n, int[] k) {
        Map<Integer, Long> cache = new HashMap<>();
        return waysRecursionMemo(n, k, cache);
    }

    static long waysRecursionMemo(int n, int[] k, Map<Integer, Long> cache) {
        if (n == 0) return 1;
        if (n < 0) return 0;

        long value = 0;
        for (int ek : k) {
            int kValue =  n - ek;
            if (!cache.containsKey(kValue)) {
                cache.put(kValue, waysRecursionMemo(kValue, k, cache));
            }
            value += cache.get(kValue);
        }
        return value;
    }


    static long waysDP(int n, int[] k) {
        int mod = largest(k) + 1;
        long[] table = new long[mod];
        table[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            long value = 0;
            for (int ek : k) {
                if (i - ek < 0) continue;
                value += table[(i - ek) % mod];
            }
            table[i % mod] = value;
        }

        return table[n % mod];
    }

    static int largest(int[] k) {
        int value = Integer.MIN_VALUE;
        for (int ek : k) {
            if (ek > value) value = ek;
        }
        return value;
    }
}

