package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class AlternatePositiveAndNegative {

    static int[] alternating_positives_and_negatives(int[] a) {
        int[] result = new int[a.length];
        int p = 0;
        int n = 0;
        while (p < a.length && a[p] < 0) p++;
        while (n < a.length && a[n] >= 0) n++;
        for (int i = 0; i < a.length; i++) {
            if (p == a.length) {
                result[i] = a[n++];
                while (n < a.length && a[n] >= 0) n++;
            } else if (n == a.length) {
                result[i] = a[p++];
                while (p < a.length && a[p] < 0) p++;
            } else if (i % 2 == 1) {
                result[i] = a[n++];
                while (n < a.length && a[n] >= 0) n++;
            } else {
                result[i] = a[p++];
                while (p < a.length && a[p] < 0) p++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 0};
        int[] result = alternating_positives_and_negatives(a);
        System.out.println();
    }
}

