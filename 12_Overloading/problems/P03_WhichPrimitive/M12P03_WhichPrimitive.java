public class M12P03_WhichPrimitive {

    static String m(short x) { return "short"; }
    static String m(int x) { return "int"; }
    static String m(long x) { return "long"; }
    static String m(double x) { return "double"; }

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
        byte b = 1;
        short s = 2;
        char c = 'c';
        float f = 1.5f;

        // byte widens to the nearest type, short
        check("m(b)", m(b), "short");
        check("m(s)", m(s), "short");
        // char cannot widen to short
        check("m(c)", m(c), "int");
        check("m(1)", m(1), "int");
        check("m(1L)", m(1L), "long");
        check("m(f)", m(f), "double");
        // arithmetic on short gives int
        check("m(s + s)", m(s + s), "int");
        check("m(-s)", m(-s), "int");
        check("m(b++)", m(b++), "short");
        check("m('a' + 'b')", m('a' + 'b'), "int");
        check("m(5 / 2)", m(5 / 2), "int");
        check("m(5 / 2.0)", m(5 / 2.0), "double");
        check("m(1f + 1L)", m(1f + 1L), "double");
        check("m(true ? b : s)", m(true ? b : s), "short");
        // wrappers are unboxed in phase 2
        check("m(Integer.valueOf(1))", m(Integer.valueOf(1)), "int");
        check("m(Short.valueOf(s))", m(Short.valueOf(s)), "short");
        check("m(Character.valueOf(c))", m(Character.valueOf(c)), "int");

        if (failed) {
            System.exit(1);
        }
    }
}
