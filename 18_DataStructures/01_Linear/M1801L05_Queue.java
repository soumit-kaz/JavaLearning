import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Random;

public class M1801L05_Queue {

    // a queue on a circular array: add at the back, remove from the front
    static class ArrayQueue<T> {
        private Object[] data = new Object[2];
        private int head;
        private int size;

        void enqueue(T value) {
            grow();
            // wrap around the end of the array with %
            data[(head + size) % data.length] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        T dequeue() {
            if (size == 0) {
                throw new NoSuchElementException("queue is empty");
            }
            T value = (T) data[head];
            // clear the slot so the object can be garbage collected
            data[head] = null;
            // the front moves forward and wraps too
            head = (head + 1) % data.length;
            size--;
            return value;
        }

        int size() {
            return size;
        }

        private void grow() {
            if (size < data.length) {
                return;
            }
            // copy in queue order, so the new array starts at index 0
            Object[] bigger = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                bigger[i] = data[(head + i) % data.length];
            }
            data = bigger;
            head = 0;
        }
    }

    static class LinkedQueue<T> {
        private static class Node<T> {
            T value;
            Node<T> next;
        }

        private Node<T> first;
        private Node<T> last;

        void enqueue(T value) {
            Node<T> node = new Node<>();
            node.value = value;
            // add at the tail
            if (last == null) {
                first = node;
            } else {
                last.next = node;
            }
            last = node;
        }

        T dequeue() {
            if (first == null) {
                throw new NoSuchElementException("queue is empty");
            }
            // remove from the head
            T value = first.value;
            first = first.next;
            // queue became empty, so clear the tail too
            if (first == null) {
                last = null;
            }
            return value;
        }

        boolean isEmpty() {
            return first == null;
        }
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 1; i <= 3; i++) {
            queue.enqueue(i);
        }
        System.out.println("dequeue: " + queue.dequeue());
        queue.enqueue(4);
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("size: " + queue.size());
        try {
            new LinkedQueue<Integer>().dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("dequeue on empty: " + e.getClass().getSimpleName());
        }

        // compare both queues against ArrayDeque with random operations
        boolean ok = queue.dequeue() == 3 && queue.dequeue() == 4;
        Random rnd = new Random(5);
        ArrayQueue<Integer> a = new ArrayQueue<>();
        LinkedQueue<Integer> b = new LinkedQueue<>();
        ArrayDeque<Integer> ref = new ArrayDeque<>();
        for (int step = 0; step < 20_000; step++) {
            if (rnd.nextInt(3) > 0 || ref.isEmpty()) {
                int value = rnd.nextInt(100);
                a.enqueue(value);
                b.enqueue(value);
                ref.addLast(value);
            } else {
                int want = ref.removeFirst();
                ok &= a.dequeue() == want && b.dequeue() == want;
            }
            ok &= a.size() == ref.size() && b.isEmpty() == ref.isEmpty();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
