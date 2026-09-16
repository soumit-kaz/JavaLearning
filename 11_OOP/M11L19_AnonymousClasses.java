public class M11L19_AnonymousClasses {

    static class Animal {
        String sound() {
            return "...";
        }

        String speak() {
            return "says " + sound();
        }
    }

    public static void main(String[] args) {
        // an anonymous class is a one-time subclass with no name
        Animal duck = new Animal() {
            @Override
            String sound() {
                return "Quack";
            }
        };
        System.out.println("duck " + duck.speak());
        System.out.println("plain " + new Animal().speak());

        // it can read effectively final local variables
        String word = "Moo";
        Animal cow = new Animal() {
            @Override
            String sound() {
                return word + "!";
            }
        };
        System.out.println("cow " + cow.speak());

        // it can be written right where it is used
        System.out.println("dog " + new Animal() {
            @Override
            String sound() {
                return "Woof";
            }
        }.speak());

        // its class has no simple name
        System.out.println("name = [" + duck.getClass().getSimpleName() + "]");
    }
}
