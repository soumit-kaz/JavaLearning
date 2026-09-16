public class M13P02_ShapeAreas {

    static class Shape {
        // a plain shape has no size
        double area() {
            return 0;
        }

        String name() {
            return "Shape";
        }

        // written once here, but uses the overridden methods
        String describe() {
            return name() + " " + twoDecimals(area());
        }
    }

    static class Circle extends Shape {
        final double r;

        Circle(double r) {
            this.r = r;
        }

        @Override
        double area() {
            return Math.PI * r * r;
        }

        @Override
        String name() {
            return "Circle";
        }
    }

    static class Rectangle extends Shape {
        final double w;
        final double h;

        Rectangle(double w, double h) {
            this.w = w;
            this.h = h;
        }

        @Override
        double area() {
            return w * h;
        }

        @Override
        String name() {
            return "Rectangle";
        }
    }

    static class Square extends Rectangle {
        Square(double side) {
            super(side, side);
        }

        // area() is inherited from Rectangle; only the name changes
        @Override
        String name() {
            return "Square";
        }
    }

    static double totalArea(Shape[] shapes) {
        double sum = 0;
        for (Shape s : shapes) {
            sum += s.area();
        }
        return sum;
    }

    // rounds to 2 decimals and always shows both digits, e.g. 6.00
    static String twoDecimals(double value) {
        long cents = Math.round(value * 100);
        return cents / 100 + "." + (cents % 100 < 10 ? "0" : "") + cents % 100;
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
        check("Circle(1)", new Circle(1).describe(), "Circle 3.14");
        check("Rectangle(2, 3)", new Rectangle(2, 3).describe(), "Rectangle 6.00");
        check("Square(4)", new Square(4).describe(), "Square 16.00");
        check("Shape()", new Shape().describe(), "Shape 0.00");
        Shape[] all = {new Circle(1), new Rectangle(2, 3), new Square(4)};
        check("total of the three", twoDecimals(totalArea(all)), "25.14");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
