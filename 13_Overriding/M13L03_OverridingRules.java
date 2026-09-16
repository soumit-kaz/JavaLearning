import java.io.FileNotFoundException;
import java.io.IOException;

public class M13L03_OverridingRules {

    static class Shape {
        protected String name() {
            return "shape";
        }

        Shape copy() {
            return new Shape();
        }

        String load() throws IOException {
            throw new IOException("disk error");
        }

        // final: subclasses cannot override this method
        final String label() {
            return "[" + name() + "]";
        }
    }

    static class Circle extends Shape {
        // access may get WIDER (protected -> public), never narrower
        @Override
        public String name() {
            return "circle";
        }

        // covariant return: an override may return a subtype
        @Override
        Circle copy() {
            return new Circle();
        }

        // checked exceptions may get NARROWER, never broader
        @Override
        String load() throws FileNotFoundException {
            throw new FileNotFoundException("circle.txt");
        }
    }

    static class Square extends Shape {
        // dropping the checked exception is also allowed
        @Override
        String load() {
            return "square data";
        }
    }

    static void tryLoad(Shape s) {
        try {
            System.out.println("load: " + s.load());
        } catch (IOException e) {
            System.out.println("load failed: " + e.getClass().getSimpleName());
        }
    }

    public static void main(String[] args) {
        // no cast needed: copy() on a Circle returns a Circle
        Circle twin = new Circle().copy();
        System.out.println("copy: " + twin.name());

        // the final method still calls the overridden name()
        Shape s = twin;
        System.out.println("label: " + s.label());

        tryLoad(new Shape());
        tryLoad(twin);
        tryLoad(new Square());

        // through a Square variable no try/catch is needed
        System.out.println("direct: " + new Square().load());
    }
}
