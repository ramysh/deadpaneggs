package trees;

/**
 * Created by rpurigella on 9/27/18.
 */
public class TreeToList {


    public static void main(String[] args) {
        TreeNode root = InPrePost.getTree();
        root = treeToListMain(root);
        TreeNode start = root;
        int count = 0;
        while(true) {
            if (start == root) count++;
            if (count > 1 ) break;
            System.out.print(start.val + " ");
            start = start.right;
        }

        System.out.println();
        count = 0;
        while(true) {
            if (start == root) count++;
            if (count > 1 ) break;
            System.out.print(start.val + " ");
            start = start.left;
        }
        System.out.println();

    }

    static TreeNode treeToListMain(TreeNode root) {
        if (root == null) return null;
        Result result = treeToList(root);
        result.head.left = result.tail;
        result.tail.right = result.head;
        return result.head;
    }

    static class Result {
        TreeNode head;
        TreeNode tail;

        public Result(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    static Result treeToList(TreeNode x) {
        if (x == null) return new Result(null, null);
        Result leftRes = treeToList(x.left);
        Result rightRes = treeToList(x.right);
        if (leftRes.tail != null) {
            leftRes.tail.right = x;
        }
        x.left = leftRes.tail;
        if (rightRes.head != null) {
            rightRes.head.left = x;
        }
        x.right = rightRes.head;
        return new Result(leftRes.head == null ? x : leftRes.head,
                        rightRes.tail == null ? x : rightRes.tail);

    }
}
