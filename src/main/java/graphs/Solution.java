package graphs;

import java.util.*;

/**
 * @author rpurigella
 */
public class Solution {

    public static void main(String[] args) {
        Integer u = 3;
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        exist(board, "ABCCED");
    }

    static int[] n1 = {0, 0, -1, 1};
    static int[] n2 = {-1, 1, 0, 0};

    public static boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (explore(i, j, 0, word, visited, board)) return true;
            }
        }
        return false;
    }

    private static boolean explore(int row, int col, int i, String word, boolean[][] visited, char[][] board) {
        if (board[row][col] != word.charAt(i)) return false;
        if (i == word.length() - 1) return true;
        visited[row][col] = true;
        for (int n = 0; n < n1.length; n++) {
            int r = row + n1[n];
            int c = col + n2[n];
            if (r >= 0 && c >=0 && r < board.length && c < board[0].length && !visited[r][c]) {
                if (explore(r, c, i+1, word, visited, board)) return true;
            }
        }
        visited[row][col] = false;;
        return false;
    }
}

