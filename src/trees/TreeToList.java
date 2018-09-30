package trees;

import static trees.Tree.Node;

/**
 * Created by rpurigella on 9/27/18.
 */
public class TreeToList {


    public static void main(String[] args) {
        Node node1 = new Node(20);
        Node node2 = new Node(10);
        Node node3 = new Node(30);
        Node node4 = new Node(5);
        Node node5 = new Node(15);
        Node node6 = new Node(25);
        Node node7 = new Node(35);
        Node node8 = new Node(2);
        Node node9 = new Node(6);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node4.left = node8;
        node4.right = node9;
        Result result =  treeToList(node1);
        if (result == null) {
            System.out.println("null tree");
            return;
        }
        result.min.left = result.max;
        result.max.right = result.min;
        System.out.print(result.min.val);
    }

    static class Result {
        Node min;
        Node max;

        public Result(Node min, Node max) {
            this.min = min;
            this.max = max;
        }
    }

    static Result treeToList(Node node) {
        if (node == null) return null;

        Node min = null;
        Node max = null;

        Result leftR = treeToList(node.left);

        if (leftR != null) {
            leftR.max.right = node;
            node.left = leftR.max;
            min = leftR.min;
        }

        Result rightR = treeToList(node.right);
        if (rightR != null) {
            node.right = rightR.min;
            rightR.min.left = node;
            max = rightR.max;
        }

        if (min == null) min = node;
        if (max == null) max = node;

        return new Result(min, max);
    }
}
