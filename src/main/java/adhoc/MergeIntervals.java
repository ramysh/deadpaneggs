package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class MergeIntervals {

    static int[][] getMergedIntervals(int[][] input) {
        List<Interval> inputList = getList(input);
        Collections.sort(inputList, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });
        List<Interval> result = new ArrayList<>();
        Interval prev = inputList.get(0);
        for (int i = 1; i < inputList.size(); i++) {
            Interval curr = inputList.get(i);
            if (curr.start > prev.end) {
                result.add(prev);
                prev = curr;
            } else {
                prev.end = Math.max(curr.end, prev.end);
            }
        }
        result.add(prev);

        return getArray(result);
    }

    static List<Interval> getList(int[][] input) {
        List<Interval> output = new ArrayList<>();
        for (int[] i : input) {
            output.add(new Interval(i[0], i[1]));
        }
        return output;
    }

    static int[][] getArray(List<Interval> input) {
        int[][] output = new int[input.size()][2];
        int i = 0;
        for (Interval interval: input) {
            output[i][0] = interval.start;
            output[i][1] = interval.end;
            i++;
        }
        return output;
    }

    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }


    public static void main(String[] args) {
        int[][] input = {
                {1, 3},
                {5, 7},
                {2, 4},
                {6, 8}
        };
        int[][] merged = getMergedIntervals(input);
        System.out.println();
    }

}

