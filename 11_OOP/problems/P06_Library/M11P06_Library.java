public class M11P06_Library {

    // a custom checked exception
    @SuppressWarnings("serial")
    static class LibraryException extends Exception {
        LibraryException(String message) {
            super(message);
        }
    }

    enum Level {
        BASIC(2), PREMIUM(4);

        final int maxLoans;

        Level(int maxLoans) {
            this.maxLoans = maxLoans;
        }
    }

    record Book(String id, String title) {
    }

    static class Member {
        final String name;
        final Level level;
        int loans;

        Member(String name, Level level) {
            this.name = name;
            this.level = level;
        }
    }

    static class Library {
        private final Book[] books;
        // borrowedBy[i] is the member holding books[i], or null
        private final Member[] borrowedBy;

        Library(Book[] books) {
            this.books = books.clone();
            this.borrowedBy = new Member[books.length];
        }

        private int indexOf(String id) throws LibraryException {
            for (int i = 0; i < books.length; i++) {
                if (books[i].id().equals(id)) {
                    return i;
                }
            }
            throw new LibraryException("no book " + id);
        }

        void borrow(Member m, String id) throws LibraryException {
            int i = indexOf(id);
            if (borrowedBy[i] != null) {
                throw new LibraryException(id + " already borrowed");
            }
            if (m.loans >= m.level.maxLoans) {
                throw new LibraryException(m.name + " at limit");
            }
            borrowedBy[i] = m;
            m.loans++;
        }

        void giveBack(Member m, String id) throws LibraryException {
            int i = indexOf(id);
            if (borrowedBy[i] != m) {
                throw new LibraryException(m.name + " does not have " + id);
            }
            borrowedBy[i] = null;
            m.loans--;
        }

        String available() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < books.length; i++) {
                if (borrowedBy[i] == null) {
                    sb.append(books[i].id()).append(' ');
                }
            }
            return sb.toString().trim();
        }
    }

    static String attempt(Library lib, Member m, String action, String id) {
        try {
            if (action.equals("borrow")) {
                lib.borrow(m, id);
            } else {
                lib.giveBack(m, id);
            }
            return ("ok, available: " + lib.available()).trim();
        } catch (LibraryException e) {
            return "error: " + e.getMessage();
        }
    }

    static boolean failed = false;

    // print one PASS or FAIL line and remember any failure
    static void check(String label, Object actual, String expected) {
        boolean ok = String.valueOf(actual).equals(expected);
        if (!ok) {
            failed = true;
        }
        System.out.println(label + " -> " + actual + " " + (ok ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Library lib = new Library(new Book[]{
                new Book("B1", "Java"), new Book("B2", "Go"), new Book("B3", "Rust")});
        Member ann = new Member("Ann", Level.BASIC);
        Member bob = new Member("Bob", Level.PREMIUM);

        check("Ann borrows B1", attempt(lib, ann, "borrow", "B1"), "ok, available: B2 B3");
        check("Bob borrows B1", attempt(lib, bob, "borrow", "B1"), "error: B1 already borrowed");
        check("Ann borrows B2", attempt(lib, ann, "borrow", "B2"), "ok, available: B3");
        check("Ann borrows B3", attempt(lib, ann, "borrow", "B3"), "error: Ann at limit");
        check("Bob borrows B3", attempt(lib, bob, "borrow", "B3"), "ok, available:");
        check("Bob returns B1", attempt(lib, bob, "return", "B1"), "error: Bob does not have B1");
        check("Ann returns B1", attempt(lib, ann, "return", "B1"), "ok, available: B1");
        check("Ann borrows B9", attempt(lib, ann, "borrow", "B9"), "error: no book B9");
        check("loans Ann Bob", ann.loans + " " + bob.loans, "1 1");

        if (failed) {
            System.exit(1);
        }
    }
}
