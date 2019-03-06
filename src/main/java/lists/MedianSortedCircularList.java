package lists;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * @author rpurigella
 */

class MedianSortedCircularList {
    public static class LinkedListNode {
        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    };

    public static LinkedListNode _insert_node_into_singlylinkedlist(LinkedListNode head, LinkedListNode tail, int val) {
        if(head == null) {
            head = new LinkedListNode(val);
            tail = head;
        }
        else {
            tail.next = new LinkedListNode(val);
            tail = tail.next;
        }
        return tail;
    }

    static int find_median(LinkedListNode ptr) {
        boolean increasing = false;
        boolean decreasing = false;

        LinkedListNode start = ptr;
        while(true) {
            if (start.next == ptr) break;
            if (start.next.val > start.val) increasing = true;
            if (start.next.val < start.val) decreasing = true;
            if (increasing && decreasing) break;
            start = start.next;
        }


        if (!increasing && !decreasing) return ptr.val;

        start = start.next;
        LinkedListNode p1 = start;
        LinkedListNode p2 = start;


        while(p2.next != start && p2.next.next != start) {
            p1 = p1.next;
            p2 = p2.next.next;
        }

        int r1 = (int) (((long) p1.val + (long) p2.val) / (long) 2);
        int r2 = (int) (((long) p1.val + (long) p2.next.val) / (long) 2);
        if (p1 == p2) return r2;
        else if (p2.next.next == start) return r1;
        else return p1.val;
    }

    public static void main(String[] args) throws IOException {
        In in = new In("lists/input1.txt");

        int res;
        int ptr_size = 0;

        LinkedListNode ptr = null;
        LinkedListNode ptr_tail = null;

        ptr_size = in.readInt();

        for(int i = 0; i < ptr_size; i++) {
            int ptr_item;
            ptr_item = in.readInt();
            ptr_tail = _insert_node_into_singlylinkedlist(ptr, ptr_tail, ptr_item);

            if(i == 0) {
                ptr = ptr_tail;
            }
        }

        //----added manually----
        ptr_tail.next = ptr;															// Till now it was linear, now join tail to head and make it circular.
        // Now we have got circular linked list but ptr will be the first element, but we need to give arbitrary node.
        // Value of arbitrary_shift will be [0, ptr_size).
        int arbitrary_shift = in.readInt();
        while (arbitrary_shift > 0)
        {
            arbitrary_shift -= 1;
            ptr = ptr.next;
        }
        //--------

        res = find_median(ptr);
        System.out.println(res);
    }
}