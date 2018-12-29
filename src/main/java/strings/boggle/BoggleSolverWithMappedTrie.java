package strings.boggle;


import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by rpurigella on 11/8/18.
 */
public class BoggleSolverWithMappedTrie {

    private class TrieNode {
        boolean isWord;
        Map<Character, TrieNode> children = new HashMap<>();
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
        x.children.put(c, put(x.children.get(c), word, d+1));
        return x;
    }

    public Set<String> getAllValidWords(String dictFile, char[][] board) {
        Set<String> validWords = new HashSet<>();
        In in = new In(dictFile);
        while(!in.isEmpty()) {
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

    static void collect(int i, int j, TrieNode root, char[][] b, Set<String> validWords) {
        Set<BoggleNode> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        BoggleNode node = new BoggleNode(i, j);
        explore(node, root, sb, validWords, seen, b);
    }

    static void explore(BoggleNode v, TrieNode root, StringBuilder sb, Set<String> validWords, Set<BoggleNode> seen, char[][] b) {
        seen.add(v);
        char c = Character.toUpperCase(b[v.i][v.j]);
        sb.append(c);
        if (root.children.get(c) != null) {
            if (root.children.get(c).isWord) {
                validWords.add(sb.toString());
            }
            for (BoggleNode w : BoggleNode.neighbors(v, b)) {
                if (!seen.contains(w)) {
                    explore(w, root.children.get(c), sb, validWords, seen, b);
                }
            }
        }
        seen.remove(v);
        sb.deleteCharAt(sb.length()-1);
    }
}
