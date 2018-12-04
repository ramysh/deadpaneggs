package recursion;

/**
 * @author rpurigella
 */
public class CountPaths {

    private static int cacheHits;
    private static int rCalls;
    private static int rMCalls;
    private static int dpAdds;


    public static void main(String[] args) {
        int[][] grid = new int[10][11];
        long start, end;

        start = System.currentTimeMillis();
        System.out.println("Recursion:");
        System.out.println("Ans = " + countPathsR(grid));
        end = System.currentTimeMillis();
        System.out.println("time taken = " + (end - start) + "ms");
        System.out.println("Method calls = " + rCalls);

        System.out.println("-----------------------");
        System.out.println("Memoization:");
        start = System.currentTimeMillis();
        System.out.println("Ans = " + countPathsRM(grid));
        end = System.currentTimeMillis();
        System.out.println("time taken = " + (end - start) + "ms");
        System.out.println("Method calls = " + rMCalls);
        System.out.println("Cache hits = " + cacheHits);

        System.out.println("-----------------------");
        System.out.println("DP");
        start = System.currentTimeMillis();
        System.out.println("Ans = " + countPathsDP(grid));
        end = System.currentTimeMillis();
        System.out.println("time taken = " + (end - start) + "ms");
        System.out.println("DP additions = " + dpAdds);
        System.out.println();
    }

    private static long countPathsR(int[][] grid) {
        if (grid == null) throw new IllegalArgumentException();
        return countPathsR(grid, 0, 0);
    }

    private static long countPathsR(int[][] grid, int m, int n) {
        rCalls++;
        if (m >= grid.length || n >= grid[0].length) {
            return 0;
        }
        if (m == grid.length - 1 && n == grid[0].length - 1) {
            return 1;
        }
        return countPathsR(grid, m , n+1) + countPathsR(grid, m+1, n);
    }

    private static long countPathsRM(int[][] grid) {
        if (grid == null) throw new IllegalArgumentException();
        Long[][] cache = new Long[grid.length][grid[0].length];
        return countPathsRM(grid, 0, 0, cache);
    }

    private static long countPathsRM(int[][] grid, int m, int n, Long[][] cache) {

        if (m >= grid.length || n >= grid[0].length) {
            return 0;
        }
        if (m == grid.length - 1 && n == grid[0].length - 1) {
            return 1;
        }

        if (cache[m][n] != null) {
            cacheHits++;
            return cache[m][n];
        } else {
            rMCalls++;
            cache[m][n] = countPathsRM(grid, m, n+1, cache) + countPathsRM(grid, m+1, n, cache);
            return cache[m][n];
        }
    }

    private static long countPathsDP(int[][] grid) {
        if (grid == null) throw new IllegalArgumentException();
        long[][] table = new long[grid.length + 1][grid[0].length + 1];

        int rows = grid.length - 1 ;
        int cols = grid[0].length - 1;

        for (int i = rows; i >= 0 ; i--) {
            for (int j = cols; j >= 0; j--) {
                if (i == rows && j == cols) {
                    table[i][j] = 1;
                } else {
                    dpAdds++;
                    table[i][j] = table[i + 1][j] + table[i][j + 1];
                }
            }
        }
        return table[0][0];
    }
}
