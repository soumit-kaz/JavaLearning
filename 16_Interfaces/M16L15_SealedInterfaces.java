public class M16L15_SealedInterfaces {

    // a sealed interface lists exactly who may implement it (Java 17)
    sealed interface Shape permits Circle, Rect, Polygon {
    }

    // records are final, so they fit well
    record Circle(double radius) implements Shape {
    }

    record Rect(double width, double height) implements Shape {
    }

    // each permitted type must be final, sealed or non-sealed
    // non-sealed opens this branch again: anyone may extend Polygon
    non-sealed static class Polygon implements Shape {
        int sides() {
            return 0;
        }
    }

    static class Triangle extends Polygon {
        @Override
        int sides() {
            return 3;
        }
    }

    // class Star implements Shape { } does not compile: Star is not permitted

    static String describe(Shape shape) {
        // every permitted type is covered, so no default is needed
        return switch (shape) {
            case Circle c -> "circle r=" + c.radius();
            case Rect(double w, double h) -> "rect area=" + w * h;
            case Polygon p -> "polygon sides=" + p.sides();
        };
    }

    public static void main(String[] args) {
        Shape[] shapes = {new Circle(1), new Rect(2, 3), new Triangle()};
        for (Shape s : shapes) {
            System.out.println(s.getClass().getSimpleName() + ": " + describe(s));
        }
    }
}
