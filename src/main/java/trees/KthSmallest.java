package trees;

/**
 * Created by rpurigella on 9/23/18.
 */
public class KthSmallest {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(13);
        TreeNode node9 = new TreeNode(15);
        TreeNode node10 = new TreeNode(35);
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

    static Integer kthSmallest(TreeNode root, int k) {
        int[] counter = new int[1];
        counter[0] = k;
        return kthSmallest(root, counter);
    }

    static TreeNode build_balanced_bst(int[] a)
    {
        if (a == null)  return null;
        TreeNode node = build_balanced_bst(a, 0, a.length);
        return node;
    }

    static TreeNode build_balanced_bst(int[] a, int l, int r) {
        if (l > r) return null;
        int pos = ((r - l) / 2) + l;
        TreeNode node = new TreeNode(a[pos]);
        node.left = build_balanced_bst(a, l, pos - 1);
        node.right = build_balanced_bst(a, pos + 1, r);
        return node;
    }

    static Integer kthSmallest(TreeNode root, int[] counter) {
        if (root == null) return null;
        Integer val = kthSmallest(root.right, counter);
        if (val != null) return val;
        if (counter[0] == 0) return root.val;
        counter[0] = counter[0] - 1;
        return kthSmallest(root.left, counter);
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }
}
