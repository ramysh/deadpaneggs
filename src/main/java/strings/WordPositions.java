package strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rpurigella on 11/7/18.
 */
public class WordPositions {

    static int[][] findPositionsUsingMap(String text, String[] words) {
        Map<String, List<Integer>> map = new HashMap<>();
        int pos = 0;
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == ' ') {
                String word = getCorrectWord(text.substring(pos, i).toLowerCase());
                if (!word.isEmpty()) {
                    addToMap(pos, word, map);
                }
                pos = i + 1;
            }
            i++;
        }

        int[][] positions = new int[words.length][];
        for (i = 0; i < words.length; i++) {
            if (map.containsKey(words[i])) {
                List<Integer> list = map.get(words[i]);
                positions[i] = new int[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    positions[i][j] = list.get(j);
                }
            } else {
                positions[i] = new int[0];
            }
        }
        return positions;
    }

    static int[][] findPositionsUsingTrieWithMap(String text, String[] words) {
        WordPositionsTrieWithMap trie = new WordPositionsTrieWithMap();
        int pos = 0;
        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);
            if (c == ' ') {
                String word = getCorrectWord(text.substring(pos, i).toLowerCase());
                if (!word.isEmpty()) {
                    trie.put(word, pos);
                }
                pos = i + 1;
            }
            i++;
        }

        int[][] positions = new int[words.length][];
        for (i = 0; i < words.length; i++) {
            List<Integer> list = trie.get(words[i]);
            if (!list.isEmpty()) {
                positions[i] = new int[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    positions[i][j] = list.get(j);
                }
            } else {
                positions[i] = new int[0];
            }
        }
        return positions;
    }


    static String getCorrectWord(String word) {
        if (word.isEmpty()) return word;
        if (word.charAt(word.length() - 1) == '.') {
            String newWord = word.substring(0, word.length() - 1);
            if (newWord.isEmpty()) {
                return ".";
            }
            return newWord;
        }
        return word;
    }

    static void addToMap(int pos, String word, Map<String, List<Integer>> map) {
        if (map.containsKey(word)) {
            map.get(word).add(pos);
        } else {
            List<Integer> posList = new ArrayList<>();
            posList.add(pos);
            map.put(word, posList);
        }
    }

    public static void main(String[] args) {
        String text = "Shameless we improving at something to. Evil true high lady roof men had open. " +
                "To projection considered it precaution an melancholy or. Wound young you thing worse along being ham. " +
                "Dissimilar of favourable solicitude if sympathize middletons at. Forfeited up if disposing perfectly in an eagerness perceived necessary. " +
                "Belonging sir curiosity discovery extremity yet forfeited prevailed own off. Travelling by introduced of mr terminated. Knew as miss my high hope quit. " +
                "In curiosity shameless dependent knowledge up. ";
        String[] words = {"at", "if", "exposed", "shameless", "hello"};

        System.out.println("Reference: ");
        for (int i = 0; i < text.length(); i++) {
            System.out.print("[" + text.charAt(i) + " = " + i + "] ");
        }
        System.out.println();

        int[][] positions;
        System.out.println();
        System.out.println();
        System.out.println("Using HashMap");
        positions = findPositionsUsingTrieWithMap(text, words);
        print(positions, words);
        System.out.println();
        System.out.println();
        System.out.println("Using TrieWithMap");
        positions = findPositionsUsingTrieWithMap(text, words);
        print(positions, words);

    }

    static void print(int[][] positions, String[] words) {
        for (int i = 0; i < words.length; i++) {
            System.out.print("[" + words[i] + "] = ");
            for (int pos : positions[i]) {
                System.out.print(pos + " ");
            }
            System.out.println();
        }
    }
}
