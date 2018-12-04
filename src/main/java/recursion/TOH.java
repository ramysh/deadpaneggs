package recursion;

import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * @author rpurigella
 */
public class TOH {
    public static void main(String[] args) {
        toh('A', 'B', 'C', 11);
    }

    static void toh(char from, char to, char temp, int n) {
        if (n == 1) {
            StdOut.println("Moving disk " + n + ": " + from + " -> " + to);
            return;
        }
        toh(from, temp, to, n-1);
        StdOut.println("Moving disk " + n + ": " + from + " -> " + to);
        toh(temp, to, from, n-1);
    }
}

