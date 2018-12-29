package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rpurigella
 */
public class WellFormedBrackets {

    public static void main(String[] args) {
        find_all_well_formed_brackets(3);
    }

    static String[] find_all_well_formed_brackets(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        solve(sb, 1, 0, n, result);
        return result.toArray(new String[result.size()]);
    }

    static void solve(StringBuilder sb, int left, int right, int n, List<String> result) {
        if (sb.length() == 2*n) {
            result.add(sb.toString());
        }
        if (left < n) {
            sb.append('(');
            solve(sb, left+1, right, n, result);
            sb.deleteCharAt(sb.length()-1);
        }
        if (right < n && right < left) {
            sb.append(')');
            solve(sb, left, right+1, n, result);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
