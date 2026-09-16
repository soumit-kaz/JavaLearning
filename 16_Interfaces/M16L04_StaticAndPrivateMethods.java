public class M16L04_StaticAndPrivateMethods {

    interface Temperature {
        double celsius();

        // a static method belongs to the interface itself (Java 8)
        // it is handy as a factory
        static Temperature ofCelsius(double c) {
            return new Celsius(c);
        }

        static Temperature ofFahrenheit(double f) {
            return new Celsius(round((f - 32) * 5 / 9));
        }

        default String describe() {
            return label(celsius()) + " (" + round(celsius() * 9 / 5 + 32) + "F)";
        }

        // a private method lets default methods share code (Java 9)
        private String label(double c) {
            return c + "C " + (c < 0 ? "freezing" : "fine");
        }

        // a private static method can help static and default methods
        private static double round(double value) {
            return Math.round(value * 10) / 10.0;
        }
    }

    static class Celsius implements Temperature {
        private final double value;

        Celsius(double value) {
            this.value = value;
        }

        @Override
        public double celsius() {
            return value;
        }
    }

    public static void main(String[] args) {
        // call a static interface method through the interface name
        Temperature cold = Temperature.ofCelsius(-5);
        Temperature warm = Temperature.ofFahrenheit(77);
        System.out.println("cold: " + cold.describe());
        System.out.println("warm: " + warm.describe());

        // static interface methods are NOT inherited:
        // Celsius.ofCelsius(1) and cold.ofCelsius(1) do not compile
        // cold.label(1) does not compile: private stays inside the interface
    }
}
