package dp;

/**
 * https://www.geeksforgeeks.org/optimal-strategy-for-a-game-dp-31/
 * @author rpurigella
 */
public class CoinPlay {
    public static void main(String[] args) {
        int[] a = {5, 3, 7, 10};
        System.out.println(maxWin(a));
    }

    static int maxWin(int[] v) {
        return maxWin(v, 0, v.length - 1, true);
    }

    static int maxWin(int[] v, int i, int j, boolean myTurn) {
        if (i > j) return 0;
        if (myTurn) {
            return Math.max(maxWin(v, i+1, j, false) + v[i], maxWin(v, i, j-1, false) + v[j]);
        } else {
            return Math.min(maxWin(v, i+1, j, true) , maxWin(v, i, j-1, true));
        }
    }
}


