import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class M17L13_Queue {

    public static void main(String[] args) {
        // ArrayDeque as a queue: first in, first out
        Queue<String> line = new ArrayDeque<>();
        line.offer("ann");
        line.offer("bob");
        line.offer("cy");
        System.out.println("queue: " + line);
        System.out.println("peek: " + line.peek());
        System.out.println("poll: " + line.poll());
        System.out.println("queue: " + line);

        // poll and peek return null when empty; remove and element throw instead
        line.clear();
        System.out.println("poll on empty: " + line.poll());
        try {
            line.remove();
        } catch (NoSuchElementException e) {
            System.out.println("remove on empty: NoSuchElementException");
        }

        // LinkedList is also a Queue (and, unlike ArrayDeque, it allows null)
        Queue<Integer> tickets = new LinkedList<>();
        tickets.offer(1);
        tickets.offer(2);
        System.out.println("LinkedList poll: " + tickets.poll());

        // a Deque can add and remove at both ends
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(2);
        deque.offerFirst(1);
        deque.offerLast(3);
        System.out.println("deque: " + deque);
        System.out.println("pollFirst: " + deque.pollFirst());
        System.out.println("pollLast: " + deque.pollLast());
    }
}
