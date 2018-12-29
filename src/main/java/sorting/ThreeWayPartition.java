package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class ThreeWayPartition {
    public static void main(String[] args) {
        int size = 20;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(25);
        }

        Util.print(a);

        // get the equal key
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        int ek = -1;
        for (int i : map.keySet()) {
            if (map.get(i) > 1) {
                ek = i;
                break;
            }
        }
        if (ek == -1) {
            System.out.print("No equal keys");
            return;
        }
        threeWayP(a, ek);
        System.out.println();
    }

    static void threeWayP(int[] a, int ek) {
        System.out.println("3 Way partition with ek = " + ek);

        int lt = 0;
        int gt = a.length - 1;
        int i = 0;

        while (i <= gt) {
            if (a[i] < ek) {
                Util.swap(a, i, lt);
                i++;
                lt++;
            } else if (a[i] > ek) {
                Util.swap(a, i, gt);
                gt--;
            } else {
                i++;
            }
        }
        Util.print(a);
    }
}

