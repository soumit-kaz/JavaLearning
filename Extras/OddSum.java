public class OddSum {
    public static int oddSum(int[][] abul) {
        int sum = 0;
        for (int[] row : abul) {
            for (int e : row) {
                if (e % 2 == 1) {
                    sum += e;
                }
            }
        }
        return sum;
    }

    // The JVM starts the program here
    public static void main(String[] args) {
        int[][] array = { { 10, 20, 31 },
                { 100, 301 } };
        int sum = oddSum(array);
        System.out.println(sum);
    }
}
