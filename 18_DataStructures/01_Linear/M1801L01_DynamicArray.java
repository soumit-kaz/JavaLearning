import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class M1801L01_DynamicArray {

    static class DynamicArray<T> {
        private Object[] data = new Object[2];
        private int size;

        int size() {
            return size;
        }

        int capacity() {
            return data.length;
        }

        void add(T value) {
            add(size, value);
        }

        void add(int index, T value) {
            // index == size is allowed: it means "append"
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("index " + index);
            }
            // array is full, so double it first
            if (size == data.length) {
                resize(data.length * 2);
            }
            // shift elements right to open a gap at index
            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }
            data[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        T get(int index) {
            checkIndex(index);
            return (T) data[index];
        }

        void set(int index, T value) {
            checkIndex(index);
            data[index] = value;
        }

        T removeAt(int index) {
            T removed = get(index);
            // shift elements left to close the gap
            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }
            // clear the old last slot so the object can be garbage collected
            data[--size] = null;
            // shrink at 1/4 full (not 1/2) to avoid resizing back and forth
            if (data.length > 2 && size <= data.length / 4) {
                resize(data.length / 2);
            }
            return removed;
        }

        int indexOf(T value) {
            for (int i = 0; i < size; i++) {
                // use equals() for objects, and handle null safely
                if (value == null ? data[i] == null : value.equals(data[i])) {
                    return i;
                }
            }
            return -1;
        }

        private void checkIndex(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("index " + index);
            }
        }

        private void resize(int newCapacity) {
            // copying is O(n), but doubling makes it rare
            Object[] copy = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                copy[i] = data[i];
            }
            data = copy;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(i > 0 ? ", " : "").append(data[i]);
            }
            return sb.append("]").toString();
        }
    }

    public static void main(String[] args) {
        DynamicArray<Integer> arr = new DynamicArray<>();
        for (int i = 1; i <= 5; i++) {
            arr.add(i * 10);
        }
        System.out.println("array: " + arr);
        System.out.println("size: " + arr.size());
        System.out.println("capacity: " + arr.capacity());
        arr.add(0, 5);
        System.out.println("add(0, 5): " + arr);
        arr.set(2, 99);
        System.out.println("set(2, 99): " + arr);
        System.out.println("removeAt(0): " + arr.removeAt(0));
        System.out.println("indexOf(40): " + arr.indexOf(40));
        try {
            arr.get(10);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("get(10): " + e.getClass().getSimpleName());
        }

        // compare against ArrayList with random operations
        boolean ok = true;
        Random rnd = new Random(42);
        DynamicArray<Integer> mine = new DynamicArray<>();
        List<Integer> ref = new ArrayList<>();
        for (int step = 0; step < 20_000; step++) {
            int op = rnd.nextInt(4);
            int value = rnd.nextInt(100);
            if (op <= 1 || ref.isEmpty()) {
                int index = rnd.nextInt(ref.size() + 1);
                mine.add(index, value);
                ref.add(index, value);
            } else if (op == 2) {
                int index = rnd.nextInt(ref.size());
                ok &= mine.removeAt(index).equals(ref.remove(index));
            } else {
                ok &= mine.indexOf(value) == ref.indexOf(value);
            }
            ok &= mine.size() == ref.size();
        }
        ok &= mine.toString().equals(ref.toString());
        System.out.println(ok ? "OK" : "FAIL");
    }
}
