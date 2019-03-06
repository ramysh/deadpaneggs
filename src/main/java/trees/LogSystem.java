package trees;

import java.util.*;

/**
 * @author rpurigella
 */
public class LogSystem {

    public static void main(String[] args) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 1);
        map.put(1, 2);
        map.put(10, 3);
        map.put(11, 4);
        SortedMap<Integer, Integer> map2 =  map.subMap(1, false, 11, false);
        System.out.println();
    }
}

