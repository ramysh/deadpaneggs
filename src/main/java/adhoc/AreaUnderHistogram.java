package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class AreaUnderHistogram {
    public static void main(String[] args) {
        long[] arr = {6, 2, 5, 4, 5, 1, 6};
        System.out.println(findMaxPossibleArea(arr, 0, 6));
    }

    static long findMaxPossibleArea(long[] arr, int l, int r) {
        int n = r - l + 1;
        long maxArea = 0;

        // rightSmaller[i] = j, implies that j is smallest index greater than i
        // such that arr[l+i]>arr[r+j] where 0<=i<(r-l+1) and 0<=j<(r-l+1)

        // To fill rightSmaller array
        int[] rightSmaller = new int[n];
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = l; i <= r; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    int popped = stack.pop();
                    rightSmaller[popped - l] = i - l;
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int popped = stack.pop();
            rightSmaller[popped - l] = n;
        }

        // leftSmaller[i] = j, implies that j is smallest index greater than i
        // such that arr[l+i]>arr[r+j] where 0<=i<(r-l+1) and 0<=j<(r-l+1)

        // To fill leftSmaller array
        int[] leftSmaller = new int[n];

        for (int i = r; i >= l; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                    int popped = stack.pop();
                    leftSmaller[popped - l] = i - l;
                }
                stack.push(i);
            }
        }
        while (!stack.isEmpty()) {
            int popped = stack.pop();
            leftSmaller[popped - l] = -1;
        }
        // Notice that all elements in subarray arr[leftSmaller[i]+1], arr[leftSmaller[i]+2],...,
        // arr[rightSmaller[i]-1] are greater than or equal to arr[i]
        // So, the max area a rectangle can have such that it must contain ith bar is
        // (arr[i]*((rightSmaller[i]-1) - (leftSmaller[i]+1) + 1)

        // to calculate histogram area. here n = r - l + 1
        long currentMaxArea;
        for (int i = 0; i < n; i++) {
            currentMaxArea = arr[i + l] * (rightSmaller[i] - leftSmaller[i] - 1);
            maxArea = Math.max(currentMaxArea, maxArea);
        }
        return maxArea;
    }
}

