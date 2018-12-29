package adhoc;

import java.util.*;

import static adhoc.Util.getRandomArray;

/**
 * @author rpurigella
 */
public class MaxSumSubArray {

    public static void mssaBrute(int[] a) {
        int[] res = new int[3];
        res[0] = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++) {
            int sum = 0;
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum > res[0]) {
                    res[0] = sum;
                    res[1] = i;
                    res[2] = j;
                }
            }
        }
        System.out.println("Sum = " + res[0]);
        System.out.println("i = " + res[1]);
        System.out.println("j = " + res[2]);
    }

    public static void mssaDivideAndConquer(int[] a) {
        Result result = mssaDAC(a, 0, a.length - 1);
        System.out.println("Sum = " + result.max);
        System.out.println("i = " + result.i);
        System.out.println("j = " + result.j);
    }

    public static void mssaKadanes(int[] a) {
        int[] res = new int[3];
        res[0] = Integer.MIN_VALUE;
        res[1] = -1;
        res[2] = -1;

        int curr_st = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum > res[0]) {
                res[0] = sum;
                res[1] = curr_st;
                res[2] = i;
            }
            if (sum < 0) {
                curr_st = i+1;
                sum = 0;
            }
        }
        System.out.println("Sum = " + res[0]);
        System.out.println("i = " + res[1]);
        System.out.println("j = " + res[2]);

    }


    private static Result mssaDAC(int[] a, int st, int end) {
        if (st == end) return new Result(a[st], st, st);
        if (st > end) return new Result(Integer.MIN_VALUE, -1, -1);

        int mid = (st + end) / 2;
        Result left = mssaDAC(a, st, mid-1);
        Result right = mssaDAC(a, mid+1, end);
        Result middle = maxCrossing(a, st, end, mid);

        return max(left, right, middle);
    }

    private static Result maxCrossing(int[] a, int st, int end, int mid) {
        int leftSum = 0;
        int leftIndex = mid;
        int sum = 0;

        for (int i = mid - 1; i >= st; i--) {
            sum = sum + a[i];
            if (sum > leftSum) {
                leftSum = sum;
                leftIndex = i;
            }
        }

        int rightSum = 0;
        int rightIndex = mid;
        sum = 0;

        for (int i = mid + 1; i <= end; i++) {
            sum = sum + a[i];
            if (sum > rightSum) {
                rightSum = sum;
                rightIndex = i;
            }
        }

        return new Result(leftSum + a[mid] + rightSum, leftIndex, rightIndex);
    }

    private static Result max(Result left, Result right, Result middle) {
        if (left.max > right.max) {
            if (left.max > middle.max) {
                return left;
            } else {
                return middle;
            }
        } else {
            if (right.max > middle.max) {
                return right;
            } else {
                return middle;
            }
        }
    }

    private static class Result {
        int max;
        int i;
        int j;

        public Result(int max, int i, int j) {
            this.max = max;
            this.i = i;
            this.j = j;
        }
    }

    private static void print(int[] a) {
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = getRandomArray(-50, 50, 50);

        print(a);
        System.out.println("------Brute-----");
        mssaBrute(a);
        System.out.println();
        System.out.println("------DAC-----");
        mssaDivideAndConquer(a);
        System.out.println();
        System.out.println("------Kadane's-----");
        mssaKadanes(a);
    }
}

