package dp;

/*
 Given a rope with length n, how should we cut the rope into m parts, with lengths n[0], n[1], ...,n[m - 1], in order to get the maximum product of n[0] * n[1] * ... * n[m - 1]?

 Input Format:
 There is only one argument denoting n.

 Output Format:
 Return maximum possible product.

 Constraints:
 2 <= n <= 111
 We have to cut at least once. (2 <= m)
 Length of the rope, as well as the length of each part are in positive integer value. (i.e. can't do partial cuts.)

 Sample Test Case:
 Sample Input:
 4
 Sample Output:
 4
 Explanation:
 For n = 4, there are two cuts possible: 1 + 3 and 2 + 2.
 We'll pick 2 + 2, because their product 2 * 2 = 4 is greater than product of the first one 1 * 3 = 3.
 (So our m = 2, n[0] = 2 and n[1] = 2 and product is n[0] * n[1] = 4.)
 */

/**
 * @author rpurigella
 */
public class CutTheRope {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(maxProductMain(n));
        System.out.println(maxProductDP(n));
    }

    static long maxProductMain(int n) {
        return maxProduct(n, n);
    }

    static long maxProduct(int n, int maxN) {
        if (n == 0) return 1;
        long val = 0;
        if (n == maxN) {
            for (int cut = 1; cut < n; cut++) {
                val = Math.max(val, maxProduct(n - cut, maxN) * cut);
            }
        } else {
            for (int cut = 1; cut <= n; cut++) {
                val = Math.max(val, maxProduct(n - cut, maxN) * cut);
            }
        }
        return val;
    }

    static long maxProductDP(int n) {
        long[] table = new long[n + 1];
        table[0] = 1;
        for (int i = 1; i < n + 1; i++) {
            long val = 0;
            int rope = (i == n) ? i - 1 : i;
            for (int cut = 1; cut <= rope; cut++) {
                val = Math.max(val, table[i-cut] * cut);
            }
            table[i] = val;
        }
        return table[n];
    }
}

