package trees;

import static trees.Tree.Node;

/**
 * Created by rpurigella on 9/23/18.
 */
public class KthSmallest {

    public static void main(String[] args) {
        Node node1 = new Node(10);
        Node node2 = new Node(6);
        Node node3 = new Node(4);
        Node node4 = new Node(3);
        Node node5 = new Node(5);
        Node node6 = new Node(8);
        Node node7 = new Node(9);
        Node node8 = new Node(13);
        Node node9 = new Node(15);
        Node node10 = new Node(35);
        node1.left = node2;
        node1.right = node9;
        node2.left = node3;
        node2.right = node7;
        node3.left = node4;
        node3.right = node5;
        node7.left = node6;
        node9.left = node8;
        node9.right = node10;
        int[] a = {8,10,12,15,16,20,15};
        build_balanced_bst(a);


    }

    static Integer kthSmallest(Node root, int k) {
        int[] counter = new int[1];
        counter[0] = k;
        return kthSmallest(root, counter);
    }

    static Node build_balanced_bst(int[] a)
    {
        if (a == null)  return null;
        Node node = build_balanced_bst(a, 0, a.length);
        return node;
    }

    static Node build_balanced_bst(int[] a, int l, int r) {
        if (l > r) return null;
        int pos = ((r - l) / 2) + l;
        Node node = new Node(a[pos]);
        node.left = build_balanced_bst(a, l, pos - 1);
        node.right = build_balanced_bst(a, pos + 1, r);
        return node;
    }

    static Integer kthSmallest(Node root, int[] counter) {
        if (root == null) return null;
        Integer val = kthSmallest(root.right, counter);
        if (val != null) return val;
        if (counter[0] == 0) return root.val;
        counter[0] = counter[0] - 1;
        return kthSmallest(root.left, counter);
    }

    public static void inOrder(Node root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
}
