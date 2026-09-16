import java.util.Iterator;
import java.util.NoSuchElementException;

public class M16L13_Iterable {

    // any Iterable can be used in a for-each loop
    static class Countdown implements Iterable<Integer> {
        private final int start;

        Countdown(int start) {
            this.start = start;
        }

        // hand out a fresh Iterator each time, so the loop can run again
        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<Integer>() {
                private int current = start;

                // is there more?
                @Override
                public boolean hasNext() {
                    return current > 0;
                }

                // give the next value and move forward
                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return current--;
                }
            };
        }
    }

    // your own array-backed list can be Iterable too
    static class Words implements Iterable<String> {
        private final String[] items;

        Words(String... items) {
            this.items = items;
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int index = 0;

                @Override
                public boolean hasNext() {
                    return index < items.length;
                }

                @Override
                public String next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }
                    return items[index++];
                }
            };
        }
    }

    public static void main(String[] args) {
        // for-each calls iterator(), then hasNext() and next()
        Countdown countdown = new Countdown(3);
        String first = "";
        for (int n : countdown) {
            first += n + " ";
        }
        // a fresh iterator lets the same object loop again
        String second = "";
        for (int n : countdown) {
            second += n + " ";
        }
        System.out.println("first: " + first.trim());
        System.out.println("second: " + second.trim());

        // the same loop written by hand
        Iterator<String> it = new Words("red", "green", "blue").iterator();
        String joined = "";
        while (it.hasNext()) {
            joined += it.next() + " ";
        }
        System.out.println("by hand: " + joined.trim());

        // next() past the end must throw
        try {
            it.next();
        } catch (NoSuchElementException e) {
            System.out.println("past end: NoSuchElementException");
        }

        // Iterable also gives a default forEach that takes a lambda
        new Words("a", "b").forEach(w -> System.out.println("forEach: " + w));
    }
}
