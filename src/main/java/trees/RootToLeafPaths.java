package trees;

import java.util.Stack;

import static trees.Tree.Node;


/**
 * @author rpurigella
 */
public class RootToLeafPaths {

    static void printPathsUsingStringBuilder(Node root) {
        StringBuilder sb = new StringBuilder();
        printPathsUsingStringBuilder(root, sb);
    }

    static void printPathsUsingStringBuilder(Node root, StringBuilder sb) {
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

    static void printPathsUsingStack(Node root) {
        Stack<Node> stack = new Stack<>();
        printPathsUsingStack(root, stack);
    }

    static void printPathsUsingStack(Node root, Stack<Node> stack) {
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

    static void print(Stack<Node> stack) {
        StringBuilder sb = new StringBuilder();
        for (Node node : stack) {
            sb.append(node.val);
            sb.append("->");
        }
        sb.append("null");
        System.out.println(sb.toString());
    }


    public static void main(String[] args){
        Node root = Tree.deserialize("100,2,3,467,5,6,7,8,#,#,9,#,10,11,12,#,#,#,#,#,#,#,#,#,#");
        printPathsUsingStringBuilder(root);
        printPathsUsingStack(root);
    }
}
