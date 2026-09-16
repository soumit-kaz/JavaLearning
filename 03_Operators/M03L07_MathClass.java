public class M03L07_MathClass {

    public static void main(String[] args) {
        // bigger, smaller and distance from zero
        System.out.println("max = " + Math.max(4, 9));
        System.out.println("min = " + Math.min(4, 9));
        System.out.println("abs = " + Math.abs(-7.5));

        // pow and sqrt always give a double
        System.out.println("pow = " + Math.pow(2, 10));
        System.out.println("sqrt = " + Math.sqrt(144));
        System.out.println("sqrt(-1) = " + Math.sqrt(-1));

        // circle area with radius 2
        double radius = 2.0;
        System.out.println("area = " + Math.PI * radius * radius);

        // abs of the smallest int is still negative
        System.out.println("abs(MIN_VALUE) = " + Math.abs(Integer.MIN_VALUE));

        // compare decimals with a small tolerance instead of ==
        double sum = 0.1 + 0.2;
        System.out.println("close to 0.3: " + (Math.abs(sum - 0.3) < 1e-9));

        // round goes to the nearest whole number and returns a long
        System.out.println("round(2.5) = " + Math.round(2.5));
        System.out.println("round(-2.5) = " + Math.round(-2.5));

        // floor always goes down, ceil always goes up
        System.out.println("floor(-2.1) = " + Math.floor(-2.1));
        System.out.println("ceil(2.1) = " + Math.ceil(2.1));

        // rint rounds halves to the even neighbour
        System.out.println("rint(2.5) = " + Math.rint(2.5));

        // round to 2 decimals: multiply, round, divide
        double price = 3.14159;
        System.out.println("2 decimals = " + Math.round(price * 100) / 100.0);

        // floorDiv rounds down, floorMod follows the sign of the right number
        System.out.println("floorDiv(-7, 2) = " + Math.floorDiv(-7, 2));
        System.out.println("-7 % 3 = " + (-7 % 3));
        System.out.println("floorMod(-7, 3) = " + Math.floorMod(-7, 3));

        // clock math: 3 hours before 1 o'clock
        int hour = 1;
        System.out.println("hour = " + Math.floorMod(hour - 3, 24));

        // ceilDiv rounds up: boxes of 6 for 20 eggs
        System.out.println("boxes = " + Math.ceilDiv(20, 6));
    }
}
