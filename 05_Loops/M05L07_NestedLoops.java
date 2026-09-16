public class M05L07_NestedLoops {

    public static void main(String[] args) {
        // the inner loop runs completely for every outer step
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 4; col++) {
                System.out.print(row + "" + col + " ");
            }
            System.out.println();
        }

        // total steps = outer count * inner count
        int steps = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                steps++;
            }
        }
        System.out.println("steps = " + steps);

        int height = 4;

        // right triangle: the inner loop depends on the row
        for (int row = 1; row <= height; row++) {
            for (int star = 1; star <= row; star++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // pyramid: height - row spaces, then 2 * row - 1 stars
        for (int row = 1; row <= height; row++) {
            for (int space = 1; space <= height - row; space++) {
                System.out.print(" ");
            }
            for (int star = 1; star <= 2 * row - 1; star++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // repeat() builds the same pyramid without inner loops
        for (int row = 1; row <= height; row++) {
            System.out.println(" ".repeat(height - row) + "*".repeat(2 * row - 1));
        }

        // multiplication table: one row per number
        int size = 5;
        for (int row = 1; row <= size; row++) {
            for (int col = 1; col <= size; col++) {
                System.out.printf("%4d", row * col);
            }
            System.out.println();
        }
    }
}
