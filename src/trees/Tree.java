package trees;


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
}
