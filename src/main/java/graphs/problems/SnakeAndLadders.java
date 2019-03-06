package graphs.problems;

import java.util.*;

/**
 * @author rpurigella
 */
public class SnakeAndLadders {

    private int minNumberOfRolls(int n, List<Integer> moves) {
        boolean[] visited = new boolean[n];
        Queue<Pos> queue = new LinkedList<>();
        visited[0] = true;
        queue.add(new Pos(0));
        while(!queue.isEmpty()) {
            Pos curr = queue.poll();
            if (curr.i == n-1) return curr.dist;
            for (int j = 1; j < 7; j++) {
                int next = curr.i + j;
                if (next >= n) continue;
                if (moves.get(next) != -1) {
                    next = moves.get(next) - 1;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(new Pos(next, curr.dist + 1));
                }
            }
        }
        return -1;
    }

    private class Pos {
        int i;
        int dist;

        public Pos(int i) {
            this.i = i;
        }

        public Pos(int i, int dist) {
            this.i = i;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        int[] m = {-1, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        List<Integer> moves = new ArrayList<Integer>();
        for (int i : m)
        {
            moves.add(i);
        }
        SnakeAndLadders snakeAndLadders = new SnakeAndLadders();
        System.out.println(snakeAndLadders.minNumberOfRolls(20, moves));
    }
}

