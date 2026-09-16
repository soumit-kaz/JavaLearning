public class M13L07_OverridingEquals {

    static class Point {
        final int x;
        final int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // the parameter MUST be Object to override Object.equals
        @Override
        public boolean equals(Object o) {
            // pattern matching: false for null and for other types
            return o instanceof Point other && x == other.x && y == other.y;
        }

        // override hashCode together with equals
        @Override
        public int hashCode() {
            return 31 * x + y;
        }
    }

    static class Book {
        final String isbn;

        Book(String isbn) {
            this.isbn = isbn;
        }

        // TRAP: equals(Book) is an OVERLOAD; Object.equals(Object) is untouched
        public boolean equals(Book other) {
            return other != null && isbn.equals(other.isbn);
        }
    }

    // like Java's own tools, this search calls equals(Object)
    static boolean contains(Object[] items, Object target) {
        for (Object item : items) {
            if (item.equals(target)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Object b = new Point(1, 2);
        // the override runs even through an Object variable
        System.out.println("a.equals(b): " + a.equals(b));
        System.out.println("contains point: " + contains(new Point[] {a}, b));

        Book b1 = new Book("123");
        Book b2 = new Book("123");
        // declared type Book picks equals(Book)
        System.out.println("b1.equals(b2): " + b1.equals(b2));

        // declared type Object picks Object.equals, which compares references
        Object b2AsObject = b2;
        System.out.println("b1.equals(object): " + b1.equals(b2AsObject));
        System.out.println("contains book: " + contains(new Book[] {b1}, b2));
    }
}
