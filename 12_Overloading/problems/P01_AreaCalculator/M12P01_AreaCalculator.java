public class M12P01_AreaCalculator {

    record Circle(double radius) {
    }

    // square
    static double area(double side) {
        return side * side;
    }

    // rectangle
    static double area(double width, double height) {
        return width * height;
    }

    // triangle from three sides (Heron's formula)
    static double area(double a, double b, double c) {
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("invalid triangle");
        }
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    static double area(Circle circle) {
        return Math.PI * circle.radius() * circle.radius();
    }

    static double round2(double value) {
        return Math.round(value * 100) / 100.0;
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
        check("area(4)", round2(area(4)), "16.0");
        check("area(3, 4)", round2(area(3, 4)), "12.0");
        check("area(3, 4, 5)", round2(area(3, 4, 5)), "6.0");
        check("area(2, 2, 2)", round2(area(2, 2, 2)), "1.73");
        check("area(new Circle(1))", round2(area(new Circle(1))), "3.14");
        // a char widens to double, so area(double) is used
        check("area('A')", round2(area('A')), "4225.0");

        try {
            check("area(1, 2, 3)", area(1, 2, 3), "invalid triangle");
        } catch (IllegalArgumentException e) {
            check("area(1, 2, 3)", e.getMessage(), "invalid triangle");
        }

        if (failed) {
            System.exit(1);
        }
    }
}
