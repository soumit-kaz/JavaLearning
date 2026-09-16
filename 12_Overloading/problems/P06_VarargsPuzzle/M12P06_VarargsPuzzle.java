public class M12P06_VarargsPuzzle {

    static String v(int... xs) { return "int... " + xs.length; }
    static String v(long... xs) { return "long... " + xs.length; }

    static String w(Object... xs) { return "Object... " + (xs == null ? "null" : xs.length); }
    static String w(Integer... xs) { return "Integer... " + (xs == null ? "null" : xs.length); }

    static String x(Object o) { return "Object"; }
    static String x(Object... os) { return "Object... " + os.length; }

    static String y(String head, Object... rest) { return "String, Object... " + rest.length; }
    static String y(Object... all) { return "Object... " + all.length; }

    static String z(int a, int b) { return "int, int"; }
    static String z(int... xs) { return "int... " + xs.length; }
    static String z(Integer a, Integer b) { return "Integer, Integer"; }

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
        check("v(1)", v(1), "int... 1");
        check("v()", v(), "int... 0");
        check("v(1, 2L)", v(1, 2L), "long... 2");
        check("v(new long[3])", v(new long[3]), "long... 3");

        check("w()", w(), "Integer... 0");
        check("w(1)", w(1), "Integer... 1");
        check("w(1, \"a\")", w(1, "a"), "Object... 2");
        check("w((Integer[]) null)", w((Integer[]) null), "Integer... null");
        check("w((Object) null)", w((Object) null), "Object... 1");

        check("x(\"s\")", x("s"), "Object");
        check("x()", x(), "Object... 0");
        // an int[] is one Object, not an Object[]
        check("x(new int[0])", x(new int[0]), "Object");
        check("x((Object[]) new String[2])", x((Object[]) new String[2]), "Object... 2");

        check("y(\"a\")", y("a"), "String, Object... 0");
        check("y(1)", y(1), "Object... 1");
        check("y(\"a\", \"b\")", y("a", "b"), "String, Object... 1");

        check("z(1, 2)", z(1, 2), "int, int");
        check("z(1)", z(1), "int... 1");
        check("z(Integer.valueOf(1), Integer.valueOf(2))", z(Integer.valueOf(1), Integer.valueOf(2)), "Integer, Integer");

        if (failed) {
            System.exit(1);
        }
    }
}
