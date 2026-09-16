import java.util.HashSet;
import java.util.Set;

public class M1802L01_HashFunctions {

    // equal records have equal hash codes: Java generates a matching pair
    record Point(int x, int y) {
    }

    // polynomial string hash, same formula as String.hashCode(): h = 31*h + c
    static int polyHash(String s) {
        int h = 0;
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }

    // a bad hash: many different strings share a length
    static int lengthHash(String s) {
        return s.length();
    }

    // any table size: floorMod keeps the index non-negative (% can be negative)
    static int indexMod(int hash, int n) {
        return Math.floorMod(hash, n);
    }

    // power-of-two table size: keep the low bits, after mixing in the high bits
    static int indexMask(int hash, int n) {
        int spread = hash ^ (hash >>> 16);
        return spread & (n - 1);
    }

    // how many of the n buckets get at least one key
    static int bucketsUsed(String[] words, int n, boolean good) {
        Set<Integer> used = new HashSet<>();
        for (String w : words) {
            int h = good ? polyHash(w) : lengthHash(w);
            used.add(indexMask(h, n));
        }
        return used.size();
    }

    public static void main(String[] args) {
        System.out.println("polyHash(hello): " + polyHash("hello"));
        System.out.println("hello.hashCode(): " + "hello".hashCode());

        // different keys with the same hash: a collision
        System.out.println("Aa hash: " + "Aa".hashCode());
        System.out.println("BB hash: " + "BB".hashCode());

        // a hash can be negative, so % alone is not a safe index
        int negative = polyHash("banana");
        System.out.println("banana hash: " + negative);
        System.out.println("hash % 16: " + negative % 16);
        System.out.println("floorMod: " + indexMod(negative, 16));
        System.out.println("mask index: " + indexMask(negative, 16));

        // keys that differ only in high bits all land in bucket 0 without spreading
        Set<Integer> raw = new HashSet<>();
        Set<Integer> spread = new HashSet<>();
        for (int i = 0; i < 64; i++) {
            int h = i << 16;
            raw.add(h & 15);
            spread.add(indexMask(h, 16));
        }
        System.out.println("raw buckets used: " + raw.size());
        System.out.println("spread buckets used: " + spread.size());

        // a good hash spreads keys over many buckets
        String[] words = {"cat", "dog", "cow", "pig", "ant", "bee", "elk", "fox", "yak", "emu"};
        System.out.println("length hash buckets: " + bucketsUsed(words, 16, false));
        System.out.println("poly hash buckets: " + bucketsUsed(words, 16, true));

        // load factor = keys / buckets; tables grow when it gets high
        double loadFactor = (double) words.length / 16;
        System.out.println("load factor: " + loadFactor);

        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        System.out.println("equal points, equal hashes: " + (a.hashCode() == b.hashCode()));

        // self-check
        boolean ok = polyHash("hello") == "hello".hashCode();
        ok &= "Aa".hashCode() == "BB".hashCode();
        ok &= raw.size() == 1 && spread.size() == 16;
        ok &= bucketsUsed(words, 16, false) == 1 && bucketsUsed(words, 16, true) > 5;
        ok &= negative < 0 && a.equals(b) && a.hashCode() == b.hashCode();
        for (int h = -1000; h <= 1000; h++) {
            int i = indexMod(h, 7);
            int j = indexMask(h, 8);
            ok &= i >= 0 && i < 7 && j >= 0 && j < 8;
            ok &= polyHash("k" + h) == ("k" + h).hashCode();
        }
        System.out.println(ok ? "OK" : "FAIL");
    }
}
