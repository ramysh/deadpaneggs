package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int [] h = {0,1,0,3,1,0,1,3,2,1,2,1};
        System.out.println(trap(h));
        System.out.println(findWater(h, h.length));
    }

    static int trap(int[] height)
    {
        int ans = 0;
        int size = height.length;
        for (int i = 0; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }

    static int findWater(int arr[], int n) {
        // initialize output
        int result = 0;
        // maximum element on left and right
        int left_max = 0, right_max = 0;
        // indices to traverse the array
        int lo = 0, hi = n-1;
        while(lo <= hi)
        {
            if(arr[lo] < arr[hi])
            {
                if(arr[lo] > left_max)
                    // update max in left
                    left_max = arr[lo];
                else
                    // water on curr element =
                    // max - curr
                    result += left_max - arr[lo];
                lo++;
            }
            else
            {
                if(arr[hi] > right_max)
                    // update right maximum
                    right_max = arr[hi];
                else
                    result += right_max - arr[hi];
                hi--;
            }
        }
        return result;
    }
}

