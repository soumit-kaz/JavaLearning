import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class M17L16_EqualsHashCodeInCollections {

    // without equals and hashCode, two equal-looking objects are different keys
    static class PlainPoint {
        int x;
        int y;

        PlainPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class MutablePoint {
        int x;
        int y;

        MutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // equal objects must have equal hash codes
        @Override
        public boolean equals(Object o) {
            return o instanceof MutablePoint p && p.x == x && p.y == y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // a record gets equals and hashCode for free, and cannot change
    record Point(int x, int y) {
    }

    public static void main(String[] args) {
        // how HashMap works: an array of buckets
        // hashCode picks the bucket, then equals finds the key inside it
        // so a lookup is O(1) on average

        Set<PlainPoint> plain = new HashSet<>();
        plain.add(new PlainPoint(1, 1));
        System.out.println("plain contains: " + plain.contains(new PlainPoint(1, 1)));

        Set<MutablePoint> points = new HashSet<>();
        MutablePoint p = new MutablePoint(1, 1);
        points.add(p);
        System.out.println("fixed contains: " + points.contains(new MutablePoint(1, 1)));

        // changing a key after adding it leaves it in the wrong bucket
        p.x = 99;
        System.out.println("after change, contains p: " + points.contains(p));

        // records are safe keys
        Set<Point> safe = new HashSet<>();
        safe.add(new Point(1, 1));
        safe.add(new Point(1, 1));
        System.out.println("record set size: " + safe.size());

        // different strings can share a hash code (a collision)
        System.out.println("Aa hash: " + "Aa".hashCode());
        System.out.println("BB hash: " + "BB".hashCode());

        // equals tells colliding keys apart
        Map<String, Integer> map = new HashMap<>();
        map.put("Aa", 1);
        map.put("BB", 2);
        System.out.println("get Aa: " + map.get("Aa"));
        System.out.println("get BB: " + map.get("BB"));

        // the bucket array doubles when it is 75% full (the load factor)
        Map<Point, String> grid = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            grid.put(new Point(i, i), "cell" + i);
        }
        System.out.println("grid size: " + grid.size());
        System.out.println("get (7,7): " + grid.get(new Point(7, 7)));
    }
}
