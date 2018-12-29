package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class Util {
    static int medianOf3(int[] a, int i, int j, int k) {
        if (a[i] < a[j]) {
            if (a[k] > a[j]) {
                return j;
            } else {
                if (a[k] > a[i]) {
                    return k;
                } else {
                    return i;
                }
            }
        } else {
            if (a[k] > a[i]) {
                return i;
            } else {
                if (a[k] > a[j]) {
                    return k;
                } else {
                    return j;
                }
            }
        }
    }

    static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    static void print(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();
    }
}

