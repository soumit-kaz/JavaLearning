import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class M17L12_Stack {

    public static void main(String[] args) {
        // ArrayDeque as a stack: last in, first out
        // (prefer it over the old java.util.Stack class)
        Deque<String> pages = new ArrayDeque<>();
        pages.push("home");
        pages.push("news");
        pages.push("article");
        System.out.println("stack: " + pages);
        System.out.println("peek: " + pages.peek());
        System.out.println("pop: " + pages.pop());
        System.out.println("stack: " + pages);

        // peek returns null when empty; pop throws instead
        pages.clear();
        System.out.println("peek on empty: " + pages.peek());
        try {
            pages.pop();
        } catch (NoSuchElementException e) {
            System.out.println("pop on empty: NoSuchElementException");
        }

        // a stack reverses the order: reverse a word
        Deque<Character> letters = new ArrayDeque<>();
        for (char c : "java".toCharArray()) {
            letters.push(c);
        }
        StringBuilder reversed = new StringBuilder();
        while (!letters.isEmpty()) {
            reversed.append(letters.pop());
        }
        System.out.println("reversed: " + reversed);
    }
}
