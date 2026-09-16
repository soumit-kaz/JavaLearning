public class M02P01_SwapVariables {

    public static void main(String[] args) {
        int a = 5;
        int b = 9;
        System.out.println("before: a = " + a + ", b = " + b);

        // keep a copy of a, because the next line overwrites it
        int temp = a;
        a = b;
        b = temp;
        System.out.println("after:  a = " + a + ", b = " + b);

        String first = "left";
        String second = "right";
        // the same three steps work for String variables
        String hold = first;
        first = second;
        second = hold;
        System.out.println("first = " + first + ", second = " + second);
    }
}
