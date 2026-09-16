import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class M17P07_TimeMap {

    static class TimeMap {
        // key -> (time -> value), with times kept sorted
        private final Map<String, TreeMap<Integer, String>> store = new HashMap<>();

        void set(String key, String value, int time) {
            store.computeIfAbsent(key, k -> new TreeMap<>()).put(time, value);
        }

        String get(String key, int time) {
            TreeMap<Integer, String> versions = store.get(key);
            if (versions == null) {
                return "";
            }
            // floorEntry finds the latest version at or before this time
            Map.Entry<Integer, String> entry = versions.floorEntry(time);
            return entry == null ? "" : entry.getValue();
        }
    }

    // print one PASS line, or stop with a FAIL line
    static void check(String call, String got, String expected) {
        String label = call + " -> \"" + got + "\"";
        if (!got.equals(expected)) {
            System.out.println(label + " FAIL, expected \"" + expected + "\"");
            System.exit(1);
        }
        System.out.println(label + " PASS");
    }

    public static void main(String[] args) {
        TimeMap map = new TimeMap();
        map.set("foo", "bar", 1);
        map.set("foo", "bar2", 4);
        map.set("city", "paris", 5);

        check("get(foo, 1)", map.get("foo", 1), "bar");
        check("get(foo, 3)", map.get("foo", 3), "bar");
        check("get(foo, 4)", map.get("foo", 4), "bar2");
        check("get(foo, 99)", map.get("foo", 99), "bar2");
        check("get(foo, 0)", map.get("foo", 0), "");
        check("get(city, 5)", map.get("city", 5), "paris");
        check("get(zzz, 5)", map.get("zzz", 5), "");
    }
}
