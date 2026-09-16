import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class M1807L01_BubbleSort {

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

    // sorts a and returns how many passes it needed
    static <T> int bubbleSort(T[] a, Comparator<T> cmp) {
        int passes = 0;
        for (int end = a.length - 1; end > 0; end--) {
            passes++;
            boolean swapped = false;
            // after each pass the largest value sits at index end
            for (int i = 0; i < end; i++) {
                // strict > never swaps equal values, so the sort is stable
                if (cmp.compare(a[i], a[i + 1]) > 0) {
                    T temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                    swapped = true;
                }
            }
            // no swaps means already sorted: O(n) on sorted input
            if (!swapped) {
                break;
            }
        }
        return passes;
    }

    public static void main(String[] args) {
        Player[] players = {new Player("A", 2), new Player("B", 1), new Player("C", 2), new Player("D", 1)};
        Player[] library = players.clone();
        bubbleSort(players, BY_SCORE);
        System.out.println(Arrays.toString(players));

        Integer[] sorted = {1, 2, 3, 4, 5, 6};
        int passes = bubbleSort(sorted, Comparator.naturalOrder());
        System.out.println("passes on sorted input: " + passes);

        // Arrays.sort on objects is stable, so a stable sort must match it exactly
        Arrays.sort(library, BY_SCORE);
        boolean ok = Arrays.equals(players, library) && passes == 1;

        // random arrays compared with Arrays.sort
        Random random = new Random(1);
        for (int t = 0; t < 300; t++) {
            Integer[] a = new Integer[random.nextInt(30)];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(20) - 10;
            }
            Integer[] expected = a.clone();
            Arrays.sort(expected);
            bubbleSort(a, Comparator.naturalOrder());
            ok &= Arrays.equals(a, expected);
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
