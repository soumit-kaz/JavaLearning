public class M04L04_NestedIf {

    public static void main(String[] args) {
        // an inner if is checked only when the outer one is true
        int age = 20;
        boolean hasTicket = false;
        if (age >= 18) {
            if (hasTicket) {
                System.out.println("enter");
            } else {
                System.out.println("buy a ticket");
            }
        } else {
            System.out.println("too young");
        }

        // largest of three with nested ifs
        int a = 4;
        int b = 9;
        int c = 6;
        if (a > b) {
            if (a > c) {
                System.out.println("max = " + a);
            } else {
                System.out.println("max = " + c);
            }
        } else if (b > c) {
            System.out.println("max = " + b);
        } else {
            System.out.println("max = " + c);
        }
    }
}
