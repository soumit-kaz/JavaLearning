public class M16P03_DiamondPuzzle {

    interface A {
        default String who() {
            return "A";
        }
    }

    interface B extends A {
        @Override
        default String who() {
            return "B";
        }
    }

    interface C extends A {
    }

    interface D extends A {
        @Override
        default String who() {
            return "D";
        }
    }

    static class Base {
        public String who() {
            return "Base";
        }
    }

    // B is more specific than A
    static class P1 implements A, B {
    }

    // C only passes on A's default, which B overrides
    static class P2 implements B, C {
    }

    // the class wins
    static class P3 extends Base implements B {
    }

    // B and D clash, so we must override
    static class P4 implements B, D {
        @Override
        public String who() {
            return B.super.who() + D.super.who();
        }
    }

    static class P5 implements C {
    }

    static class Parent implements B {
    }

    // Parent only inherited B's default, so it still clashes with D
    static class P6 extends Parent implements D {
        @Override
        public String who() {
            return "6:" + super.who() + D.super.who();
        }
    }

    interface E extends B, D {
        @Override
        default String who() {
            return "E(" + B.super.who() + "," + D.super.who() + ")";
        }
    }

    // E is the most specific
    static class P7 implements E, A {
    }

    abstract static class AbstractWho {
        public abstract String who();
    }

    // an abstract class method also counts as "class wins", so we must implement it
    static class P8 extends AbstractWho implements B {
        @Override
        public String who() {
            return "8:" + B.super.who();
        }
    }

    // F turns who() abstract again
    interface F extends B {
        @Override
        String who();
    }

    static class P9 implements F {
        @Override
        public String who() {
            return "9";
        }
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
        // calls go through type A, so the object's real class decides
        check("P1 implements A, B", new P1().who(), "B");
        check("P2 implements B, C", new P2().who(), "B");
        check("P3 extends Base implements B", new P3().who(), "Base");
        check("P4 implements B, D (override)", new P4().who(), "BD");
        check("P5 implements C", new P5().who(), "A");
        check("P6 extends Parent(B) implements D (override)", new P6().who(), "6:BD");
        check("P7 implements E, A", new P7().who(), "E(B,D)");
        check("P8 extends AbstractWho implements B", new P8().who(), "8:B");
        check("P9 implements F", new P9().who(), "9");

        if (failures > 0) {
            System.exit(1);
        }
    }
}
