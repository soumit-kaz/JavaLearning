public class M10L04_VariableScope {

    // a static field can be used by every method in the class
    static int count = 100;

    static void shadow() {
        // a local variable hides the static field with the same name
        int count = 5;
        System.out.println("local count = " + count);
        System.out.println("field count = " + M10L04_VariableScope.count);
    }

    // a parameter is a local variable of its method
    static int triple(int n) {
        int result = n * 3;
        return result;
    }

    public static void main(String[] args) {
        shadow();

        // result and n from triple do not exist here; only the returned value comes back
        int result = triple(4);
        System.out.println("result = " + result);

        for (int i = 0; i < 2; i++) {
            // square lives only inside this loop body
            int square = i * i;
            System.out.println("square = " + square);
        }
        // square and i are gone here

        // a block { } also limits scope
        {
            int temp = 7;
            System.out.println("temp = " + temp);
        }

        // so the same name can be used again after the block ends
        int temp = 8;
        System.out.println("temp = " + temp);
    }
}
