package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given t, a[t], b[t].
 * If shifting from a to b, lose that t's profit.
 * Ex:
 * t = 5
 * A = 5, 2, 11, 9, 10
 * B = 10, 4, 6, 10, 9
 *
 * If we do only A, profit is 5 + 2 + 11 + 9 + 10 = 37
 * only B, 10 + 4 + 6 + 10 + 9 profit = 39
 *
 * but if we do B, then switch (we lose the next t's profit), then do A, A, A
 * i.e., 10 + 0 + 11 + 9 + 10 = 40 = max profit
 *
 * @author rpurigella
 */
public class ProductionPlan {

    static class Result {
        int profit;
        List<Character> seq;
    }

    public static void main(String[] args) {
        int t = 5;
        int[] a = {10, 4, 6, 10, 9};
        int[] b = {5, 2, 11, 9, 10};
        Result result = getProfitSequence(t, a, b);
        System.out.println("Max profit = " + result.profit);
        System.out.print("Sequence = ");
        for (char c : result.seq) {
            System.out.print(c + " ");
        }

        System.out.println();
    }

    static Result getProfitSequence(int t, int[] a, int[] b) {
        int[] pa = new int[t];
        int[] pb = new int[t];

        pa[0] = a[0];
        pb[0] = b[0];
        pa[1] = a[0] + a[1];
        pb[1] = b[0] + b[1];

        for (int i = 2; i < t; i++) {
            pa[i] = Math.max(pa[i-1], pb[i-2]) + a[i];
            pb[i] = Math.max(pb[i-1], pa[i-2]) + b[i];
        }

        Result result = new Result();
        result.profit = Math.max(pa[t-1], pb[t-1]);
        result.seq = buildPath(t, pa, pb);
        return result;
    }

    static List<Character> buildPath(int t, int[] pa, int[] pb) {
        List<Character> seq = new ArrayList<>();
        char p = (pa[t - 1] > pb[t - 1] ? 'A' : 'B');
        seq.add(p);
        int i = t - 1;
        while(i > 1) {
            if (p == 'A') {
                if (pa[i - 1] > pb[i - 2]) {
                    seq.add('A');
                } else {
                    seq.add('-');
                    p = 'B';
                }
            } else {
                if (pb[i - 1] > pa[i - 2]) {
                    seq.add('B');
                } else  {
                    seq.add('-');
                    p = 'A';
                }
            }
            i--;
        }
        seq.add((pa[0] > pb[0]) ? 'A' : 'B');
        Collections.reverse(seq);
        return seq;
    }
}

