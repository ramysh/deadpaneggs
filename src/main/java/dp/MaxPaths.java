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
        List<Pos> path = getMaxValuePath(grid);
        System.out.println();
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static List<Pos> getMaxValuePath(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] table = new int[rows + 1][cols + 1];

        for (int i = 0; i < rows + 1; i++) {
            table[i][cols] = Integer.MIN_VALUE;
        }

        for (int i = 0; i < cols + 1; i++) {
            table[rows][i] = Integer.MIN_VALUE;
        }

        table[rows - 1][cols - 1] = grid[rows - 1][cols - 1];

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i == rows - 1 && j == cols - 1) continue;
                table[i][j] = Math.max(table[i][j+1], table[i+1][j]) + grid[i][j];
            }
        }
        return getPath(table);
    }

    static List<Pos> getPath(int[][] table) {
        List<Pos> path = new ArrayList<>();
        Pos pos = new Pos(0,0);
        while(true) {
            path.add(pos);
            if (pos.x == table.length - 2 && pos.y == table[0].length - 2) break;
            if (table[pos.x][pos.y + 1] > table[pos.x + 1][pos.y]) {
                pos = new Pos(pos.x, pos.y + 1);
            } else {
                pos = new Pos(pos.x + 1, pos.y);
            }
        }
        return path;
    }

}