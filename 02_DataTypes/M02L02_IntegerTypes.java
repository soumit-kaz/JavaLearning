public class M02L02_IntegerTypes {

    public static void main(String[] args) {
        // four whole-number types, from small to big
        byte small = 100;
        short medium = 30000;
        int number = 2000000000;
        long big = 9000000000000000000L;
        System.out.println(small + " " + medium + " " + number + " " + big);

        // each type has limits
        System.out.println("byte:  " + Byte.MIN_VALUE + " to " + Byte.MAX_VALUE);
        System.out.println("short: " + Short.MIN_VALUE + " to " + Short.MAX_VALUE);
        System.out.println("int:   " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE);
        System.out.println("long:  " + Long.MIN_VALUE + " to " + Long.MAX_VALUE);

        // a whole-number literal is an int; bigger values need the L suffix
        long population = 8_000_000_000L;
        System.out.println(population);

        // underscores only make numbers easier to read
        int million = 1_000_000;
        System.out.println(million);

        // the same number 42 in decimal, binary, octal and hex
        System.out.println(42 + " " + 0b101010 + " " + 052 + " " + 0x2A);

        // a leading 0 means octal, so 010 is 8
        System.out.println("010 = " + 010);

        // byte + byte gives an int
        byte x = 10;
        byte y = 20;
        int sum = x + y;
        System.out.println(sum);

        // int + long gives a long
        System.out.println(number + big / 1000);

        // a constant that fits needs no cast, even from two final values
        final byte first = 10;
        final byte second = 20;
        byte total = first + second;
        System.out.println(total);
    }
}
