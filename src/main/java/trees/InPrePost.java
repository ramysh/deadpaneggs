package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static trees.Tree.Node;

/**
 * @author rpurigella
 */
public class InPrePost {

    public static void main(String[] args) {
        //Node root = Tree.deserialize("20,10,30,5,15,25,35,#,#,#,#,#,#,#,#");
        Node root = getTree();
        inOrder(root);
        System.out.println();
        preOrder(root);
        System.out.println();
        postOrder(root);
    }

    private static Node getTree() {
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

    public static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    static void inOrderNR(Node root) {
        Stack<Node> s = new Stack<>();
        while (root != null || !s.isEmpty()) {
            if (root != null) {
                s.push(root);
                root = root.left;
                continue;
            }
            Node node = s.pop();
            System.out.print(node.val + " ");
            root = node.right;
        }
    }

    static void preOrderNR(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            Node node = s.pop();
            System.out.print(node.val + " ");
            if (node.right != null) s.push(node.right);
            if (node.left != null) s.push(node.left);
        }
    }

    public static void preOrder(Node root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void postOrder(Node root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    static void postOrderNR(Node root) {
        if (root == null) return;
        Stack<Node> s = new Stack<>();
        s.push(root);
        Node prev = null;
        List<Node> list = new ArrayList<>();
        while (!s.isEmpty()) {
            Node curr = s.pop();
            if ((curr.left == null && curr.right == null) ||
                    beenVisited(curr, prev)) {
                list.add(curr);
                prev = curr;
                continue;
            }
            s.push(curr);
            if(curr.right != null) s.push(curr.right);
            if(curr.left != null) s.push(curr.left);
        }
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i).val + " ");
        }
        System.out.print(list.get(list.size()-1).val);
    }

    static boolean beenVisited(Node curr, Node prev) {
        if (prev == null) return false;
        if (curr.left != null && curr.left == prev) return true;
        if (curr.right != null && curr.right == prev) return true;
        return false;
    }



}
