import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class M1804P02_LastStoneWeight {

    static int lastStoneWeight(int[] stones) {
        // a max-heap: the heaviest stone is always on top
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int s : stones) {
            heap.offer(s);
        }
        while (heap.size() > 1) {
            // smash the two heaviest stones together
            int y = heap.poll();
            int x = heap.poll();
            // equal stones both disappear; otherwise the difference goes back in
            if (y != x) {
                heap.offer(y - x);
            }
        }
        return heap.isEmpty() ? 0 : heap.peek();
    }

    static void test(int[] stones, int expected) {
        int got = lastStoneWeight(stones);
        System.out.println(Arrays.toString(stones) + " -> " + got + "  " + (got == expected ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(new int[]{2, 7, 4, 1, 8, 1}, 1);
        test(new int[]{1}, 1);
        test(new int[]{3, 3}, 0);
        test(new int[]{10, 4}, 6);
        test(new int[]{5, 5, 5}, 5);
        test(new int[]{31, 26, 33, 21, 40}, 9);
        test(new int[]{}, 0);
    }
}
