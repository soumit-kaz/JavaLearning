public class M05P01_MultiplicationTable {

    public static void main(String[] args) {
        int number = 7;

        // one line for each multiplier from 1 to 10
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %2d = %2d%n", number, i, number * i);
        }
    }
}
