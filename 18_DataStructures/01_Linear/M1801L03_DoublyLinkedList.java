import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class M1801L03_DoublyLinkedList {

    static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    static class DoublyLinkedList<T> {
        // sentinel nodes: every real node has a prev and a next, so no null checks
        private final Node<T> head = new Node<>(null);
        private final Node<T> tail = new Node<>(null);
        private int size;

        DoublyLinkedList() {
            head.next = tail;
            tail.prev = head;
        }

        int size() {
            return size;
        }

        Node<T> addFirst(T value) {
            return insertAfter(head, value);
        }

        Node<T> addLast(T value) {
            return insertAfter(tail.prev, value);
        }

        T removeFirst() {
            checkNotEmpty();
            return remove(head.next);
        }

        T removeLast() {
            checkNotEmpty();
            // O(1) because we can step back from the tail
            return remove(tail.prev);
        }

        T remove(Node<T> node) {
            // with a node reference no search is needed: neighbours skip over it
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return node.value;
        }

        private Node<T> insertAfter(Node<T> before, T value) {
            Node<T> node = new Node<>(value);
            Node<T> after = before.next;
            // four links change: two on the new node, one on each neighbour
            node.prev = before;
            node.next = after;
            before.next = node;
            after.prev = node;
            size++;
            return node;
        }

        private void checkNotEmpty() {
            if (size == 0) {
                throw new NoSuchElementException("list is empty");
            }
        }

        List<T> forward() {
            List<T> out = new ArrayList<>();
            for (Node<T> cur = head.next; cur != tail; cur = cur.next) {
                out.add(cur.value);
            }
            return out;
        }

        List<T> backward() {
            List<T> out = new ArrayList<>();
            for (Node<T> cur = tail.prev; cur != head; cur = cur.prev) {
                out.add(cur.value);
            }
            return out;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addLast("B");
        list.addFirst("A");
        Node<String> c = list.addLast("C");
        list.addLast("D");
        System.out.println("forward: " + list.forward());
        System.out.println("backward: " + list.backward());
        System.out.println("remove(C node): " + list.remove(c));
        System.out.println("removeFirst: " + list.removeFirst());
        System.out.println("removeLast: " + list.removeLast());
        list.removeLast();
        try {
            list.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("removeLast on empty: " + e.getClass().getSimpleName());
        }

        // compare against java.util.LinkedList with random operations
        boolean ok = list.size() == 0;
        Random rnd = new Random(11);
        DoublyLinkedList<Integer> mine = new DoublyLinkedList<>();
        LinkedList<Integer> ref = new LinkedList<>();
        for (int step = 0; step < 20_000; step++) {
            int value = rnd.nextInt(100);
            int op = rnd.nextInt(4);
            if (op == 0) {
                mine.addFirst(value);
                ref.addFirst(value);
            } else if (op == 1) {
                mine.addLast(value);
                ref.addLast(value);
            } else if (op == 2 && !ref.isEmpty()) {
                ok &= mine.removeFirst().equals(ref.removeFirst());
            } else if (op == 3 && !ref.isEmpty()) {
                ok &= mine.removeLast().equals(ref.removeLast());
            }
            ok &= mine.size() == ref.size();
        }
        ok &= mine.forward().equals(ref) && mine.backward().equals(ref.reversed());
        System.out.println(ok ? "OK" : "FAIL");
    }
}
