public class M14P05_CollisionDoubleDispatch {

    static class SpaceObject {
        String collideWith(SpaceObject other) {
            return "nothing happens";
        }

        String hitByAsteroid(Asteroid a) {
            return "nothing happens";
        }

        String hitByShip(Ship s) {
            return "nothing happens";
        }

        String hitByStation(Station s) {
            return "nothing happens";
        }
    }

    static class Asteroid extends SpaceObject {
        @Override
        String collideWith(SpaceObject other) {
            // second dispatch: now the other object's class decides
            return other.hitByAsteroid(this);
        }

        @Override
        String hitByAsteroid(Asteroid a) {
            return "asteroids merge";
        }

        @Override
        String hitByShip(Ship s) {
            return "ship destroyed";
        }

        @Override
        String hitByStation(Station s) {
            return "station damaged";
        }
    }

    static class Ship extends SpaceObject {
        @Override
        String collideWith(SpaceObject other) {
            return other.hitByShip(this);
        }

        @Override
        String hitByAsteroid(Asteroid a) {
            return "ship destroyed";
        }

        @Override
        String hitByShip(Ship s) {
            return "ships bounce";
        }

        @Override
        String hitByStation(Station s) {
            return "ship docks";
        }
    }

    static class Station extends SpaceObject {
        @Override
        String collideWith(SpaceObject other) {
            return other.hitByStation(this);
        }

        @Override
        String hitByAsteroid(Asteroid a) {
            return "station damaged";
        }

        @Override
        String hitByShip(Ship s) {
            return "ship docks";
        }

        @Override
        String hitByStation(Station s) {
            return "stations ignore";
        }
    }

    static SpaceObject create(String name) {
        switch (name) {
            case "Asteroid":
                return new Asteroid();
            case "Ship":
                return new Ship();
            case "Station":
                return new Station();
            default:
                return new SpaceObject();
        }
    }

    static String collide(String input) {
        String[] names = input.split("\\+");
        SpaceObject a = create(names[0]);
        SpaceObject b = create(names[1]);
        // first dispatch: a's class decides
        return a.collideWith(b);
    }

    static boolean allPassed = true;

    static void test(String input, String actual, String expected) {
        boolean pass = actual.equals(expected);
        allPassed = allPassed && pass;
        System.out.println(input + " -> " + actual + "  " + (pass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        String[][] tests = {
            { "Asteroid+Asteroid", "asteroids merge" },
            { "Asteroid+Ship", "ship destroyed" },
            { "Ship+Asteroid", "ship destroyed" },
            { "Ship+Ship", "ships bounce" },
            { "Ship+Station", "ship docks" },
            { "Station+Ship", "ship docks" },
            { "Station+Asteroid", "station damaged" },
            { "Station+Station", "stations ignore" },
            { "Comet+Ship", "nothing happens" },
        };
        for (String[] t : tests) {
            test(t[0], collide(t[0]), t[1]);
        }
        if (!allPassed) {
            System.exit(1);
        }
    }
}
