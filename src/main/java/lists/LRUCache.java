package lists;


import java.util.*;

/**
 * @author rpurigella
 */
public class LRUCache {

    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class CacheList {
        Node first;
        Node last;

        public void remove(Node node) {
            if (first == null) {
                return;
            }
            if (first == node && last == node) {
                first = null;
                last = null;
            } else if (last == node) {
                Node temp = last;
                last = last.prev;
                last.next = null;
                temp.prev = null;
            } else if (first == node) {
                Node temp = first;
                first = first.next;
                first.prev = null;
                temp.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }

        }

        public void addToFront(Node node) {
            if (first == null) {
                first = node;
                last = node;
            } else {
                first.prev = node;
                node.next = first;
                first = node;
            }
        }

        public Node removeLast() {
            if (first == null) {
                return null;
            }
            Node node;
            if (first == last) {
                node = last;
                first = null;
                last = null;
            } else {
                node = last;
                last = last.prev;
                last.next = null;
                node.prev = null;
            }
            return node;
        }

    }

    static Map<Integer, Node> cacheMap = new HashMap<>();
    static CacheList cacheList = new CacheList();

    static int[] implement_LRU_cache(int capacity, int[] query_type, int[] key, int[] value) {
        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < query_type.length; i++) {
            if (query_type[i] == 0) {
                resultList.add(get(key[i]));
            } else {
                set(capacity, key[i], value[i]);
            }
        }

        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }

    static int get(int key) {
        if (!cacheMap.containsKey(key)) return -1;
        Node node = cacheMap.get(key);
        cacheList.remove(node);
        cacheList.addToFront(node);
        return node.value;
    }

    static void set(int capacity, int key, int value) {
        Node node;
        if (cacheMap.containsKey(key)) {
            node = cacheMap.get(key);
            cacheList.remove(node);
            node.value = value;
        } else {
            if (cacheMap.size() == capacity) {
                Node purged = cacheList.removeLast();
                if (purged != null) cacheMap.remove(purged.key);
            }
            node = new Node(key, value);
            cacheMap.put(key, node);
        }
        cacheList.addToFront(node);
    }

    public static void main(String[] args) {
        int[] query_type = {1, 1, 0, 1, 0, 1, 0};
        int[] key = {5, 10, 5, 15, 10, 5, 5};
        int[] value = {11, 22, 1, 33, 1, 55, 1};
        int capacity = 2;
        int[] result = implement_LRU_cache(capacity, query_type, key, value);
        for (int i : result) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

