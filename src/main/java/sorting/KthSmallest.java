package sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/**
 * @author rpurigella
 */
public class KthSmallest {

    public static void main(String[] args) {
        int size = 11;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }
        Util.print(a);

        int k = 5;
        System.out.println(k + "thSmallest = " + kthSmallest(a, k));
        System.out.println(k + "thSmallest = " + kthSmallestRecursive(a, k));
        System.out.println();

        // to verify output
        int[] sortedA = Arrays.copyOf(a, a.length);
        Arrays.sort(sortedA);
        Util.print(sortedA);
    }

    static int kthSmallest(int[] a, int k) {
        StdRandom.shuffle(a); //Not doing this now as the input is random. Otherwise needed.
        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            int p = partition(a, lo, hi);
            if (p == k) return a[p];
            else if (p < k) lo = p + 1;
            else hi = p - 1;
        }
        return a[k];
    }

    static int kthSmallestRecursive(int[] a, int k) {
        //StdRandom.shuffle(a); //Not doing this now as the input is random. Otherwise needed.
        return kthSmallestRecursive(a, 0, a.length - 1, k);
    }

    static int kthSmallestRecursive(int[] a, int lo, int hi, int k) {
        if (lo == hi) return a[lo];
        int p = partition(a, lo, hi);
        if (p == k) return a[p];
        else if (p < k) return kthSmallestRecursive(a, p + 1, hi, k);
        else return kthSmallestRecursive(a, lo, p - 1, k);
    }

    static int partition(int[] a , int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while(a[++i] < a[lo]) {
                if (i == hi) break;
            }
            while (a[--j] > a[lo]) {
                if (j == lo) break;
            }
            if (i >= j) break;
            Util.swap(a, i, j);
        }
        Util.swap(a, lo, j);
        return j;
    }
}

