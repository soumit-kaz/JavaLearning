public class M14L04_DoubleDispatch {

    static class Shape {
        // first dispatch: the call lands in the real shape's class
        void accept(ShapeVisitor visitor) {
        }
    }

    static class Circle extends Shape {
        final double radius;

        Circle(double radius) {
            this.radius = radius;
        }

        @Override
        void accept(ShapeVisitor visitor) {
            // "this" is a Circle here, so visitCircle is called
            visitor.visitCircle(this);
        }
    }

    static class Square extends Shape {
        final double side;

        Square(double side) {
            this.side = side;
        }

        @Override
        void accept(ShapeVisitor visitor) {
            visitor.visitSquare(this);
        }
    }

    // one method per shape; each subclass is one operation
    static class ShapeVisitor {
        void visitCircle(Circle c) {
        }

        void visitSquare(Square s) {
        }
    }

    static class AreaVisitor extends ShapeVisitor {
        double total;

        @Override
        void visitCircle(Circle c) {
            total += Math.PI * c.radius * c.radius;
        }

        @Override
        void visitSquare(Square s) {
            total += s.side * s.side;
        }
    }

    static class NameVisitor extends ShapeVisitor {
        String names = "";

        @Override
        void visitCircle(Circle c) {
            names += "circle ";
        }

        @Override
        void visitSquare(Square s) {
            names += "square ";
        }
    }

    // plain overloads only see the declared types
    static String name(Shape s) {
        return "shape";
    }

    static String name(Circle c) {
        return "circle";
    }

    public static void main(String[] args) {
        Shape[] shapes = { new Circle(1), new Square(2), new Square(1) };

        // the declared type is Shape, so name(Shape) is chosen
        System.out.println("overload: " + name(shapes[0]));

        AreaVisitor area = new AreaVisitor();
        NameVisitor names = new NameVisitor();
        for (Shape s : shapes) {
            // two virtual calls: the shape decides, then the visitor decides
            s.accept(area);
            s.accept(names);
        }
        System.out.println("area: " + Math.round(area.total * 100) / 100.0);
        System.out.println("names: " + names.names.trim());
    }
}
