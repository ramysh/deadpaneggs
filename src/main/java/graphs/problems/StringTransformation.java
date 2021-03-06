package graphs.problems;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by rpurigella on 11/4/18.
 */
public class StringTransformation {

    /*
  * Complete the function below.
  */
    static String[] string_transformation(String[] words, String start, String stop) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String word : words) {
            graph.put(word, new HashSet<String>());
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i==j) continue;
                if (differByOne(words[i], words[j])) {
                    graph.get(words[i]).add(words[j]);
                }
            }
        }

        Set<String> seen = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Map<String, String> map = new HashMap<>();

        for (String sg : graph.keySet()) {
            if (differByOne(sg, start)) {
                queue.add(sg);
                seen.add(sg);
            }
        }

        while (!queue.isEmpty()) {
            String v = queue.poll();
            if (v.equals(stop) || differByOne(v, stop)) return buildPath(map, start, stop, v);
            for (String w : graph.get(v)) {
                if (!seen.contains(w)) {
                    seen.add(w);
                    map.put(w, v);
                    queue.add(w);
                }
            }
        }
        String[] path = new String[1];
        path[0] = "-1";
        return path;
    }

    static boolean differByOne(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return (diff == 1);
    }

    static String[] buildPath(Map<String, String> map, String start, String stop, String v) {
        List<String> path = new ArrayList<>();
        path.add(stop);
        if (!v.equals(stop)) {
            path.add(v);
        }
        String parent = map.get(v);
        while(parent != null) {
            path.add(parent);
            parent = map.get(parent);
        }
        path.add(start);
        Collections.reverse(path);
        return path.toArray(new String[path.size()]);
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int wordsSize = Integer.parseInt(scan.nextLine().trim());

        String[] words = new String[wordsSize];


        for (int wordsItr = 0; wordsItr < wordsSize; wordsItr++) {
            String wordsItem = scan.nextLine();
            words[wordsItr] = wordsItem;

        }

        String start = scan.nextLine();

        String stop = scan.nextLine();

        String[] res = string_transformation(words, start, stop);

        for (int resItr = 0; resItr < res.length; resItr++) {
            bw.write(res[resItr]);

            if (resItr != res.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();
        bw.close();
    }


}
