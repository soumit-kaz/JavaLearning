public class M12L07_OverloadingPitfalls {

    static class Animal {
        String eat(Object food) {
            return "Animal eats";
        }
    }

    static class Dog extends Animal {
        // different parameter type: an overload, NOT an override
        String eat(String food) {
            return "Dog eats";
        }
    }

    static String describe(Animal a) { return "some animal"; }
    static String describe(Dog d) { return "a dog"; }

    static class BadPoint {
        final int x;

        BadPoint(int x) {
            this.x = x;
        }

        // wrong: this overloads equals instead of overriding equals(Object)
        public boolean equals(BadPoint other) {
            return other != null && x == other.x;
        }
    }

    public static void main(String[] args) {
        // the compiler picks the overload from the DECLARED type
        Animal pet = new Dog();
        System.out.println("describe(pet) -> " + describe(pet));
        System.out.println("describe((Dog) pet) -> " + describe((Dog) pet));

        // Animal only knows eat(Object), and Dog did not override it
        System.out.println("pet.eat -> " + pet.eat("bone"));
        Dog realDog = new Dog();
        System.out.println("realDog.eat -> " + realDog.eat("bone"));

        // the declared type Object picks Object.equals, which compares identity
        BadPoint b1 = new BadPoint(1);
        BadPoint b2 = new BadPoint(1);
        Object b2AsObject = b2;
        System.out.println("equals(BadPoint) -> " + b1.equals(b2));
        System.out.println("equals(Object) -> " + b1.equals(b2AsObject));

        // println(char[]) prints the characters
        char[] letters = {'h', 'i'};
        System.out.println(letters);

        // + uses the Object form, which looks like [C@1b6d3586
        String joined = "letters: " + letters;
        System.out.println("starts with [C@: " + joined.startsWith("letters: [C@"));

        // the fix: make a String from the chars
        System.out.println("letters: " + new String(letters));
    }
}
