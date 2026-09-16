import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class M1804P03_KClosestPoints {

    record Point(int x, int y) {
        // squared distance is enough for comparing; long avoids overflow for big coordinates
        long dist() {
            return (long) x * x + (long) y * y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }

    // order by distance, then x, then y, so answers are always the same
    static int compare(Point a, Point b) {
        if (a.dist() != b.dist()) {
            return Long.compare(a.dist(), b.dist());
        }
        if (a.x() != b.x()) {
            return Integer.compare(a.x(), b.x());
        }
        return Integer.compare(a.y(), b.y());
    }

    static List<Point> kClosest(List<Point> points, int k) {
        // a max-heap: the farthest of the chosen points is on top
        PriorityQueue<Point> heap = new PriorityQueue<>((a, b) -> compare(b, a));
        for (Point p : points) {
            heap.offer(p);
            // too many points: drop the farthest one
            if (heap.size() > k) {
                heap.poll();
            }
        }
        List<Point> result = new ArrayList<>(heap);
        result.sort((a, b) -> compare(a, b));
        return result;
    }

    // slow answer: sort all points and take the first k
    static List<Point> bruteForce(List<Point> points, int k) {
        List<Point> sorted = new ArrayList<>(points);
        sorted.sort((a, b) -> compare(a, b));
        return new ArrayList<>(sorted.subList(0, Math.min(k, sorted.size())));
    }

    static List<Point> points(int... xy) {
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < xy.length; i += 2) {
            list.add(new Point(xy[i], xy[i + 1]));
        }
        return list;
    }

    static void test(List<Point> pts, int k, List<Point> expected) {
        List<Point> got = kClosest(pts, k);
        System.out.println(pts + " k=" + k + " -> " + got + "  " + (got.equals(expected) ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        test(points(1, 3, -2, 2), 1, points(-2, 2));
        test(points(3, 3, 5, -1, -2, 4), 2, points(3, 3, -2, 4));
        test(points(0, 0), 1, points(0, 0));
        test(points(2, 2, 1, 1), 5, points(1, 1, 2, 2));
        test(points(40000, 40000, 1, 1), 1, points(1, 1));
        test(points(), 2, points());

        Random rnd = new Random(4);
        boolean match = true;
        for (int t = 0; t < 200; t++) {
            List<Point> pts = new ArrayList<>();
            int n = rnd.nextInt(30);
            for (int i = 0; i < n; i++) {
                pts.add(new Point(rnd.nextInt(21) - 10, rnd.nextInt(21) - 10));
            }
            int k = 1 + rnd.nextInt(30);
            match &= kClosest(pts, k).equals(bruteForce(pts, k));
        }
        System.out.println("random 200 cases -> " + (match ? "match" : "differ") + "  " + (match ? "PASS" : "FAIL"));
    }
}
