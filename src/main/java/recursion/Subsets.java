package recursion;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author rpurigella
 */
public class Subsets {

    public static void main(String[] args) {
        generate_all_subsets("xyzw");
    }

    static String[] generate_all_subsets(String s) {
        List<String> list = new ArrayList<>();
        char[] ssf = new char[s.length()];
        subsets(s, 0, ssf, 0, list);
        return list.toArray(new String[list.size()]);
    }

    static void subsets(String s, int i, char[] ssf, int j, List<String> list) {
        if (i == s.length()) {
            list.add(new String(ssf, 0, j));
            return;
        }
        subsets(s, i+1, ssf, j, list);
        ssf[j] = s.charAt(i);
        subsets(s, i+1, ssf, j+1, list);
    }
}