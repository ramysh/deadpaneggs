package strings;

/**
 * Created by rpurigella on 11/11/18.
 */
public class RegexMatcher {

    static boolean matcher(String s, String pattern) {
        return helper(s, 0, pattern, 0);
    }

    static boolean helper(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) return true;
        if (i < s.length() && j == p.length()) return false;
        if (i == s.length() && j < p.length()) {
            int x = j+1;
            for (; x < p.length(); x = x+2){
                if (p.charAt(x) != '*') return false;
            }
            if (x == p.length()) return false;
            return true;
        }
        boolean isStar = false;
        char c = p.charAt(j);
        if (j < p.length()-1) {
            if (p.charAt(j+1) == '*') {
                isStar = true;
            }
        }
        if (!isStar) {
            if (c == '.') {
                return helper(s, i+1, p, j+1);
            } else {
                return s.charAt(i) == c && helper(s, i+1, p, j+1);
            }
        } else {
            if (c == '.') {
                return helper(s, i+1, p, j) || helper(s, i+1, p, j+2) || helper(s, i, p, j+2);
            } else {
                return helper(s, i, p, j+2) ||
                        (s.charAt(i) == c && helper(s, i+1, p, j)) ||
                        (s.charAt(i) == c && helper(s, i+1, p, j+2));

            }
        }
    }

    public static void main(String[] args) {
        System.out.println(matcher("abcdefghijklmnop", "a.*.*.*mnop"));
    }
}
