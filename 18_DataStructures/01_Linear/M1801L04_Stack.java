import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.Random;

public class M1801L04_Stack {

    static class ArrayStack<T> {
        private Object[] data = new Object[2];
        private int size;

        void push(T value) {
            // grow when full
            if (size == data.length) {
                Object[] bigger = new Object[data.length * 2];
                for (int i = 0; i < size; i++) {
                    bigger[i] = data[i];
                }
                data = bigger;
            }
            data[size++] = value;
        }

        T pop() {
            T value = peek();
            // clear the slot so the object can be garbage collected
            data[--size] = null;
            return value;
        }

        @SuppressWarnings("unchecked")
        T peek() {
            if (size == 0) {
                throw new NoSuchElementException("stack is empty");
            }
            // the top is the last used slot
            return (T) data[size - 1];
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    static class LinkedStack<T> {
        private static class Node<T> {
            T value;
            Node<T> next;
        }

        private Node<T> top;

        void push(T value) {
            // the new node goes in front and becomes the top
            Node<T> node = new Node<>();
            node.value = value;
            node.next = top;
            top = node;
        }

        T pop() {
            if (top == null) {
                throw new NoSuchElementException("stack is empty");
            }
            T value = top.value;
            top = top.next;
            return value;
        }

        boolean isEmpty() {
            return top == null;
        }
    }

    static boolean isBalanced(String text) {
        ArrayStack<Character> open = new ArrayStack<>();
        for (char c : text.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                open.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                // a closer must match the most recent opener
                if (open.isEmpty()) {
                    return false;
                }
                char o = open.pop();
                if ((c == ')' && o != '(') || (c == ']' && o != '[') || (c == '}' && o != '{')) {
                    return false;
                }
            }
        }
        // leftover openers were never closed
        return open.isEmpty();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 1; i <= 3; i++) {
            stack.push(i);
        }
        System.out.println("peek: " + stack.peek());
        System.out.println("pop: " + stack.pop());
        System.out.println("pop: " + stack.pop());
        String[] inputs = {"a(b[c]{d})", "(]", "(()", "", "{[()]}}"};
        boolean[] expected = {true, false, false, true, false};
        boolean ok = true;
        for (int i = 0; i < inputs.length; i++) {
            System.out.println("\"" + inputs[i] + "\" balanced: " + isBalanced(inputs[i]));
            ok &= isBalanced(inputs[i]) == expected[i];
        }

        // compare both stacks against ArrayDeque with random operations
        Random rnd = new Random(3);
        ArrayStack<Integer> a = new ArrayStack<>();
        LinkedStack<Integer> b = new LinkedStack<>();
        Deque<Integer> ref = new ArrayDeque<>();
        for (int step = 0; step < 20_000; step++) {
            if (rnd.nextInt(3) > 0 || ref.isEmpty()) {
                int value = rnd.nextInt(100);
                a.push(value);
                b.push(value);
                ref.push(value);
            } else {
                int want = ref.pop();
                ok &= a.pop() == want && b.pop() == want;
            }
            ok &= a.isEmpty() == ref.isEmpty() && b.isEmpty() == ref.isEmpty();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
