package trees;

import java.util.*;

/**
 * @author rpurigella
 */
public class TreeLink {

    static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        public TreeLinkNode(int x) {
            val = x;
        }
    }

    static class HeadAndTail {
        TreeLinkNode head;
        TreeLinkNode tail;
    }

    public static void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode curr = root;
        HeadAndTail headAndTail = new HeadAndTail();
        while (curr != null) {
            while (curr != null) {
                add(curr.left, headAndTail);
                add(curr.right, headAndTail);
                curr = curr.next;
            }
            curr = headAndTail.head;
            headAndTail.head = null;
            headAndTail.tail = null;
        }
    }

    private static void add(TreeLinkNode x, HeadAndTail headAndTail) {
        if (x == null) return;
        if (headAndTail.head == null) {
            headAndTail.head = x;
            headAndTail.tail = x;
        } else {
            headAndTail.tail.next = x;
            headAndTail.tail = x;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode root = new TreeLinkNode(1);
        root.left = new TreeLinkNode(2);
        root.right = new TreeLinkNode(3);
        connect(root);
    }
}

