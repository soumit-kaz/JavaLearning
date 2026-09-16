public class M15L03_TemplateMethod {

    abstract static class Beverage {
        // template method: the fixed recipe, final so the order never changes
        final String prepare() {
            String steps = "boil, " + brew() + ", pour";
            if (wantsExtras()) {
                steps += ", " + extras();
            }
            return steps;
        }

        // steps each subclass must write
        abstract String brew();

        abstract String extras();

        // hook: has a default body, subclasses may override it
        boolean wantsExtras() {
            return true;
        }
    }

    static class Tea extends Beverage {
        @Override
        String brew() {
            return "steep tea";
        }

        @Override
        String extras() {
            return "lemon";
        }
    }

    static class BlackCoffee extends Beverage {
        @Override
        String brew() {
            return "drip coffee";
        }

        @Override
        String extras() {
            return "milk";
        }

        // turn the optional step off
        @Override
        boolean wantsExtras() {
            return false;
        }

        // String prepare() { ... } would not compile here: prepare() is final
    }

    // this code knows only the abstract type
    static void serve(Beverage[] drinks) {
        for (Beverage b : drinks) {
            System.out.println(b.getClass().getSimpleName() + ": " + b.prepare());
        }
    }

    public static void main(String[] args) {
        serve(new Beverage[] {new Tea(), new BlackCoffee()});
    }
}
