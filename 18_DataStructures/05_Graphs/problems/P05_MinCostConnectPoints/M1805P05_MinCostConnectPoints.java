import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class M1805P05_MinCostConnectPoints {

    static int minCostConnectPoints(int[][] points) {
        int n = points.length;
        // best[v] = cheapest cost to connect v to the tree built so far
        int[] best = new int[n];
        Arrays.fill(best, Integer.MAX_VALUE);
        best[0] = 0;
        boolean[] inTree = new boolean[n];
        int total = 0;
        // Prim without a heap: O(n^2), ideal because every pair is an edge
        for (int step = 0; step < n; step++) {
            int v = -1;
            for (int u = 0; u < n; u++) {
                if (!inTree[u] && (v == -1 || best[u] < best[v])) {
                    v = u;
                }
            }
            inTree[v] = true;
            total += best[v];
            // v is in the tree now, so it may offer cheaper connections
            for (int u = 0; u < n; u++) {
                if (!inTree[u]) {
                    best[u] = Math.min(best[u], distance(points[v], points[u]));
                }
            }
        }
        return total;
    }

    static int distance(int[] a, int[] b) {
        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
    }

    static int kruskal(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new int[]{distance(points[i], points[j]), i, j});
            }
        }
        edges.sort((a, b) -> Integer.compare(a[0], b[0]));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        int total = 0;
        for (int[] e : edges) {
            int a = root(parent, e[1]);
            int b = root(parent, e[2]);
            if (a != b) {
                parent[a] = b;
                total += e[0];
            }
        }
        return total;
    }

    private static int root(int[] parent, int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    static void test(int[][] points, int expected) {
        int got = minCostConnectPoints(points);
        System.out.println(Arrays.deepToString(points) + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}, 20);
        test(new int[][]{{3, 12}, {-2, 5}, {-4, 1}}, 18);
        test(new int[][]{{0, 0}}, 0);
        test(new int[][]{{-1000000, -1000000}, {1000000, 1000000}}, 4000000);

        // Prim must agree with Kruskal
        Random rnd = new Random(10);
        boolean match = true;
        for (int t = 0; t < 200; t++) {
            int[][] points = new int[1 + rnd.nextInt(30)][];
            for (int i = 0; i < points.length; i++) {
                points[i] = new int[]{rnd.nextInt(201) - 100, rnd.nextInt(201) - 100};
            }
            match &= minCostConnectPoints(points) == kruskal(points);
        }
        System.out.println("random 200 cases Prim vs Kruskal -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
