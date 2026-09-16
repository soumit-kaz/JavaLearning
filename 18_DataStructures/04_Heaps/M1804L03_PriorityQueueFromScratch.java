import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804L03_PriorityQueueFromScratch {

    // a heap of any type; the comparator decides what comes first
    static class MyPriorityQueue<E> {
        private final List<E> heap = new ArrayList<>();
        private final Comparator<? super E> cmp;

        MyPriorityQueue(Comparator<? super E> cmp) {
            this.cmp = cmp;
        }

        void offer(E item) {
            // the list grows by itself; add at the end and sift up
            heap.add(item);
            int i = heap.size() - 1;
            while (i > 0 && less(i, (i - 1) / 2)) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        // null when empty, like java.util.PriorityQueue
        E peek() {
            return heap.isEmpty() ? null : heap.get(0);
        }

        E poll() {
            if (heap.isEmpty()) {
                return null;
            }
            E top = heap.get(0);
            E last = heap.remove(heap.size() - 1);
            if (heap.isEmpty()) {
                return top;
            }
            // put the last item at the root and sift it down
            heap.set(0, last);
            int i = 0;
            while (2 * i + 1 < heap.size()) {
                int child = 2 * i + 1;
                if (child + 1 < heap.size() && less(child + 1, child)) {
                    child++;
                }
                if (!less(child, i)) {
                    break;
                }
                swap(i, child);
                i = child;
            }
            return top;
        }

        private boolean less(int a, int b) {
            return cmp.compare(heap.get(a), heap.get(b)) < 0;
        }

        private void swap(int a, int b) {
            E t = heap.get(a);
            heap.set(a, heap.get(b));
            heap.set(b, t);
        }

        boolean isEmpty() {
            return heap.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyPriorityQueue<String> byLength = new MyPriorityQueue<>(
                Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));
        for (String s : List.of("pear", "fig", "banana", "kiwi")) {
            byLength.offer(s);
        }
        System.out.println("by length: " + byLength.poll() + " " + byLength.poll());
        MyPriorityQueue<Integer> maxFirst = new MyPriorityQueue<>(Comparator.reverseOrder());
        maxFirst.offer(3);
        maxFirst.offer(9);
        System.out.println("max first: " + maxFirst.poll());

        // self-check against java.util.PriorityQueue with a custom comparator
        boolean ok = new MyPriorityQueue<Integer>(Comparator.naturalOrder()).poll() == null;
        Comparator<Integer> byLastDigit = Comparator.comparingInt((Integer x) -> x % 10)
                .thenComparing(Comparator.naturalOrder());
        MyPriorityQueue<Integer> mine = new MyPriorityQueue<>(byLastDigit);
        PriorityQueue<Integer> jdk = new PriorityQueue<>(byLastDigit);
        Random rnd = new Random(4);
        for (int step = 0; step < 100_000; step++) {
            if (rnd.nextInt(3) > 0) {
                int v = rnd.nextInt(10_000);
                mine.offer(v);
                jdk.offer(v);
            } else {
                ok &= Objects.equals(mine.poll(), jdk.poll());
            }
            ok &= Objects.equals(mine.peek(), jdk.peek());
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
