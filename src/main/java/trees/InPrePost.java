package trees;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.StringStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static trees.Tree.Node;

/**
 * @author rpurigella
 */
public class InPrePost {

    public static void main(String[] args) {
        Node root = getTree();

        inOrder(root);
        System.out.println();
        inOrderNR(root);
        System.out.println();

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

    static Node getTree() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node20 = new Node(20);
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

    static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    static void inOrderNR(Node root) {
        Stack<Node> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                Node node = stack.pop();
                System.out.print(node.val + " ");
                root = node.right;
            }
        }
    }

    static void preOrderNR(Node root) {
        Stack<Node> stack = new Stack<>();
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

    static void postOrderNR(Node root) {
        Stack<Node> stack = new Stack<>();
        Node prev = null;
        while(root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                Node node = stack.pop();
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

}
