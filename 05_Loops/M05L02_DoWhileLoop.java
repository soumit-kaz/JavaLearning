public class M05L02_DoWhileLoop {

    public static void main(String[] args) {
        // do-while runs the body first, then checks the condition
        int count = 1;
        do {
            System.out.println("count = " + count);
            count++;
        } while (count <= 3);

        // so it runs at least once, even when the condition is false
        int n = 10;
        do {
            System.out.println("n = " + n);
        } while (n < 5);

        // 0 has one digit: do-while counts it, while (x > 0) would not
        int number = 0;
        int digits = 0;
        do {
            digits++;
            number /= 10;
        } while (number > 0);
        System.out.println("digits of 0 = " + digits);

        // keep doubling until the value passes 100
        int value = 1;
        do {
            value *= 2;
        } while (value <= 100);
        System.out.println("value = " + value);
    }
}
