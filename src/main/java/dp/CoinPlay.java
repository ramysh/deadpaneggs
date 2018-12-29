package dp;

/**
 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 * @author rpurigella
 */
public class CoinPlay {
    public static void main(String[] args) {
        int[] a = {8, 15, 3, 7};
        System.out.println(maxWin(a));
    }

    static int maxWin(int[] v) {
        return 0;
    }


}

/*
    static int solve(int[] a, int i, int j, boolean myTurn) {
        if (j < i) return 0;
        if (myTurn) {
            int val1 = solve(a, i+1, j, !myTurn) + a[i];
            int val2 = solve(a, i, j-1, !myTurn) + a[j];
            return Math.max(val1, val2);
        } else if (a[i] > a[j]) {
            return solve(a, i+1, j, !myTurn);
        } else {
            return solve(a, i, j-1, !myTurn);
        }
    }
 */