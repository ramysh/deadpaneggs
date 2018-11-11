package trees;

import java.util.Stack;
import java.util.StringTokenizer;

import static trees.Tree.Node;


/**
 * @author rpurigella
 */
public class RootToLeafPaths {

    public static void main(String[] args){
        Node n = createTree("40 10 5 # # 15 # # 45 22 # # 65 # #");
        //n = createTree();
        //printInorder(n);
        printAllPaths(n);

    }

    static Node createTree(String data) {
        if (data == null || data.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(data, " ");
        return deserialize(st);
    }

    static Node deserialize(StringTokenizer st) {
        if (!st.hasMoreTokens())
            return null;
        String s = st.nextToken();
        if (s.equals("#"))
            return null;
        Node root = new Node(Integer.valueOf(s));
        root.left = deserialize(st);
        root.right = deserialize(st);

        return root;
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
