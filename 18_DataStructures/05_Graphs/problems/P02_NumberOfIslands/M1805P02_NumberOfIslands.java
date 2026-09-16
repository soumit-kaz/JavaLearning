import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class M1805P02_NumberOfIslands {

    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != '1' || visited[r][c]) {
                    continue;
                }
                // new unvisited land: a new island, flood it with BFS
                islands++;
                Queue<int[]> queue = new ArrayDeque<>();
                visited[r][c] = true;
                queue.add(new int[]{r, c});
                while (!queue.isEmpty()) {
                    int[] cell = queue.poll();
                    for (int[] d : DIRECTIONS) {
                        int nr = cell[0] + d[0];
                        int nc = cell[1] + d[1];
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '1' && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            queue.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        return islands;
    }

    static int numIslandsUnionFind(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int cols = grid[0].length;
        int[] parent = new int[grid.length * cols];
        int islands = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != '1') {
                    continue;
                }
                // each land cell starts as its own island
                int id = r * cols + c;
                parent[id] = id;
                islands++;
                // merging with land above or to the left removes one island each time
                if (r > 0 && grid[r - 1][c] == '1' && union(parent, id, id - cols)) {
                    islands--;
                }
                if (c > 0 && grid[r][c - 1] == '1' && union(parent, id, id - 1)) {
                    islands--;
                }
            }
        }
        return islands;
    }

    private static boolean union(int[] parent, int a, int b) {
        int ra = find(parent, a);
        int rb = find(parent, b);
        if (ra == rb) {
            return false;
        }
        parent[ra] = rb;
        return true;
    }

    private static int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    static char[][] toGrid(String... rows) {
        char[][] grid = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            grid[i] = rows[i].toCharArray();
        }
        return grid;
    }

    static void test(String[] rows, int expected) {
        char[][] grid = toGrid(rows);
        int got = numIslands(grid);
        boolean pass = got == expected && numIslandsUnionFind(grid) == expected;
        System.out.println(Arrays.toString(rows) + " -> " + got + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new String[]{"11110", "11010", "11000", "00000"}, 1);
        test(new String[]{"11000", "11000", "00100", "00011"}, 3);
        test(new String[]{"101", "010", "101"}, 5);
        test(new String[]{"111", "101", "111"}, 1);
        test(new String[]{"0"}, 0);
        test(new String[]{}, 0);

        // BFS and union-find must always agree
        Random rnd = new Random(3);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            char[][] grid = new char[1 + rnd.nextInt(9)][1 + rnd.nextInt(9)];
            for (char[] row : grid) {
                for (int c = 0; c < row.length; c++) {
                    row[c] = rnd.nextBoolean() ? '1' : '0';
                }
            }
            match &= numIslands(grid) == numIslandsUnionFind(grid);
        }
        System.out.println("random 300 grids BFS vs union-find -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
