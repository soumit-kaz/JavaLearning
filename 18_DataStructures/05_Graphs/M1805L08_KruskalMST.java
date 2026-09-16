import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class M1805L08_KruskalMST {

    // small union-find from the previous lesson, just enough for Kruskal
    static int find(int[] parent, int x) {
        while (parent[x] != x) {
            // path halving keeps the trees short
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // Kruskal: take edges cheapest first, skip any that would close a cycle
    // edges are {u, v, weight}; returns the total weight, or -1 if the graph is disconnected
    static long kruskal(int n, int[][] edges, List<int[]> chosen) {
        int[][] sorted = edges.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[2], b[2]));
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        long total = 0;
        for (int[] e : sorted) {
            int a = find(parent, e[0]);
            int b = find(parent, e[1]);
            // same root: u and v are already connected
            if (a != b) {
                parent[a] = b;
                total += e[2];
                chosen.add(e);
            }
        }
        return chosen.size() == n - 1 ? total : -1;
    }

    // check only: try every set of n - 1 edges and keep the cheapest one that connects all
    static long bruteForce(int n, int[][] edges) {
        long best = -1;
        for (int mask = 0; mask < 1 << edges.length; mask++) {
            if (Integer.bitCount(mask) != n - 1) {
                continue;
            }
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            int joins = 0;
            long total = 0;
            for (int i = 0; i < edges.length; i++) {
                if ((mask >> i & 1) == 1) {
                    int a = find(parent, edges[i][0]);
                    int b = find(parent, edges[i][1]);
                    if (a != b) {
                        parent[a] = b;
                        joins++;
                    }
                    total += edges[i][2];
                }
            }
            // n - 1 joins means the chosen edges form a spanning tree
            if (joins == n - 1 && (best == -1 || total < best)) {
                best = total;
            }
        }
        return best;
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1, 4}, {0, 2, 3}, {1, 2, 1}, {1, 3, 2}, {2, 3, 4}, {3, 4, 2}, {4, 5, 6}, {3, 5, 7}};
        List<int[]> chosen = new ArrayList<>();
        long total = kruskal(6, edges, chosen);
        System.out.println("total: " + total);
        System.out.println("edges: " + Arrays.deepToString(chosen.toArray(new int[0][])));
        int[][] split = {{0, 1, 1}, {2, 3, 1}};
        System.out.println("disconnected: " + kruskal(4, split, new ArrayList<>()));

        boolean ok = total == 14 && chosen.size() == 5 && kruskal(4, split, new ArrayList<>()) == -1;
        // self-check: small random graphs against trying every edge set
        Random rnd = new Random(8);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(6);
            int[][] es = new int[rnd.nextInt(10)][];
            for (int i = 0; i < es.length; i++) {
                es[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n), rnd.nextInt(50)};
            }
            ok &= kruskal(n, es, new ArrayList<>()) == bruteForce(n, es);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
