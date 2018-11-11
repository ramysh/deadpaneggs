package strings.boggle;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rpurigella on 11/8/18.
 */
public class BoggleSolverWithTrie {

    private static int R = 26;

    private class TrieNode {
        boolean isWord;
        TrieNode[] next = new TrieNode[R];
    }

    private TrieNode root;

    public void put(String word) {
        root = put(root, word, 0);
    }

    TrieNode put(TrieNode x, String word, int d) {
        if (x == null) x = new TrieNode();
        if (d == word.length()) {
            x.isWord = true;
            return x;
        }
        char c = Character.toUpperCase(word.charAt(d));
        int index = c - 'A';
        x.next[index] = put(x.next[index], word, d+1);
        return x;
    }

    public Set<String> getAllValidWords(String dictFile, char[][] board) {
        Set<String> validWords = new HashSet<>();
        In in = new In(dictFile);
        while (!in.isEmpty()) {
            String word = in.readLine().trim();
            put(word);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                collect(i, j, root, board, validWords);
            }
        }

        return validWords;
    }

    private static void collect(int i, int j, TrieNode root, char[][] b, Set<String> validWords) {
        boolean[][] visited = new boolean[b.length][b[0].length];
        StringBuilder sb = new StringBuilder();
        BoggleNode node = new BoggleNode(i, j);
        explore(node, sb, root, visited, b, validWords);
    }

    private static void explore(BoggleNode v, StringBuilder sb, TrieNode root, boolean[][] visited, char[][] b, Set<String> validWords) {
        visited[v.i][v.j] = true;
        char c = Character.toUpperCase(b[v.i][v.j]);
        sb.append(b[v.i][v.j]);
        int index = c - 'A';
        if (root.next[index] != null) {
            if (root.next[index].isWord) {
                validWords.add(sb.toString());
            }
            for (BoggleNode w : BoggleNode.neighbors(v, b)) {
                if (!visited[w.i][w.j]) {
                    explore(w, sb, root.next[index], visited, b, validWords);
                }
            }
        }
        visited[v.i][v.j] = false;
        sb.deleteCharAt(sb.length()-1);
    }
}
