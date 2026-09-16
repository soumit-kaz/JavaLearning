import java.util.Arrays;

public class M11L15_Immutability {

    // final class + private final fields + no setters = immutable
    static final class Money {
        // static final: a constant
        static final String CURRENCY = "USD";
        // final field: set once in the constructor, then never changes
        private final long cents;

        Money(long cents) {
            this.cents = cents;
        }

        // "changing" the value creates a new object
        Money add(long more) {
            return new Money(cents + more);
        }

        // final method: subclasses cannot override it (here the class is final anyway)
        final long getCents() {
            return cents;
        }
    }

    static final class Scores {
        private final int[] scores;

        // copy on the way in, so the caller cannot change our array later
        Scores(int[] scores) {
            this.scores = scores.clone();
        }

        // copy on the way out, so callers get their own array
        int[] getScores() {
            return scores.clone();
        }
    }

    public static void main(String[] args) {
        Money price = new Money(500);
        Money total = price.add(250);
        System.out.println("price = " + price.getCents() + ", total = " + total.getCents() + " " + Money.CURRENCY);

        // String is immutable too: the result must be stored
        String name = "java";
        name.toUpperCase();
        System.out.println("name = " + name);
        name = name.toUpperCase();
        System.out.println("name = " + name);

        // a final reference cannot be reassigned, but the array can still change
        final int[] data = {90, 80, 70};
        Scores safe = new Scores(data);
        data[0] = 0;
        safe.getScores()[1] = 0;
        System.out.println("data = " + Arrays.toString(data));
        System.out.println("safe = " + Arrays.toString(safe.getScores()));
    }
}
