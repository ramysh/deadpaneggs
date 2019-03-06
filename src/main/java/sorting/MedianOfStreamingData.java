package sorting;

import java.util.*;

/**
 * @author rpurigella
 */
public class MedianOfStreamingData {

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        int[] a = {1, 2, 3};
        List<Integer> stream = new ArrayList<>();
        for (int i : a) {
           stream.add(i);
        }

        findMedianStream(stream.iterator());
    }

    public static void findMedianStream(Iterator<Integer> stream) {
        MedianFinder medianFinder = new MedianFinder();
        while(stream.hasNext()) {
            medianFinder.addNum(stream.next());
            System.out.println(medianFinder.findMedian());
        }
    }
}

class MedianFinder {

    private PriorityQueue<Integer> maxPQ;
    private PriorityQueue<Integer> minPQ;

    public MedianFinder() {
        maxPQ = new PriorityQueue(Collections.reverseOrder());
        minPQ = new PriorityQueue();
    }

    public void addNum(int num) {
        if (minPQ.isEmpty() && maxPQ.isEmpty()) {
            maxPQ.add(num);
            return;
        }
        if (maxPQ.peek() >= num) {
            maxPQ.add(num);
        } else {
            minPQ.add(num);
        }
        if (Math.abs(minPQ.size() - maxPQ.size()) == 2) {
            if (minPQ.size() > maxPQ.size()) {
                maxPQ.add(minPQ.poll());
            } else {
                minPQ.add(maxPQ.poll());
            }
        }
    }

    public double findMedian() {
        if (minPQ.size() > maxPQ.size()) {
            return (double) minPQ.peek();
        } else if (minPQ.size() < maxPQ.size()) {
            return (double) maxPQ.peek();
        } else {
            return ((double) minPQ.peek() + (double) maxPQ.peek()) / (double) 2;
        }
    }
}

