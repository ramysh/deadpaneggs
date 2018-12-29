package adhoc;

import java.util.*;

import static adhoc.Util.getRandomArray;
import static adhoc.Util.print;

/**
 * @author rpurigella
 */
public class ThreeSum {

    public static void threeSumBrute(int[] a, int target) {
        List<String> results = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                for (int k = j + 1; k < a.length; k++) {
                    if (a[i] + a[j] + a[k] == target) {
                        results.add(a[i] + "," + a[j] + "," + a[k]);
                    }
                }
            }
        }
        System.out.println("-------Brute--------");
        print(results);
    }

    public static void threeSumHash(int[] a, int target) {
        List<String> results = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            set.clear();
            List<String> twoSumResult = twoSumHash(a, set, i + 1, target - a[i]);
            for (String r : twoSumResult) {
                results.add(a[i] + "," + r);
            }
        }
        System.out.println("-------Hash--------");
        print(results);
    }

    private static List<String> twoSumHash(int[] a, Set<Integer> set, int index, int target) {
        List<String> twoSumResults = new ArrayList<>();
        for (int i = index; i < a.length; i++) {
            if (set.contains(target - a[i])) {
                twoSumResults.add(a[i] + "," + (target - a[i]));
            } else {
                set.add(a[i]);
            }
        }
        return twoSumResults;
    }

    public static void threeSumSortScan(int[] a, int target) {
        Arrays.sort(a);
        print(a);
        Set<String> results = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            List<String> twoSumResult = twoSumSortScan(a, i + 1, target - a[i]);
            for (String r : twoSumResult) {
                results.add(a[i] + "," + r);
            }
        }
        System.out.println("-------ScanSort--------");
        print(results);
    }

    private static List<String> twoSumSortScan(int[] a, int index, int target) {
        List<String> twoSumResults = new ArrayList<>();
        int i = index;
        int j = a.length - 1;
        while (i < j) {
            if (a[i] + a[j] < target) {
                i++;
            } else if (a[i] + a[j] > target) {
                j--;
            } else {
                twoSumResults.add(a[i] + "," + a[j]);
                i++;
                j--;
            }
        }
        return twoSumResults;
    }



    public static void main(String[] args) {
        //int[] a = getRandomArray(-50, 50, 15);
        int[] a = {-37, -13, 44, -3, -23, 27, 15, 28, 37, -2, -3, 1, -10, 26, 32};
        print(a);
        threeSumBrute(a, 0);
        threeSumHash(a, 0);
        threeSumSortScan(a, 0);
    }


}

