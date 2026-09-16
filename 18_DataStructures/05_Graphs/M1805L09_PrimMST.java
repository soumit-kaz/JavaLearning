import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1805L09_PrimMST {

    // Prim: grow the tree from vertex 0, always adding the cheapest edge leaving it
    // edges are {u, v, weight}; returns the total weight, or -1 if the graph is disconnected
    static long prim(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }
        boolean[] inTree = new boolean[n];
        // heap entries are {weight, vertex}, cheapest first
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        heap.add(new int[]{0, 0});
        long total = 0;
        int added = 0;
        while (!heap.isEmpty()) {
            int[] top = heap.poll();
            int u = top[1];
            // an old entry for a vertex that is already in the tree
            if (inTree[u]) {
                continue;
            }
            inTree[u] = true;
            total += top[0];
            added++;
            for (int[] e : adj.get(u)) {
                if (!inTree[e[0]]) {
                    heap.add(new int[]{e[1], e[0]});
                }
            }
        }
        return added == n ? total : -1;
    }

    // check only: Kruskal from the previous lesson with a tiny union-find
    static long kruskal(int n, int[][] edges) {
        int[][] sorted = edges.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[2], b[2]));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        long total = 0;
        int joins = 0;
        for (int[] e : sorted) {
            int a = root(parent, e[0]);
            int b = root(parent, e[1]);
            if (a != b) {
                parent[a] = b;
                total += e[2];
                joins++;
            }
        }
        return joins == n - 1 ? total : -1;
    }

    static int root(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 2}, {2, 3, 4}, {3, 4, 2}, {4, 5, 6}, {3, 5, 7}};
        long total = prim(6, edges);
        System.out.println("total: " + total);
        int[][] split = {{0, 1, 1}, {2, 3, 1}};
        System.out.println("disconnected: " + prim(4, split));

        boolean ok = total == 14 && prim(4, split) == -1;
        // self-check: Prim and Kruskal always find the same total weight
        Random rnd = new Random(9);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(10);
            int[][] es = new int[rnd.nextInt(3 * n)][];
            for (int i = 0; i < es.length; i++) {
                es[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n), rnd.nextInt(50)};
            }
            ok &= prim(n, es) == kruskal(n, es);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
