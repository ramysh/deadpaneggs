package graphs.problems;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * Created by rpurigella on 11/6/18.
 */
public class LongestPathDAG {

    static int[] find_longest_path(int dag_nodes, int[] dag_from, int[] dag_to, int[] dag_weight, int from_node, int to_node) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i = 1; i <= dag_nodes; i ++) {
            graph.put(i, new HashMap<Integer, Integer>());
        }
        for (int e = 0; e < dag_from.length; e++) {
            graph.get(dag_from[e]).put(dag_to[e], dag_weight[e]);
        }

        Set<Integer> seen = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int v : graph.keySet()) {
            if (!seen.contains(v)) {
                explore(v, seen, stack, graph);
            }
        }

        List<Integer> order = new ArrayList<>();
        while(!stack.isEmpty()) {
            order.add(stack.pop());
        }

        long dist[] = new long[dag_nodes+1];
        for (int i = 0; i < dist.length; i++) {
            dist[i] = Long.MIN_VALUE;
        }
        dist[from_node] = 0;
        int[] prev = new int[dag_nodes+1];

        for (int v : order) {
            for (int w : graph.get(v).keySet()) {
                long d = dist[v] + graph.get(v).get(w);
                if (dist[w] < d) {
                    dist[w] = d;
                    prev[w] = v;
                }
            }
        }
        return buildPath(prev, to_node);
    }

    static void explore(int v, Set<Integer> seen, Stack<Integer> stack, Map<Integer, Map<Integer, Integer>> graph) {
        seen.add(v);
        for (int w : graph.get(v).keySet()) {
            if (!seen.contains(w)) {
                explore(w, seen, stack, graph);
            }
        }
        stack.push(v);
    }

    static int[] buildPath(int[] prev, int w) {
        List<Integer> path = new ArrayList<>();
        int target = w;
        path.add(target);
        while(prev[target] != 0) {
            path.add(prev[target]);
            target = prev[target];
        }
        Collections.reverse(path);
        int[] retPath = new int[path.size()];
        for (int i = 0 ; i < path.size(); i++) {
            retPath[i] = path.get(i);
        }
        return retPath;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] res;
        String[] dag_nodesm = in.nextLine().split(" ");
        int dag_nodes = Integer.parseInt(dag_nodesm[0]);
        int dag_edges = Integer.parseInt(dag_nodesm[1]);

        int[] dag_from = new int[dag_edges];
        int[] dag_to = new int[dag_edges];
        int[] dag_weight = new int[dag_edges];

        for (int dag_i = 0; dag_i < dag_edges; dag_i++) {
            String[] dag_fromvw = in.nextLine().split(" ");
            dag_from[dag_i] = Integer.parseInt(dag_fromvw[0]);
            dag_to[dag_i] = Integer.parseInt(dag_fromvw[1]);
            dag_weight[dag_i] = Integer.parseInt(dag_fromvw[2]);
        }

        int from_node;
        from_node = Integer.parseInt(in.nextLine().trim());

        int to_node;
        to_node = Integer.parseInt(in.nextLine().trim());

        res = find_longest_path(dag_nodes, dag_from, dag_to, dag_weight, from_node, to_node);
        for(int res_i = 0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }

        bw.close();
    }
}
