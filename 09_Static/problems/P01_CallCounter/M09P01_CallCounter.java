public class M09P01_CallCounter {

    // static variable remembers its value between calls
    static int calls = 0;

    static String greet(String name) {
        calls++;
        return "Hello " + name + " #" + calls;
    }

    static void reset() {
        calls = 0;
    }

    static void check(String input, String actual, String expected) {
        String status = actual.equals(expected) ? "PASS" : "FAIL";
        System.out.println(input + " -> " + actual + "  " + status);
    }

    public static void main(String[] args) {
        check("greet(Ana)", greet("Ana"), "Hello Ana #1");
        check("greet(Ben)", greet("Ben"), "Hello Ben #2");
        check("greet(Ana)", greet("Ana"), "Hello Ana #3");
        check("calls", String.valueOf(calls), "3");
        reset();
        check("reset, greet(Cy)", greet("Cy"), "Hello Cy #1");
    }
}
