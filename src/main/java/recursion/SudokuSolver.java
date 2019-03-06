package recursion;

import java.util.*;

/**
 * @author rpurigella
 */
public class SudokuSolver {

    static final int[] NUMS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static final int N = 9;
    static final int[][] BOUNDARIES = {
                                    {0, 2, 0, 2},
                                    {0, 2, 3, 5},
                                    {0, 2, 6, 8},
                                    {3, 5, 0, 2},
                                    {3, 5, 3, 5},
                                    {3, 5, 6, 8},
                                    {6, 7, 0, 2},
                                    {6, 7, 3, 5},
                                    {6, 7, 6, 8}
    };
    static final int[][] QUADRANTS = {
                                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                                    {0, 0, 0, 1, 1, 1, 2, 2, 2},
                                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                                    {3, 3, 3, 4, 4, 4, 5, 5, 5},
                                    {6, 6, 6, 7, 7, 7, 8, 8, 8},
                                    {6, 6, 6, 7, 7, 7, 8, 8, 8},
                                    {6, 6, 6, 7, 7, 7, 8, 8, 8},
    };


    static int[][] getPartialBoard() {
        int[][] board = {
                {0, 9, 7, 0, 0, 6, 0, 0, 0},
                {8, 0, 0, 5, 3, 0, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 6, 5, 7, 0, 8},
                {0, 6, 0, 0, 0, 0, 0, 3, 0},
                {1, 0, 3, 4, 2, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 4, 0},
                {0, 0, 0, 0, 5, 1, 0, 0, 7},
                {0, 0, 0, 6, 0, 0, 1, 8, 0}
        };
        return board;
    }

    static int[][] sudokuSolver(int[][] board) {
        int[][] result = new int[N][N];
        solve(0, 0, board, result);
        if (result[0][0] == 0) return null;
        return result;
    }

    static void solve(int row, int col, int[][] board, int[][] result) {
        if (row == N) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result[i][j] = board[i][j];
                }
            }
            return;
        }
        if (col == N) {
            solve(row + 1, 0, board, result);
        } else if (board[row][col] != 0) {
            solve(row, col + 1, board, result);
        } else {
            for (int x : NUMS) {
                if (canBePlaced(x, row, col, board)) {
                    board[row][col] = x;
                    solve(row, col + 1, board, result);
                    board[row][col] = 0;
                }
            }
        }
    }

    static boolean canBePlaced(int x, int row, int col, int[][] board) {
        for (int i = 0; i < N; i++){
            if (board[i][col] == x) return false;
            if (board[row][i] == x) return false;
        }
        int[] boundary = BOUNDARIES[QUADRANTS[row][col]];
        for (int i = boundary[0]; i <= boundary[1]; i++) {
            for (int j = boundary[2]; j <= boundary[3]; j++) {
                if (board[i][j] == x) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = getPartialBoard();
        print(board);
        int[][] solved = sudokuSolver(board);
        if (solved != null) {
            print(solved);
        }
        else {
            System.out.println("No Solution");
        }

    }

    static void print(int[][] board) {
        System.out.println();
        System.out.println("-----");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}

