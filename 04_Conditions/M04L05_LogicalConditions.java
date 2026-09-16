public class M04L05_LogicalConditions {

    public static void main(String[] args) {
        int age = 25;
        boolean hasTicket = true;

        // && replaces a nested if: both must be true
        if (age >= 18 && hasTicket) {
            System.out.println("enter");
        }

        // 1 <= month <= 12 must be written as two comparisons
        int month = 13;
        if (month >= 1 && month <= 12) {
            System.out.println("valid month");
        } else {
            System.out.println("invalid month");
        }

        // || is true if either side is true
        char letter = 'e';
        if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
            System.out.println("vowel");
        }

        // outside a range: below the start OR above the end
        int speed = 130;
        if (speed < 40 || speed > 120) {
            System.out.println("unsafe speed");
        }

        // short-circuit keeps the division safe
        int count = 0;
        int total = 50;
        if (count != 0 && total / count > 10) {
            System.out.println("high average");
        } else {
            System.out.println("no average");
        }

        // put the literal first so a null answer cannot crash
        String answer = null;
        if ("yes".equals(answer)) {
            System.out.println("yes");
        } else {
            System.out.println("no answer");
        }
    }
}
