public class M04L03_ElseIfLadder {

    public static void main(String[] args) {
        int score = 85;

        // checked top to bottom, the first true one wins
        if (score >= 90) {
            System.out.println("A");
        } else if (score >= 80) {
            System.out.println("B");
        } else if (score >= 70) {
            System.out.println("C");
        } else {
            System.out.println("F");
        }

        // without a final else, nothing may run
        int temperature = 20;
        if (temperature > 30) {
            System.out.println("hot");
        } else if (temperature < 10) {
            System.out.println("cold");
        }

        // wrong order: 85 >= 70 is already true, so B is never reached
        if (score >= 70) {
            System.out.println("C");
        } else if (score >= 80) {
            System.out.println("B");
        }

        // sign of a number
        int n = -3;
        if (n > 0) {
            System.out.println("positive");
        } else if (n < 0) {
            System.out.println("negative");
        } else {
            System.out.println("zero");
        }
    }
}
