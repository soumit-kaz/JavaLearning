public class M14P02_StaticHidingPuzzle {

    static class Base {
        static int created;

        Base() {
            created++;
        }

        static String id() {
            return "Base";
        }

        String kind() {
            return "base";
        }

        // id() is fixed to Base.id(); kind() depends on the object
        String who() {
            return id() + ":" + kind();
        }
    }

    static class Derived extends Base {
        // a separate counter that hides Base.created
        static int created;

        Derived() {
            created++;
        }

        static String id() {
            return "Derived";
        }

        @Override
        String kind() {
            return "derived";
        }
    }

    static Base makeDerived() {
        return new Derived();
    }

    // static calls through a variable use the declared type
    @SuppressWarnings("static")
    static String idOf(Base ref) {
        return ref.id();
    }

    // the expression before the dot still runs, even for a static call
    @SuppressWarnings("static")
    static String idOfNewObject() {
        return makeDerived().id();
    }

    static boolean allPassed = true;

    static void test(String input, Object actual, Object expected) {
        boolean pass = String.valueOf(actual).equals(String.valueOf(expected));
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Base b = new Derived();
        Derived d = new Derived();
        Base plain = new Base();

        test("b.id() with Base b = new Derived()", idOf(b), "Base");
        test("Derived.id()", Derived.id(), "Derived");
        test("b.who()", b.who(), "Base:derived");
        test("plain.who()", plain.who(), "Base:base");
        test("nothing.id() with Base nothing = null", idOf(null), "Base");
        test("Base.created", Base.created, 3);
        test("Derived.created", Derived.created, 2);
        test("makeDerived().id()", idOfNewObject(), "Base");
        test("Derived.created afterwards", Derived.created, 3);
        test("d.who()", d.who(), "Base:derived");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
