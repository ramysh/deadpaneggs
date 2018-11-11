package strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrieXYZ implements Iterable<String> {

    private static int R = 256;

    private static class Node {
        private boolean isString;
        private Node[] next = new Node[R];
    }

    private Node root;
    private int N = 0;

    public void add(String key) {
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (!x.isString) N++;
            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }

    public boolean contains(String key) {
        Node node = get(root, key, 0);
        return node != null && node.isString;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void remove(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.isString) {
                N--;
                x.isString = false;
            }
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.isString) return x;
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) return x;
        }
        return null;
    }

    @Override
    public Iterator<String> iterator() {
        return keys().iterator();
    }

    private Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> items = new ArrayList<>();
        Node start = get(root, prefix, 0);
        collect(start, new StringBuilder(prefix), items);
        return items;

    }

    private void collect(Node x, StringBuilder prefix, List<String> items) {
        if (x == null) return;
        if (x.isString) items.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            prefix.append(c);
            collect(x.next[c], prefix, items);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    public Iterable<String> keysMatchingDot(String pattern) {
        List<String> items = new ArrayList<>();
        collect(root, pattern, new StringBuilder(), items);
        return items;
    }

    private void collect(Node x, String pattern, StringBuilder prefix, List<String> items) {
        if (x == null) return;
        int d = prefix.length();
        if (pattern.length() == d) {
            if (x.isString) items.add(prefix.toString());
            return;
        }
        char c = pattern.charAt(d);
        if (c != '.') {
            prefix.append(c);
            collect(x.next[c], pattern, prefix, items);
            prefix.deleteCharAt(prefix.length() - 1);
        } else {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.next[ch], pattern, prefix, items);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        TrieXYZ set = new TrieXYZ();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            set.add(key);
        }

        StdOut.println("keys:");
        for (String key : set) {
            StdOut.println(key);
        }
        StdOut.println();
        StdOut.println("Size =" + set.size());
        StdOut.println();

        StdOut.println("keysWithPrefix(\"shor\"):");
        for (String s : set.keysWithPrefix("shor"))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("keysMatchingDot(\"a.a.\"):");
        for (String s : set.keysMatchingDot("a.a."))
            StdOut.println(s);
        StdOut.println();
    }
}
