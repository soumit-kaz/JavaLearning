public class M16L14_AutoCloseable {

    // AutoCloseable has one method: close()
    static class Connection implements AutoCloseable {
        private final String name;
        private final boolean failOnClose;

        Connection(String name, boolean failOnClose) {
            this.name = name;
            this.failOnClose = failOnClose;
            System.out.println("open " + name);
        }

        void send(boolean fail) {
            if (fail) {
                throw new IllegalStateException(name + " send failed");
            }
        }

        // close() that throws no checked exception means callers need no catch for it
        @Override
        public void close() {
            System.out.println("close " + name);
            if (failOnClose) {
                throw new IllegalStateException(name + " close failed");
            }
        }
    }

    public static void main(String[] args) {
        // try-with-resources calls close() for you, in reverse order
        try (Connection a = new Connection("A", false); Connection b = new Connection("B", false)) {
            a.send(false);
            b.send(false);
        }

        // close() still runs when the body throws
        try (Connection c = new Connection("C", false)) {
            c.send(true);
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }

        // if close() also throws, the body's exception wins
        // and the close() exception is kept as "suppressed"
        try (Connection d = new Connection("D", true)) {
            d.send(true);
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
            System.out.println("suppressed: " + e.getSuppressed()[0].getMessage());
        }
    }
}
