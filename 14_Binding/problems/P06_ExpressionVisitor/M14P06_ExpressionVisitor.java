public class M14P06_ExpressionVisitor {

    // one method per node type; subclasses override what they need
    static class ExprVisitor {
        void visitNum(Num n) {
        }

        void visitVar(Var v) {
        }

        void visitAdd(Add a) {
        }

        void visitMul(Mul m) {
        }
    }

    static class Expr {
        void accept(ExprVisitor visitor) {
        }
    }

    static class Num extends Expr {
        final long value;

        Num(long value) {
            this.value = value;
        }

        @Override
        void accept(ExprVisitor visitor) {
            visitor.visitNum(this);
        }
    }

    static class Var extends Expr {
        final String name;

        Var(String name) {
            this.name = name;
        }

        @Override
        void accept(ExprVisitor visitor) {
            visitor.visitVar(this);
        }
    }

    static class Add extends Expr {
        final Expr left;
        final Expr right;

        Add(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }

        @Override
        void accept(ExprVisitor visitor) {
            visitor.visitAdd(this);
        }
    }

    static class Mul extends Expr {
        final Expr left;
        final Expr right;

        Mul(Expr left, Expr right) {
            this.left = left;
            this.right = right;
        }

        @Override
        void accept(ExprVisitor visitor) {
            visitor.visitMul(this);
        }
    }

    static class Printer extends ExprVisitor {
        private String text;

        String print(Expr e) {
            e.accept(this);
            return text;
        }

        @Override
        void visitNum(Num n) {
            text = String.valueOf(n.value);
        }

        @Override
        void visitVar(Var v) {
            text = v.name;
        }

        @Override
        void visitAdd(Add a) {
            text = "(" + print(a.left) + " + " + print(a.right) + ")";
        }

        @Override
        void visitMul(Mul m) {
            text = "(" + print(m.left) + " * " + print(m.right) + ")";
        }
    }

    static class Evaluator extends ExprVisitor {
        private final String varName;
        private final long varValue;
        private long value;

        Evaluator(String varName, long varValue) {
            this.varName = varName;
            this.varValue = varValue;
        }

        long eval(Expr e) {
            e.accept(this);
            return value;
        }

        @Override
        void visitNum(Num n) {
            value = n.value;
        }

        @Override
        void visitVar(Var v) {
            if (!v.name.equals(varName)) {
                throw new IllegalStateException("unknown variable " + v.name);
            }
            value = varValue;
        }

        @Override
        void visitAdd(Add a) {
            value = eval(a.left) + eval(a.right);
        }

        @Override
        void visitMul(Mul m) {
            value = eval(m.left) * eval(m.right);
        }
    }

    // counts only numbers; the other visit methods keep walking the tree
    static class NumberCounter extends ExprVisitor {
        int count;

        @Override
        void visitNum(Num n) {
            count++;
        }

        @Override
        void visitAdd(Add a) {
            a.left.accept(this);
            a.right.accept(this);
        }

        @Override
        void visitMul(Mul m) {
            m.left.accept(this);
            m.right.accept(this);
        }
    }

    static String evaluate(Expr e, String varName, long varValue) {
        try {
            return String.valueOf(new Evaluator(varName, varValue).eval(e));
        } catch (IllegalStateException ex) {
            return "error: " + ex.getMessage();
        }
    }

    static boolean allPassed = true;

    static void test(String input, Object actual, Object expected) {
        boolean pass = String.valueOf(actual).equals(String.valueOf(expected));
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        Expr e1 = new Mul(new Add(new Var("x"), new Num(2)), new Num(3));
        Expr e2 = new Add(new Num(1), new Mul(new Num(4), new Var("y")));
        String p1 = new Printer().print(e1);
        String p2 = new Printer().print(e2);

        test("print e1", p1, "((x + 2) * 3)");
        test("print e2", p2, "(1 + (4 * y))");
        test(p1 + " with x=4", evaluate(e1, "x", 4), 18);
        test(p2 + " with y=-2", evaluate(e2, "y", -2), -7);
        test(p1 + " with only y set", evaluate(e1, "y", 1), "error: unknown variable x");

        NumberCounter counter = new NumberCounter();
        e1.accept(counter);
        e2.accept(counter);
        test("numbers in e1 and e2", counter.count, 4);
        if (!allPassed) {
            System.exit(1);
        }
    }
}
