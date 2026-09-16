public class M13P05_ConstructorTrap {

    // collects what happens, in order
    static String trace = "";

    static void log(String step) {
        trace += (trace.isEmpty() ? "" : " | ") + step;
    }

    static class Widget {
        Widget() {
            log("Widget()");
            // overridable call inside a constructor
            log(render());
        }

        String render() {
            return "widget";
        }
    }

    static class Label extends Widget {
        private String text = "default";
        // a final int with a constant value is filled in by the compiler
        private final int width = 10;

        Label(String text) {
            super();
            log("Label() text was " + this.text);
            this.text = text;
        }

        @Override
        String render() {
            return "label text=" + text + " width=" + width;
        }
    }

    static class EarlyLabel extends Widget {
        private final String text;

        EarlyLabel(String text) {
            // Java 25: set the field before super(), so render() sees it
            this.text = text;
            super();
            log("EarlyLabel()");
        }

        @Override
        String render() {
            return "early text=" + text;
        }
    }

    static String run(int which) {
        trace = "";
        if (which == 1) {
            new Widget();
        } else if (which == 2) {
            Label label = new Label("Hi");
            log("after: " + label.render());
        } else {
            new EarlyLabel("Now");
        }
        return trace;
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("new Widget()", run(1), "Widget() | widget");
        check("new Label(\"Hi\")", run(2),
                "Widget() | label text=null width=10 | Label() text was default | after: label text=Hi width=10");
        check("new EarlyLabel(\"Now\")", run(3), "Widget() | early text=Now | EarlyLabel()");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
