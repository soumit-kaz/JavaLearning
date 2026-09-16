public class M15P02_NumberSequence {

    abstract static class Sequence {
        private boolean finished;

        // subclasses produce the next number, or call finish() when there is none
        abstract long produce();

        final void finish() {
            finished = true;
        }

        // shared code: collect up to "max" numbers into a string
        final String take(int max) {
            String result = "";
            for (int i = 0; i < max; i++) {
                long value = produce();
                if (finished) {
                    break;
                }
                result += (i == 0 ? "" : " ") + value;
            }
            return "[" + result + "]";
        }
    }

    static class Range extends Sequence {
        private long current;
        private final long end;
        private final long step;

        Range(long start, long end, long step) {
            if (step == 0) {
                throw new IllegalArgumentException("step must not be 0");
            }
            this.current = start;
            this.end = end;
            this.step = step;
        }

        @Override
        long produce() {
            boolean done = step > 0 ? current >= end : current <= end;
            if (done) {
                finish();
                return 0;
            }
            long value = current;
            current += step;
            return value;
        }
    }

    static class Fibonacci extends Sequence {
        private long a = 0;
        private long b = 1;
        private final long limit;

        Fibonacci(long limit) {
            this.limit = limit;
        }

        @Override
        long produce() {
            if (a > limit) {
                finish();
                return 0;
            }
            long value = a;
            long next = a + b;
            a = b;
            b = next;
            return value;
        }
    }

    // wraps another sequence and keeps only multiples of k
    static class MultiplesOf extends Sequence {
        private final Sequence source;
        private final long k;

        MultiplesOf(Sequence source, long k) {
            this.source = source;
            this.k = k;
        }

        @Override
        long produce() {
            while (true) {
                long value = source.produce();
                if (source.finished) {
                    finish();
                    return 0;
                }
                if (value % k == 0) {
                    return value;
                }
            }
        }
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
        check("Range(0, 10, 3)", new Range(0, 10, 3).take(100), "[0 3 6 9]");
        check("Range(5, 0, -2)", new Range(5, 0, -2).take(100), "[5 3 1]");
        check("Range(3, 3, 1)", new Range(3, 3, 1).take(100), "[]");
        check("Range(0, 1000, 1) take 4", new Range(0, 1000, 1).take(4), "[0 1 2 3]");
        check("Fibonacci(50)", new Fibonacci(50).take(100), "[0 1 1 2 3 5 8 13 21 34]");
        check("MultiplesOf(Range(1, 20, 1), 7)", new MultiplesOf(new Range(1, 20, 1), 7).take(100), "[7 14]");
        check("MultiplesOf(Fibonacci(100), 2)", new MultiplesOf(new Fibonacci(100), 2).take(100), "[0 2 8 34]");

        String error;
        try {
            new Range(0, 5, 0);
            error = "no exception";
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        check("Range(0, 5, 0)", error, "step must not be 0");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
