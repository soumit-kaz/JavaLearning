public class M12P02_MaxOverloads {

    static String max(int a, int b) {
        return "int " + Math.max(a, b);
    }

    static String max(long a, long b) {
        return "long " + Math.max(a, b);
    }

    static String max(double a, double b) {
        return "double " + Math.max(a, b);
    }

    static String max(int a, int b, int c) {
        return "three ints " + Math.max(a, Math.max(b, c));
    }

    static String max(String a, String b) {
        return "String " + (a.compareTo(b) >= 0 ? a : b);
    }

    // fallback for any other number of ints
    static String max(int... values) {
        if (values.length == 0) {
            return "varargs none";
        }
        int best = values[0];
        for (int v : values) {
            best = Math.max(best, v);
        }
        return "varargs " + best;
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
        check("max(3, 7)", max(3, 7), "int 7");
        check("max(3L, 7)", max(3L, 7), "long 7");
        check("max(3, 7.5)", max(3, 7.5), "double 7.5");
        check("max(2.5f, 1)", max(2.5f, 1), "double 2.5");
        check("max(1, 9, 4)", max(1, 9, 4), "three ints 9");
        check("max(1, 2, 3, 4)", max(1, 2, 3, 4), "varargs 4");
        check("max(5)", max(5), "varargs 5");
        check("max()", max(), "varargs none");
        check("max(\"pear\", \"apple\")", max("pear", "apple"), "String pear");
        check("max('a', 'b')", max('a', 'b'), "int 98");
        check("max((short) 4, (byte) 6)", max((short) 4, (byte) 6), "int 6");

        if (failed) {
            System.exit(1);
        }
    }
}
