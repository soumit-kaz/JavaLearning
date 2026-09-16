public class M13P04_EqualsOverloadTrap {

    static class BuggyIsbn {
        final String code;

        BuggyIsbn(String code) {
            this.code = code;
        }

        // bug: this is an overload, Object.equals(Object) is still in use
        public boolean equals(BuggyIsbn other) {
            return other != null && code.equals(other.code);
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }
    }

    static class Isbn {
        final String code;

        // ignore dashes and spaces so "978-0-13" equals "978013"
        Isbn(String raw) {
            this.code = raw.replace("-", "").replace(" ", "");
        }

        // fixed: parameter type Object and @Override
        @Override
        public boolean equals(Object o) {
            return o instanceof Isbn other && code.equals(other.code);
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }
    }

    // like library code: it only knows Object, so it calls equals(Object)
    static int indexOf(Object[] items, Object target) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(target)) {
                return i;
            }
        }
        return -1;
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
        BuggyIsbn b1 = new BuggyIsbn("123");
        BuggyIsbn b2 = new BuggyIsbn("123");
        Object b2AsObject = b2;
        check("buggy b1.equals(b2)", b1.equals(b2), true);
        check("buggy b1.equals((Object) b2)", b1.equals(b2AsObject), false);
        check("buggy indexOf([b1], b2)", indexOf(new Object[] {b1}, b2), -1);

        Isbn i1 = new Isbn("978-0-13");
        Isbn i2 = new Isbn("978 0 13");
        Object i2AsObject = i2;
        check("fixed i1.equals(i2)", i1.equals(i2), true);
        check("fixed i1.equals((Object) i2)", i1.equals(i2AsObject), true);
        check("fixed indexOf([x, i1], i2)", indexOf(new Object[] {new Isbn("111"), i1}, i2), 1);
        check("fixed same hashCode", i1.hashCode() == i2.hashCode(), true);
        if (failures > 0) {
            System.exit(1);
        }
    }
}
