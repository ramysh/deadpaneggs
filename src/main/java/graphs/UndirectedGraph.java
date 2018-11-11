package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Created by rpurigella on 11/3/18.
 */
public class UndirectedGraph {

    private int V;
    private int E;
    private List<Vertex> adjList;

    public UndirectedGraph(In in) {
        this.V = in.readInt();
        adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            Vertex vertex = new Vertex();
            vertex.label = i;
            vertex.neighbors = new ArrayList<>();
            adjList.add(vertex);
        }
        this.E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            adjList.get(v).neighbors.add(adjList.get(w));
            adjList.get(w).neighbors.add(adjList.get(v));
        }
    }

    public void print(UndirectedGraph g) {
        StdOut.println("Graph = ");
        for (Vertex v : g.adjList) {
            StdOut.print(v.label + " : { ");
            for (Vertex w : v.neighbors) {
                StdOut.print(w.label + " ");
            }
            StdOut.println("}");
        }
    }

    public void dfsCC() {
        StdOut.println("DFS Connected components: ");
        Set<Integer> seen = new HashSet<>();
        for (Vertex v : adjList) {
            if (!seen.contains(v.label)) {
                List<Integer> comp = new ArrayList<>();
                explore(v, comp, seen);
                for (Integer cv : comp) {
                    StdOut.print(cv + " ");
                }
                StdOut.println();
            }
        }
    }

    private void explore(Vertex v, List<Integer> comp, Set<Integer> seen) {
        seen.add(v.label);
        comp.add(v.label);
        for (Vertex w : v.neighbors) {
            if (!seen.contains(w.label)) {
                explore(w, comp, seen);
            }
        }
    }

    public List<Integer> dfsPath(Vertex start, Vertex end) {
        Set<Integer> seen = new HashSet<>();
        Map<Integer, Vertex> parentMap = new HashMap<>();
        parentMap.put(start.label, null);
        explore(start, end, seen, parentMap);
        return buildPath(parentMap, end);
    }

    private void explore(Vertex start, Vertex end, Set<Integer> seen, Map<Integer, Vertex> parentMap) {
        seen.add(start.label);
        if (start.label == end.label) {
            return;
        }
        for (Vertex w : start.neighbors) {
            if (!seen.contains(w.label)) {
                parentMap.put(w.label, start);
                explore(w, end, seen, parentMap);
            }
        }
    }

    private List<Integer> buildPath(Map<Integer, Vertex> map, Vertex end) {
        if (!map.containsKey(end.label)) return new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Vertex vertex = end;
        while (vertex != null) {
            path.add(vertex.label);
            vertex = map.get(vertex.label);
        }
        Collections.reverse(path);
        return path;
    }

    public void bfsCC() {
        StdOut.println("BFS Connected components: ");
        Set<Integer> seen = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        for (Vertex v : adjList) {
            if (!seen.contains(v.label)) {
                seen.add(v.label);
                queue.add(v);
                List<Integer> comp = new ArrayList<>();
                while(!queue.isEmpty()) {
                    Vertex curr = queue.poll();
                    comp.add(curr.label);
                    for(Vertex w : curr.neighbors) {
                        if (!seen.contains(w.label)) {
                            seen.add(w.label);
                            queue.add(w);
                        }
                    }
                }
                for (Integer cv : comp) {
                    StdOut.print(cv + " ");
                }
                StdOut.println();
            }
        }
    }

    public List<Integer> bfsPath(Vertex start, Vertex end) {
        Set<Integer> seen = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Integer, Vertex> map = new HashMap<>();
        seen.add(start.label);
        queue.add(start);
        map.put(start.label, null);
        while(!queue.isEmpty()) {
            Vertex curr = queue.poll();
            if (curr.label == end.label) {
                return buildPath(map, end);
            }
            for (Vertex w : curr.neighbors) {
                if (!seen.contains(w.label)) {
                    seen.add(w.label);
                    map.put(w.label, curr);
                    queue.add(w);
                }
            }
        }
        return null;
    }

    public Map<Integer, List<Integer>> bfsMultiSourcePaths(List<Vertex> ms) {
        Set<Integer> seen = new HashSet<>();
        Queue<Vertex> queue = new LinkedList<>();
        Map<Integer, Vertex> map = new HashMap<>();
        for (Vertex start : ms) {
            seen.add(start.label);
            queue.add(start);
            map.put(start.label, null);
        }
        while(!queue.isEmpty()) {
            Vertex curr = queue.poll();
            for (Vertex w : curr.neighbors) {
                if (!seen.contains(w.label)) {
                    map.put(w.label, curr);
                    seen.add(w.label);
                    queue.add(w);
                }
            }
        }

        Map<Integer, List<Integer>> msPaths = new HashMap<>();
        for (int i = 0; i < V; i++) {
            msPaths.put(i, buildPath(map, adjList.get(i)));
        }
        return msPaths;
    }

    public boolean isCyclic() {
        Set<Integer> seen = new HashSet<>();
        for (Vertex v : adjList) {
            if (!seen.contains(v.label)) {
                if (explore(v, v, seen)) return true;
            }
        }
        return false;
    }

    private boolean explore(Vertex v, Vertex u, Set<Integer> seen) {
        seen.add(v.label);
        for (Vertex w : v.neighbors) {
            if (seen.contains(w.label) && u != w) {
                return true;
            }
            else if (!seen.contains(w.label)) {
                if (explore(w, v, seen)) return true;
            }
        }
        return false;
    }

    //TODO
    public boolean isTwoColorable() {
        return false;
    }

    public static void main(String[] args) {
        In in = new In("graphs/ud_graph_cycle.txt");
        UndirectedGraph g = new UndirectedGraph(in);
        g.print(g);

        /*
        g.dfsCC();

        g.bfsCC();

        Vertex target = new Vertex();
        Integer source = 0;
        target.label = 4;
        List<Integer> path = g.dfsPath(g.adjList.get(source), target);
        StdOut.print("DFS Path = ");
        for (Integer i : path) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        path = g.bfsPath(g.adjList.get(source), target);
        StdOut.print("BFS Path = ");
        for (Integer i : path) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        StdOut.print("Multi source shortest Paths from set { ");
        List<Vertex> set = new ArrayList<>();
        set.add(g.adjList.get(0));
        set.add(g.adjList.get(1));
        set.add(g.adjList.get(2));
        set.add(g.adjList.get(4));
        set.add(g.adjList.get(7));
        for (Vertex s : set) {
            StdOut.print(s.label + " ");
        }
        StdOut.println("}");
        Map<Integer, List<Integer>> msp = g.bfsMultiSourcePaths(set);
        for (Integer node : msp.keySet()) {
            StdOut.print(node + ": ");
            for (Integer nodePath : msp.get(node)) {
                StdOut.print(nodePath + " ");
            }
            StdOut.println();
        }

        */
        boolean hasCycle = g.isCyclic();
        StdOut.println("Has Cycle = " + hasCycle);

    }
}