public class M14L03_OverloadMeetsOverride {

    static class Printer {
        String print(Object o) {
            return "Printer.print(Object)";
        }

        String print(String s) {
            return "Printer.print(String)";
        }
    }

    static class FancyPrinter extends Printer {
        @Override
        String print(Object o) {
            return "Fancy.print(Object)";
        }

        @Override
        String print(String s) {
            return "Fancy.print(String)";
        }

        // a new overload that Printer does not have
        String print(Integer i) {
            return "Fancy.print(Integer)";
        }
    }

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // println and + call toString() through Object: dynamic
        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }

        // trap: an overload, not an override of equals(Object)
        public boolean equals(Point other) {
            return other != null && x == other.x && y == other.y;
        }
    }

    public static void main(String[] args) {
        Printer p = new FancyPrinter();
        Object text = "hello";

        // compile time: declared type Object picks print(Object)
        // runtime: the object is a FancyPrinter, so its version runs
        System.out.println("p.print(text): " + p.print(text));
        System.out.println("p.print(\"hi\"): " + p.print("hi"));

        // Printer has no print(Integer), so print(Object) is chosen
        System.out.println("p.print(42): " + p.print(42));

        // with the declared type FancyPrinter, the new overload is visible
        FancyPrinter fp = new FancyPrinter();
        System.out.println("fp.print(42): " + fp.print(42));

        Object pointAsObject = new Point(1, 2);
        System.out.println("toString: " + pointAsObject);

        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        // declared as Point, so equals(Point) is chosen
        System.out.println("a.equals(b): " + a.equals(b));
        // declared as Object, so Object.equals(Object) compares identity
        Object bAsObject = b;
        System.out.println("a.equals(bAsObject): " + a.equals(bAsObject));
        // fix: override equals(Object) (and hashCode) with @Override
    }
}
