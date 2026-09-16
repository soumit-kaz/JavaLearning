public class M16L01_InterfaceBasics {

    // an interface is a list of methods that a class promises to have
    interface Shape {
        // every field is automatically public static final
        int SIDES_UNKNOWN = -1;

        // no body: the method is automatically public and abstract
        double area();

        int sides();
    }

    // "implements" makes the promise; the class must write every method
    static class Square implements Shape {
        private final double side;

        Square(double side) {
            this.side = side;
        }

        // the method must be public; leaving out "public" does not compile
        @Override
        public double area() {
            return side * side;
        }

        @Override
        public int sides() {
            return 4;
        }

        double diagonal() {
            return side * Math.sqrt(2);
        }
    }

    static class Circle implements Shape {
        private final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public int sides() {
            return SIDES_UNKNOWN;
        }
    }

    // an abstract class may implement only part of an interface
    abstract static class Polygon implements Shape {
        @Override
        public int sides() {
            return 3;
        }
    }

    // the first concrete class must write the rest
    static class Triangle extends Polygon {
        @Override
        public double area() {
            return 6;
        }
    }

    // code that only knows the interface works with every shape
    static double totalArea(Shape[] shapes) {
        double total = 0;
        for (Shape s : shapes) {
            total += s.area();
        }
        return total;
    }

    public static void main(String[] args) {
        // an interface is a type; new Shape() does not compile
        Shape shape = new Square(3);
        System.out.println("square area: " + shape.area());

        // only Shape methods are visible; shape.diagonal() does not compile
        if (shape instanceof Square square) {
            System.out.printf("diagonal: %.2f%n", square.diagonal());
        }

        Shape[] shapes = {new Square(1), new Circle(1), new Triangle()};
        System.out.printf("total area: %.2f%n", totalArea(shapes));
        System.out.println("circle sides: " + shapes[1].sides());

        // constants are used through the interface name
        System.out.println("SIDES_UNKNOWN: " + Shape.SIDES_UNKNOWN);
    }
}
