import java.math.BigInteger;
import java.util.*;

/**
 * @author rpurigella
 */
public class Sandbox {

    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.empty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append('c');
        sb.append('a');
        sb.append('b');
        System.out.println(sb.reverse());
        System.out.println(longestValidParentheses("(()(()("));
    }
}

