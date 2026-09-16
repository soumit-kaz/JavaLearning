public class M09L06_StaticNestedClass {

    // private: only code inside this class can use it
    private static int secretCode = 1234;

    // static nested class: a class that lives inside another class
    static class Helper {
        // it can read the outer class's static members, even private ones
        static int readSecret() {
            return secretCode;
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            // this.x means the field; x alone means the parameter
            this.x = x;
            this.y = y;
        }

        String show() {
            return "(" + x + ", " + y + ")";
        }

        // a static method that makes and returns a new object
        static Point origin() {
            return new Point(0, 0);
        }
    }

    static class Animal {
        static String kind() {
            return "animal";
        }
    }

    // extends: Dog is a kind of Animal (module 13 explains this)
    static class Dog extends Animal {
        // same static method name: this HIDES Animal.kind(), it does not override it
        static String kind() {
            return "dog";
        }
    }

    public static void main(String[] args) {
        System.out.println("secret = " + Helper.readSecret());

        // inside this file the short name works
        Point p = new Point(3, 4);
        System.out.println("p = " + p.show());

        // the full name includes the outer class
        M09L06_StaticNestedClass.Point q = new M09L06_StaticNestedClass.Point(7, 8);
        System.out.println("q = " + q.show());
        System.out.println("origin = " + Point.origin().show());

        // for static methods, the class name you write decides which one runs
        System.out.println("Animal.kind() = " + Animal.kind());
        System.out.println("Dog.kind() = " + Dog.kind());
    }
}
