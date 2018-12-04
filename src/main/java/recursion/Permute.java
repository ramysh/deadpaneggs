package recursion;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author rpurigella
 */
public class Permute {

    public static void main(String[] args) {
        permuteMain("abcd");
    }

    private static void permuteMain(String s) {
        permute(s.toCharArray(), 0);
    }

    private static void permute(char[] a, int i) {
        if (i == a.length - 1) {
            print(a);
        }
        for (int j = i; j < a.length; j++) {
            swap(a, i, j);
            permute(a, i+1);
            swap(a, i, j);
        }
    }

    private static void print(char[] a) {
        for(char c : a) {
            StdOut.print(c);
        }
        StdOut.println();
    }

    private static void swap(char[] a, int i, int j) {
        char tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
