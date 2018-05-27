package com.ik.trees.hw;

import com.ik.trees.util.Tree;

import static com.ik.trees.util.Tree.Node;

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
        System.out.println(findMin(null, null, null));
        System.out.println(findMin(null, 1, 2));
        System.out.println(findMin(null, null, 2));
        System.out.println(findMin(null, 2, null));
        System.out.println(findMin(1, 2, 4));

        Tree.Node n = Tree.createTree("40 10 5 # # 15 # # 45 44 # # 65 # #");
        System.out.println(isValidBST(n));

    }

    static boolean isBST(Node root) {
        Result result = isBSTUtil(root);
        return result.ans;
    }

    static Result isBSTUtil(Node root) {
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

    public static boolean isValidBST(Node root) {
        TreeNodeWrapper prev = new TreeNodeWrapper(null);
        return isValidBST(root, prev);
    }

    private static boolean isValidBST(Node root, TreeNodeWrapper prev) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left, prev)) {
            return false;
        }

        if (prev.node != null && root.val <= prev.node.val) {
            return false;
        }
        prev.node = root;
        return isValidBST(root.right, prev);
    }

    /**
     * A wrapper class around TreeNode to retain the value across recursive call
     */
    private static class TreeNodeWrapper {
        Node node;

        TreeNodeWrapper(Node node) {
            this.node = node;
        }
    }
}
