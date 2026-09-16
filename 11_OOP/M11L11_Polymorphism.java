public class M11L11_Polymorphism {

    static class Shape {
        String name() {
            return "shape";
        }

        double area() {
            return 0;
        }

        // an inherited method still calls the child's versions
        String describe() {
            return name() + " " + area();
        }
    }

    static class Circle extends Shape {
        double r;

        Circle(double r) {
            this.r = r;
        }

        // @Override asks the compiler to check that the parent has this method
        @Override
        String name() {
            return "circle";
        }

        @Override
        double area() {
            return 3.0 * r * r;
        }
    }

    static class Square extends Shape {
        double side;

        Square(double side) {
            this.side = side;
        }

        @Override
        String name() {
            return "square";
        }

        @Override
        double area() {
            return side * side;
        }
    }

    // one method works for every kind of shape
    static double totalArea(Shape[] shapes) {
        double total = 0;
        for (Shape s : shapes) {
            total += s.area();
        }
        return total;
    }

    public static void main(String[] args) {
        // a parent-type variable can hold any child object
        Shape[] shapes = {new Circle(2), new Square(3), new Shape()};
        for (Shape s : shapes) {
            // the real object decides which method runs
            System.out.println(s.describe());
        }
        System.out.println("total = " + totalArea(shapes));
    }
}
