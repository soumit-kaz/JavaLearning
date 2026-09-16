import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804P04_MeetingRooms {

    // meetings are [start, end); a meeting ending at t frees its room for one starting at t
    static int minRooms(int[][] meetings) {
        int[][] sorted = meetings.clone();
        // handle meetings in order of start time
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        // end times of the meetings currently using a room; the earliest end is on top
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        int rooms = 0;
        for (int[] m : sorted) {
            // if the earliest meeting already ended, reuse its room
            if (!endTimes.isEmpty() && endTimes.peek() <= m[0]) {
                endTimes.poll();
            } else {
                rooms++;
            }
            endTimes.offer(m[1]);
        }
        return rooms;
    }

    // slow answer: the most meetings running at the same moment (checked at every start time)
    static int bruteForce(int[][] meetings) {
        int best = 0;
        for (int[] m : meetings) {
            int running = 0;
            for (int[] other : meetings) {
                if (other[0] <= m[0] && m[0] < other[1]) {
                    running++;
                }
            }
            best = Math.max(best, running);
        }
        return best;
    }

    static void test(int[][] meetings, int expected) {
        int got = minRooms(meetings);
        System.out.println(Arrays.deepToString(meetings) + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[][]{{0, 30}, {5, 10}, {15, 20}}, 2);
        test(new int[][]{{7, 10}, {2, 4}}, 1);
        test(new int[][]{{1, 5}, {5, 10}, {10, 15}}, 1);
        test(new int[][]{{1, 10}, {2, 9}, {3, 8}, {4, 7}}, 4);
        test(new int[][]{}, 0);

        Random rnd = new Random(7);
        boolean match = true;
        for (int t = 0; t < 300; t++) {
            int[][] meetings = new int[rnd.nextInt(20)][];
            for (int i = 0; i < meetings.length; i++) {
                int start = rnd.nextInt(50);
                meetings[i] = new int[]{start, start + 1 + rnd.nextInt(15)};
            }
            match &= minRooms(meetings) == bruteForce(meetings);
        }
        System.out.println("random 300 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
