package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class Util {


    public static void print(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void print(Set<String> set) {
        for (String s : set) {
            System.out.println(s);
        }
    }

    public static void print(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static int[] getRandomArray(int min, int max, int size) {
        Random random = new Random();
        int[] a = new int[size];

        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(max - min) + min;
        }

        return a;
    }
}

