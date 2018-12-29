package recursion;

/**
 * @author rpurigella
 */
public class NNodeBinaryTrees {

    public static void main(String[] args) {
        System.out.println(how_many_BSTs(3));
    }

    static long how_many_BSTs(int n) {
        if (n == 0) return 1L;
        n--;
        long val = 0L;
        for (int i = 0; i <= n; i++) {
            val = val + how_many_BSTs(n-i) * how_many_BSTs(i);
        }
        return val;
    }

    static long solve(int n, int i) {
        if (i == n-1) {
            return 1;
        }
        long lr = solve(n, i+1);
        long rr = solve(n, i+1);
        return lr + rr;
    }
}
