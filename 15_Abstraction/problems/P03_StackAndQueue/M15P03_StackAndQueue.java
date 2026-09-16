public class M15P03_StackAndQueue {

    abstract static class IntContainer {
        // shared state: a fixed-size array and a count
        protected final int[] data;
        protected int size;

        IntContainer(int capacity) {
            data = new int[capacity];
        }

        // shared checks, then the subclass decides where the value goes
        final void add(int value) {
            if (size == data.length) {
                throw new IllegalStateException("full");
            }
            store(value);
            size++;
        }

        final int remove() {
            if (size == 0) {
                throw new IllegalStateException("empty");
            }
            int value = take();
            size--;
            return value;
        }

        // the i-th element in the order remove() would return them
        abstract int peekAt(int i);

        abstract void store(int value);

        abstract int take();

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < size; i++) {
                s += (i == 0 ? "" : " ") + peekAt(i);
            }
            return "[" + s + "]";
        }
    }

    // last in, first out
    static class IntStack extends IntContainer {
        IntStack(int capacity) {
            super(capacity);
        }

        @Override
        void store(int value) {
            data[size] = value;
        }

        @Override
        int take() {
            return data[size - 1];
        }

        @Override
        int peekAt(int i) {
            return data[size - 1 - i];
        }
    }

    // first in, first out, using a circular array
    static class IntQueue extends IntContainer {
        private int head;

        IntQueue(int capacity) {
            super(capacity);
        }

        @Override
        void store(int value) {
            data[(head + size) % data.length] = value;
        }

        @Override
        int take() {
            int value = data[head];
            head = (head + 1) % data.length;
            return value;
        }

        @Override
        int peekAt(int i) {
            return data[(head + i) % data.length];
        }
    }

    // "+5" adds 5, "-" removes one; errors stop the run
    static String run(IntContainer c, String ops) {
        String removed = "";
        try {
            for (String op : ops.split(" ")) {
                if (op.equals("-")) {
                    removed += c.remove() + " ";
                } else {
                    c.add(Integer.parseInt(op.substring(1)));
                }
            }
        } catch (IllegalStateException e) {
            removed += "error:" + e.getMessage() + " ";
        }
        return "removed " + removed + "left " + c;
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        check("IntStack(3) +1 +2 +3 - -", run(new IntStack(3), "+1 +2 +3 - -"), "removed 3 2 left [1]");
        check("IntQueue(3) +1 +2 +3 - -", run(new IntQueue(3), "+1 +2 +3 - -"), "removed 1 2 left [3]");
        check("IntQueue(3) +1 +2 - +3 +4 -", run(new IntQueue(3), "+1 +2 - +3 +4 -"), "removed 1 2 left [3 4]");
        check("IntStack(2) +1 +2 +3", run(new IntStack(2), "+1 +2 +3"), "removed error:full left [2 1]");
        check("IntQueue(2) +1 - -", run(new IntQueue(2), "+1 - -"), "removed 1 error:empty left []");
        check("IntQueue(3) +1 +2 +3 - - - +4 +5 +6", run(new IntQueue(3), "+1 +2 +3 - - - +4 +5 +6"), "removed 1 2 3 left [4 5 6]");
        if (failures > 0) {
            System.exit(1);
        }
    }
}
