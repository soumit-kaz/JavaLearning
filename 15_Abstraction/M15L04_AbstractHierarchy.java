public class M15L04_AbstractHierarchy {

    // level 1: the most general idea
    abstract static class Shape {
        abstract double area();

        abstract double perimeter();
    }

    // level 2: still abstract, but perimeter can be written once here
    abstract static class Polygon extends Shape {
        abstract double[] sides();

        @Override
        double perimeter() {
            double sum = 0;
            for (double side : sides()) {
                sum += side;
            }
            return sum;
        }
    }

    // level 3: concrete, fills in the last missing methods
    static class Square extends Polygon {
        private final double side;

        Square(double side) {
            this.side = side;
        }

        @Override
        double[] sides() {
            return new double[] {side, side, side, side};
        }

        @Override
        double area() {
            return side * side;
        }
    }

    static class Printer {
        String print(String text) {
            return "plain: " + text;
        }
    }

    // an abstract subclass may make an inherited concrete method abstract again
    abstract static class FancyPrinter extends Printer {
        @Override
        abstract String print(String text);
    }

    static class StarPrinter extends FancyPrinter {
        @Override
        String print(String text) {
            return "*** " + text + " ***";
        }
    }

    // sealed: only the listed classes may extend Vehicle
    abstract static sealed class Vehicle permits Car, Truck {
    }

    // each permitted subclass must be final, sealed or non-sealed
    static final class Car extends Vehicle {
    }

    // non-sealed: anyone may extend Truck again
    static non-sealed class Truck extends Vehicle {
        int wheels() {
            return 6;
        }
    }

    static class BigTruck extends Truck {
        @Override
        int wheels() {
            return 18;
        }
    }

    // no default needed: the compiler knows every direct subclass
    static String toll(Vehicle v) {
        return switch (v) {
            case Car c -> "car toll 2";
            case Truck t -> "truck toll " + t.wheels();
        };
    }

    public static void main(String[] args) {
        Shape sq = new Square(3);
        System.out.println("area = " + sq.area() + ", perimeter = " + sq.perimeter());

        Printer star = new StarPrinter();
        System.out.println(new Printer().print("hi"));
        System.out.println(star.print("hi"));

        System.out.println(toll(new Car()));
        // BigTruck is still a Truck, so "case Truck" covers it
        System.out.println(toll(new BigTruck()));
    }
}
