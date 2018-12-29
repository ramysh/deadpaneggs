package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class MinHeap<T extends Comparable<T>> {
    T[] pq;
    int n;

    public MinHeap() {
        pq = (T[]) new Comparable[2];
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(T item) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = item;
        swim(n);
    }

    public T getMin() {
        if (n == 0) throw new IllegalStateException("Heap empty");
        return pq[1];
    }

    public T delMin() {
        if (n == 0) throw new IllegalStateException("Heap empty");
        T min = pq[1];
        swap(pq, 1, n);
        pq[n] = null;
        n--;
        sink(1);
        if (n > 0 && n == (pq.length - 1)/4) resize(pq.length/2);
        return min;
    }

    private void swap(T[] pq, int i, int j) {
        T temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void swim(int k) {
        while(k > 1 && less(k, k/2)) {
            swap(pq, k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j)) j++;
            if (less(k, j)) break;
            swap(pq, k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int item = random.nextInt(500);
            System.out.print(item + " ");
            minHeap.insert(item);
        }
        System.out.println();

        while (minHeap.size() > 0) {
            System.out.print(minHeap.delMin() + " ");
        }

    }

}

