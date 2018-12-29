package adhoc;

import java.util.*;

import static adhoc.Util.getRandomArray;
import static adhoc.Util.print;

/**
 * @author rpurigella
 */
public class TwoSum {

    public static void twoSumBrute(int[] a, int k) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1 ; j < a.length; j++) {
                if (a[i] + a[j] == k) {
                    results.add(a[i] + ", " + a[j]);
                }
            }
        }
        System.out.println("-------Brute---------");
        print(results);
    }

    public static void twoSumHash(int[] a, int k) {
        List<String> results = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int x : a) {
            int y = k - x;
            if (set.contains(y)) {
                results.add(x + ", " + y);
            } else {
                set.add(x);
            }
        }
        System.out.println("-------Hash---------");
        print(results);
    }

    public static void twoSumSort(int[] a, int k) {
        List<String> results = new ArrayList<>();
        Arrays.sort(a);
        int i = 0;
        int j = a.length - 1;

        while (i < j) {
            if (a[i] + a[j] > k) {
                j--;
            } else if (a[i] + a[j] < k) {
                i++;
            } else if (a[i] + a[j] == k) {
                results.add(a[i] + ", " + a[j]);
                i++;
            }
        }
        System.out.println("-------SortAndScan---------");
        print(results);
    }



    public static void main(String[] args) {
        int[] a = getRandomArray(-50, 50, 25);
        print(a);
        twoSumBrute(a, 0);
        twoSumHash(a, 0);
        twoSumSort(a, 0);
    }
}

