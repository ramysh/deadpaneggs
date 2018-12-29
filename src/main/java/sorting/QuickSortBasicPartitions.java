package sorting;

import java.util.Arrays;
import java.util.Random;


/**
 * @author rpurigella
 */
public class QuickSortBasicPartitions {

    public static void main(String[] args) {

        /*
        int size = 11;
        int[] a = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }
        */

        int[] a = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        Util.print(a);
        partitions(a);
    }



    static void partitions(int[] a) {
        int[] hp = Arrays.copyOf(a, a.length);
        int[] lp = Arrays.copyOf(a, a.length);
        int i, j, k;

        i = 0;
        k = hp.length - 1;
        j = (hp.length - 1) / 2;
        int pIndex = Util.medianOf3(a, i, j, k);
        System.out.println("Pivot element = Median of " + a[0] + " " + a[5] + " " + a[a.length - 1] + " = " + a[pIndex]);
        System.out.println("Pivot Index = " + pIndex);

        System.out.println();
        System.out.println();

        System.out.println("Hoare's partition");
        hoaresPartition(hp, 0, hp.length - 1, pIndex);
        Util.print(hp);
        System.out.println();

        System.out.println("Lomuto's partition");
        lomutosPartition(lp, 0, lp.length - 1, pIndex);
        Util.print(lp);
        System.out.println();
    }

    static int lomutosPartition(int[] a, int lo, int hi, int pIndex) {
        Util.swap(a, pIndex, hi);
        int pivot = a[hi];
        int i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) {
                Util.swap(a, i, j);
                i++;
            }
        }
        Util.swap(a, i, hi);
        return i;
    }

    static int hoaresPartition(int[] a, int lo, int hi, int pIndex) {
        Util.swap(a, lo, pIndex);
        int i = lo;
        int j = hi + 1;

        while (true) {
            while (a[++i] < a[lo]) {
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

