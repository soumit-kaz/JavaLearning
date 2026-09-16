import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804L01_BinaryHeap {

    // array min-heap: parent of i is (i-1)/2, children are 2i+1 and 2i+2
    static class MinHeap {
        private int[] data;
        private int size;

        MinHeap() {
            data = new int[4];
        }

        // bottom-up heapify in O(n): leaves are already heaps, so sift down from the last parent
        MinHeap(int[] values) {
            data = Arrays.copyOf(values, Math.max(4, values.length));
            size = values.length;
            for (int i = size / 2 - 1; i >= 0; i--) {
                siftDown(i);
            }
        }

        void push(int value) {
            if (size == data.length) {
                data = Arrays.copyOf(data, size * 2);
            }
            data[size] = value;
            siftUp(size);
            size++;
        }

        int peek() {
            if (size == 0) {
                throw new NoSuchElementException("heap is empty");
            }
            return data[0];
        }

        int pop() {
            int top = peek();
            // move the last value to the root, then sift it down
            size--;
            data[0] = data[size];
            siftDown(0);
            return top;
        }

        int size() {
            return size;
        }

        // move up while smaller than the parent
        private void siftUp(int i) {
            while (i > 0 && data[i] < data[(i - 1) / 2]) {
                swap(i, (i - 1) / 2);
                i = (i - 1) / 2;
            }
        }

        // swap with the smaller child until the value is in place
        private void siftDown(int i) {
            while (2 * i + 1 < size) {
                int child = 2 * i + 1;
                if (child + 1 < size && data[child + 1] < data[child]) {
                    child++;
                }
                if (data[child] >= data[i]) {
                    return;
                }
                swap(i, child);
                i = child;
            }
        }

        private void swap(int i, int j) {
            int t = data[i];
            data[i] = data[j];
            data[j] = t;
        }
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        for (int v : new int[]{5, 3, 8, 1, 9, 2}) {
            heap.push(v);
        }
        System.out.println("peek: " + heap.peek());
        System.out.println("pop: " + heap.pop());
        System.out.println("pop: " + heap.pop());
        MinHeap built = new MinHeap(new int[]{4, 10, 3, 5, 1});
        System.out.println("heapify then pop: " + built.pop());

        boolean ok = heap.peek() == 3 && built.pop() == 3 && built.size() == 3;
        try {
            new MinHeap().pop();
            ok = false;
        } catch (NoSuchElementException e) {
            // expected: popping an empty heap throws
        }

        // random pushes and pops compared with PriorityQueue
        Random rnd = new Random(1);
        for (int t = 0; t < 20; t++) {
            int[] seed = new int[rnd.nextInt(20)];
            for (int i = 0; i < seed.length; i++) {
                seed[i] = rnd.nextInt(100);
            }
            MinHeap mine = new MinHeap(seed);
            PriorityQueue<Integer> jdk = new PriorityQueue<>();
            for (int v : seed) {
                jdk.add(v);
            }
            for (int step = 0; step < 5000; step++) {
                if (jdk.isEmpty() || rnd.nextInt(3) > 0) {
                    int v = rnd.nextInt(1000);
                    mine.push(v);
                    jdk.add(v);
                } else {
                    ok &= mine.pop() == jdk.poll();
                }
                ok &= mine.size() == jdk.size();
            }
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
