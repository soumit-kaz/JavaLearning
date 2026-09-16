import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class M1805L03_DepthFirstSearch {

    // recursive DFS: go as deep as possible before backing up
    static void dfsRecursive(List<List<Integer>> adj, int u, boolean[] seen, List<Integer> order) {
        seen[u] = true;
        order.add(u);
        for (int v : adj.get(u)) {
            if (!seen[v]) {
                dfsRecursive(adj, v, seen, order);
            }
        }
    }

    // iterative DFS with an explicit stack: safe on very deep graphs
    static List<Integer> dfsIterative(List<List<Integer>> adj, int start) {
        boolean[] seen = new boolean[adj.size()];
        List<Integer> order = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (seen[u]) {
                continue;
            }
            seen[u] = true;
            order.add(u);
            // push in reverse so neighbors pop in the same order as the recursive version
            List<Integer> nb = adj.get(u);
            for (int i = nb.size() - 1; i >= 0; i--) {
                stack.push(nb.get(i));
            }
        }
        return order;
    }

    // label each connected piece: comp[v] = component id; returns the count
    static int components(List<List<Integer>> adj, int[] comp) {
        Arrays.fill(comp, -1);
        boolean[] seen = new boolean[adj.size()];
        int count = 0;
        for (int s = 0; s < adj.size(); s++) {
            if (!seen[s]) {
                // one DFS finds one whole component
                for (int v : dfsIterative(adj, s)) {
                    seen[v] = true;
                    comp[v] = count;
                }
                count++;
            }
        }
        return count;
    }

    static List<List<Integer>> undirected(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        return adj;
    }

    public static void main(String[] args) {
        // 0-1, 0-2, 1-3, 2-3, 3-4 and a separate piece 5-6
        List<List<Integer>> adj = undirected(7, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {5, 6}});
        List<Integer> order = new ArrayList<>();
        dfsRecursive(adj, 0, new boolean[7], order);
        System.out.println("recursive: " + order);
        System.out.println("iterative: " + dfsIterative(adj, 0));
        int[] comp = new int[7];
        int count = components(adj, comp);
        System.out.println("components: " + count);
        System.out.println("labels: " + Arrays.toString(comp));

        boolean ok = order.equals(List.of(0, 1, 3, 2, 4)) && count == 2 && comp[6] == 1;
        // self-check: both DFS versions match; components agree with reachability
        Random rnd = new Random(3);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(10);
            int[][] edges = new int[rnd.nextInt(2 * n)][];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n)};
            }
            List<List<Integer>> g = undirected(n, edges);
            List<Integer> a = new ArrayList<>();
            boolean[] seen = new boolean[n];
            dfsRecursive(g, 0, seen, a);
            ok &= a.equals(dfsIterative(g, 0));
            int[] c = new int[n];
            components(g, c);
            for (int v = 0; v < n; v++) {
                ok &= seen[v] == (c[v] == c[0]);
            }
            // an edge never joins two different components
            for (int[] e : edges) {
                ok &= c[e[0]] == c[e[1]];
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
