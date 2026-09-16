import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804L04_TwoHeapsMedian {

    static class MedianFinder {
        // low holds the smaller half (max on top), high holds the larger half (min on top)
        private final PriorityQueue<Integer> low = new PriorityQueue<>(Collections.reverseOrder());
        private final PriorityQueue<Integer> high = new PriorityQueue<>();

        void add(int x) {
            // put x on the correct side
            if (low.isEmpty() || x <= low.peek()) {
                low.add(x);
            } else {
                high.add(x);
            }
            // rebalance so low has the same size as high, or one more
            if (low.size() > high.size() + 1) {
                high.add(low.poll());
            } else if (high.size() > low.size()) {
                low.add(high.poll());
            }
        }

        double median() {
            if (low.isEmpty()) {
                throw new IllegalStateException("no numbers yet");
            }
            if (low.size() > high.size()) {
                return low.peek();
            }
            // long avoids overflow when adding two big ints
            return ((long) low.peek() + high.peek()) / 2.0;
        }
    }

    // slow reference: sort everything and pick the middle
    static double sortedMedian(List<Integer> values) {
        List<Integer> s = new ArrayList<>(values);
        Collections.sort(s);
        int n = s.size();
        return n % 2 == 1 ? s.get(n / 2) : ((long) s.get(n / 2 - 1) + s.get(n / 2)) / 2.0;
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        StringBuilder medians = new StringBuilder();
        for (int x : new int[]{5, 15, 1, 3, 8}) {
            mf.add(x);
            medians.append(mf.median()).append(' ');
        }
        System.out.println("medians: " + medians.toString().trim());
        MedianFinder big = new MedianFinder();
        big.add(Integer.MAX_VALUE);
        big.add(Integer.MAX_VALUE - 2);
        System.out.println("huge ints: " + (long) big.median());

        // self-check: empty case, overflow case, and sorting after every insert
        boolean ok = big.median() == Integer.MAX_VALUE - 1;
        ok &= medians.toString().trim().equals("5.0 10.0 5.0 4.0 5.0");
        try {
            new MedianFinder().median();
            ok = false;
        } catch (IllegalStateException e) {
            // expected: no median before any add
        }
        Random rnd = new Random(7);
        MedianFinder r = new MedianFinder();
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            int v = rnd.nextInt(201) - 100;
            r.add(v);
            all.add(v);
            ok &= r.median() == sortedMedian(all);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
