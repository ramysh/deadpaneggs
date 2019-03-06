package dp;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. He can climb a certain number of steps together.
 * For instance, the person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top.
 * @author rpurigella
 */
public class NStairs {
    public static void main(String[] args) {
        int[] steps = {4,7};
        int n = 6;
        System.out.println(countWaysToClimb(steps, n));
        System.out.println(waysRecursive(steps, n));
    }

    static int countWaysToClimb(int[] steps, int n) {
        int[] table = new int[n+1];
        table[0] = 1;

        for (int i = 1; i < table.length; i++) {
            for(int k : steps) {
                if (i-k < 0) continue;
                table[i] += table[i-k];
            }
        }
        return table[n];
    }

    static long waysRecursive(int[] steps, int n) {
        if (n == 0) return 1;
        if (n < 0) return -1;
        long val = 0;
        for (int s : steps) {
            long result = waysRecursive(steps, n-s);
            if (result < 1) continue;
            val += result;
        }
        return val;
    }
}

