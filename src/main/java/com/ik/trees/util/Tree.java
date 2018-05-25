package com.ik.trees.util;


import java.util.StringTokenizer;

/**
 * @author rpurigella
 */
public class Tree {

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int value) {
            this.val = value;
        }
    }


    public static Node createTree(String data) {
        if (data == null || data.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(data, " ");
        return deserialize(st);
    }

    public static Node deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens())
            return null;
        String s = st.nextToken();
        if (s.equals("#"))
            return null;
        Node root = new Node(Integer.valueOf(s));
        root.left = deserialize(st);
        root.right = deserialize(st);

        return root;
    }

    public static void printInorder(Node root) {
        if(root == null) return;
        printInorder(root.left);
        System.out.print(root.val+" ");
        printInorder(root.right);
    }
}
