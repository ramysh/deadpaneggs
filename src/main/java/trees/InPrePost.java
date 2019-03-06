package trees;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * @author rpurigella
 */
public class InPrePost {

    public static void main(String[] args) {
        TreeNode root = getTree();

        inOrder(root);
        System.out.println();
        inOrderNR(root);
        System.out.println();
        InorderIterator inorderIterator = new InorderIterator(root);
        while(inorderIterator.hasNext()) {
            System.out.print(inorderIterator.next() + " ");
        }

        System.out.println();
        System.out.println();

        preOrder(root);
        System.out.println();
        preOrderNR(root);

        System.out.println();
        System.out.println();

        postOrder(root);
        System.out.println();
        postOrderNR(root);
    }

    public static TreeNode getTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node20 = new TreeNode(20);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node5.left = node7;
        node7.left = node9;
        node7.right = node20;
        node9.right = node11;
        node6.right = node8;
        node8.left = node10;
        return node1;
    }

    static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    static void inOrderNR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                System.out.print(node.val + " ");
                root = node.right;
            }
        }
    }

    static void preOrderNR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                if (root.right != null) stack.push(root.right);
                System.out.print(root.val + " ");
                root = root.left;
            } else {
                root = stack.pop();
            }
        }
    }

    static void postOrderNR(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode node = stack.pop();
                if (node.right != null && prev != node.right) {
                    stack.push(node);
                    root = node.right;
                } else {
                    System.out.print(node.val + " ");
                    prev = node;
                }
            }
        }
    }

    static class InorderIterator implements Iterator<Integer> {
        Stack<TreeNode> stack ;
        TreeNode root;

        public InorderIterator(TreeNode root) {
            this.root = root;
            stack = new Stack<>();

        }

        @Override
        public boolean hasNext() {
            return root != null || !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode node = stack.pop();
            root = node.right;
            return node.val;
        }
    }

}
