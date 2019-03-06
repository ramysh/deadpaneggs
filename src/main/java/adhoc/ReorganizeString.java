package adhoc;

import java.util.*;

/**
 * @author rpurigella
 */
public class ReorganizeString {
    public String reorganizeString(String s) {
        if (s == null || s.length() == 0) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int count = map.get(c);
                count++;
                map.put(c, count);
            } else {
                map.put(c, 1);
            }
        }

        PriorityQueue<Entry> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (char c :  map.keySet()) {
            pq.offer(new Entry(c, map.get(c)));
        }

        char[] result = new char[s.length()];
        int pos = 0;
        while(pq.size() > 1) {
            Entry e1, e2;
            e1 = pq.poll();
            e2 = pq.poll();
            result[pos++] = e1.c;
            result[pos++] = e2.c;
            if (e1.count > 1) {
                e1.count--;
                pq.offer(e1);
            }
            if (e2.count > 1) {
                e2.count--;
                pq.offer(e2);
            }
        }
        if (pq.size() == 0) return new String(result);
        if (pq.size() == 1 && pq.peek().count > 1) return "";
        result[pos] = pq.poll().c;

        return new String(result);
    }

    private class Entry implements Comparable<Entry> {
        char c;
        int count;

        @Override
        public int compareTo(Entry o) {
            return Integer.compare(this.count, o.count);
        }

        public Entry(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        ReorganizeString reorganizeString = new ReorganizeString();
        System.out.println(reorganizeString.reorganizeString("vvvlo"));
    }
}

