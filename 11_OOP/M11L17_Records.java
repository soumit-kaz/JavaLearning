public class M11L17_Records {

    // a record gets private final fields, a constructor, accessors,
    // equals, hashCode and toString for free
    record Point(int x, int y) {
    }

    record Range(int min, int max) {
        // compact constructor: checks the values before the fields are set
        Range {
            if (min > max) {
                throw new IllegalArgumentException("min > max");
            }
        }

        int length() {
            return max - min;
        }

        // records are immutable, so a "wither" returns a new record
        Range withMax(int newMax) {
            return new Range(min, newMax);
        }
    }

    record Circle(Point center, int radius) {
    }

    static String describe(Object shape) {
        // a record pattern takes the record apart into variables
        return switch (shape) {
            case Circle(Point(int x, int y), int r) when r == 0 -> "dot at " + x + "," + y;
            case Circle(Point center, int r) -> "circle r=" + r + " at " + center;
            default -> "unknown";
        };
    }

    public static void main(String[] args) {
        // accessors use the component name, without "get"
        Point p = new Point(3, 4);
        Point same = new Point(3, 4);
        System.out.println(p + " x = " + p.x());
        System.out.println("equals: " + p.equals(same) + ", ==: " + (p == same));

        Range range = new Range(1, 10);
        System.out.println(range + " length = " + range.length() + ", " + range.withMax(20));
        try {
            new Range(5, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("error: " + e.getMessage());
        }

        System.out.println(describe(new Circle(new Point(1, 2), 5)));
        System.out.println(describe(new Circle(new Point(3, 3), 0)));
        System.out.println(describe("text"));
    }
}
