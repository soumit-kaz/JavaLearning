import java.util.Iterator;
import java.util.NoSuchElementException;

public class M16P01_RangeIterable {

    // Range(start, end, step): end is excluded, step may be negative
    static class Range implements Iterable<Integer> {
        private final int start;
        private final int end;
        private final int step;

        Range(int start, int end, int step) {
            if (step == 0) {
                throw new IllegalArgumentException("step must not be 0");
            }
            this.start = start;
            this.end = end;
            this.step = step;
        }

        // number of values; long math avoids int overflow
        int size() {
            long distance = (long) end - start;
            // empty when there is no distance or the step points the wrong way
            if (distance == 0 || (distance > 0) != (step > 0)) {
                return 0;
            }
            long stepSize = Math.abs((long) step);
            return (int) ((Math.abs(distance) + stepSize - 1) / stepSize);
        }

        // a fresh iterator each time, so the range can be looped twice
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private long current = start;
                private int produced = 0;
                private final int total = size();

                // counting is safer than "current < end", which can overflow
                @Override
                public boolean hasNext() {
                    return produced < total;
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    int value = (int) current;
                    current += step;
                    produced++;
                    return value;
                }
            };
        }
    }

    static String text(Range range) {
        String result = "";
        for (int value : range) {
            result += (result.isEmpty() ? "" : ",") + value;
        }
        return "[" + result + "]";
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        int max = Integer.MAX_VALUE;
        check("Range(0,5,1)", text(new Range(0, 5, 1)), "[0,1,2,3,4]");
        check("Range(0,10,3)", text(new Range(0, 10, 3)), "[0,3,6,9]");
        check("Range(10,0,-4)", text(new Range(10, 0, -4)), "[10,6,2]");
        check("Range(3,3,1)", text(new Range(3, 3, 1)), "[]");
        check("Range(0,5,-1)", text(new Range(0, 5, -1)), "[]");
        check("Range(MAX-2,MAX,1)", text(new Range(max - 2, max, 1)), "[" + (max - 2) + "," + (max - 1) + "]");
        check("Range(MIN,MAX,MAX).size()", new Range(Integer.MIN_VALUE, max, max).size(), 3);

        Range twice = new Range(1, 4, 1);
        check("same Range looped twice", text(twice) + text(twice), "[1,2,3][1,2,3]");

        String result;
        try {
            new Range(0, 1, 0);
            result = "no error";
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }
        check("Range(0,1,0)", result, "step must not be 0");

        Iterator<Integer> it = new Range(0, 1, 1).iterator();
        it.next();
        try {
            it.next();
            result = "no error";
        } catch (NoSuchElementException e) {
            result = "NoSuchElementException";
        }
        check("next() after the end", result, "NoSuchElementException");

        if (failures > 0) {
            System.exit(1);
        }
    }
}
