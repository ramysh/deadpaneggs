package dp;

/**
 * @author rpurigella
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] values = {6,
                1,
                6,
                1,
                1,
                10,
                1,
                8,
                3,
                2,
                7,
                3};
        System.out.println(maxStolenValueRecursion(values));
        System.out.println(maxStolenValueDP(values));
    }

    static int maxStolenValueRecursion(int[] values) {
        return maxValue(values, 0, false);
    }

    static int maxValue(int[] values, int i, boolean stolen) {
        if (i == values.length) return 0;
        if (!stolen) {
            return Math.max(maxValue(values, i + 1, false), values[i] + maxValue(values, i + 1, true));
        } else {
            return maxValue(values, i + 1, false);
        }
    }

    static int maxStolenValueDP(int[] values) {
        int v = values.length;
        int[] table = new int[v + 1];
        table[0] = 0;
        table[1] = values[0];
        for (int i = 2; i < v + 1; i++) {
            table[i] = Math.max(table[i-1], values[i-1] + table[i-2]);
        }
        return table[v];
    }
}
