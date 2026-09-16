import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class M1805P01_FloodFill {

    static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // work on a copy so the caller's image stays unchanged
        int[][] img = new int[image.length][];
        for (int i = 0; i < image.length; i++) {
            img[i] = image[i].clone();
        }
        int original = img[sr][sc];
        // same color: nothing to do (and without this check we would loop forever)
        if (original == color) {
            return img;
        }
        Queue<int[]> queue = new ArrayDeque<>();
        img[sr][sc] = color;
        queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : DIRECTIONS) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                // recoloring a cell also marks it as visited
                if (r >= 0 && r < img.length && c >= 0 && c < img[0].length && img[r][c] == original) {
                    img[r][c] = color;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return img;
    }

    static void test(int[][] image, int sr, int sc, int color, int[][] expected) {
        int[][] got = floodFill(image, sr, sc, color);
        boolean pass = Arrays.deepEquals(got, expected);
        System.out.println(Arrays.deepToString(image) + " (" + sr + "," + sc + ") color=" + color + " -> "
                + Arrays.deepToString(got) + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2, new int[][]{{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        test(new int[][]{{0, 0, 0}, {0, 0, 0}}, 0, 0, 0, new int[][]{{0, 0, 0}, {0, 0, 0}});
        test(new int[][]{{5}}, 0, 0, 7, new int[][]{{7}});
        test(new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}}, 1, 1, 9, new int[][]{{1, 0, 1}, {0, 9, 0}, {1, 0, 1}});
        test(new int[][]{{3, 3, 4, 3}}, 0, 0, 1, new int[][]{{1, 1, 4, 3}});
    }
}
