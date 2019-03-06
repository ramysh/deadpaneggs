package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class SlidingWindowMaximum {

    static int[] max_in_sliding_window(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int pos= 0;

        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.removeLast();
                }
                deque.addLast(i);
            } else {
                result[pos++] = nums[deque.peekFirst()];
                if (i - k == deque.peekFirst()) {
                    deque.removeFirst();
                }
                while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.removeLast();
                }
                deque.addLast(i);
            }
        }
        result[pos] = nums[deque.peekFirst()];
        return result;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        int res[] = max_in_sliding_window(a, 3);
        for (int i : res) {
            System.out.print(i + " ");
        }
        System.out.println();

    }
}

