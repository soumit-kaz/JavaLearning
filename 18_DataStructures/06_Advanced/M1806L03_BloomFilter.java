import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class M1806L03_BloomFilter {

    static class BloomFilter {
        private final long[] bits;
        private final int bitCount;
        private final int hashCount;

        BloomFilter(int bitCount, int hashCount) {
            if (bitCount <= 0 || hashCount <= 0) {
                throw new IllegalArgumentException("sizes must be positive");
            }
            this.bitCount = bitCount;
            this.hashCount = hashCount;
            // 64 bits per long, rounded up
            this.bits = new long[(bitCount + 63) / 64];
        }

        static BloomFilter forExpected(int items, double falsePositiveRate) {
            // sizing formulas: m = -n ln p / (ln 2)^2 and k = (m / n) ln 2
            double ln2 = Math.log(2);
            int m = (int) Math.ceil(-items * Math.log(falsePositiveRate) / (ln2 * ln2));
            int k = Math.max(1, (int) Math.round((double) m / items * ln2));
            return new BloomFilter(m, k);
        }

        private int position(String item, int i) {
            // two base hashes combined give k different hash functions
            int h1 = item.hashCode();
            int h2 = mix(h1) | 1;
            return Math.floorMod(h1 + i * h2, bitCount);
        }

        private static int mix(int x) {
            // scramble the bits so h2 does not look like h1
            x ^= x >>> 16;
            x *= 0x7feb352d;
            x ^= x >>> 15;
            x *= 0x846ca68b;
            x ^= x >>> 16;
            return x;
        }

        void add(String item) {
            for (int i = 0; i < hashCount; i++) {
                int p = position(item, i);
                bits[p / 64] |= 1L << (p % 64);
            }
        }

        boolean mightContain(String item) {
            for (int i = 0; i < hashCount; i++) {
                int p = position(item, i);
                // one clear bit proves the item was never added
                if ((bits[p / 64] & (1L << (p % 64))) == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        BloomFilter small = new BloomFilter(64, 3);
        small.add("apple");
        small.add("banana");
        System.out.println("mightContain(apple): " + small.mightContain("apple"));
        System.out.println("mightContain(cherry): " + small.mightContain("cherry"));

        boolean ok = small.mightContain("apple") && small.mightContain("banana");
        try {
            new BloomFilter(0, 3);
            ok = false;
        } catch (IllegalArgumentException e) {
            // expected
        }

        // fill a filter sized for 20000 items at 1%
        int n = 20000;
        BloomFilter filter = BloomFilter.forExpected(n, 0.01);
        System.out.println("bits: " + filter.bitCount);
        System.out.println("hashes: " + filter.hashCount);
        Random random = new Random(5);
        Set<String> added = new HashSet<>();
        while (added.size() < n) {
            added.add("user-" + random.nextInt(Integer.MAX_VALUE));
        }
        for (String s : added) {
            filter.add(s);
        }

        // no false negatives: every added item is found
        for (String s : added) {
            ok &= filter.mightContain(s);
        }

        // measure false positives on items that were never added
        int probes = 100000;
        int falsePositives = 0;
        for (int i = 0; i < probes; i++) {
            if (filter.mightContain("probe-" + i)) {
                falsePositives++;
            }
        }
        double rate = 100.0 * falsePositives / probes;
        System.out.printf("false positives: %.2f%%%n", rate);
        ok &= rate < 2.0;
        System.out.println(ok ? "OK" : "FAIL");
    }
}
