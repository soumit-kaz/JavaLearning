public class M11L12_CastingAndInstanceof {

    static class Shape {
    }

    static class Circle extends Shape {
        double r = 1;
    }

    static class Square extends Shape {
        double side = 2;

        double diagonal() {
            return side * Math.sqrt(2);
        }
    }

    static String kind(Shape s) {
        // a switch can match on the type too
        return switch (s) {
            case Circle c -> "circle r=" + c.r;
            case Square q -> "square side=" + q.side;
            default -> "shape";
        };
    }

    public static void main(String[] args) {
        // upcast: automatic, a Square is a Shape
        Shape s = new Square();

        // s.diagonal() does not compile: the variable type is Shape
        // downcast: needs an explicit cast
        Square sq = (Square) s;
        System.out.println("diagonal = " + sq.diagonal());

        // a wrong downcast compiles but fails at run time
        Shape c = new Circle();
        try {
            Square wrong = (Square) c;
            System.out.println(wrong.side);
        } catch (ClassCastException e) {
            System.out.println("ClassCastException");
        }

        // instanceof checks the type first
        System.out.println("c instanceof Square: " + (c instanceof Square));

        // pattern matching: test and cast in one step
        if (s instanceof Square q && q.side > 1) {
            System.out.println("big square = " + q.diagonal());
        }

        System.out.println("kind = " + kind(c) + ", " + kind(new Shape()));

        // null is never an instance of anything
        Shape nothing = null;
        System.out.println("null instanceof Shape: " + (nothing instanceof Shape));
    }
}
