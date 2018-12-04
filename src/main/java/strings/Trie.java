package strings;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class Trie implements Iterable<String> {

    private static int R = 256;
    private int N = 0;

    private static class Node {
        boolean isString;
        Node[] next = new Node[R];
    }

    private Node root;

    /*
     * Add a String to the set
     */
    public void add(String key) {
        root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            if (!x.isString)  N++;
            x.isString = true;
            return x;

        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, d + 1);
        return x;
    }


    /*
     * Tells if a word is in the set
     */
    public boolean contains(String key) {
        Node node = get(root, key, 0);
        if (node != null && node.isString) return true;
        return false;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    /*
     * Removes a word from the set if present
     */
    public void delete(String s) {
        root = delete(root, s, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            if (x.isString) N--;
            x.isString = false;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.isString) return x;
        for (char i = 0; i < R; i++) {
            if (x.next[i] != null) return x;
        }
        return null;
    }

    /*
     * Returns the size of the set
     */
    public int size() {
        return N;
    }

    /*
     * Returns all the keys in the set
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    private void collect(Node x, StringBuilder sb, List<String> keys) {
        if (x == null) return;
        if (x.isString) {
            keys.add(sb.toString());
        }
        for (char c = 0; c < R; c++) {
            sb.append(c);
            collect(x.next[c], sb, keys);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        StringBuilder sb = new StringBuilder(prefix);
        collect(get(root, prefix, 0), sb, keys);
        return keys;
    }

    /*
     * All keys where . in the pattern matches any character
     */
    public Iterable<String> keysThatMatch(String pattern) {
        List<String> keys = new ArrayList<>();
        collect(root, pattern, new StringBuilder(), keys);
        return keys;
    }

    private void collect(Node x, String pattern, StringBuilder sb, List<String> keys) {
        if (x == null) return;
        int d = sb.length();
        if (d == pattern.length()) {
            if (x.isString) keys.add(sb.toString());
            return;
        }
        char c = pattern.charAt(d);
        if (c != '.') {
            sb.append(c);
            collect(x.next[c], pattern, sb, keys);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            for (char i = 0; i < R; i++) {
                sb.append(i);
                collect(x.next[i], pattern, sb, keys);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    /*
     * Returns the string in the set that is the longest prefix of query
     */
    public String longestPrefixOf(String query) {
        int d = longestPrefixOf(root, query, -1, 0);
        return (d == -1) ? null : query.substring(0, d);
    }

    private int longestPrefixOf(Node x, String query, int length, int d) {
        if (x == null) return length;
        if (x.isString)length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, length, d+1);
    }

    /*
     * . = any one char
     * ? = zero or any 1 char
     * * = zero and 1 or more of any char
     */
    public Iterable<String> matcher(String pattern) {
        Set<String> keys = new TreeSet<>();
        collect(root, pattern, new StringBuilder(), keys, 0);
        return keys;
    }

    private void collect(Node x, String pattern, StringBuilder prefix, Set<String> keys, int d) {
        if (x == null) return;
        if (d == pattern.length()) {
            if (x.isString) keys.add(prefix.toString());
            return;
        }
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char i = 0; i < R; i++) {
                prefix.append(c);
                collect(x.next[c], pattern, prefix, keys, d+1);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else if (c == '?') {
            collect(x, pattern, prefix, keys, d+1);
            for (char i = 0; i < R; i++) {
                prefix.append(i);
                collect(x.next[i], pattern, prefix, keys, d+1);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else if (c == '*') {
            collect(x, pattern, prefix, keys, d+1);
            for (char i = 0; i < R; i++) {
                prefix.append(i);
                collect(x.next[i], pattern, prefix, keys, d);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        } else {
            prefix.append(c);
            collect(x.next[c], pattern, prefix, keys, d+1);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }


    @Override
    public Iterator<String> iterator() {
        return keys().iterator();
    }


    public static void main(String[] args) {
        Trie set = new Trie();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            set.add(key);
        }

        String lp = "shell";
        StdOut.println("LongestPrefixOf : " + lp + " = " + set.longestPrefixOf(lp));

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

        StdOut.println("keysThatMatch(\".....\"):");
        for (String s : set.keysThatMatch("....."))
            StdOut.println(s);
        StdOut.println();

        StdOut.println("matcher():");
        for (String s : set.matcher("?*"))
            StdOut.println(s);
        StdOut.println();
    }
}

