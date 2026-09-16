public class M04L01_If {

    public static void main(String[] args) {
        int temperature = 32;

        // the block runs only when the condition is true
        if (temperature > 30) {
            System.out.println("hot");
        }

        // a false condition skips the block
        if (temperature < 0) {
            System.out.println("freezing");
        }

        // a boolean variable can be the condition
        boolean isSunny = true;
        if (isSunny) {
            System.out.println("sunglasses");
        }

        // ! flips the condition
        boolean isRaining = false;
        if (!isRaining) {
            System.out.println("no umbrella");
        }

        // a block can hold several statements
        int stock = 0;
        if (stock == 0) {
            stock = 10;
            System.out.println("restocked: " + stock);
        }

        // code after the if always runs
        System.out.println("done");
    }
}
