import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class M1802L04_HashSet {

    static class Node<E> {
        final E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    // a hash set is a chaining hash map that stores only keys
    static class MyHashSet<E> {
        private Node<E>[] buckets = newTable(8);
        private int size;

        @SuppressWarnings("unchecked")
        private static <E> Node<E>[] newTable(int capacity) {
            return (Node<E>[]) new Node<?>[capacity];
        }

        private static int indexFor(Object o, int n) {
            int h = Objects.hashCode(o);
            return (h ^ (h >>> 16)) & (n - 1);
        }

        boolean contains(Object o) {
            for (Node<E> n = buckets[indexFor(o, buckets.length)]; n != null; n = n.next) {
                if (Objects.equals(n.item, o)) {
                    return true;
                }
            }
            return false;
        }

        boolean add(E item) {
            // no duplicates
            if (contains(item)) {
                return false;
            }
            int i = indexFor(item, buckets.length);
            buckets[i] = new Node<>(item, buckets[i]);
            size++;
            if (size > buckets.length * 3 / 4) {
                grow();
            }
            return true;
        }

        boolean remove(Object o) {
            int i = indexFor(o, buckets.length);
            Node<E> prev = null;
            for (Node<E> n = buckets[i]; n != null; prev = n, n = n.next) {
                if (Objects.equals(n.item, o)) {
                    if (prev == null) {
                        buckets[i] = n.next;
                    } else {
                        prev.next = n.next;
                    }
                    size--;
                    return true;
                }
            }
            return false;
        }

        int size() {
            return size;
        }

        // every item, bucket by bucket (no useful order)
        List<E> items() {
            List<E> out = new ArrayList<>();
            for (Node<E> head : buckets) {
                for (Node<E> n = head; n != null; n = n.next) {
                    out.add(n.item);
                }
            }
            return out;
        }

        // double the table and re-add everything
        private void grow() {
            Node<E>[] old = buckets;
            buckets = newTable(old.length * 2);
            size = 0;
            for (Node<E> head : old) {
                for (Node<E> n = head; n != null; n = n.next) {
                    add(n.item);
                }
            }
        }
    }

    // items in a or b
    static <E> MyHashSet<E> union(MyHashSet<E> a, MyHashSet<E> b) {
        MyHashSet<E> out = new MyHashSet<>();
        for (E e : a.items()) {
            out.add(e);
        }
        for (E e : b.items()) {
            out.add(e);
        }
        return out;
    }

    // items in both a and b
    static <E> MyHashSet<E> intersection(MyHashSet<E> a, MyHashSet<E> b) {
        MyHashSet<E> out = new MyHashSet<>();
        for (E e : a.items()) {
            if (b.contains(e)) {
                out.add(e);
            }
        }
        return out;
    }

    // items in a but not in b
    static <E> MyHashSet<E> difference(MyHashSet<E> a, MyHashSet<E> b) {
        MyHashSet<E> out = new MyHashSet<>();
        for (E e : a.items()) {
            if (!b.contains(e)) {
                out.add(e);
            }
        }
        return out;
    }

    static MyHashSet<Integer> setOf(int... values) {
        MyHashSet<Integer> set = new MyHashSet<>();
        for (int v : values) {
            set.add(v);
        }
        return set;
    }

    public static void main(String[] args) {
        MyHashSet<String> set = new MyHashSet<>();
        System.out.println("add a: " + set.add("a"));
        System.out.println("add a again: " + set.add("a"));
        set.add("b");
        System.out.println("remove b: " + set.remove("b"));
        System.out.println("size: " + set.size());

        MyHashSet<Integer> a = setOf(1, 2, 3, 4);
        MyHashSet<Integer> b = setOf(3, 4, 5);
        System.out.println("union: " + union(a, b).items());
        System.out.println("intersection: " + intersection(a, b).items());
        System.out.println("difference: " + difference(a, b).items());

        // mutating a key after adding it strands it in the wrong bucket
        MyHashSet<List<Integer>> lists = new MyHashSet<>();
        List<Integer> key = new ArrayList<>(List.of(1, 2));
        lists.add(key);
        key.add(3);
        boolean found = lists.contains(key);
        System.out.println("mutated key found: " + found);

        // self-check: set ops and random operations against java.util.HashSet
        boolean ok = !found && union(a, b).size() == 5;
        ok &= new HashSet<>(intersection(a, b).items()).equals(Set.of(3, 4));
        ok &= new HashSet<>(difference(a, b).items()).equals(Set.of(1, 2));
        Random rnd = new Random(5);
        MyHashSet<Integer> mine = new MyHashSet<>();
        Set<Integer> jdk = new HashSet<>();
        for (int step = 0; step < 100_000; step++) {
            int k = rnd.nextInt(1000);
            switch (rnd.nextInt(3)) {
                case 0 -> ok &= mine.add(k) == jdk.add(k);
                case 1 -> ok &= mine.remove(k) == jdk.remove(k);
                default -> ok &= mine.contains(k) == jdk.contains(k);
            }
        }
        ok &= mine.size() == jdk.size() && new HashSet<>(mine.items()).equals(jdk);
        System.out.println(ok ? "OK" : "FAIL");
    }
}
