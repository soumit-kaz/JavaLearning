import java.util.Arrays;

public class M10L03_PassByValue {

    static class Dog {
        String name;
    }

    // primitives: the method gets a copy of the number
    static void addTen(int number) {
        number = number + 10;
    }

    // arrays: the reference is copied, so both point to the same array
    static void doubleAll(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] * 2;
        }
    }

    // the local reference points to a new array; the caller keeps the old one
    static void replace(int[] numbers) {
        numbers = new int[] {0, 0, 0};
    }

    // objects work like arrays: changes through the reference are shared
    static void rename(Dog dog) {
        dog.name = "Max";
    }

    static void replace(Dog dog) {
        dog = new Dog();
        dog.name = "Buddy";
    }

    // a String can never change, so the caller keeps its text
    static void shout(String text) {
        text = text.toUpperCase();
    }

    // a StringBuilder can change, so the caller sees the "!"
    static void excite(StringBuilder text) {
        text.append("!");
    }

    public static void main(String[] args) {
        int value = 5;
        addTen(value);
        System.out.println("value = " + value);

        int[] data = {1, 2, 3};
        doubleAll(data);
        System.out.println("doubled = " + Arrays.toString(data));
        replace(data);
        System.out.println("after replace = " + Arrays.toString(data));

        Dog dog = new Dog();
        dog.name = "Rex";
        rename(dog);
        System.out.println("dog = " + dog.name);
        replace(dog);
        System.out.println("dog = " + dog.name);

        String word = "hi";
        shout(word);
        System.out.println("word = " + word);

        StringBuilder sb = new StringBuilder("hi");
        excite(sb);
        System.out.println("sb = " + sb);
    }
}
