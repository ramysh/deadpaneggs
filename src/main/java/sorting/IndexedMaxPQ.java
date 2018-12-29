package sorting;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author rpurigella
 */
public class IndexedMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> {

    private int maxN;
    private int n;
    private int[] pq;
    private int[] qp;
    private Key[] keys;

    public IndexedMaxPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i < maxN + 1; i++) {
            qp[i] = -1;
        }
     }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return (n == 0);
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        return (qp[i] != -1);
    }

    public int maxIndex() {
        return pq[1];
    }

    public Key maxKey() {
        return keys[pq[1]];
    }

    public Key keyOf(int i) {
        return keys[i];
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        n++;
        pq[n] = i;
        qp[i] = n;
        keys[i] = key;
        swim(n);
    }

    public int delMax() {
        int max = pq[1];
        swap(1, n);
        n--;
        sink(1);
        qp[max] = -1;
        keys[max] = null;
        return max;
    }

    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public void delete(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        int index = qp[i];
        swap(index, n);
        n--;
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    private void swim(int k) {
        while(k > 1 && less(k/2, k)) {
            swap(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (less(j, k)) break;
            swap(j, k);
            k = j;
        }
    }

    private void swap(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }


    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexedMaxPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexedMaxPQ<>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
}

