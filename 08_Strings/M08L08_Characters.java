public class M08L08_Characters {

    public static void main(String[] args) {
        // Character methods test what kind of character something is
        for (char c : "aZ5 _".toCharArray()) {
            System.out.println("[" + c + "] letter=" + Character.isLetter(c)
                    + " digit=" + Character.isDigit(c)
                    + " space=" + Character.isWhitespace(c)
                    + " upper=" + Character.isUpperCase(c));
        }

        // change the case of one character
        System.out.println(Character.toUpperCase('q') + " " + Character.toLowerCase('Q'));

        // a char is stored as a number (its character code)
        System.out.println("code of A: " + (int) 'A');
        System.out.println("char 66: " + (char) 66);

        // char + int gives an int; cast back to get a letter
        System.out.println("'a' + 2 = " + ('a' + 2));
        System.out.println("(char) ('a' + 2) = " + (char) ('a' + 2));

        // c - 'a' is the position in the alphabet; c - '0' is a digit's value
        System.out.println("'c' - 'a' = " + ('c' - 'a'));
        System.out.println("'7' - '0' = " + ('7' - '0'));

        // Caesar shift: move each letter 3 places, wrapping after z
        StringBuilder secret = new StringBuilder();
        for (char c : "xyz abc".toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                secret.append((char) ('a' + (c - 'a' + 3) % 26));
            } else {
                secret.append(c);
            }
        }
        System.out.println("caesar: " + secret);

        // count letters with an int[26] array indexed by c - 'a'
        int[] counts = new int[26];
        for (char c : "banana".toCharArray()) {
            counts[c - 'a']++;
        }
        System.out.println("a=" + counts[0] + " b=" + counts[1] + " n=" + counts['n' - 'a']);

        // toCharArray gives a COPY; new String turns it back into text
        String word = "hello";
        char[] letters = word.toCharArray();
        letters[0] = 'J';
        System.out.println(new String(letters) + " " + word);

        // an emoji needs two chars, so length() is not the number of symbols
        String smile = "hi" + Character.toString(0x1F600);
        System.out.println("length: " + smile.length());
        System.out.println("code points: " + smile.codePointCount(0, smile.length()));
    }
}
