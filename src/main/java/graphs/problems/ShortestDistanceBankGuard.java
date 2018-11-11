package graphs.problems;

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by rpurigella on 11/5/18.
 */
public class ShortestDistanceBankGuard {

    static int[][] shortestDistanceFromGuard(char[][] graph) {
        int[] n1 = {0, 0, -1, 1};
        int[] n2 = {1, -1, 0, 0};
        int rows = graph.length;
        int cols = graph[0].length;

        int[][] dist = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Queue<Node> q = new LinkedList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 'G') {
                    q.offer(new Node(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while(!q.isEmpty()) {
            Node v = q.poll();
            for (Node w : neighbors(v, graph, n1, n2)) {
                if (!visited[w.r][w.c]) {
                    visited[w.r][w.c] = true;
                    dist[w.r][w.c] = dist[v.r][v.c] + 1;
                    q.offer(w);
                }
            }
        }

        int[][] output = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (graph[i][j] == 'W') {
                    output[i][j] = -1;
                } else if (graph[i][j] == 'G') {
                    output[i][j] = 0;
                } else {
                    if (dist[i][j] == 0) {
                        output[i][j] = -9;
                    } else {
                        output[i][j] = dist[i][j];
                    }
                }
            }
        }
        return output;
    }

    static List<Node> neighbors(Node v, char[][] graph, int[] n1, int[] n2) {
        List<Node> list = new ArrayList<>();
        int rows = graph.length;
        int cols = graph[0].length;
        for (int i = 0; i < n1.length; i++) {
            int r = v.r + n1[i];
            int c = v.c + n2[i];
            if (r < rows && c < cols && r >= 0 && c >= 0 && graph[r][c] != 'W') {
                list.add(new Node(r,c));
            }
        }
        return list;
    }

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        char[][] input = {{'O', 'O', 'O', 'O', 'O'},
                          {'O', 'W', 'W', 'W', 'O'},
                          {'O', 'W', 'O', 'G', 'O'},
                          {'O', 'W', 'W', 'W', 'O'},
                          {'O', 'O', 'O', 'O', 'G'}};

        int[][] output = shortestDistanceFromGuard(input);
        StdOut.println();
        for (int[] row : output) {
            for(int col : row) {
                StdOut.print(col + "  ");
            }
            StdOut.println();
        }
        StdOut.println();
    }
}
