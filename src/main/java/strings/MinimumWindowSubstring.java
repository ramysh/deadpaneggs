package strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 * @author rpurigella
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        System.out.println(minWindow("ABAACBAB", "AABBC"));
    }

    static String minWindow(String text, String chars) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length(); i++) {
            set.add(chars.charAt(i));
        }

        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int rLeft = 0;
        int rRight = 0;

        while (right < text.length()) {
            if (set.contains(text.charAt(right))) {
                add(text.charAt(right), map);
            }
            if (map.size() == set.size()) {
                while (map.size() == set.size()) {
                    if (right - left + 1 < min) {
                        min = right - left + 1;
                        rLeft = left;
                        rRight = right;
                    }
                    if (set.contains(text.charAt(left))) {
                        remove(text.charAt(left), map);
                    }
                    left++;
                }
            }
            right++;
        }
        if (min == Integer.MAX_VALUE) {
            return "";
        } else {
            return text.substring(rLeft, rRight+1);
        }
    }

    static void add(char c, Map<Character, Integer> map) {
        if (map.containsKey(c)) {
            map.put(c, map.get(c) + 1);
        } else {
            map.put(c, 1);
        }
    }

    static void remove(char c, Map<Character, Integer> map) {
        if (!map.containsKey(c)) return;
        int val = map.get(c);
        if (val == 1) {
            map.remove(c);
            return;
        }
        map.put(c, val - 1);
    }
}

