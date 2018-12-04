package dp;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author rpurigella
 */
public class CoinChange {

    /*
     * find the min number of coins needed to make change
     */
    public static void main(String[] args) {
        int A = 100;
        int k[] = {5, 30, 17, 15};
        for (int i : getChangeRecursionMain(A, k)) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        for (int i : getChangeDP(A, k)) {
            StdOut.print(i + " ");
        }
        StdOut.println();
    }

    static List<Integer> getChangeDP(int A, int[] k) {
        int[] table = new int[A+1];
        for (int i = 0; i < table.length; i++) {
            table[i] = Integer.MAX_VALUE;
        }
        table[0] = 0;
        for (int i = 1; i < table.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < k.length; j++) {
                if (i - k[j] < 0) continue;
                min = Math.min(min, table[i - k[j]]);
            }
            if (min != Integer.MAX_VALUE) table[i] = 1 + min;
        }
        return buildPath(table, k);
    }

    static List<Integer> buildPath(int[] table, int[] k) {
        List<Integer> path = new ArrayList<>();
        if (table[table.length - 1] == Integer.MAX_VALUE) return path;
        int curr = table[table.length - 1];
        int index = table.length - 1;
        while(curr != 0) {
            for (int i = 0; i < k.length; i++) {
                if (table[index - k[i]] == curr - 1){
                    index = index - k[i];
                    path.add(k[i]);
                    break;
                }
            }
            curr--;
        }
        return path;
    }

    static List<Integer> getChangeRecursionMain(int A, int[] k) {
        Map<Integer, Integer> cache = new HashMap<>();
        Map<Integer, Integer> pathMap = new HashMap<>();
        getChangeRecursion(A, k, cache, pathMap);
        return buildPath(pathMap, A);
    }

    static int getChangeRecursion(int A, int[] k, Map<Integer, Integer> cache, Map<Integer, Integer> pathMap) {
        if (A == 0) return 1;
        if (A < 0) return Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;
        int kValue = 0;
        for (int i = 0; i < k.length; i++) {
            int denom = A - k[i];
            if (!cache.containsKey(denom)) {
                cache.put(denom, getChangeRecursion(denom, k, cache, pathMap));
            }
            if (min > cache.get(denom)) {
                min = cache.get(denom);
                kValue = k[i];
            }
        }
        if (min != Integer.MAX_VALUE) {
            pathMap.put(A, kValue);
            return 1 + min;
        }
        return  min;
    }

    static List<Integer> buildPath(Map<Integer, Integer> pathMap, int A) {
        List<Integer> path = new ArrayList<>();
        int curr = A;
        while (curr !=0) {
            if (!pathMap.containsKey(curr)) break;
            path.add(pathMap.get(curr));
            curr = curr - pathMap.get(curr);
        }
        return path;
    }
}
