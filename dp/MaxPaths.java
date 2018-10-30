package dp;

/**
 * @author rpurigella
 */
public class MaxPaths {

    public static void main(String[] args) {
        int[][] grid =
                {{5, 4, 15, 25},
                {44, 9, 0, 17},
                {12, 45, 7, 16},
                {6, 34, 2, 3}};
        System.out.println(maxPathRecursion(grid));
        System.out.println(maxPathMemoization(grid));
        System.out.println(maxPathDP(grid));
    }

    private static int maxPathRecursion(int[][] grid) {
        return maxPathRecursion(grid, 0, 0);
    }

    private static int maxPathRecursion(int[][] grid, int r, int c) {
        int rows = grid.length - 1;
        int cols = grid[0].length - 1;

        if (r > rows || c > cols) {
            return 0;
        }

        if (r == rows && c == cols) {
            return grid[r][c];
        }

        return grid[r][c] + Math.max(maxPathRecursion(grid, r, c + 1), maxPathRecursion(grid, r + 1, c));
    }

    private static int maxPathMemoization(int[][] grid) {
        Integer[][] cache = new Integer[grid.length][grid[0].length];
        return maxPathMemoization(grid, cache, 0, 0);
    }

    private static int maxPathMemoization(int[][] grid, Integer[][] cache, int r, int c) {
        int rows = grid.length - 1;
        int cols = grid[0].length - 1;

        if (r > rows || c > cols) {
            return 0;
        }

        if (r == rows && c == cols) {
            return grid[r][c];
        }

        if (cache[r][c] != null) {
            return cache[r][c];
        }

        cache[r][c] = grid[r][c] + Math.max(maxPathMemoization(grid, cache, r, c + 1), maxPathMemoization(grid, cache, r + 1, c));
        return cache[r][c];
    }

    private static int maxPathDP(int[][] grid) {
        int[][] table = new int[grid.length + 1][grid[0].length + 1];
        int rows = grid.length - 1;
        int cols = grid[0].length - 1;

        for (int row = rows; row >= 0; row--) {
            for (int col = cols; col >=0; col--) {
                table[row][col] = grid[row][col] + Math.max(table[row][col+1], table[row+1][col]);
            }
        }
        return table[0][0];
    }
}