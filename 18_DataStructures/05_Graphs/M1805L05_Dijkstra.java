import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1805L05_Dijkstra {

    static final long INF = Long.MAX_VALUE / 4;

    // Dijkstra: non-negative weights only; parent[] lets us rebuild the path
    static long[] dijkstra(List<List<int[]>> adj, int src, int[] parent) {
        long[] dist = new long[adj.size()];
        Arrays.fill(dist, INF);
        Arrays.fill(parent, -1);
        dist[src] = 0;
        // heap entries are {distance, vertex}, smallest distance first
        PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        heap.add(new long[]{0, src});
        while (!heap.isEmpty()) {
            long[] top = heap.poll();
            int u = (int) top[1];
            // skip stale entries (a shorter distance was found later)
            if (top[0] > dist[u]) {
                continue;
            }
            for (int[] e : adj.get(u)) {
                // relax: is going through u shorter?
                if (dist[u] + e[1] < dist[e[0]]) {
                    dist[e[0]] = dist[u] + e[1];
                    parent[e[0]] = u;
                    heap.add(new long[]{dist[e[0]], e[0]});
                }
            }
        }
        return dist;
    }

    // walk parent links back from the target
    static List<Integer> path(int[] parent, int target) {
        List<Integer> p = new ArrayList<>();
        for (int v = target; v != -1; v = parent[v]) {
            p.add(0, v);
        }
        return p;
    }

    // edges are {from, to, weight}
    static List<List<int[]>> build(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
        }
        return adj;
    }

    // check only: try every vertex as a middle stop (all pairs, O(n^3))
    static long[][] allPairs(int n, int[][] edges) {
        long[][] d = new long[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }
        for (int[] e : edges) {
            d[e[0]][e[1]] = Math.min(d[e[0]][e[1]], e[2]);
        }
        for (int m = 0; m < n; m++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][m] + d[m][j]);
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}};
        int[] parent = new int[4];
        long[] d = dijkstra(build(4, edges), 0, parent);
        System.out.println("dist: " + Arrays.toString(d));
        System.out.println("path to 3: " + path(parent, 3));

        boolean ok = d[3] == 4 && path(parent, 3).equals(List.of(0, 2, 1, 3));
        // self-check: random non-negative graphs against the all-pairs table
        Random rnd = new Random(5);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(9);
            int[][] es = new int[rnd.nextInt(3 * n)][];
            for (int i = 0; i < es.length; i++) {
                es[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n), rnd.nextInt(20)};
            }
            int src = rnd.nextInt(n);
            long[] got = dijkstra(build(n, es), src, new int[n]);
            long[] expected = allPairs(n, es)[src];
            for (int v = 0; v < n; v++) {
                ok &= got[v] == expected[v];
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
