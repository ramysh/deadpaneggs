package trees;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author rpurigella
 */
public class Tree {

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node(int value) {
            this.val = value;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("val=").append(val);
            sb.append(", left=").append(left);
            sb.append(", right=").append(right);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node7.right = node10;
        node10.left = node11;
        node10.right = node12;
        String s = serialize(node1);
        Node root = deserialize(s);
        System.out.println(s);
    }

    public static String serialize(Node root){
        if (root == null) return null;
        StringBuilder sb = new StringBuilder();
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (true) {
            int s = q.size();
            if (s == 0) break;
            while (s > 0) {
                Node node = q.remove();
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

    public static Node deserialize(String s) {
        if (s == null || s.length() < 5) return null;
        String[] st = s.split(",");
        Node root = new Node(Integer.parseInt(st[0]));
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty()) {
            Node n = queue.remove();
            if (n == null) continue;
            if (st[i].equals("#")) {
                n.left = null;
            } else {
                n.left = new Node(Integer.parseInt(st[i]));
            }
            if (st[i+1].equals("#")) {
                n.left = null;
            } else {
                n.right = new Node(Integer.parseInt(st[i+1]));
            }
            queue.add(n.left);
            queue.add(n.right);
            i = i+2;
        }
        return root;
    }

}
