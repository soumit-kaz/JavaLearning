public class M09P05_InitOrderPuzzle {

    // every step adds a short token here
    static String log = "";

    static void note(String token) {
        log = log.isEmpty() ? token : log + " " + token;
    }

    static class Widget {
        static int made;

        static {
            note("S");
        }

        {
            note("I");
        }

        Widget() {
            made++;
            note("C" + made);
        }

        static void ping() {
            note("P");
        }
    }

    static class Gadget {
        static final int SIZE = 3;
        static int count = 0;

        static {
            note("G");
        }
    }

    // compare the log with the expected tokens, then clear it
    static void check(String step, String expected) {
        String status = log.equals(expected) ? "PASS" : "FAIL";
        System.out.println(step + " -> " + log + "  " + status);
        log = "";
    }

    public static void main(String[] args) {
        Widget.ping();
        check("Widget.ping()", "S P");

        new Widget();
        check("new Widget()", "I C1");

        new Widget();
        check("new Widget() again", "I C2");

        // a constant is copied in by the compiler, so Gadget is not set up yet
        note("size" + Gadget.SIZE);
        check("Gadget.SIZE", "size3");

        // Gadget is set up before count is read
        note("count" + Gadget.count);
        check("Gadget.count", "G count0");
    }
}
