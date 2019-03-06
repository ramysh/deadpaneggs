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
                return helper(s, i+1, p, j) || helper(s, i, p, j+2);
            } else {
                return helper(s, i, p, j+2) ||
                        (s.charAt(i) == c && helper(s, i+1, p, j));

            }
        }
    }

    public static void main(String[] args) {
        System.out.println(pattern_matcher("abcdgggg", "a.c.*.*gg*"));
    }

    public static boolean pattern_matcher(String text,String pattern) {

        int pLen = pattern.length();

        //This will store true at location i, if simplifiedPattern has "*" character at location i
        boolean isStarChar[] = new boolean[pLen];
        String simplifiedPattern = simplifyPattern(pattern, isStarChar, pLen);
        return matcher(simplifiedPattern, text, isStarChar);

    }

    public static String simplifyPattern(String pattern, boolean isStarChar[], int pLen) {

        int ind = 0;
        char simplifiedChars[] = new char[pattern.length()];

        for(int i = 0 ; i < pLen ; ) {

            simplifiedChars[ind] = pattern.charAt(i);

            if(i + 1 < pLen && pattern.charAt(i+1) == '*') {


                if(ind - 1 >= 0 && isStarChar[ind-1] && simplifiedChars[ind-1] == simplifiedChars[ind]) {
                    ind--;
                } else {
                    isStarChar[ind] = true;
                }

                i = i + 2;
            } else {
                i++;
            }
            ind++;
        }

        return new String(simplifiedChars , 0 , ind);
    }


    public static boolean matcher(String pattern, String text, boolean isStarChar[]) {

        int pLen = pattern.length(), tLen = text.length();

        // If both strings are null, then return true.

        if(pLen == 0 && tLen == 0) {
            return true;
        }

        // If pattern is null but text is not, then return false.

        if(pLen == 0) {
            return false;
        }

        // dp[i][j] is true, if first i characters in given string matches the first j characters of pattern.

        boolean dp[][] = new boolean[tLen + 1][pLen + 1];
        dp[0][0] = true;
        if(isStarChar[0]) {
            dp[0][1] = true;
        }

        // If the given text is null,
        // then it will be true till the all the characters in simplified string have "*".

        for(int pInd = 1 ; pInd < pLen ; pInd++) {

            if(dp[0][pInd] && isStarChar[pInd]) {
                dp[0][pInd+1] = true;
                continue;
            }

            break;
        }

        for(int tInd = 0 ; tInd < tLen ; tInd++) {

            for(int pInd = 0 ; pInd < pLen ; pInd++) {

                /* Note : First i character of text string means substring of text string
                 *        with [0,i-1] positions.
                 *        same for pattern string.
                 */

                // Case 1, explained in editorial

                if(dp[tInd + 1][pInd] && isStarChar[pInd]) {
                    dp[tInd + 1][pInd + 1] = true;
                    continue;
                }

                // Case 2, explained in editorial

                if(
                        dp[tInd][pInd]
                                && (pattern.charAt(pInd) == '.' || pattern.charAt(pInd) == text.charAt(tInd))
                        ) {
                    dp[tInd + 1][pInd + 1] = true;
                    continue;
                }

                // Case 3, explained in editorial

                if(dp[tInd][pInd + 1] && isStarChar[pInd] &&
                        (pattern.charAt(pInd) == '.' || pattern.charAt(pInd) == text.charAt(tInd))) {
                    dp[tInd + 1][pInd + 1] = true;
                    continue;
                }
            }
        }

        return dp[tLen][pLen];

    }
}
