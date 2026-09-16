import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class M1806P02_RedundantConnection {

    static class DisjointSet {
        private final int[] parent;

        DisjointSet(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            // same root means this edge would close a cycle
            if (rootA == rootB) {
                return false;
            }
            parent[rootA] = rootB;
            return true;
        }
    }

    static int[] findRedundantConnection(int[][] edges) {
        // nodes are labelled 1..n, so make room for index n
        DisjointSet ds = new DisjointSet(edges.length + 1);
        for (int[] edge : edges) {
            // the first edge that closes a cycle is the last cycle edge in the input
            if (!ds.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }

    static boolean connectedWithout(int[][] edges, int skip) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            if (i != skip) {
                graph.get(edges[i][0]).add(edges[i][1]);
                graph.get(edges[i][1]).add(edges[i][0]);
            }
        }
        boolean[] seen = new boolean[n + 1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        seen[1] = true;
        int reached = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            reached++;
            for (int v : graph.get(u)) {
                if (!seen[v]) {
                    seen[v] = true;
                    queue.add(v);
                }
            }
        }
        return reached == n;
    }

    static int[] slow(int[][] edges) {
        // try removing edges from the back until the graph stays connected
        for (int skip = edges.length - 1; skip >= 0; skip--) {
            if (connectedWithout(edges, skip)) {
                return edges[skip];
            }
        }
        return new int[0];
    }

    static void check(String input, Object output, Object expected) {
        System.out.println(input + " -> " + output + "  " + (Objects.equals(output, expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        int[][] a = {{1, 2}, {1, 3}, {2, 3}};
        int[][] b = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[][] c = {{1, 2}, {2, 1}};
        int[][] d = {{3, 4}, {1, 2}, {2, 4}, {3, 5}, {2, 5}};
        check(Arrays.deepToString(a), Arrays.toString(findRedundantConnection(a)), "[2, 3]");
        check(Arrays.deepToString(b), Arrays.toString(findRedundantConnection(b)), "[1, 4]");
        check(Arrays.deepToString(c), Arrays.toString(findRedundantConnection(c)), "[2, 1]");
        check(Arrays.deepToString(d), Arrays.toString(findRedundantConnection(d)), "[2, 5]");

        Random random = new Random(4);
        boolean allMatch = true;
        for (int t = 0; t < 300; t++) {
            int n = 2 + random.nextInt(30);
            List<int[]> list = new ArrayList<>();
            // build a random tree, then add one extra edge
            for (int v = 2; v <= n; v++) {
                list.add(new int[]{1 + random.nextInt(v - 1), v});
            }
            int x = 1 + random.nextInt(n);
            int y = 1 + random.nextInt(n);
            while (y == x) {
                y = 1 + random.nextInt(n);
            }
            list.add(new int[]{x, y});
            Collections.shuffle(list, random);
            int[][] edges = list.toArray(new int[0][]);
            if (!Arrays.equals(findRedundantConnection(edges), slow(edges))) {
                allMatch = false;
            }
        }
        check("random 300 trees plus one edge", allMatch ? "match" : "differ", "match");
    }
}
