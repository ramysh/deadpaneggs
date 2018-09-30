package trees;

import static trees.Tree.Node;

/**
 * Created by rpurigella on 9/29/18.
 */
public class UpsideDown {

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
        node4.left = node6;
        node4.right = node7;
        upside_down(node1);

    }

    static Node upside_down(Node root) {
        if (root == null) return null;
        Result result =  upside_down_recursive(root);
        return result.root;
    }

    static Result upside_down_recursive(Node node) {
        if (node.left == null) return new Result(node, node);
        Result leftRes = upside_down_recursive(node.left);
        leftRes.result.left = node.right;
        leftRes.result.right = node;
        node.left = null;
        node.right = null;
        return new Result(leftRes.root, node);
    }

    static class Result {
        Node root;
        Node result;

        public Result(Node root, Node result) {
            this.root = root;
            this.result = result;
        }
    }
}


