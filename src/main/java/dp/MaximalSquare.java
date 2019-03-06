package dp;

import java.util.*;

/**
 * @author rpurigella
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '1', '1', '1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '0', '0', '0'},
                {'0', '1', '1', '1', '1', '0', '0', '0'}
        };
        System.out.println(maximalSquare(matrix));
        System.out.println(maximalSquareDP(matrix));
    }

    static int maximalSquare(char[][] matrix) {
        int max = 0;
        if (matrix == null) return 0;
        if (matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '0') continue;
                int curr = 1;
                int next = 1;
                boolean flag = true;
                while(i + next < rows && j + next < cols && flag) {
                    for (int offset = 0; offset <= next; offset++) {
                        if (matrix[i+offset][j+next] == '0') {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) continue;
                    for (int offset = 0; offset < next; offset++) {
                        if (matrix[i+next][j+offset] == '0') {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        curr++;
                        next++;
                    }
                }
                if (curr > max) max = curr;
            }
        }
        return max * max;
    }

    static int maximalSquareDP(char[][] matrix) {
        if (matrix == null) return 0;
        if (matrix.length == 0) return 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] table = new int[cols + 1];
        int max = 0;
        int prev = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int tmp = table[j+1];
                if (matrix[i][j] == '1') {
                    table[j+1] = Math.min(prev, Math.min(table[j+1], table[j])) + 1;
                    if (max < table[j+1]) max = table[j+1];
                } else {
                    table[j+1] = 0;
                }
                prev = tmp;
            }
        }
        return max * max;
    }
}

