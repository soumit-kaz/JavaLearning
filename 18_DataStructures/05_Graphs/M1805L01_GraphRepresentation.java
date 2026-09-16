import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M1805L01_GraphRepresentation {

    // one weighted edge: from -> to with a cost
    record Edge(int from, int to, int weight) {
        @Override
        public String toString() {
            return from + "->" + to + "(" + weight + ")";
        }
    }

    // adjacency matrix: w[u][v] is the weight, 0 means "no edge"
    static class MatrixGraph {
        final int[][] w;
        final boolean directed;

        MatrixGraph(int n, boolean directed) {
            w = new int[n][n];
            this.directed = directed;
        }

        void addEdge(int u, int v, int weight) {
            w[u][v] = weight;
            // undirected: store both directions
            if (!directed) {
                w[v][u] = weight;
            }
        }

        boolean hasEdge(int u, int v) {
            return w[u][v] != 0;
        }
    }

    // adjacency list: one list of {neighbor, weight} per vertex
    static class ListGraph {
        final List<List<int[]>> adj = new ArrayList<>();
        final boolean directed;

        ListGraph(int n, boolean directed) {
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            this.directed = directed;
        }

        void addEdge(int u, int v, int weight) {
            adj.get(u).add(new int[]{v, weight});
            if (!directed) {
                adj.get(v).add(new int[]{u, weight});
            }
        }

        // checking an edge costs O(degree) here, O(1) in the matrix
        int weight(int u, int v) {
            for (int[] e : adj.get(u)) {
                if (e[0] == v) {
                    return e[1];
                }
            }
            return 0;
        }

        List<Integer> neighbors(int u) {
            List<Integer> out = new ArrayList<>();
            for (int[] e : adj.get(u)) {
                out.add(e[0]);
            }
            return out;
        }
    }

    // convert an edge list (the simplest form) into an adjacency list
    static ListGraph fromEdges(int n, List<Edge> edges, boolean directed) {
        ListGraph g = new ListGraph(n, directed);
        for (Edge e : edges) {
            g.addEdge(e.from(), e.to(), e.weight());
        }
        return g;
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 4));
        edges.add(new Edge(0, 2, 1));
        edges.add(new Edge(2, 1, 2));
        edges.add(new Edge(1, 3, 5));

        ListGraph undirected = fromEdges(4, edges, false);
        ListGraph directed = fromEdges(4, edges, true);
        System.out.println("undirected neighbors of 1: " + undirected.neighbors(1));
        System.out.println("directed neighbors of 1: " + directed.neighbors(1));
        System.out.println("edges: " + edges);

        // self-check: matrix and list must agree on random graphs
        boolean ok = undirected.neighbors(1).equals(List.of(0, 2, 3)) && directed.neighbors(1).equals(List.of(3));
        Random rnd = new Random(1);
        for (int t = 0; t < 200; t++) {
            int n = 1 + rnd.nextInt(8);
            boolean dir = rnd.nextBoolean();
            List<Edge> list = new ArrayList<>();
            MatrixGraph m = new MatrixGraph(n, dir);
            boolean[][] used = new boolean[n][n];
            for (int k = 0; k < 2 * n; k++) {
                int u = rnd.nextInt(n);
                int v = rnd.nextInt(n);
                // keep it simple: no self-loops, no parallel edges
                if (u == v || used[u][v] || used[v][u]) {
                    continue;
                }
                used[u][v] = true;
                int w = 1 + rnd.nextInt(9);
                list.add(new Edge(u, v, w));
                m.addEdge(u, v, w);
            }
            ListGraph g = fromEdges(n, list, dir);
            int degreeSum = 0;
            for (int u = 0; u < n; u++) {
                degreeSum += g.adj.get(u).size();
                for (int v = 0; v < n; v++) {
                    ok &= m.w[u][v] == g.weight(u, v);
                    ok &= m.hasEdge(u, v) == (g.weight(u, v) != 0);
                }
            }
            // undirected edges are stored twice
            ok &= degreeSum == (dir ? list.size() : 2 * list.size());
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
