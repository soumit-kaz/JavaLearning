public class M05L01_WhileLoop {

    public static void main(String[] args) {
        // the body repeats as long as the condition is true
        int count = 1;
        while (count <= 3) {
            System.out.println("count = " + count);
            // change the variable, or the loop never ends
            count++;
        }

        // after the loop, the condition is false
        System.out.println("after = " + count);

        // a false condition at the start means zero runs
        int n = 10;
        while (n < 5) {
            System.out.println("never printed");
        }

        // while fits when the number of steps is unknown
        int value = 100;
        int halvings = 0;
        while (value > 1) {
            value /= 2;
            halvings++;
        }
        System.out.println("halvings = " + halvings);

        // a boolean flag can stop the loop
        int step = 0;
        boolean running = true;
        while (running) {
            step++;
            if (step == 3) {
                running = false;
            }
        }
        System.out.println("steps = " + step);
    }
}
