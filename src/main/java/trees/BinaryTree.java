package trees;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author rpurigella
 */
public class BinaryTree {

    public static String serialize(TreeNode root){
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (true) {
            int s = q.size();
            if (s == 0) break;
            while (s > 0) {
                TreeNode node = q.remove();
                if (node != null) {
                    sb.append(node.val);
                    sb.append(",");
                    q.add(node.left);
                    q.add(node.right);
                } else {
                    sb.append("#,");
                }
                s--;
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static TreeNode deserialize(String s) {
        if (s == null || s.length() < 5) return null;
        String[] st = s.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(st[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode n = queue.remove();
            if (n == null) continue;
            if (st[i].equals("#")) {
                n.left = null;
            } else {
                n.left = new TreeNode(Integer.parseInt(st[i]));
            }
            if (st[i+1].equals("#")) {
                n.right = null;
            } else {
                n.right = new TreeNode(Integer.parseInt(st[i+1]));
            }
            queue.add(n.left);
            queue.add(n.right);
            i = i+2;
        }
        return root;
    }

    public static TreeNode clone(TreeNode root) {
        if (root == null) return null;
        TreeNode node = new TreeNode(root.val);
        node.left = clone(root.left);
        node.right = clone(root.right);
        return node;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);
        TreeNode node10 = new TreeNode(10);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node7.right = node10;
        node10.left = node11;
        node10.right = node12;
        String s1 = serialize(node1);
        TreeNode root = deserialize(s1);
        System.out.println(s1);
        TreeNode root2 = clone(root);
        String s2 = serialize(root2);
        System.out.println(s1.equals(s2));
    }
}
