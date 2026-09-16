import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class M17P05_MergeIntervals {

    record Interval(int start, int end) {
        @Override
        public String toString() {
            return "[" + start + "," + end + "]";
        }
    }

    static List<Interval> merge(List<Interval> intervals) {
        // sort by start so overlapping intervals sit next to each other
        List<Interval> sorted = new ArrayList<>(intervals);
        sorted.sort(Comparator.comparingInt(Interval::start));

        List<Interval> merged = new ArrayList<>();
        for (Interval current : sorted) {
            int last = merged.size() - 1;
            if (last >= 0 && current.start() <= merged.get(last).end()) {
                // overlap: replace the last interval with a wider one
                Interval prev = merged.get(last);
                merged.set(last, new Interval(prev.start(), Math.max(prev.end(), current.end())));
            } else {
                merged.add(current);
            }
        }
        return merged;
    }

    static Interval iv(int start, int end) {
        return new Interval(start, end);
    }

    // print one PASS line, or stop with a FAIL line
    static void test(List<Interval> intervals, List<Interval> expected) {
        List<Interval> got = merge(intervals);
        String label = intervals + " -> " + got;
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected " + expected);
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        test(List.of(iv(1, 3), iv(2, 6), iv(8, 10), iv(15, 18)), List.of(iv(1, 6), iv(8, 10), iv(15, 18)));
        test(List.of(iv(1, 4), iv(4, 5)), List.of(iv(1, 5)));
        test(List.of(iv(1, 10), iv(2, 3), iv(4, 5)), List.of(iv(1, 10)));
        test(List.of(iv(5, 6), iv(1, 2)), List.of(iv(1, 2), iv(5, 6)));
        test(List.of(), List.of());
    }
}
