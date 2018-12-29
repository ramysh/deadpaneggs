package lists;

import java.util.*;

/**
 * TODO
 * @author rpurigella
 */
public class MedianSortedCircularList {

    private static class LinkedListNode {
        int val;
        LinkedListNode next;

        public LinkedListNode(int val) {
            this.val = val;
        }
    }

    static int find_median(LinkedListNode ptr) {
        return 0;
    }


    public static void main(String[] args) {
        int[] a = {-10, 0, 10};
        int ptrIndex = 1;
        LinkedListNode ptr = null;

        LinkedListNode head = new LinkedListNode(a[0]);
        LinkedListNode tail = head;
        for (int i = 1; i < a.length; i++) {
            tail.next = new LinkedListNode(a[i]);
            tail = tail.next;
            if (ptrIndex == i) {
                ptr = tail;
            }
        }
        tail.next = head;

        System.out.println(find_median(ptr));
    }

}

