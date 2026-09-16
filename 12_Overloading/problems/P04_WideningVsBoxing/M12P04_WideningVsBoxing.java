public class M12P04_WideningVsBoxing {

    static String p(long x) { return "long"; }
    static String p(Integer x) { return "Integer"; }
    static String p(Object x) { return "Object"; }
    static String p(int... x) { return "int..."; }

    static String r(Long x) { return "Long"; }
    static String r(Object x) { return "Object"; }

    static String s(double x) { return "double"; }
    static String s(Integer x) { return "Integer"; }

    static String u(long x) { return "long"; }
    static String u(Integer... x) { return "Integer..."; }

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
        // widening beats boxing
        check("p(5)", p(5), "long");
        check("p('x')", p('x'), "long");
        check("p(Integer.valueOf(5))", p(Integer.valueOf(5)), "Integer");
        // a Short is an Object, found in phase 1
        check("p(Short.valueOf((short) 5))", p(Short.valueOf((short) 5)), "Object");
        // double cannot widen to long, so it is boxed
        check("p(5.0)", p(5.0), "Object");
        check("p()", p(), "int...");
        check("p(1, 2)", p(1, 2), "int...");

        // int cannot widen and box to Long, but it can box to Integer -> Object
        check("r(5)", r(5), "Object");
        check("r(5L)", r(5L), "Long");

        check("s(5)", s(5), "double");
        check("s(Integer.valueOf(5))", s(Integer.valueOf(5)), "Integer");

        // unboxing then widening beats varargs
        check("u(Integer.valueOf(5))", u(Integer.valueOf(5)), "long");
        check("u(1, 2)", u(1, 2), "Integer...");

        if (failed) {
            System.exit(1);
        }
    }
}
