import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class M1805L04_TopologicalSort {

    // Kahn (BFS style): repeatedly take a vertex with in-degree 0; null if there is a cycle
    static List<Integer> kahn(List<List<Integer>> adj) {
        int n = adj.size();
        int[] inDegree = new int[n];
        for (List<Integer> out : adj) {
            for (int v : out) {
                inDegree[v]++;
            }
        }
        Deque<Integer> ready = new ArrayDeque<>();
        for (int v = 0; v < n; v++) {
            if (inDegree[v] == 0) {
                ready.add(v);
            }
        }
        List<Integer> order = new ArrayList<>();
        while (!ready.isEmpty()) {
            int u = ready.poll();
            order.add(u);
            // removing u lowers the in-degree of everything after it
            for (int v : adj.get(u)) {
                inDegree[v]--;
                if (inDegree[v] == 0) {
                    ready.add(v);
                }
            }
        }
        // vertices stuck on a cycle never reach in-degree 0
        return order.size() == n ? order : null;
    }

    static final int WHITE = 0;
    static final int GRAY = 1;
    static final int BLACK = 2;

    // DFS style: reverse finish order is a topological order; null if there is a cycle
    static List<Integer> dfsOrder(List<List<Integer>> adj) {
        int[] color = new int[adj.size()];
        List<Integer> finished = new ArrayList<>();
        for (int v = 0; v < adj.size(); v++) {
            if (color[v] == WHITE && !visit(adj, v, color, finished)) {
                return null;
            }
        }
        Collections.reverse(finished);
        return finished;
    }

    // returns false when a cycle is found
    static boolean visit(List<List<Integer>> adj, int u, int[] color, List<Integer> finished) {
        // gray = still on the current path
        color[u] = GRAY;
        for (int v : adj.get(u)) {
            // an edge back to a gray vertex closes a cycle
            if (color[v] == GRAY) {
                return false;
            }
            if (color[v] == WHITE && !visit(adj, v, color, finished)) {
                return false;
            }
        }
        color[u] = BLACK;
        finished.add(u);
        return true;
    }

    // valid order: every edge u -> v has u before v
    static boolean isValid(List<List<Integer>> adj, List<Integer> order) {
        if (order.size() != adj.size()) {
            return false;
        }
        int[] pos = new int[adj.size()];
        for (int i = 0; i < order.size(); i++) {
            pos[order.get(i)] = i;
        }
        for (int u = 0; u < adj.size(); u++) {
            for (int v : adj.get(u)) {
                if (pos[u] >= pos[v]) {
                    return false;
                }
            }
        }
        return true;
    }

    static List<List<Integer>> directed(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }
        return adj;
    }

    public static void main(String[] args) {
        // 5 -> 2, 5 -> 0, 4 -> 0, 4 -> 1, 2 -> 3, 3 -> 1
        List<List<Integer>> dag = directed(6, new int[][]{{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}});
        List<Integer> k = kahn(dag);
        List<Integer> d = dfsOrder(dag);
        System.out.println("kahn: " + k);
        System.out.println("dfs: " + d);
        List<List<Integer>> cyclic = directed(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        System.out.println("cycle: " + kahn(cyclic));

        boolean ok = isValid(dag, k) && isValid(dag, d) && kahn(cyclic) == null && dfsOrder(cyclic) == null;
        // self-check: both methods agree on "has a cycle" and give valid orders
        Random rnd = new Random(4);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(8);
            int[][] edges = new int[rnd.nextInt(2 * n)][];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n)};
            }
            List<List<Integer>> g = directed(n, edges);
            List<Integer> a = kahn(g);
            List<Integer> b = dfsOrder(g);
            ok &= (a == null) == (b == null);
            if (a != null && b != null) {
                ok &= isValid(g, a) && isValid(g, b);
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
