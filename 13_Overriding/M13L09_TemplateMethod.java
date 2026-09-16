public class M13L09_TemplateMethod {

    static class Beverage {
        // template method: final, so the steps and their order cannot change
        final String prepare() {
            String steps = "boil, " + brew();
            if (wantsMilk()) {
                steps += ", milk";
            }
            return steps;
        }

        // a step that subclasses fill in
        String brew() {
            return "hot water";
        }

        // a hook: an optional step with a default answer
        boolean wantsMilk() {
            return false;
        }
    }

    static class Tea extends Beverage {
        @Override
        String brew() {
            return "steep tea";
        }
    }

    static class Coffee extends Beverage {
        @Override
        String brew() {
            return "drip coffee";
        }

        @Override
        boolean wantsMilk() {
            return true;
        }
    }

    public static void main(String[] args) {
        // the parent controls the flow and calls the child's overrides
        System.out.println("water: " + new Beverage().prepare());
        System.out.println("tea: " + new Tea().prepare());
        System.out.println("coffee: " + new Coffee().prepare());
    }
}
