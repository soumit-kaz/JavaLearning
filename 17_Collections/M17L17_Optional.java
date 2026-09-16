import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class M17L17_Optional {

    record User(String name, String email) {
    }

    static final List<User> USERS = List.of(new User("ann", "ann@mail.com"), new User("bob", null));

    // Optional says "there may be no result" in the return type
    static Optional<User> findUser(String name) {
        for (User u : USERS) {
            if (u.name().equals(name)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<User> ann = findUser("ann");
        Optional<User> zed = findUser("zed");
        System.out.println("ann isPresent: " + ann.isPresent());
        System.out.println("zed isEmpty: " + zed.isEmpty());

        // get the value, or a fallback when there is none
        System.out.println("ann name: " + ann.map(User::name).orElse("none"));
        System.out.println("zed name: " + zed.map(User::name).orElse("none"));

        // ifPresent runs only when there is a value
        ann.ifPresent(u -> System.out.println("found: " + u.name()));

        // ofNullable turns a possible null into an empty Optional
        Optional<String> noEmail = Optional.ofNullable(USERS.get(1).email());
        System.out.println("bob email: " + noEmail.orElse("no email"));

        // map chains safely: a null result becomes empty
        for (String name : List.of("ann", "bob", "zed")) {
            String email = findUser(name).map(User::email).orElse("no email");
            System.out.println(name + ": " + email);
        }

        // orElseThrow throws when the Optional is empty
        try {
            zed.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("zed orElseThrow: NoSuchElementException");
        }

        // Optional.of(null) throws: use ofNullable when a value may be null
        try {
            Optional.of(USERS.get(1).email());
        } catch (NullPointerException e) {
            System.out.println("of(null): NullPointerException");
        }
    }
}
