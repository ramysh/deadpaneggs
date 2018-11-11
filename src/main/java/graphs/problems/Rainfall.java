package graphs.problems;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

/**
 * Created by rpurigella on 11/5/18.
 */
public class Rainfall {

    static void rainfall(int[][] eMap) {

        // Create Vertices
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < eMap.length; i++) {
            for (int j = 0; j < eMap[0].length; j++) {
                graph.put(gVertex(i,j, eMap.length), new ArrayList<Integer>());
            }
        }

        // get Edges
        for (int i = 0; i < eMap.length; i++) {
            for (int j = 0; j < eMap[0].length; j++) {
                int edge = getEdge(i, j, graph, eMap);
                if (edge != -1) {
                    graph.get(gVertex(i,j, eMap.length)).add(edge);
                }
            }
        }

        // get sinks
        Set<Integer> sinks = new HashSet<>();
        for (int v : graph.keySet()) {
            if (graph.get(v).size() == 0) {
                sinks.add(v);
            }
        }

        // reverse graph
        Map<Integer, List<Integer>> rGraph = new HashMap<>();
        for (int v : graph.keySet()) {
            rGraph.put(v, new ArrayList<Integer>());
        }
        for (int v : graph.keySet()) {
            for (int w : graph.get(v)) {
                rGraph.get(w).add(v);
            }
        }


        // find connected components
        List<List<Integer>> ccList = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for (int v : sinks) {
            if (!seen.contains(v)) {
                List<Integer> cc = new ArrayList<>();
                explore(v, rGraph, seen, cc);
                ccList.add(cc);
            }
        }

        StdOut.println("Sizes of basins = ");
        for (List<Integer> cc : ccList) {
            StdOut.println(cc.size());
        }
        StdOut.println();
        StdOut.println("Basins = ");
        char[][] basinMap = new char[eMap.length][eMap[0].length];
        char basin = 'A';
        for (List<Integer> cc : ccList) {
            for (int v : cc) {
                basinMap[getRow(v, eMap.length)][getCol(v, eMap.length)] = basin;
            }
            basin++;
        }
        for (char[] aBasinMap : basinMap) {
            for (int j = 0; j < basinMap[0].length; j++) {
                StdOut.print(aBasinMap[j] + " ");
            }
            StdOut.println();
        }
    }

    static void explore(int v, Map<Integer, List<Integer>> rGraph, Set<Integer> seen, List<Integer> cc) {
        seen.add(v);
        cc.add(v);
        for (int w : rGraph.get(v)) {
            if (!seen.contains(w)) {
                explore(w, rGraph, seen, cc);
            }
        }
    }

    static int gVertex(int i, int j, int len) {
        return i*len+j;
    }

    static int getRow(int v, int len) {
        return v/len;
    }

    static int getCol(int v, int len) {
        return v%len;
    }

    static int getEdge(int i, int j, Map<Integer, List<Integer>> graph, int[][] eMap) {
        int[] n1 = {0, 0, 1, -1};
        int[] n2 = {1, -1, 0, 0};
        int min = Integer.MAX_VALUE;
        int r = -1;
        int c = -1;
        for (int n = 0; n < n1.length; n++) {
            int row = i + n1[n];
            int col = j + n2[n];
            if (row < eMap.length && col < eMap[0].length && row >=0 && col >=0) {
                if (eMap[row][col] < min) {
                    min = eMap[row][col];
                    r = row;
                    c = col;
                }
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return eMap[r][c] < eMap[i][j] ? gVertex(r,c, eMap.length) : -1;
    }

    public static void main(String[] args) {
        int rows = StdIn.readInt();
        int cols = StdIn.readInt();

        int[][] eMap = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            //String sa[] = s.split(" ");
            for (int j = 0; j < cols; j++) {
                eMap[i][j] = StdIn.readInt();
            }
        }
        StdOut.println("Input = ");
        for (int[] anEMap : eMap) {
            for (int j = 0; j < eMap[0].length; j++) {
                StdOut.print(anEMap[j] + " ");
            }
            StdOut.println();
        }

        rainfall(eMap);
    }
}