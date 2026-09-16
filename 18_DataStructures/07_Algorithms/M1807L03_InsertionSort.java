import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class M1807L03_InsertionSort {

    // name is only a label; sorting looks at score alone
    static class Player {
        final String name;
        final int score;

        Player(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            return name + score;
        }
    }

    static final Comparator<Player> BY_SCORE = (x, y) -> Integer.compare(x.score, y.score);

    // sorts a and returns how many shifts it made
    static <T> long insertionSort(T[] a, Comparator<T> cmp) {
        long shifts = 0;
        for (int i = 1; i < a.length; i++) {
            T key = a[i];
            int j = i - 1;
            // strict > never moves key past an equal value, so the sort is stable
            while (j >= 0 && cmp.compare(a[j], key) > 0) {
                a[j + 1] = a[j];
                j--;
                shifts++;
            }
            a[j + 1] = key;
        }
        return shifts;
    }

    // pairs i < j with a[i] > a[j]; each shift above fixes exactly one of them
    static long countInversions(Integer[] a) {
        long count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Player[] players = {new Player("A", 2), new Player("B", 1), new Player("C", 2), new Player("D", 1)};
        Player[] library = players.clone();
        insertionSort(players, BY_SCORE);
        System.out.println(Arrays.toString(players));

        Integer[] nearlySorted = {1, 2, 4, 3, 5, 6};
        long shifts = insertionSort(nearlySorted, Comparator.naturalOrder());
        System.out.println("shifts on nearly sorted input: " + shifts);

        // Arrays.sort on objects is stable, so a stable sort must match it exactly
        Arrays.sort(library, BY_SCORE);
        boolean ok = Arrays.equals(players, library) && shifts == 1;

        // random arrays: match Arrays.sort, and shifts equal the inversion count
        Random random = new Random(3);
        for (int t = 0; t < 300; t++) {
            Integer[] a = new Integer[random.nextInt(30)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(20) - 10;
            }
            long inversions = countInversions(a);
            Integer[] expected = a.clone();
            Arrays.sort(expected);
            ok &= insertionSort(a, Comparator.naturalOrder()) == inversions;
            ok &= Arrays.equals(a, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
