package graphs.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Created by rpurigella on 10/16/18.
 */
public class CountIslands {

    private static final Scanner scan = new Scanner(System.in);
    private static int[] row_add = {0, 0, -1, -1, -1, 1, 1, 1};
    private static int[] col_add = {1, -1, -1, 0, 1, -1, 0, 1};


    static int count_islands(int[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != -1 && matrix[i][j] != 0) {
                    count++;
                    explore(matrix, i, j);
                }
            }
        }
        //can restore matrix array by marking all -1's as 1's
        return count;
    }

    static void explore(int[][] matrix, int row, int col){
        matrix[row][col] = -1;
        for (int x = 0; x < 8; x++){
            int next_row = row + row_add[x];
            int next_col = col + col_add[x];
            if (within(next_row, next_col, matrix) && matrix[next_row][next_col] != -1 && matrix[next_row][next_col] !=0) {
                explore(matrix, next_row, next_col);
            }
        }
    }

    static boolean within(int row, int col, int[][] matrix) {
        return (row >= 0 && row < matrix.length && col >=0 && col < matrix[0].length);
    }



/*
6
5
1 1 0 0 0
0 1 0 0 1
1 0 0 1 1
0 0 0 0 0
1 0 1 0 1
1 1 1 1 1
*/
    // Dont use extra memory
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int rows = Integer.parseInt(scan.nextLine().trim());

        int cols = Integer.parseInt(scan.nextLine().trim());

        int[][] matrix = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            String line = scan.nextLine();
            String[] splitLine = line.split(" ");
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = Integer.parseInt(splitLine[c]);
            }
        }

        bufferedWriter.newLine();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                bufferedWriter.write(matrix[r][c] + " ");
            }
            bufferedWriter.newLine();
        }

        bufferedWriter.newLine();
        int islands = count_islands(matrix);
        bufferedWriter.write("Count = " + islands);

        bufferedWriter.newLine();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                bufferedWriter.write(matrix[r][c] + " ");
            }
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scan.close();
    }
}

