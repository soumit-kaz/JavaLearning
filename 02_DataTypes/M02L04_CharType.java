public class M02L04_CharType {

    public static void main(String[] args) {
        // a char holds one character in single quotes
        char letter = 'J';
        char digit = '7';
        System.out.println(letter + " " + digit);

        // escape sequences also work for chars
        char tab = '\t';
        char quote = '\'';
        System.out.println("a" + tab + "b " + quote);

        // charAt gives the char at a position; the first is 0
        String word = "Java";
        char first = word.charAt(0);
        System.out.println("first = " + first);

        // every char is really a number from 0 to 65535
        System.out.println((int) letter);
        char fromNumber = 74;
        System.out.println(fromNumber);

        // char + int gives an int; cast back to see the letter
        System.out.println('A' + 1);
        System.out.println((char) ('A' + 1));

        // two chars added give a number, not text
        System.out.println('a' + 'b');
        System.out.println("" + 'a' + 'b');

        // turn a digit char into its value, and back
        int value = digit - '0';
        char back = (char) ('0' + 5);
        System.out.println(value + " " + back);

        // lower case is 32 after upper case
        System.out.println('a' - 'A');
        char upper = (char) ('g' - 32);
        System.out.println(upper);
    }
}
