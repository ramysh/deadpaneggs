package dp;

/**
 * There are n stairs, a person standing at the bottom wants to reach the top. He can climb a certain number of steps together.
 * For instance, the person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top.
 * @author rpurigella
 */
public class NStairs {
    public static void main(String[] args) {
        int[] steps = {2,3};
        int n = 7;
        System.out.println(countWaysToClimb(steps, n));
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
}

