public class M11L01_ClassesAndObjects {

    // a class is a blueprint; objects are built from it
    static class Rectangle {
        double width;
        double height;

        // a method works on the data of its own object
        double area() {
            return width * height;
        }

        // a method can also change its object
        void scale(double factor) {
            width = width * factor;
            height = height * factor;
        }
    }

    public static void main(String[] args) {
        // new creates an object
        Rectangle r = new Rectangle();
        r.width = 2;
        r.height = 3;
        System.out.println("area = " + r.area());

        r.scale(2);
        System.out.println("scaled = " + r.area());

        // a variable holds a reference; assigning copies the reference, not the object
        Rectangle same = r;
        same.width = 1;
        System.out.println("r.width = " + r.width);
        System.out.println("r == same: " + (r == same));

        // each new makes a separate object
        Rectangle other = new Rectangle();
        System.out.println("r == other: " + (r == other));

        // null means "no object"; using it throws NullPointerException
        Rectangle nothing = null;
        try {
            System.out.println(nothing.area());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException");
        }

        // an object with no references left can be garbage collected
        other = null;
        System.out.println("other = " + other);
    }
}
