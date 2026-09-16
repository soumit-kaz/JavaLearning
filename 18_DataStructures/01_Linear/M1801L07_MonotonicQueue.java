import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

public class M1801L07_MonotonicQueue {

    static int[] slidingWindowMax(int[] values, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("window size must be positive");
        }
        if (k > values.length) {
            return new int[0];
        }
        int[] result = new int[values.length - k + 1];
        // indexes whose values go from big (front) to small (back)
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            // drop the front index once it slides out of the window
            if (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            // smaller values behind the new one can never be a maximum again
            while (!deque.isEmpty() && values[deque.peekLast()] <= values[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            // the front is the biggest value in the current window
            if (i >= k - 1) {
                result[i - k + 1] = values[deque.peekFirst()];
            }
        }
        return result;
    }

    // O(n*k) version for the self-check
    static int[] slowWindowMax(int[] values, int k) {
        int[] result = new int[Math.max(0, values.length - k + 1)];
        for (int start = 0; start < result.length; start++) {
            int max = values[start];
            for (int i = start; i < start + k; i++) {
                max = Math.max(max, values[i]);
            }
            result[start] = max;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] values = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println("values: " + Arrays.toString(values));
        System.out.println("window max k=3: " + Arrays.toString(slidingWindowMax(values, 3)));
        try {
            slidingWindowMax(values, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("k=0: " + e.getClass().getSimpleName());
        }

        // known answers, then random arrays against the slow version
        boolean ok = Arrays.equals(slidingWindowMax(values, 3), new int[] {3, 3, 5, 5, 6, 7});
        ok &= slidingWindowMax(values, 9).length == 0;
        Random rnd = new Random(77);
        for (int t = 0; t < 500; t++) {
            int[] random = new int[rnd.nextInt(30)];
            for (int i = 0; i < random.length; i++) {
                random[i] = rnd.nextInt(10);
            }
            int k = 1 + rnd.nextInt(32);
            ok &= Arrays.equals(slidingWindowMax(random, k), slowWindowMax(random, k));
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
