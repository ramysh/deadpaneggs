package sorting;

import java.util.*;


/**
 * Given K sorted arrays of size N each, merge them and print the sorted output. Assume N is very large compared to K. N may not even be known. The arrays could be just sorted streams, for instance, timestamp streams.
 * All arrays might be sorted in increasing manner or decreasing manner. Sort all of them in the manner they appear.
 * @author rpurigella
 */
public class MergeKSortedArraysNEach {

    public static void main(String[] args) {
        int[][] arr = {
                {7, 6, 5, 4},
                {8, 6, 4, 2},
                {11, 10, 9, 0}
        } ;
        int[] res = mergeArrays(arr);
        for (int eRes : res) {
            System.out.print(eRes + " ");
        }
        System.out.println();
    }

    static int[] mergeArrays(int[][] arr) {
        PriorityQueue<Element> pq = new PriorityQueue<>(arr.length, Collections.reverseOrder());
        int row = 0;
        while (row < arr.length) {
            pq.add(new Element(arr[row][0], row, 0));
            row++;
        }
        int res[] = new int[arr.length * arr[0].length];
        int pos = 0;
        while (pos < res.length) {
            Element max = pq.poll();
            res[pos] = max.num;
            int nextRow = max.row;
            int nextCol = max.col + 1;
            if (nextCol < arr[nextRow].length) {
                pq.add(new Element(arr[nextRow][nextCol], nextRow, nextCol));
            }
            pos++;
        }

        return res;
    }

    static class Element implements Comparable<Element>{
        int num;
        int row;
        int col;

        public Element(int num, int row, int col) {
            this.num = num;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.num, o.num);
        }
    }
}

