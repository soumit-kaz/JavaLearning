import java.util.function.Predicate;

public class M16P04_ValidatorChain {

    // a validator returns "" when the value is fine, otherwise the errors
    @FunctionalInterface
    interface Validator<T> {
        String validate(T value);

        default boolean isValid(T value) {
            return validate(value).isEmpty();
        }

        // run both and keep all errors
        default Validator<T> and(Validator<T> other) {
            return value -> join(validate(value), other.validate(value));
        }

        // run the second only if the first passed
        default Validator<T> andThen(Validator<T> other) {
            return value -> {
                String first = validate(value);
                return first.isEmpty() ? other.validate(value) : first;
            };
        }

        // pass if either one passes
        default Validator<T> or(Validator<T> other) {
            return value -> {
                String first = validate(value);
                String second = other.validate(value);
                if (first.isEmpty() || second.isEmpty()) {
                    return "";
                }
                return "(" + first + ") or (" + second + ")";
            };
        }

        static <T> Validator<T> rule(Predicate<T> test, String message) {
            return value -> test.test(value) ? "" : message;
        }

        static <T> Validator<T> required() {
            return value -> value == null ? "required" : "";
        }
    }

    static String join(String a, String b) {
        if (a.isEmpty()) {
            return b;
        }
        if (b.isEmpty()) {
            return a;
        }
        return a + "; " + b;
    }

    // adds the field name in front of an error
    static String field(String name, String error) {
        return error.isEmpty() ? "" : name + ": " + error;
    }

    record User(String name, String email, Integer age, String phone) {
    }

    // the variable type tells Java what T is in each helper call
    static final Validator<String> TEXT_REQUIRED = Validator.required();
    static final Validator<String> NOT_BLANK = Validator.rule(s -> !s.isBlank(), "blank");
    static final Validator<String> SHORT = Validator.rule(s -> s.length() <= 10, "too long");
    static final Validator<String> CAPITAL = Validator.rule(s -> Character.isUpperCase(s.charAt(0)), "not capitalized");
    static final Validator<String> EMAIL_SHAPE = Validator.rule(
            s -> s.contains("@") && s.indexOf('.', s.indexOf('@')) > 0, "bad email");
    static final Validator<String> EMPTY = Validator.rule(p -> p == null, "not empty");
    static final Validator<String> ELEVEN = Validator.rule(p -> p != null && p.length() == 11, "not 11 digits");

    static final Validator<Integer> AGE_REQUIRED = Validator.required();
    static final Validator<Integer> ADULT = Validator.rule(a -> a >= 18, "too young");
    static final Validator<Integer> ALIVE = Validator.rule(a -> a <= 130, "too old");

    // andThen puts required() first, so later rules never see null
    static final Validator<String> NAME = TEXT_REQUIRED.andThen(NOT_BLANK).andThen(SHORT.and(CAPITAL));
    static final Validator<String> EMAIL = TEXT_REQUIRED.andThen(EMAIL_SHAPE);
    static final Validator<Integer> AGE = AGE_REQUIRED.andThen(ADULT.and(ALIVE));
    static final Validator<String> PHONE = EMPTY.or(ELEVEN);

    static String validateUser(User user) {
        if (user == null) {
            return "required";
        }
        return join(join(field("name", NAME.validate(user.name())), field("email", EMAIL.validate(user.email()))),
                join(field("age", AGE.validate(user.age())), field("phone", PHONE.validate(user.phone()))));
    }

    static int failures = 0;

    static void check(String label, Object actual, Object expected) {
        boolean ok = String.valueOf(actual).equals(String.valueOf(expected));
        if (!ok) {
            failures++;
        }
        System.out.println(label + " -> " + actual + "  " + (ok ? "PASS" : "FAIL expected " + expected));
    }

    public static void main(String[] args) {
        User good = new User("Rina", "rina@mail.com", 30, null);
        User bad = new User("rinaaaaaaaaaaa", "rina@mail", 12, "123");
        User blank = new User("  ", null, null, "01711000000");

        check("good user", "[" + validateUser(good) + "]", "[]");
        check("bad user", validateUser(bad),
                "name: too long; not capitalized; email: bad email; age: too young; phone: (not empty) or (not 11 digits)");
        check("blank user", validateUser(blank), "name: blank; email: required; age: required");
        check("null user", validateUser(null), "required");
        check("age 200", AGE.validate(200), "too old");
        check("AGE.isValid(40)", AGE.isValid(40), true);
        check("name null (andThen stops early)", NAME.validate(null), "required");

        if (failures > 0) {
            System.exit(1);
        }
    }
}
