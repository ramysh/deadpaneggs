package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * @author rpurigella
 */
public class BasicSorts {

    static void insertionSort(int[] a ) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j-1] > a[j]) {
                    Util.swap(a, j-1, j);
                } else {
                    break;
                }
            }
        }

        Util.print(a);
    }

    static void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            Util.swap(a, i, min);
        }
        Util.print(a);
    }


    public static void main(String[] args) {
        int size = 11;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }

        int[] a1 = Arrays.copyOf(a, a.length);
        int[] a2 = Arrays.copyOf(a, a.length);
        insertionSort(a1);
        selectionSort(a2);
    }
}

