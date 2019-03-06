package trees;

/**
 * @author rpurigella
 */
public class IsBST {

    static class Result {
        Integer min;
        Integer max;
        boolean ans;

        public Result(Integer min, Integer max, boolean ans) {
            this.min = min;
            this.max = max;
            this.ans = ans;
        }
    }

    public static void main(String[] args) {
        TreeNode root = BinaryTree.deserialize("20,10,30,5,15,19,35,#,#,#,#,#,#,#,#");
        System.out.println(isBST(root));
    }

    /*
     * Using result object
     */
    static boolean isBST(TreeNode root) {
        Result result = isBSTUtil(root);
        return result.ans;
    }

    static Result isBSTUtil(TreeNode root) {
        if (root == null) {
            return new Result(null, null, true);
        }
        Result resL = isBSTUtil(root.left);
        if (!resL.ans) {
            return new Result(null, null, false);
        }
        Result resR = isBSTUtil(root.right);
        if (!resR.ans) {
            return new Result(null, null, false);
        }

        if (root.left != null) {
            if (resL.max >= root.val || root.left.val >= root.val) {
                return new Result(null, null, false);
            }
        }
        if (root.right != null) {
            if (resR.min <= root.val || root.right.val <= root.val) {
                return new Result(null, null, false);
            }
        }

        Integer min = findMin(resL.min, resR.min, root.val);
        Integer max = findMax(resL.max, resR.max, root.val);
        return new Result(min, max, true);
    }

    static Integer findMin(Integer one, Integer two, Integer three) {
        Integer min = null;
        if (one != null) {
            min = one;
        }
        if (min == null) {
            min = two;
        } else if (two != null && two < min) {
            min = two;
        }
        if (min == null) {
            min = three;
        } else if (three != null && three < min) {
            min = three;
        }
        return min;
    }

    static Integer findMax(Integer one, Integer two, Integer three) {
        Integer min = null;
        if (one != null) {
            min = one;
        }
        if (min == null) {
            min = two;
        } else if (two != null && two > min) {
            min = two;
        }
        if (min == null) {
            min = three;
        } else if (three != null && three > min) {
            min = three;
        }
        return min;
    }
}
