package graphs;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by rpurigella on 10/16/18.
 */
public class KnightsTourChess {

    static int find_minimum_number_of_moves(int rows, int cols, int start_row, int start_col, int end_row, int end_col) {
        Queue<Coordinate> q = new LinkedList<>();
        Coordinate start = new Coordinate(start_row, start_col);
        Coordinate target = new Coordinate(end_row, end_col);
        boolean[][] marked = new boolean[rows][cols];
        start.setDist(0);
        q.add(start);
        marked[start_row][start_col] = true;
        while(!q.isEmpty()) {
            Coordinate x = q.poll();
            if (x.row == target.row && x.col == target.col) {
                return x.dist;
            }
            for (Coordinate w : neighbors(x)) {
                if (within(w, rows, cols) && !marked[w.row][w.col]) {
                    marked[w.row][w.col] = true;
                    w.setDist(x.dist + 1);
                    q.add(w);
                }
            }
        }
        return -1;
    }

    static boolean within(Coordinate coordinate, int rows, int cols) {
        return coordinate.row < rows && coordinate.col < cols && coordinate.row >= 0 && coordinate.col >= 0;
    }

    static List<Coordinate> neighbors(Coordinate coordinate) {
        List<Coordinate> list = new ArrayList<>();
        list.add(new Coordinate(coordinate.row + 1, coordinate.col + 2));
        list.add(new Coordinate(coordinate.row - 1, coordinate.col + 2));
        list.add(new Coordinate(coordinate.row + 1, coordinate.col - 2));
        list.add(new Coordinate(coordinate.row - 1, coordinate.col - 2));
        list.add(new Coordinate(coordinate.row + 2, coordinate.col + 1));
        list.add(new Coordinate(coordinate.row - 2, coordinate.col + 1));
        list.add(new Coordinate(coordinate.row + 2, coordinate.col - 1));
        list.add(new Coordinate(coordinate.row - 2, coordinate.col - 1));
        return list;
    }

    static class Coordinate {
        private int row;
        private int col;
        private int dist;

        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int rows = Integer.parseInt(scan.nextLine().trim());

        int cols = Integer.parseInt(scan.nextLine().trim());

        int start_row = Integer.parseInt(scan.nextLine().trim());

        int start_col = Integer.parseInt(scan.nextLine().trim());

        int end_row = Integer.parseInt(scan.nextLine().trim());

        int end_col = Integer.parseInt(scan.nextLine().trim());

        int res = find_minimum_number_of_moves(rows, cols, start_row, start_col, end_row, end_col);

        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }
}
