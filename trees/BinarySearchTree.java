package trees;
import static trees.Tree.Node;

/**
 * @author rpurigella
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        BST bst = new BST();
        Node root = null;
        root = bst.insert(root, 20);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 15);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 17);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 13);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 25);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 35);
        InPrePost.inOrder(root);
        System.out.println();
        root = bst.insert(root, 22);
        InPrePost.inOrder(root);
        System.out.println();

        System.out.println("Searching for 100, no root: " + bst.search(null, 100));
        System.out.println("Searching for 20: " + bst.search(root, 20));
        System.out.println("Searching for 12: " + bst.search(root, 12));
        System.out.println("Searching for 44: " + bst.search(root, 44));
        System.out.println("Searching for 24: " + bst.search(root, 24));
        System.out.println("Searching for 15: " + bst.search(root, 15));
        System.out.println("Searching for 35: " + bst.search(root, 35));

    }

    static class BST {

        Node insert(Node root, int val) {
            if (root == null) {
                return new Node(val);
            }
            if (val < root.val) {
                root.left = insert(root.left, val);
            } else {
                root.right = insert(root.right, val);
            }
            return root;
        }

        Node search(Node root, int val) {
            if (root == null) {
                return null;
            } else if (root.val == val) {
                return root;
            } else if (val < root.val) {
                return search(root.left, val);
            } else {
                return search(root.right, val);
            }
        }

        private Node deleteMin(Node x) {
            if (x.left == null) return x.right;
            x.left = deleteMin(x.left);
            return x;
        }


        private Node delete(Node root, int val) {
            if (root == null) return null;

            if      (val < root.val) root.left  = delete(root.left,  val);
            else if (val > root.val) root.right = delete(root.right, val);
            else {
                if (root.right == null) return root.left;
                if (root.left  == null) return root.right;
                Node t = root;
                root = min(t.right);
                root.right = deleteMin(t.right);
                root.left = t.left;
            }
            return root;
        }

        private Node min(Node x) {
            if (x.left == null) return x;
            else                return min(x.left);
        }

    }
}
