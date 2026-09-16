public class M04L08_TernaryOperator {

    public static void main(String[] args) {
        // condition ? valueIfTrue : valueIfFalse
        int age = 20;
        String status = age >= 18 ? "adult" : "minor";
        System.out.println(status);

        // the same choice written with if-else
        String status2;
        if (age >= 18) {
            status2 = "adult";
        } else {
            status2 = "minor";
        }
        System.out.println(status2);

        // it works right inside println
        int items = 1;
        System.out.println(items + (items == 1 ? " item" : " items"));

        // the bigger of two numbers
        int a = 4;
        int b = 9;
        int max = a > b ? a : b;
        System.out.println("max = " + max);

        // both values get a common type: int and double give a double
        System.out.println(true ? 1 : 2.0);

        // nested ternaries work but are hard to read; prefer else-if
        int score = 65;
        String grade = score >= 80 ? "A" : score >= 60 ? "B" : "C";
        System.out.println(grade);
    }
}
