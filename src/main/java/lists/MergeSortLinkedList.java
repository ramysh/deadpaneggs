package lists;

import java.util.*;

/**
 * @author rpurigella
 */
public class MergeSortLinkedList {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    static Node mergeSort(Node head) {
        if (head == null || head.next == null) return head;
        Node middle = getMiddle(head);
        Node middleNext = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(middleNext);
        return merge(left, right);
    }

    static Node getMiddle(Node head) {
        Node slow  = head;
        Node fast = head.next.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static Node merge(Node left, Node right) {
        Node prehead = new Node(-1);
        Node tail = prehead;

        while(left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }

        if (left == null) {
            tail.next = right;
        } else {
            tail.next = left;
        }

        return prehead.next;
    }

    public static void main(String[] args) {
        int size = 11;
        int[] a = new int[size];

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            a[i] = random.nextInt(50);
        }

        Node head = null;
        for (int i = 0; i < size; i++) {
            Node node = new Node(a[i]);
            if (head == null) {
                head = node;
            } else {
                node.next = head;
                head = node;
            }
        }

        System.out.println("Original list");
        Node start = head;
        while (start != null) {
            System.out.print(start.val + " ");
            start = start.next;
        }
        System.out.println();

        head = mergeSort(head);

        System.out.println("Original list");
        start = head;
        while (start != null) {
            System.out.print(start.val + " ");
            start = start.next;
        }
        System.out.println();

    }
}

