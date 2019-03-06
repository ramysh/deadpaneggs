package trees;

/**
 * Created by rpurigella on 9/29/18.
 */
public class UpsideDown {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;
        node4.right = node7;
        upside_down(node1);

    }

    static TreeNode upside_down(TreeNode root) {
        if (root == null) return null;
        Result result =  upside_down_recursive(root);
        return result.root;
    }

    static Result upside_down_recursive(TreeNode node) {
        if (node.left == null) return new Result(node, node);
        Result leftRes = upside_down_recursive(node.left);
        leftRes.result.left = node.right;
        leftRes.result.right = node;
        node.left = null;
        node.right = null;
        return new Result(leftRes.root, node);
    }

    static class Result {
        TreeNode root;
        TreeNode result;

        public Result(TreeNode root, TreeNode result) {
            this.root = root;
            this.result = result;
        }
    }
}


