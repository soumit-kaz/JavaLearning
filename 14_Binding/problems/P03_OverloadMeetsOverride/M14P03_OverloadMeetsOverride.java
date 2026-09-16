public class M14P03_OverloadMeetsOverride {

    static class Animal {
        String meet(Animal a) {
            return "Animal meets Animal";
        }

        String meet(Dog d) {
            return "Animal meets Dog";
        }
    }

    static class Dog extends Animal {
        @Override
        String meet(Animal a) {
            return "Dog meets Animal";
        }

        @Override
        String meet(Dog d) {
            return "Dog meets Dog";
        }

        // a new overload that only Dog variables can see
        String meet(Cat c) {
            return "Dog meets Cat";
        }
    }

    static class Cat extends Animal {
        @Override
        String meet(Animal a) {
            return "Cat meets Animal";
        }
    }

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // an overload, not an override: equals(Object) is still Object's
        public boolean equals(Point other) {
            return other != null && x == other.x && y == other.y;
        }
    }

    // code that only knows Object calls equals(Object)
    static int indexOf(Object[] items, Object target) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    static boolean allPassed = true;

    static void test(String input, Object actual, Object expected) {
        boolean pass = String.valueOf(actual).equals(String.valueOf(expected));
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Animal a1 = new Dog();
        Animal a2 = new Dog();
        Dog d = new Dog();
        Cat c = new Cat();
        Animal ac = c;

        // compile time picks the parameter type, runtime picks the class
        test("a1.meet(a2)", a1.meet(a2), "Dog meets Animal");
        test("a1.meet(d)", a1.meet(d), "Dog meets Dog");
        test("a1.meet(c)", a1.meet(c), "Dog meets Animal");
        test("d.meet(c)", d.meet(c), "Dog meets Cat");
        test("d.meet(ac)", d.meet(ac), "Dog meets Animal");
        test("ac.meet(d)", ac.meet(d), "Animal meets Dog");
        test("c.meet(a1)", c.meet(a1), "Cat meets Animal");

        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Object p2AsObject = p2;
        Point[] points = { p1 };
        test("p1.equals(p2)", p1.equals(p2), true);
        test("p1.equals((Object) p2)", p1.equals(p2AsObject), false);
        test("indexOf(points, p2)", indexOf(points, p2), -1);
        if (!allPassed) {
            System.exit(1);
        }
    }
}
