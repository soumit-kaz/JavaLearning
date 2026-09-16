public class M05P08_PatternPrinting {

    public static void main(String[] args) {
        int size = 4;

        // pyramid: size - row spaces, then 2 * row - 1 stars
        for (int row = 1; row <= size; row++) {
            System.out.println(" ".repeat(size - row) + "*".repeat(2 * row - 1));
        }
        System.out.println();

        // diamond: the pyramid, then the same rows backwards without the widest one
        for (int row = 1; row <= size; row++) {
            System.out.println(" ".repeat(size - row) + "*".repeat(2 * row - 1));
        }
        for (int row = size - 1; row >= 1; row--) {
            System.out.println(" ".repeat(size - row) + "*".repeat(2 * row - 1));
        }
        System.out.println();

        // Floyd's triangle: keep counting across the rows
        int number = 1;
        for (int row = 1; row <= size; row++) {
            String line = "";
            for (int col = 1; col <= row; col++) {
                line += (col == 1 ? "" : " ") + number;
                number++;
            }
            System.out.println(line);
        }
    }
}
