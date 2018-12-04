package dp;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 Consider a maze mapped to a matrix with an upper left corner at coordinates (row, column) = (0, 0).
 Any movement must be in increasing row or column direction. You must determine the number of distinct paths through the maze.
 You will always start at position (0, 0), the top left, and end up at (max(row), max(column)), the bottom right.

 As an example, consider the following  where '1' indicates an open cell and '0' indicates blocked. You can only travel through open cells, so no path can go through the cell at (0, 2).
 There are two distinct paths to the goal.
 1 1 0 1
 1 1 1 1

 * @author rpurigella
 */
public class NumPathsWith1sAnd0s {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 1},
        };
        StdOut.println(numberOfPathsRecursive(grid));
        StdOut.println(numberOfPaths(grid));
    }

    static int numberOfPathsRecursive(int[][] a) {
        return paths(a, 0, 0);
    }

    static int paths(int[][] a , int i, int j) {
        int n = a.length;
        int m = a[0].length;
        if (i == n-1 && j == m-1) return 1;
        if (i > n-1 || j > m-1) return 0;
        if (a[i][j] == 0) return 0;
        return paths(a, i+1, j) + paths(a, i, j+1);
    }

    static int numberOfPaths(int[][] a) {
        int n = a.length;
        int m = a[0].length;
        int[][] table = new int[n+1][m+1];
        table[n-1][m-1] = 1;
        for (int i = n-1; i >=0; i--) {
            for (int j = m-1; j >=0; j--) {
                if (i == n-1 && j == m-1) continue;
                if (a[i][j] == 0) {
                    table[i][j] = 0;
                } else {
                    table[i][j] = table[i+1][j] + table[i][j+1];
                }
            }
        }
        return table[0][0];
    }
}

