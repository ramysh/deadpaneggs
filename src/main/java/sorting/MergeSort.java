package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class MergeSort {

    static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        sort(aux, a, 0, a.length - 1);
    }

    static void sort(int[] aux, int[] a, int lo, int hi) {
        if (hi == lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);
        merge(aux, a, lo, hi, mid);
    }

    static void merge(int[] aux, int[] a, int lo, int hi, int mid) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)           a[k] = aux[j++];
            else if (j > hi)            a[k] = aux[i++];
            else if (aux[j] < aux[i])   a[k] = aux[j++];
            else                        a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        int size = 11;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }
        Util.print(a);
        mergeSort(a);
        Util.print(a);
    }
}

