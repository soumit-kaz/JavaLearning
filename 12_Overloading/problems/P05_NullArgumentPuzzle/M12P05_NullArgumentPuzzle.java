public class M12P05_NullArgumentPuzzle {

    static class Animal {
    }

    static class Dog extends Animal {
    }

    static String a(Object o) { return "Object"; }
    static String a(String s) { return "String"; }

    static String b(Object o) { return "Object"; }
    static String b(Object[] o) { return "Object[]"; }
    static String b(String[] o) { return "String[]"; }

    static String c(Object o) { return "Object"; }
    static String c(int... xs) { return xs == null ? "int... (null array)" : "int..."; }

    static String d(Animal a) { return "Animal"; }
    static String d(Dog d) { return "Dog"; }

    static String g(Object o) { return "Object"; }
    static String g(Object... os) { return os == null ? "Object... (null array)" : "Object... (" + os.length + ")"; }

    static String i(char[] cs) { return "char[]"; }
    static String i(Object o) { return "Object"; }

    static String k(int x) { return "int"; }
    static String k(Integer x) { return "Integer"; }

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
        check("a(null)", a(null), "String");
        check("a((Object) null)", a((Object) null), "Object");
        check("b(null)", b(null), "String[]");
        check("b((Object[]) null)", b((Object[]) null), "Object[]");
        // null fits the int[] parameter, and int[] is more specific than Object
        check("c(null)", c(null), "int... (null array)");
        check("c((Object) null)", c((Object) null), "Object");
        check("d(null)", d(null), "Dog");
        check("d((Animal) null)", d((Animal) null), "Animal");
        check("g((Object) null)", g((Object) null), "Object");
        check("g((Object[]) null)", g((Object[]) null), "Object... (null array)");
        check("g(null, null)", g(null, null), "Object... (2)");
        check("i(null)", i(null), "char[]");
        // null cannot be an int, so only Integer fits
        check("k(null)", k(null), "Integer");

        if (failed) {
            System.exit(1);
        }
    }
}
