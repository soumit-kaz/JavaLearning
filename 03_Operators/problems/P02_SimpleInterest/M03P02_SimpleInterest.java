public class M03P02_SimpleInterest {

    public static void main(String[] args) {
        double principal = 5000.0;
        double ratePercent = 4.5;
        int years = 3;

        // simple interest = principal * rate * time / 100
        double interest = principal * ratePercent * years / 100;

        // the final amount adds the interest to the principal
        double amount = principal + interest;

        System.out.printf("interest = %.2f%n", interest);
        System.out.printf("amount = %.2f%n", amount);
    }
}
