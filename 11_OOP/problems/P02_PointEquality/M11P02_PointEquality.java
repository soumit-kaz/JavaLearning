public class M11P02_PointEquality {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    // Bug: equals(BadPoint) overloads instead of overriding equals(Object)
    static class BadPoint {
        final int x;
        final int y;

        BadPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        boolean equals(BadPoint p) {
            return p != null && x == p.x && y == p.y;
        }
    }

    // Counts distinct points using equals, like a set would
    static int countDistinct(Object[] items) {
        int count = 0;
        for (int i = 0; i < items.length; i++) {
            boolean seen = false;
            for (int j = 0; j < i; j++) {
                if (items[j].equals(items[i])) {
                    seen = true;
                }
            }
            if (!seen) {
                count++;
            }
        }
        return count;
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
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        Point c = new Point(2, 1);
        check("(1,2) equals (1,2)", a.equals(b), "true");
        check("(1,2) equals (2,1)", a.equals(c), "false");
        check("(1,2) equals null", a.equals(null), "false");
        check("(1,2) equals \"(1,2)\"", a.equals("(1,2)"), "false");
        check("equal points same hash", a.hashCode() == b.hashCode(), "true");
        check("distinct [(1,2),(1,2),(2,1)]", countDistinct(new Object[]{a, b, c}), "2");

        BadPoint p = new BadPoint(1, 2);
        BadPoint q = new BadPoint(1, 2);
        Object asObject = q;
        check("BadPoint equals(BadPoint)", p.equals(q), "true");
        check("BadPoint equals(Object)", p.equals(asObject), "false");
        check("distinct BadPoints", countDistinct(new Object[]{p, q}), "2");

        if (failed) {
            System.exit(1);
        }
    }
}
