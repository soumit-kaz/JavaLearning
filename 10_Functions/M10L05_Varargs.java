public class M10L05_Varargs {

    // int... accepts any number of ints; inside, numbers is an int[]
    static int sum(int... numbers) {
        int total = 0;
        for (int n : numbers) {
            total += n;
        }
        return total;
    }

    // the varargs parameter must come last
    static String join(String separator, String... words) {
        return String.join(separator, words);
    }

    // a normal first parameter forces at least one value
    static int max(int first, int... rest) {
        int best = first;
        for (int n : rest) {
            best = Math.max(best, n);
        }
        return best;
    }

    static int countItems(String... items) {
        // a caller may pass a null array on purpose
        if (items == null) {
            return -1;
        }
        return items.length;
    }

    static void setFirst(int... numbers) {
        numbers[0] = 99;
    }

    public static void main(String[] args) {
        // no arguments gives an empty array, not null
        System.out.println("sum() = " + sum());
        System.out.println("sum(1, 2, 3) = " + sum(1, 2, 3));
        System.out.println("join = " + join("-", "a", "b", "c"));
        System.out.println("max = " + max(4, 9, 2));

        // an existing array can be passed directly, and it is the same array
        int[] data = {1, 2, 3};
        setFirst(data);
        System.out.println("data[0] = " + data[0]);

        // separate values: Java builds a new array, data is untouched
        setFirst(data[1], data[2]);
        System.out.println("data[1] = " + data[1]);

        // cast null to String[] for a null array, or to String for one null item
        System.out.println("null array = " + countItems((String[]) null));
        System.out.println("one null = " + countItems((String) null));
    }
}
