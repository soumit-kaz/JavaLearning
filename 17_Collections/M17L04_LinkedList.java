import java.util.LinkedList;

public class M17L04_LinkedList {

    public static void main(String[] args) {
        // LinkedList is a doubly linked list: fast at both ends
        LinkedList<String> tasks = new LinkedList<>();
        tasks.add("write");
        tasks.addFirst("plan");
        tasks.addLast("test");
        System.out.println("tasks: " + tasks);
        System.out.println("first: " + tasks.getFirst());
        System.out.println("last: " + tasks.getLast());

        // removing at the ends is O(1)
        System.out.println("removeFirst: " + tasks.removeFirst());
        System.out.println("removeLast: " + tasks.removeLast());
        System.out.println("tasks: " + tasks);

        // get(i) walks node by node (O(n)), so ArrayList is usually the better choice
        tasks.add("review");
        System.out.println("get(1): " + tasks.get(1));
    }
}
