package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the path with the max value in the grid from (0,0) to (n-1, m-1)
 * @author rpurigella
 */
public class MaxPaths {

    public static void main(String[] args) {
        int[][] grid =
                {{5, -4, -152, -25},
                {44, -9, 0, -17},
                {12, -45, 7, -16},
                {6, -34, 2, 30},
                {6, 34, 2, 3}};
        getMaxValuePath(grid);
    }

    static class Pos {
        int i;
        int j;

        public Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static List<Pos> getMaxValuePath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] table = new int[n+1][m+1];

        for (int i = 0; i < n+1; i++) {
            table[i][m] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < m+1; i++) {
            table[n][i] = Integer.MIN_VALUE;
        }

        table[n-1][m-1] = grid[n-1][m-1];

        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if (i == n-1 && j == m-1) continue;
                table[i][j] = grid[i][j] + Math.max(table[i][j+1], table[i+1][j]);
            }
        }
        return getPath(table);
    }

    static List<Pos> getPath(int[][] table) {
        List<Pos> path = new ArrayList<>();
        int n = table.length -1;
        int m = table[0].length -1;

        Pos start = new Pos(0,0);
        path.add(start);
        while(true) {
            if (start.i == n-1 && start.j == m-1) break;
            if (table[start.i][start.j+1] > table[start.i+1][start.j]) {
                start = new Pos(start.i, start.j+1);
                path.add(start);
            } else {
                start = new Pos(start.i+1, start.j);
                path.add(start);
            }
        }
        return path;
    }




}