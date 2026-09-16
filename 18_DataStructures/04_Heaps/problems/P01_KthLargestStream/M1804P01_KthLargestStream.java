import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804P01_KthLargestStream {

    static class KthLargest {
        private final int k;
        // a min-heap holding only the k largest values seen so far
        private final PriorityQueue<Integer> heap = new PriorityQueue<>();

        KthLargest(int k, int[] initial) {
            this.k = k;
            for (int x : initial) {
                add(x);
            }
        }

        // returns the k-th largest value, or null while fewer than k values were seen
        Integer add(int value) {
            if (heap.size() < k) {
                heap.offer(value);
            } else if (value > heap.peek()) {
                // the smallest of the top k is pushed out by a bigger value
                heap.poll();
                heap.offer(value);
            }
            // the root of the heap is the smallest of the k largest = the k-th largest
            return heap.size() < k ? null : heap.peek();
        }
    }

    static List<Integer> run(int k, int[] initial, int[] adds) {
        KthLargest kl = new KthLargest(k, initial);
        List<Integer> out = new ArrayList<>();
        for (int a : adds) {
            out.add(kl.add(a));
        }
        return out;
    }

    // slow answer: sort everything seen so far after each add
    static List<Integer> bruteForce(int k, int[] initial, int[] adds) {
        List<Integer> all = new ArrayList<>();
        for (int x : initial) {
            all.add(x);
        }
        List<Integer> out = new ArrayList<>();
        for (int a : adds) {
            all.add(a);
            List<Integer> sorted = new ArrayList<>(all);
            Collections.sort(sorted, Collections.reverseOrder());
            out.add(sorted.size() < k ? null : sorted.get(k - 1));
        }
        return out;
    }

    static void test(int k, int[] initial, int[] adds, List<Integer> expected) {
        List<Integer> got = run(k, initial, adds);
        System.out.println("k=" + k + " init=" + Arrays.toString(initial) + " add " + Arrays.toString(adds)
                + " -> " + got + "  " + (got.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(3, new int[]{4, 5, 8, 2}, new int[]{3, 5, 10, 9, 4}, List.of(4, 5, 5, 8, 8));
        test(1, new int[]{}, new int[]{-3, -2, -4, 0, 4}, List.of(-3, -2, -2, 0, 4));
        test(2, new int[]{0}, new int[]{-1, 1, -2, -4, 3}, List.of(-1, 0, 0, 0, 1));
        test(3, new int[]{}, new int[]{1, 2, 3}, Arrays.asList(null, null, 1));

        Random rnd = new Random(1);
        boolean match = true;
        for (int t = 0; t < 200; t++) {
            int k = 1 + rnd.nextInt(6);
            int[] initial = new int[rnd.nextInt(8)];
            for (int i = 0; i < initial.length; i++) {
                initial[i] = rnd.nextInt(100);
            }
            int[] adds = new int[20];
            for (int i = 0; i < adds.length; i++) {
                adds[i] = rnd.nextInt(100);
            }
            match &= run(k, initial, adds).equals(bruteForce(k, initial, adds));
        }
        System.out.println("random 200 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
