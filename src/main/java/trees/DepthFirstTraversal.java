package trees;

import java.util.Stack;

import static trees.Tree.Node;

/**
 * @author rpurigella
 */
public class DepthFirstTraversal {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        dftRecursive(node1);
        System.out.println();
        dftIterative(node1);
    }

    private static void dftRecursive(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        dftRecursive(root.left);
        dftRecursive(root.right);
    }

    private static void dftIterative(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.val + " ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
