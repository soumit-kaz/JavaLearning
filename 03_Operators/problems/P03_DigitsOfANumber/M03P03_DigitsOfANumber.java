public class M03P03_DigitsOfANumber {

    public static void main(String[] args) {
        int number = 482;

        // the last digit is the remainder after dividing by 10
        int ones = number % 10;

        // drop the last digit with / 10, then take the new last digit
        int tens = (number / 10) % 10;

        // two digits dropped leaves the first digit
        int hundreds = number / 100;

        System.out.println("hundreds = " + hundreds);
        System.out.println("tens = " + tens);
        System.out.println("ones = " + ones);

        // sum of the digits
        System.out.println("digit sum = " + (hundreds + tens + ones));

        // build the reversed number from the digits
        int reversed = ones * 100 + tens * 10 + hundreds;
        System.out.println("reversed = " + reversed);
    }
}
