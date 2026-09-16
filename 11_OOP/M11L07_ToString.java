public class M11L07_ToString {

    // no toString written: the default gives ClassName@hash
    static class Coin {
        int value;

        Coin(int value) {
            this.value = value;
        }
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // @Override: we replace the toString every class already has
        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }

    public static void main(String[] args) {
        // the hash part changes between runs, so print only the part before @
        String plain = new Coin(5).toString();
        System.out.println("coin = " + plain.substring(0, plain.indexOf('@')));

        Point p = new Point(1, 2);
        System.out.println("toString = " + p.toString());

        // println and + call toString automatically
        System.out.println(p);
        System.out.println("p = " + p);

        // a null reference prints as "null"
        Point missing = null;
        System.out.println("missing = " + missing);
    }
}
