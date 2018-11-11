package trees;

import static trees.Tree.Node;

/**
 * Created by rpurigella on 9/14/18.
 */
public class Unival {

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(5);
        Node node3 = new Node(5);
        Node node4 = new Node(5);
        Node node5 = new Node(5);
        Node node6 = new Node(5);
        //BoggleNode node7 = new BoggleNode(6);
        /*BoggleNode node8 = new BoggleNode(8);
        BoggleNode node9 = new BoggleNode(9);
        BoggleNode node10 = new BoggleNode(10);
        BoggleNode node11 = new BoggleNode(11);
        BoggleNode node20 = new BoggleNode(20);*/
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;


        Result result = isUnival(node1, 5);
        System.out.println(result.getCount());
    }

    static int findSingleValueTrees(Node n) {
        return 0;
    }

    /* TODO redo solution with val removed. Can be different values in each subtree */
    public static Result isUnival(Node root, int val) {
        if (root == null) return new Result(0, true);

        if (root.left == null && root.right == null) {
            if (root.val == val) {
                return new Result(1, true);
            } else {
                return new Result(0, false);
            }
        }

        Result resL = Result.init();
        Result resR = Result.init();

        if (root.left != null) {
            resL = isUnival(root.left, val);
        }

        if (root.right != null) {
            resR = isUnival(root.right, val);
        }

        if (root.val == val) {
            if (resL.isSubtreeUnival() && resR.isSubtreeUnival()) {
                return new Result(1 + resL.getCount() + resR.getCount(), true);
            } else {
                return new Result(resL.getCount() + resR.getCount(), false);
            }
        } else {
            return new Result(resL.getCount() + resR.getCount(), false);
        }

    }

    static class Result {
        private int count;
        private boolean isSubtreeUnival;

        public int getCount() {
            return count;
        }

        public boolean isSubtreeUnival() {
            return isSubtreeUnival;
        }

        public Result(int count, boolean isSubtreeUnival) {
            this.count = count;
            this.isSubtreeUnival = isSubtreeUnival;
        }

        public static Result init() {
            return new Result(0, true);
        }
    }
}
