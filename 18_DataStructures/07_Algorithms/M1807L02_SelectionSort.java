import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class M1807L02_SelectionSort {

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

    // sorts a and returns how many swaps it made (never more than n - 1)
    static <T> int selectionSort(T[] a, Comparator<T> cmp) {
        int swaps = 0;
        for (int i = 0; i < a.length - 1; i++) {
            // find the smallest value in the unsorted part a[i..]
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (cmp.compare(a[j], a[min]) < 0) {
                    min = j;
                }
            }
            // the long-distance swap can jump over an equal value, so this sort is unstable
            if (min != i) {
                T temp = a[i];
                a[i] = a[min];
                a[min] = temp;
                swaps++;
            }
        }
        return swaps;
    }

    public static void main(String[] args) {
        Player[] players = {new Player("A", 2), new Player("B", 1), new Player("C", 2), new Player("D", 1)};
        Player[] library = players.clone();
        selectionSort(players, BY_SCORE);
        // C2 ends up before A2: equal scores lost their order
        System.out.println(Arrays.toString(players));

        // the stable Arrays.sort keeps A2 before C2, so the results differ
        Arrays.sort(library, BY_SCORE);
        boolean ok = !Arrays.equals(players, library);

        // random arrays compared with Arrays.sort
        Random random = new Random(2);
        for (int t = 0; t < 300; t++) {
            Integer[] a = new Integer[random.nextInt(30)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(20) - 10;
            }
            Integer[] expected = a.clone();
            Arrays.sort(expected);
            int swaps = selectionSort(a, Comparator.naturalOrder());
            ok &= Arrays.equals(a, expected) && swaps <= Math.max(0, a.length - 1);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
