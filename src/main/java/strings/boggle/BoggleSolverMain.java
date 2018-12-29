package strings.boggle;

import edu.princeton.cs.algs4.StdIn;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by rpurigella on 11/7/18.
 */
public class BoggleSolverMain {

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'G', 'E', 'F', 'K'},
                {'G', 'J', 'I', 'M'},
                {'N', 'O', 'Q', 'Z'}
        };

        char [][] board2 = {
                {'A', 'C', 'S'},
                {'B', 'X', 'Z'}
        };

        char[][] board3 = {
                {'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'},
                {'K', 'L', 'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S', 'T'},
                {'U', 'V', 'W', 'X', 'Z'}
        };

        Set<String> validWords = new HashSet<>();

        //validWords = BoggleSolverWithHashMap.allValidWordsUsingMap("CollinsWords.txt", board3);
        System.out.println(validWords.size());
        for (String word : validWords) {
            //System.out.println(word);
        }
        System.out.println();
        System.out.println();
        System.out.println();

        BoggleSolverWithMappedTrie solver2 = new BoggleSolverWithMappedTrie();
        validWords = solver2.getAllValidWords("strings/CollinsWords.txt", board3);
        System.out.println(validWords.size());
        for (String word : validWords) {
            //System.out.println(word);
        }
        System.out.println();

        BoggleSolverWithTrie solver3 = new BoggleSolverWithTrie();
        validWords = solver3.getAllValidWords("strings/CollinsWords.txt", board3);
        System.out.println(validWords.size());
        for (String word : validWords) {
            //System.out.println(word);
        }
        System.out.println();

        char[][] board4;
        int r = StdIn.readInt();
        int c = StdIn.readInt();
        board4 = new char[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                char ch = StdIn.readString().charAt(0);
                board4[i][j] = ch;
            }
        }

        validWords = solver3.getAllValidWords("strings/CollinsWords.txt", board4);
    }
}
