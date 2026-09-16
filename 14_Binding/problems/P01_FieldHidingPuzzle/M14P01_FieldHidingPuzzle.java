public class M14P01_FieldHidingPuzzle {

    static class A {
        String name = "A";

        String getName() {
            return name;
        }

        // inside A, name always means A.name
        String show() {
            return name + "/" + getName();
        }
    }

    static class B extends A {
        String name = "B";

        @Override
        String getName() {
            return name;
        }
    }

    static class C extends B {
        String name = "C";

        // C does not override getName(), so B.getName() is used
        @Override
        String show() {
            return name + "/" + super.show();
        }
    }

    static boolean allPassed = true;

    static void test(String input, String actual, String expected) {
        boolean pass = actual.equals(expected);
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        A ab = new B();
        B bc = new C();
        C c = new C();

        test("A ab = new B(); ab.name", ab.name, "A");
        test("ab.getName()", ab.getName(), "B");
        test("ab.show()", ab.show(), "A/B");
        test("B bc = new C(); bc.name", bc.name, "B");
        test("bc.getName()", bc.getName(), "B");
        test("bc.show()", bc.show(), "C/A/B");
        test("C c = new C(); c.name", c.name, "C");
        test("((A) c).name", ((A) c).name, "A");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
