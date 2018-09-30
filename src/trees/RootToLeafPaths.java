package trees;

import java.util.Stack;
import static trees.Tree.Node;


/**
 * @author rpurigella
 */
public class RootToLeafPaths {

    public static void main(String[] args){
        Node n = null;
        //n = createTree();
        //printInorder(n);
        printAllPaths(n);

    }

    static void printAllPaths(Node root) {
        printAllPaths(root, new Stack<Integer>());
    }

    static void printAllPaths(Node root, Stack<Integer> stack) {
        if (root == null) return;
        stack.push(root.val);
        if (root.left == null && root.right == null) {
            print(stack);
        }
        printAllPaths(root.left, stack);
        printAllPaths(root.right, stack);
        stack.pop();
    }

    static void print(Stack<Integer> stack) {
        int j = 0;
        for (int i : stack) {
            if (j != 0) {
                System.out.print(" ");
                j++;
            }
            System.out.print(i);
        }
        System.out.println();
    }

}
