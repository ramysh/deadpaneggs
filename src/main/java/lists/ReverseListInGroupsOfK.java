package lists;

/**
 * @author rpurigella
 */
public class ReverseListInGroupsOfK {

    static class LinkedListNode {
        int val;
        LinkedListNode next;

        public LinkedListNode(int val) {
            this.val = val;
        }
    }

    static class Result {
        LinkedListNode head;
        LinkedListNode tail;

        public Result(LinkedListNode head, LinkedListNode tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    static LinkedListNode reverse_linked_list_in_groups_of_k(LinkedListNode head, int k) {
        LinkedListNode kth = head;
        LinkedListNode bkth = head;
        LinkedListNode newH = null, lastT = null;
        while (kth != null) {
            head = kth;
            for (int i = 0; i < k; i++) {
                bkth = kth;
                kth = kth.next;
                if (kth == null) break;
            }
            Result result = reverse(head, bkth);
            if (newH == null) {
                newH = result.head;
            }
            if (lastT == null) {
                lastT = result.tail;
            }
            else {
                lastT.next = result.head;
                lastT = result.tail;
            }
        }
        return newH;
    }

    static Result reverse(LinkedListNode head, LinkedListNode bkth) {
        bkth.next = null;

        LinkedListNode prev = null;
        LinkedListNode curr = head;
        LinkedListNode tail = null;
        while (curr != null) {
            LinkedListNode tmp = curr;
            curr = curr.next;
            tmp.next = prev;
            prev = tmp;
            if (tail == null) tail = tmp;
        }
        return new Result(prev, tail);
    }


    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        LinkedListNode head = new LinkedListNode(a[0]);
        LinkedListNode tail = head;
        for (int i = 1; i < a.length; i++) {
            tail.next = new LinkedListNode(a[i]);
            tail = tail.next;
        }

        System.out.println("Original list");
        LinkedListNode start = head;
        while (start != null) {
            System.out.print(start.val + " ");
            start = start.next;
        }
        System.out.println();


        System.out.println("Modified list");
        start = reverse_linked_list_in_groups_of_k(head, k);
        while (start != null) {
            System.out.print(start.val + " ");
            start = start.next;
        }
        System.out.println();
    }
}

