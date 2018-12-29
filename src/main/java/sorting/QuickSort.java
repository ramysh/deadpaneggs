package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class QuickSort {

    public static void main(String[] args) {
        int size = 11;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }

        Util.print(a);
        System.out.println("Using Hoarse's");
        quickSortHoares(a);
        System.out.println("Using Lomutos's");
        quickSortHoares(a);
    }

    static void quickSortHoares(int[] a) {
        int[] a1 = Arrays.copyOf(a, a.length);
        sortHoares(a1, 0, a1.length - 1);
        Util.print(a1);
    }

    static void sortHoares(int[] a, int lo, int hi) {
        if (lo >= hi) return;

        int i, j, k;
        i = lo;
        k = hi;
        j = (hi + lo) / 2;
        int pivotIndex = Util.medianOf3(a, i, j, k);

        int p = QuickSortBasicPartitions.hoaresPartition(a, lo, hi, pivotIndex);
        sortHoares(a, lo, p-1);
        sortHoares(a, p+1, hi);
    }


    static void quickSortLomutos(int[] a) {
        int[] a1 = Arrays.copyOf(a, a.length);
        sortLomutos(a1, 0, a1.length - 1);
        Util.print(a1);
    }

    static void sortLomutos(int[] a, int lo, int hi) {
        if (lo >= hi) return;

        int i, j, k;
        i = lo;
        k = hi;
        j = (hi + lo) / 2;
        int pivotIndex = Util.medianOf3(a, i, j, k);

        int p = QuickSortBasicPartitions.lomutosPartition(a, lo, hi, pivotIndex);
        sortLomutos(a, lo, p-1);
        sortLomutos(a, p+1, hi);
    }
}

