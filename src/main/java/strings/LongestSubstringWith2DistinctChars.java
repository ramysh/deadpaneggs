package strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by rpurigella on 11/1/18.
 */
public class LongestSubstringWith2DistinctChars {

    static int ls2c(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        char[] c = s.toCharArray();
        int n = c.length;
        int max = 0;
        while (j < n) {
            add(c[j], map);
            if (map.size() == 2) {
               if (j-i+1 > max) max = j-i+1;
            } else if (map.size() > 2) {
                while (map.size() > 2) {
                    remove(c[i], map);
                    i++;
                }
            }
            j++;
        }
        return max;
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

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            set.add(key);
        }

        for (String s : set) {
            StdOut.println("LS2C " + s + " = " + ls2c(s));
        }
    }
}
