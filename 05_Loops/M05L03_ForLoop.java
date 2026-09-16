public class M05L03_ForLoop {

    public static void main(String[] args) {
        // for (start; condition; update) keeps the counter in one line
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // counting down
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        // steps of 2
        for (int i = 0; i <= 10; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // a char can be the counter
        for (char c = 'a'; c <= 'e'; c++) {
            System.out.print(c);
        }
        System.out.println();

        // two variables can move together
        for (int left = 0, right = 4; left < right; left++, right--) {
            System.out.print(left + "-" + right + " ");
        }
        System.out.println();

        // i exists only inside its loop; declare it before to use it after
        int k;
        for (k = 0; k < 3; k++) {
            System.out.print(k + " ");
        }
        System.out.println();
        System.out.println("k after = " + k);

        // string positions go from 0 to length() - 1
        String word = "Banana";
        for (int i = 0; i < word.length(); i++) {
            System.out.print(word.charAt(i) + " ");
        }
        System.out.println();

        // walk backwards to reverse the text
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        System.out.println("reversed = " + reversed);
    }
}
