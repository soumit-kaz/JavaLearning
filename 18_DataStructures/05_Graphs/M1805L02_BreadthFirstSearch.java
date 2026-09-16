import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class M1805L02_BreadthFirstSearch {

    // BFS: visit order, and dist[v] = fewest edges from the start (-1 if unreachable)
    static List<Integer> bfs(List<List<Integer>> adj, int start, int[] dist) {
        Arrays.fill(dist, -1);
        List<Integer> order = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        // mark when adding to the queue, so no vertex is added twice
        dist[start] = 0;
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order.add(u);
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
            }
        }
        return order;
    }

    // grid BFS: fewest steps from top-left to bottom-right, '#' is a wall
    static int gridSteps(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int[][] steps = new int[rows][cols];
        for (int[] row : steps) {
            Arrays.fill(row, -1);
        }
        // down, up, right, left
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> queue = new ArrayDeque<>();
        steps[0][0] = 0;
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                boolean inside = r >= 0 && r < rows && c >= 0 && c < cols;
                if (inside && grid[r].charAt(c) != '#' && steps[r][c] == -1) {
                    steps[r][c] = steps[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return steps[rows - 1][cols - 1];
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
        int[] dist = new int[7];
        List<Integer> order = bfs(adj, 0, dist);
        System.out.println("order: " + order);
        System.out.println("dist: " + Arrays.toString(dist));
        String[] maze = {"..#.", ".##.", "...."};
        System.out.println("grid steps: " + gridSteps(maze));

        boolean ok = order.equals(List.of(0, 1, 2, 3, 4)) && dist[4] == 3 && dist[5] == -1 && gridSteps(maze) == 5;
        // self-check: BFS distances obey the shortest-path rules on random graphs
        Random rnd = new Random(2);
        for (int t = 0; t < 300; t++) {
            int n = 1 + rnd.nextInt(10);
            int[][] edges = new int[rnd.nextInt(2 * n)][];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = new int[]{rnd.nextInt(n), rnd.nextInt(n)};
            }
            List<List<Integer>> g = undirected(n, edges);
            int[] d = new int[n];
            int reached = bfs(g, 0, d).size();
            int count = 0;
            for (int v = 0; v < n; v++) {
                count += d[v] >= 0 ? 1 : 0;
                boolean hasParent = v == 0;
                for (int w : g.get(v)) {
                    // neighbors are both reached or both unreached, at most 1 apart
                    ok &= (d[v] == -1) == (d[w] == -1);
                    ok &= Math.abs(d[v] - d[w]) <= 1;
                    hasParent |= d[v] > 0 && d[w] == d[v] - 1;
                }
                // every reached vertex except the start has a neighbor one step closer
                ok &= d[v] == -1 || hasParent;
            }
            ok &= reached == count;
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
