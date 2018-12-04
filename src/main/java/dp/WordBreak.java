package dp;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * https://leetcode.com/problems/word-break/
 * @author rpurigella
 */
public class WordBreak {
    public static void main(String[] args) {
        //List<String> dictionary = Arrays.asList("kick", "start", "kickstart", "is", "awe", "some", "awesome");
        //List<String> result = wordBreakMain(7, dictionary, "kickstartisawesome");
        List<String> dictionary = Arrays.asList("to", "do", "todo");
        //List<String> result = wordBreakMain(7, dictionary, "totodo");
        List<String> dpResult = solver(7, dictionary, "totodo");
        for (String word : dpResult) {
            System.out.println(word);
        }
    }

    public static List<String> solver(int dictionaryCount,List<String> dictionary, String txt) {
        final int M = txt.length();
        String[][] dp = new String[M+1][];

        dp[M] = new String[] { "" };
        Set<String> dict = new HashSet<>(dictionary);

        for (int i = M-1; i >= 0; i--) {
            List<String> out = new ArrayList<>();
            for (int j = i; j < M; j++) {
                String word = txt.substring(i, j+1);
                if (dict.contains(word)) {
                    for (String suffix : dp[j+1]) {
                        if (suffix.isEmpty())
                            out.add(word);
                        else
                            out.add(word + " " + suffix);
                    }
                }
            }
            dp[i] = out.toArray(new String[0]);
        }

        return Arrays.asList(dp[0]);
    }

    static List<String> wordBreakMain(int dictionaryCount,List<String> dictionary, String txt) {
        List<String> result = new ArrayList<>();
        Set<String> dictSet = new HashSet<>();
        for (String word : dictionary) {
            dictSet.add(word);
        }
        wordBreak(dictSet, 0, txt, result, new StringBuilder());
        return result;
    }

    static void wordBreak(Set<String> dictSet, int i, String text, List<String> result, StringBuilder prefix) {
        if (text.length() == 0) {
            prefix.deleteCharAt(prefix.length() - 1);
            result.add(prefix.toString());
            prefix.append(" ");
            return;
        }
        if (i == text.length()) {
            return;
        }
        String sub = text.substring(0, i+1);
        if (dictSet.contains(sub)) {
            prefix.append(sub);
            prefix.append(" ");
            wordBreak(dictSet, 0, text.substring(i+1), result, prefix);
            prefix.delete(prefix.length() - (sub.length() + 1), prefix.length());
        }
        wordBreak(dictSet, i+1, text, result, prefix);
    }
}

