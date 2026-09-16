import java.util.LinkedList;
import java.util.Random;

public class M1801L02_SinglyLinkedList {

    static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    static class SinglyLinkedList {
        Node head;
        Node tail;

        void addFirst(int value) {
            head = new Node(value, head);
            // first node is both head and tail
            if (tail == null) {
                tail = head;
            }
        }

        void addLast(int value) {
            Node node = new Node(value, null);
            if (tail == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        boolean removeValue(int value) {
            // dummy node: removing the head needs no special case
            Node dummy = new Node(0, head);
            for (Node prev = dummy; prev.next != null; prev = prev.next) {
                if (prev.next.value == value) {
                    if (prev.next == tail) {
                        tail = prev == dummy ? null : prev;
                    }
                    prev.next = prev.next.next;
                    head = dummy.next;
                    return true;
                }
            }
            return false;
        }

        void reverse() {
            tail = head;
            Node prev = null;
            while (head != null) {
                // save next before changing the link
                Node next = head.next;
                head.next = prev;
                prev = head;
                head = next;
            }
            head = prev;
        }

        Node middle() {
            // fast moves twice as fast, so slow ends in the middle
            Node slow = head;
            Node fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (Node cur = head; cur != null; cur = cur.next) {
                sb.append(cur.value).append(cur.next != null ? ", " : "");
            }
            return sb.append("]").toString();
        }
    }

    static Node cycleStart(Node head) {
        // Floyd: slow moves 1 step, fast moves 2; they meet inside a cycle
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // restart one pointer at head; they meet at the cycle start
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int i = 1; i <= 5; i++) {
            list.addLast(i);
        }
        System.out.println("list: " + list);
        System.out.println("middle: " + list.middle().value);
        list.reverse();
        System.out.println("reversed: " + list);
        list.removeValue(5);
        list.removeValue(1);
        System.out.println("remove 5 and 1: " + list);
        System.out.println("tail: " + list.tail.value);
        boolean ok = cycleStart(list.head) == null;
        // make a cycle: [4, 3, 2] and the tail points back to 3
        list.tail.next = list.head.next;
        Node start = cycleStart(list.head);
        System.out.println("cycle starts at: " + start.value);
        ok &= start.value == 3;
        // compare against java.util.LinkedList with random operations
        Random rnd = new Random(7);
        SinglyLinkedList mine = new SinglyLinkedList();
        LinkedList<Integer> ref = new LinkedList<>();
        for (int step = 0; step < 20_000; step++) {
            int value = rnd.nextInt(20);
            int op = rnd.nextInt(10);
            if (op < 3) {
                mine.addFirst(value);
                ref.addFirst(value);
            } else if (op < 6) {
                mine.addLast(value);
                ref.addLast(value);
            } else if (op < 9) {
                ok &= mine.removeValue(value) == ref.remove(Integer.valueOf(value));
            } else {
                mine.reverse();
                ref = new LinkedList<>(ref.reversed());
            }
            // tail must always point at the real last node
            ok &= ref.isEmpty() ? mine.tail == null : mine.tail.value == ref.getLast();
        }
        ok &= mine.toString().equals(ref.toString());
        System.out.println(ok ? "OK" : "FAIL");
    }
}
