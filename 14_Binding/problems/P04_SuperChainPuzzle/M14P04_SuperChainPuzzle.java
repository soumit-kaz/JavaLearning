public class M14P04_SuperChainPuzzle {

    static class L1 {
        String run() {
            return "L1.run>" + step();
        }

        String step() {
            return "L1.step";
        }

        String go() {
            return "L1.go>" + run();
        }
    }

    static class L2 extends L1 {
        @Override
        String run() {
            // super is fixed to L1, but step() inside L1.run() is dynamic
            return "L2.run>" + super.run();
        }

        @Override
        String step() {
            return "L2.step>" + super.step();
        }
    }

    static class L3 extends L2 {
        @Override
        String step() {
            return "L3.step";
        }

        @Override
        String go() {
            return "L3.go>" + super.go();
        }

        String direct() {
            // L2.step() runs, even though this object overrides step()
            return super.step();
        }

        String viaCast() {
            // a cast does not skip overriding
            return ((L1) this).step();
        }
    }

    static boolean allPassed = true;

    static void test(String input, String actual, String expected) {
        boolean pass = actual.equals(expected);
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test("new L1().go()", new L1().go(), "L1.go>L1.run>L1.step");
        test("new L2().run()", new L2().run(), "L2.run>L1.run>L2.step>L1.step");
        test("new L2().go()", new L2().go(), "L1.go>L2.run>L1.run>L2.step>L1.step");
        test("new L3().run()", new L3().run(), "L2.run>L1.run>L3.step");
        test("new L3().go()", new L3().go(), "L3.go>L1.go>L2.run>L1.run>L3.step");
        test("new L3().direct()", new L3().direct(), "L2.step>L1.step");
        test("new L3().viaCast()", new L3().viaCast(), "L3.step");
        if (!allPassed) {
            System.exit(1);
        }
    }
}
