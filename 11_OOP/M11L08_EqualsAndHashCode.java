public class M11L08_EqualsAndHashCode {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // the parameter must be Object, or it is an overload instead of an override
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }
            Point other = (Point) o;
            return x == other.x && y == other.y;
        }

        // rule: equal objects must return the same hash code
        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    // a tiny lookup table: each object goes into the slot chosen by its hash code
    static Object[] slots = new Object[16];

    static int slotOf(Object o) {
        return Math.floorMod(o.hashCode(), slots.length);
    }

    static boolean contains(Object o) {
        Object found = slots[slotOf(o)];
        return found != null && found.equals(o);
    }

    public static void main(String[] args) {
        // == asks "same object?", equals asks "same value?"
        String a = new String("hello");
        String b = new String("hello");
        System.out.println("a == b: " + (a == b) + ", a.equals(b): " + a.equals(b));

        // string literals are shared from the string pool
        String c = "hello";
        String d = "hello";
        System.out.println("c == d: " + (c == d));

        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        System.out.println("p1.equals(p2): " + p1.equals(p2));
        System.out.println("p1.equals(null): " + p1.equals(null));
        System.out.println("hashes: " + p1.hashCode() + " " + p2.hashCode());

        // store p1, then find it with an equal point
        slots[slotOf(p1)] = p1;
        System.out.println("contains p2: " + contains(p2));

        // changing a field used by hashCode "loses" the stored object
        p1.x = 9;
        System.out.println("contains p1 after change: " + contains(p1));
    }
}
