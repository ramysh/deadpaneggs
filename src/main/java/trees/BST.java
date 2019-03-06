package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author rpurigella
 */
public class BST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        int n;
        Node left;
        Node right;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    private Node root;


    public void add(Key key, Value value) {
        root = add(root, key, value);
    }

    private Node add(Node root, Key key, Value value) {
        if (root == null) return new Node(key, value, 1);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            root.value = value;
        } else if (cmp > 0) {
            root.right = add(root.right, key, value);
        } else {
            root.left = add(root.left, key, value);
        }
        root.n = size(root.left) + size(root.right) + 1;
        return root;
    }

    public Value get(Key key) {
        Node node = get(root, key);
        return (node == null) ? null : node.value;
    }

    private Node get(Node root, Key key) {
        if (root == null) return null;
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        } else if (cmp > 0) {
            return get(root.right, key);
        }else {
            return get(root.left, key);
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        return node.n;
    }

    public Iterable<Key> inOrder() {
        List<Key> keys = new ArrayList<>();
        inOrder(root, keys);
        return keys;
    }

    private void inOrder(Node root, List<Key> keys) {
        if (root == null) return;
        inOrder(root.left, keys);
        keys.add(root.key);
        inOrder(root.right, keys);
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rint = random.nextInt(50);
            System.out.print(rint + " ");
            bst.add(rint, "" + rint);
        }
        System.out.println();

        for (int i : bst.inOrder()) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < 50; i++) {
            String value = bst.get(i);
            if (value != null) System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("ST size = " + bst.size());
    }
}

