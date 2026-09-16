import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class M16L10_MethodReferences {

    interface Formatter {
        String format(String text);
    }

    static String shout(String text) {
        return text.toUpperCase() + "!";
    }

    static class Greeter {
        private final String greeting;

        Greeter(String greeting) {
            this.greeting = greeting;
        }

        String greet(String name) {
            return greeting + ", " + name;
        }
    }

    static String applyTo(Formatter formatter, String text) {
        return formatter.format(text);
    }

    public static void main(String[] args) {
        // a method reference is a shorter lambda that only calls one method

        // 1) static method: text -> shout(text)
        Formatter loud = M16L10_MethodReferences::shout;
        System.out.println("static: " + applyTo(loud, "hello"));

        // 2) method of one specific object: name -> hi.greet(name)
        Greeter hi = new Greeter("Hi");
        Formatter greet = hi::greet;
        System.out.println("object: " + applyTo(greet, "Rina"));

        // 3) method of the object passed in: text -> text.trim()
        Formatter trim = String::trim;
        System.out.println("passed in: [" + applyTo(trim, "  space  ") + "]");
        // the first value becomes the object, the second the argument
        BiFunction<String, String, Boolean> starts = String::startsWith;
        System.out.println("startsWith: " + starts.apply("interface", "inter"));

        // 4) constructor: greeting -> new Greeter(greeting)
        Function<String, Greeter> maker = Greeter::new;
        System.out.println("constructor: " + maker.apply("Hello").greet("Karim"));
        Supplier<StringBuilder> builder = StringBuilder::new;
        System.out.println("builder: " + builder.get().append("new"));

        // the same reference fits any interface with a matching shape
        Function<String, Integer> length = String::length;
        System.out.println("length: " + length.apply("hello"));
    }
}
