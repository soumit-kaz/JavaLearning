import java.util.Comparator;
import java.util.PriorityQueue;

public class M17L14_PriorityQueue {

    record Task(String name, int priority) {
    }

    public static void main(String[] args) {
        int[] values = {5, 1, 8, 3, 9, 2};

        // PriorityQueue is a MIN-heap: poll always returns the smallest
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int v : values) {
            minHeap.offer(v);
        }
        System.out.println("peek: " + minHeap.peek());

        // printing a heap does NOT show sorted order; polling does
        System.out.println("printed: " + minHeap);
        StringBuilder order = new StringBuilder();
        while (!minHeap.isEmpty()) {
            order.append(minHeap.poll()).append(' ');
        }
        System.out.println("polled: " + order.toString().trim());

        // reverse the order to get a MAX-heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for (int v : values) {
            maxHeap.offer(v);
        }
        System.out.println("max: " + maxHeap.peek());

        // keep the 3 largest values in a small min-heap
        PriorityQueue<Integer> topThree = new PriorityQueue<>();
        for (int v : values) {
            topThree.offer(v);
            if (topThree.size() > 3) {
                topThree.poll();
            }
        }
        System.out.println("3rd largest: " + topThree.peek());

        // a heap of records needs a comparator: highest priority first
        PriorityQueue<Task> tasks = new PriorityQueue<>(
                Comparator.comparingInt(Task::priority).reversed());
        tasks.offer(new Task("backup", 1));
        tasks.offer(new Task("fix bug", 10));
        tasks.offer(new Task("deploy", 5));
        while (!tasks.isEmpty()) {
            System.out.println("task: " + tasks.poll().name());
        }
    }
}
