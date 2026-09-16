public class M11P04_TimeOfDay {

    static final class Time {
        private final int totalSeconds;

        Time(int hours, int minutes, int seconds) {
            // Wrap around a 24-hour day, also for negative values
            this.totalSeconds = Math.floorMod(hours * 3600 + minutes * 60 + seconds, 24 * 3600);
        }

        Time(int hours, int minutes) {
            this(hours, minutes, 0);
        }

        Time(int hours) {
            this(hours, 0, 0);
        }

        Time() {
            this(0);
        }

        // Returns a new Time; this one never changes
        Time plusSeconds(int seconds) {
            return new Time(0, 0, totalSeconds + seconds);
        }

        boolean isBefore(Time other) {
            return totalSeconds < other.totalSeconds;
        }

        @Override
        public String toString() {
            int h = totalSeconds / 3600;
            int m = totalSeconds / 60 % 60;
            int s = totalSeconds % 60;
            return two(h) + ":" + two(m) + ":" + two(s);
        }

        private static String two(int n) {
            return n < 10 ? "0" + n : String.valueOf(n);
        }
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        check("Time()", new Time(), "00:00:00");
        check("Time(9)", new Time(9), "09:00:00");
        check("Time(9, 5)", new Time(9, 5), "09:05:00");
        check("Time(23, 59, 59)", new Time(23, 59, 59), "23:59:59");
        check("Time(1, 75, 70)", new Time(1, 75, 70), "02:16:10");
        check("Time(25, 0)", new Time(25, 0), "01:00:00");
        check("Time(0, -1)", new Time(0, -1), "23:59:00");

        Time t = new Time(23, 59, 30);
        Time later = t.plusSeconds(45);
        check("23:59:30 + 45s", later, "00:00:15");
        check("original unchanged", t, "23:59:30");
        check("00:00:15 before 23:59:30", later.isBefore(t), "true");

        if (failed) {
            System.exit(1);
        }
    }
}
