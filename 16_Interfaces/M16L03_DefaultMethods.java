public class M16L03_DefaultMethods {

    interface Logger {
        String log(String message);

        // a default method has a body; classes get it for free (Java 8)
        // it may call the abstract methods of the same interface
        default String info(String message) {
            return log("[INFO] " + message);
        }

        default String warn(String message) {
            return log("[WARN] " + message);
        }
    }

    // only log() must be written
    static class ConsoleLogger implements Logger {
        @Override
        public String log(String message) {
            return message;
        }
    }

    static class LoudLogger extends ConsoleLogger {
        // a class may still override a default method
        @Override
        public String warn(String message) {
            return log("[WARN!!!] " + message.toUpperCase());
        }
    }

    interface Greeter {
        default String greet() {
            return "Hello";
        }
    }

    static class PoliteGreeter implements Greeter {
        @Override
        public String greet() {
            // InterfaceName.super.method() runs the interface's default version
            // plain super.greet() would look in the superclass (Object) and not compile
            return Greeter.super.greet() + ", nice to meet you";
        }
    }

    public static void main(String[] args) {
        Logger console = new ConsoleLogger();
        System.out.println("info: " + console.info("started"));
        System.out.println("warn: " + console.warn("disk full"));

        // the object decides which version runs
        Logger loud = new LoudLogger();
        System.out.println("loud warn: " + loud.warn("disk full"));

        System.out.println("polite: " + new PoliteGreeter().greet());

        // adding a new default method later does not break old classes
    }
}
