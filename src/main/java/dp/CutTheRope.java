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
 * @author rpurigella TODO
 */
public class CutTheRope {
    public static void main(String[] args) {
        System.out.println(max_product_from_cut_pieces(5));
    }

/*
    static long max_product_from_cut_pieces(int n) {
        long[] table = new long[n+1];
        table[0] = 1;

        for (int i = 1; i <= n; i++) {

            int x = (i == n) ? i - 1 : i;

            for (int j = 1; j <= x; j++) {
                long val = table[i - j] * j;
                if (table[i] < val) {
                    table[i] = val;
                }
            }
        }
        return table[n];
    }
    */
    static long max_product_from_cut_pieces(int n) {
        long[] table = new long[n+1];
        table[0] = 1;
        table[1] = 1;
        for(int i = 2; i < table.length; i++) {
          for(int j = 1; j <= i/2; j++) {
                table[i] = Math.max(table[i], (i-j) * j);
                table[i] = Math.max(table[i], table[i-j] * j);
            }
        }
        return table[n];
    }
}

