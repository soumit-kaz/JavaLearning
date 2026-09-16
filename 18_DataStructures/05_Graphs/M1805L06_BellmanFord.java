import java.util.Arrays;
import java.util.Random;

public class M1805L06_BellmanFord {

    static final long INF = Long.MAX_VALUE / 4;

    // Bellman-Ford: works with negative weights; returns null on a reachable negative cycle
    // edges are {from, to, weight}
    static long[] bellmanFord(int n, int[][] edges, int src) {
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[src] = 0;
        // a shortest path has at most n - 1 edges, so n - 1 rounds are enough
        for (int round = 1; round < n; round++) {
            for (int[] e : edges) {
                if (dist[e[0]] != INF && dist[e[0]] + e[2] < dist[e[1]]) {
                    dist[e[1]] = dist[e[0]] + e[2];
                }
            }
        }
        // one more improvement means a negative cycle
        for (int[] e : edges) {
            if (dist[e[0]] != INF && dist[e[0]] + e[2] < dist[e[1]]) {
                return null;
            }
        }
        return dist;
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
                    if (d[i][m] != INF && d[m][j] != INF) {
                        d[i][j] = Math.min(d[i][j], d[i][m] + d[m][j]);
                    }
                }
            }
        }
        return d;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 4}, {0, 2, 5}, {2, 1, -3}, {1, 3, 2}};
        long[] d = bellmanFord(4, edges, 0);
        System.out.println("dist: " + Arrays.toString(d));
        int[][] negCycle = {{0, 1, 1}, {1, 2, -2}, {2, 1, 1}};
        System.out.println("negative cycle: " + (bellmanFord(3, negCycle, 0) == null));

        boolean ok = Arrays.equals(d, new long[]{0, 2, 5, 4}) && bellmanFord(3, negCycle, 0) == null;
        // self-check: random graphs with some negative weights against the all-pairs table
        Random rnd = new Random(6);
        for (int t = 0; t < 500; t++) {
            int n = 1 + rnd.nextInt(7);
            int[][] es = new int[rnd.nextInt(2 * n)][];
            for (int i = 0; i < es.length; i++) {
                es[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n), rnd.nextInt(25) - 5};
            }
            int src = rnd.nextInt(n);
            long[][] all = allPairs(n, es);
            // a negative cycle counts only if src can reach it
            boolean cycle = false;
            for (int v = 0; v < n; v++) {
                cycle |= all[src][v] != INF && all[v][v] < 0;
            }
            long[] got = bellmanFord(n, es, src);
            ok &= (got == null) == cycle;
            if (got != null && !cycle) {
                ok &= Arrays.equals(got, all[src]);
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
