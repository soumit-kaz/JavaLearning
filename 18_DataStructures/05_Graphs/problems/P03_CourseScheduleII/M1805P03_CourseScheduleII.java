import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class M1805P03_CourseScheduleII {

    static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            next.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];
        // [a, b] means b must come before a, so the edge is b -> a
        for (int[] p : prerequisites) {
            next.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }
        // courses with no prerequisites can be taken first
        Queue<Integer> ready = new ArrayDeque<>();
        for (int c = 0; c < numCourses; c++) {
            if (inDegree[c] == 0) {
                ready.add(c);
            }
        }
        int[] order = new int[numCourses];
        int taken = 0;
        while (!ready.isEmpty()) {
            int c = ready.poll();
            order[taken++] = c;
            for (int after : next.get(c)) {
                inDegree[after]--;
                if (inDegree[after] == 0) {
                    ready.add(after);
                }
            }
        }
        // some courses never became ready: there is a cycle
        return taken == numCourses ? order : new int[0];
    }

    static void test(int n, int[][] prerequisites, int[] expected) {
        int[] got = findOrder(n, prerequisites);
        boolean pass = Arrays.equals(got, expected);
        System.out.println("n=" + n + " prereq=" + Arrays.deepToString(prerequisites) + " -> " + Arrays.toString(got)
                + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(2, new int[][]{{1, 0}}, new int[]{0, 1});
        test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, new int[]{0, 1, 2, 3});
        test(1, new int[][]{}, new int[]{0});
        test(2, new int[][]{{0, 1}, {1, 0}}, new int[]{});
        test(3, new int[][]{{0, 0}}, new int[]{});
        test(3, new int[][]{{1, 0}, {1, 0}, {2, 1}}, new int[]{0, 1, 2});
        test(3, new int[][]{{0, 2}, {1, 2}}, new int[]{2, 0, 1});
    }
}
