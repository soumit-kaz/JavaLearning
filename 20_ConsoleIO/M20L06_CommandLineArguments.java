public class M20L06_CommandLineArguments {

    public static void main(String[] args) {
        // args holds the words written after the program name:
        //   java M20L06_CommandLineArguments.java add 2 3
        System.out.println("number of arguments: " + args.length);

        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "] = " + args[i]);
        }

        // always check the length first, or you get an
        // ArrayIndexOutOfBoundsException when nothing was given
        if (args.length == 0) {
            System.out.println("no arguments; try: java M20L06_CommandLineArguments.java add 2 3");
            return;
        }

        // arguments are text, so numbers have to be converted
        if (args.length == 3) {
            String what = args[0];
            int a = Integer.parseInt(args[1]);
            int b = Integer.parseInt(args[2]);

            if (what.equals("add")) {
                System.out.println(a + " + " + b + " = " + (a + b));
            } else if (what.equals("mul")) {
                System.out.println(a + " * " + b + " = " + (a * b));
            } else {
                System.out.println("I do not know how to " + what);
            }
        } else {
            System.out.println("I need three arguments, like: add 2 3");
        }
    }
}
