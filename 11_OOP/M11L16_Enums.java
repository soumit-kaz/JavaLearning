public class M11L16_Enums {

    // an enum is a class with a fixed set of named objects
    enum Light {
        RED(30), GREEN(25), YELLOW(5);

        // each constant stores its own value
        final int seconds;

        // enum constructors are always private
        Light(int seconds) {
            this.seconds = seconds;
        }

        // an enum method can return another constant
        Light next() {
            return switch (this) {
                case RED -> GREEN;
                case GREEN -> YELLOW;
                case YELLOW -> RED;
            };
        }
    }

    public static void main(String[] args) {
        Light light = Light.RED;
        // name, position (from 0), and == is safe for enum constants
        System.out.println(light.name() + " " + light.ordinal() + " " + (light == Light.RED));

        // values() returns all constants in order
        for (Light l : Light.values()) {
            System.out.println(l + " -> " + l.next() + " " + l.seconds + "s");
        }

        // valueOf needs the exact name
        System.out.println("valueOf = " + Light.valueOf("GREEN"));
        try {
            Light.valueOf("green");
        } catch (IllegalArgumentException e) {
            System.out.println("error: no constant green");
        }
    }
}
