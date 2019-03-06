package trees;

import java.util.Stack;


/**
 * @author rpurigella
 */
public class RootToLeafPaths {

    static void printPathsUsingStringBuilder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        printPathsUsingStringBuilder(root, sb);
    }

    static void printPathsUsingStringBuilder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        int n = sb.length();
        sb.append(root.val);
        sb.append("->");
        if (root.left == null && root.right == null) {
            sb.append("null");
            System.out.println(sb.toString());
        } else {
            printPathsUsingStringBuilder(root.left, sb);
            printPathsUsingStringBuilder(root.right, sb);
        }
        sb.delete(n, sb.length());
    }

    static void printPathsUsingStack(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        printPathsUsingStack(root, stack);
    }

    static void printPathsUsingStack(TreeNode root, Stack<TreeNode> stack) {
        if (root == null) return;
        stack.push(root);
        if (root.left == null && root.right == null) {
            print(stack);
        } else {
            printPathsUsingStack(root.left, stack);
            printPathsUsingStack(root.right, stack);
        }
        stack.pop();
    }

    static void print(Stack<TreeNode> stack) {
        StringBuilder sb = new StringBuilder();
        for (TreeNode node : stack) {
            sb.append(node.val);
            sb.append("->");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }


    public static void main(String[] args){
        TreeNode root = BinaryTree.deserialize("100,2,3,467,5,6,7,8,#,#,9,#,10,11,12,#,#,#,#,#,#,#,#,#,#");
        printPathsUsingStringBuilder(root);
        printPathsUsingStack(root);
    }
}
