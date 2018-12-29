package strings;

import java.util.*;

/**
 * Created by rpurigella on 11/7/18.
 */
public class WordPositionsTrieWithMap {

    private class Node {
        Map<Character, Node> children = new HashMap<>();
        List<Integer> positions = new ArrayList<>();
    }

    private Node root;

    public void put(String word, int pos) {
        root = put(root, word, pos, 0);
    }

    private Node put(Node root, String word, int pos, int d) {
        if (root == null) root = new Node();
        if (d == word.length()) {
            root.positions.add(pos);
            return root;
        }
        char c = word.charAt(d);
        c = Character.toLowerCase(c);
        root.children.put(c, put(root.children.get(c), word, pos, d+1));
        return root;
    }

    public List<Integer> get(String word) {
        Node node = get(root, word, 0);
        if (node != null) return node.positions;
        return Collections.emptyList();
    }

    private Node get(Node x, String word, int d) {
        if (x == null) return null;
        if (d == word.length()) {
            return x;
        }
        char c = Character.toLowerCase(word.charAt(d));
        return get(x.children.get(c), word, d+1);
    }
}
