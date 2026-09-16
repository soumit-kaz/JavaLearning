public class M12L05_Varargs {

    static String integerOrVarargs(Integer x) { return "Integer"; }
    static String integerOrVarargs(int... xs) { return "int..."; }

    static String fixedOrVarargs(int a, int b) { return "(int, int)"; }
    static String fixedOrVarargs(int... xs) { return "int... " + xs.length; }

    static String intOrLongVarargs(int... xs) { return "int..."; }
    static String intOrLongVarargs(long... xs) { return "long..."; }

    static String count(Object... items) {
        return items == null ? "null array" : "count " + items.length;
    }

    public static void main(String[] args) {
        // phase 3: varargs is tried only when phases 1 and 2 find nothing
        System.out.println("integerOrVarargs(5) -> " + integerOrVarargs(5));
        System.out.println("integerOrVarargs() -> " + integerOrVarargs());

        // a fixed-arity method always wins over varargs
        System.out.println("fixedOrVarargs(1, 2) -> " + fixedOrVarargs(1, 2));
        System.out.println("fixedOrVarargs(1) -> " + fixedOrVarargs(1));

        // int... is more specific than long...
        System.out.println("intOrLongVarargs(1) -> " + intOrLongVarargs(1));
        System.out.println("intOrLongVarargs(1, 2L) -> " + intOrLongVarargs(1, 2L));

        // a String[] IS the varargs array; cast to Object to pass it as one item
        String[] words = {"a", "b"};
        System.out.println("(Object[]) words -> " + count((Object[]) words));
        System.out.println("(Object) words -> " + count((Object) words));

        // an int[] is not an Object[], so it is always one item
        System.out.println("int[] -> " + count(new int[] {1, 2, 3}));

        // a null array can be passed too
        System.out.println("(Object[]) null -> " + count((Object[]) null));
    }
}
