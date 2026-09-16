public class M13L10_LiskovSubstitution {

    static class Rectangle {
        int width;
        int height;

        // promise: setWidth changes only the width
        void setWidth(int w) {
            width = w;
        }

        void setHeight(int h) {
            height = h;
        }

        int area() {
            return width * height;
        }
    }

    // keeps the promise: adds a color, changes nothing else
    static class ColoredRectangle extends Rectangle {
        String color = "red";
    }

    // breaks the promise: setting one side changes both
    static class Square extends Rectangle {
        @Override
        void setWidth(int w) {
            width = w;
            height = w;
        }

        @Override
        void setHeight(int h) {
            width = h;
            height = h;
        }
    }

    // correct for any real Rectangle: expects 20
    static int resize(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);
        return r.area();
    }

    public static void main(String[] args) {
        // a subclass must work wherever its parent is expected
        System.out.println("rectangle: " + resize(new Rectangle()));
        System.out.println("colored: " + resize(new ColoredRectangle()));
        // Square is not a safe Rectangle: better not to extend it at all
        System.out.println("square: " + resize(new Square()));
    }
}
