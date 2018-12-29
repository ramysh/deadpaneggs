package strings.boggle;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rpurigella on 11/8/18.
 */
public class BoggleSolverWithHashMap {
    public static Set<String> allValidWordsUsingMap (String dictionary, char[][] board){
        Set<String> words = new HashSet<>();
        In in = new In(dictionary);
        while (!in.isEmpty()) {
            String word = in.readLine().trim();
            words.add(word);
        }

        Set<String> validWords = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                collect(i, j, validWords, board, words);
            }
        }
        return validWords;
    }

    private static void collect(int i, int j, Set<String> set, char[][] b, Set<String> words) {
        Set<BoggleNode> seen = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        BoggleNode boggleNode = new BoggleNode(i, j);
        explore(boggleNode, sb, set, seen, b, words);
    }

    private static void explore(BoggleNode v, StringBuilder sb, Set<String> set,
                                Set<BoggleNode> seen, char[][] b, Set<String> words) {
        seen.add(v);
        sb.append(b[v.i][v.j]);
        String word = sb.toString();
        if (words.contains(word)) {
            set.add(word);
        }
        for (BoggleNode w : BoggleNode.neighbors(v, b)) {
            if (!seen.contains(w)) {
                explore(w, sb, set, seen, b, words);
            }
        }
        seen.remove(v);
        sb.deleteCharAt(sb.length() - 1);
    }
}
