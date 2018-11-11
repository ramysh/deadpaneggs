package graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;


/**
 * Created by rpurigella on 11/3/18.
 */
public class DirectedGraph {
    private int V;
    private int E;
    private List<Vertex> adjList;

    public DirectedGraph() {}

    public DirectedGraph(In in) {
        this.V = in.readInt();
        adjList = new ArrayList<>(V);
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
        }
    }

    public DirectedGraph reverse() {
        DirectedGraph g = new DirectedGraph();
        g.adjList = new ArrayList<>(this.V);
        for (int i = 0; i < this.V; i++) {
            Vertex vertex = new Vertex();
            vertex.label = i;
            vertex.neighbors = new ArrayList<>();
            g.adjList.add(vertex);
        }
        for (int i = 0; i < this.V; i++) {
            for (Vertex v : this.adjList.get(i).neighbors) {
                g.adjList.get(v.label).neighbors.add(g.adjList.get(i));
            }
        }
        return g;
    }

    public void print(DirectedGraph g) {
        StdOut.println("Graph = ");
        for (Vertex v : g.adjList) {
            StdOut.print(v.label + " : { ");
            for (Vertex w : v.neighbors) {
                StdOut.print(w.label + " ");
            }
            StdOut.println("}");
        }
    }

    public List<Integer> cycle() {
        Set<Integer> seen = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        List<Integer> cycle = new ArrayList<>();
        Vertex[] parent = new Vertex[V];

        for (Vertex v: adjList) {
            if (!seen.contains(v.label)) {
                explore(v, seen, onStack, cycle, parent);
            }
        }
        return cycle;
    }

    private void explore(Vertex v, Set<Integer> seen, Set<Integer> onStack, List<Integer> cycle, Vertex[] parent) {
        seen.add(v.label);
        onStack.add(v.label);
        for (Vertex w : v.neighbors) {
            if (!seen.contains(w.label)) {
                parent[w.label] = v;
                explore(w, seen, onStack, cycle, parent);
            } else if (onStack.contains(w.label)) {
                cycle.add(v.label);
                Vertex x = v;
                while(parent[x.label] != w) {
                    cycle.add(parent[x.label].label);
                    x = parent[x.label];
                }
                cycle.add(w.label);
                return;
            }
        }
        onStack.remove(v.label);
    }


    public List<Integer> topologicalOrderUsingQueue() {
        List<Integer> tOrder = new ArrayList<>();
        if (cycle().size() > 0) {
            return tOrder;
        }
        int[] inDegree = new int[V];
        Queue<Integer> sources = new LinkedList<>();
        for (Vertex v : adjList) {
            for (Vertex w : v.neighbors) {
                inDegree[w.label]++;
            }
        }
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) sources.add(i);
        }

        while (!sources.isEmpty()) {
            int v = sources.remove();
            tOrder.add(v);
            for (Vertex w : adjList.get(v).neighbors) {
                inDegree[w.label]--;
                if (inDegree[w.label] == 0) sources.add(w.label);
            }
        }

        return tOrder;
    }

    public List<Integer> topologicalOrder() {
        List<Integer> tOrder = new ArrayList<>();
        if (cycle().size() > 0) {
            return tOrder;
        }
        Stack<Integer> order = reversePostOrder();
        while(!order.isEmpty()) {
            tOrder.add(order.pop());
        }
        return tOrder;
    }

    Stack<Integer> reversePostOrder() {
        Stack<Integer> order = new Stack<>();
        Set<Integer> seen = new HashSet<>();
        for (Vertex v: adjList) {
            if (!seen.contains(v.label)) {
                explore(v, seen, order);
            }
        }
        return order;
    }

    private void explore(Vertex v, Set<Integer> seen, Stack<Integer> order) {
        seen.add(v.label);
        for (Vertex w : v.neighbors) {
            if (!seen.contains(w.label)) {
                explore(w, seen, order);
            }
        }
        order.push(v.label);
    }

    // Main recursive function to print all possible
    // topological sorts
    //TODO revisit backtracking
    private void allTopologicalSortsUtil(boolean[] visited,
                                         int[] indegree, ArrayList<Integer> list) {
        // To indicate whether all topological are found
        // or not
        boolean flag = false;

        for (int i = 0; i < this.V; i++) {
            // If indegree is 0 and not yet visited then
            // only choose that vertex
            if (!visited[i] && indegree[i] == 0) {

                // including in result
                visited[i] = true;
                list.add(i);
                for (Vertex adjacent : this.adjList.get(i).neighbors) {
                    indegree[adjacent.label]--;
                }
                allTopologicalSortsUtil(visited, indegree, list);

                // resetting visited, res and indegree for
                // backtracking
                visited[i] = false;
                list.remove(list.size() - 1);
                for (Vertex adjacent : this.adjList.get(i).neighbors) {
                    indegree[adjacent.label]++;
                }

                flag = true;
            }
        }
        // We reach here if all vertices are visited.
        // So we print the solution here
        if (!flag) {
            for (int i : list) System.out.print(i + " ");
            System.out.println();
        }

    }

    public static void main(String[] args) {
        In in = new In("graphs/d_graph001.txt");
        DirectedGraph g = new DirectedGraph(in);
        g.print(g);
        StdOut.print("Reverse ");
        g.print(g.reverse());

        List<Integer> cycle = g.cycle();
        StdOut.print("Cycle Path = ");
        for (Integer i : cycle) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        StdOut.print("Topological Order = ");
        for (Integer i : g.topologicalOrder()) {
            StdOut.print(i + " ");
        }
        StdOut.println();

        int[] inDegree = new int[g.V];
        for (Vertex v : g.adjList) {
            for (Vertex w : v.neighbors) {
                inDegree[w.label]++;
            }
        }
        g.allTopologicalSortsUtil(new boolean[g.V], inDegree, new ArrayList<Integer>());
    }
}
