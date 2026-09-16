# 17 - Collections, Generics and Streams

Collections are Java's ready-made data structures (the Java version of the C++ STL).
Run a lesson with `java M17L01_ArrayList.java`.

## Lessons

| File | What it shows |
|---|---|
| [M17L01_ArrayList](M17L01_ArrayList.java) | `add`, `get`, `set`, `remove`, `contains`, looping |
| [M17L02_ArrayListPitfalls](M17L02_ArrayListPitfalls.java) | `int` vs `Integer`, `remove(int)` vs `remove(Object)`, `==` on `Integer`, bad index, unboxing `null` |
| [M17L03_Generics](M17L03_Generics.java) | `Box<T>`, a generic method, a bounded type, a wildcard |
| [M17L04_LinkedList](M17L04_LinkedList.java) | `LinkedList` add/get/remove at both ends |
| [M17L05_ImmutableLists](M17L05_ImmutableLists.java) | `List.of`, `unmodifiableList`, `Arrays.asList`, `subList` views |
| [M17L06_Iterator](M17L06_Iterator.java) | `Iterator`, safe removal, `ConcurrentModificationException`, `removeIf` |
| [M17L07_HashSet](M17L07_HashSet.java) | `HashSet`, `LinkedHashSet`, set operations |
| [M17L08_TreeSet](M17L08_TreeSet.java) | Sorted set, `floor` / `ceiling` / `higher`, `headSet` / `tailSet`, a comparator |
| [M17L09_HashMap](M17L09_HashMap.java) | `put` / `get` / `getOrDefault`, iterating, counting with `merge`, `computeIfAbsent` |
| [M17L10_TreeMap](M17L10_TreeMap.java) | Sorted keys, `floorEntry`, `ceilingKey`, `headMap` / `tailMap` |
| [M17L11_LinkedHashMap](M17L11_LinkedHashMap.java) | Insertion order and access order |
| [M17L12_Stack](M17L12_Stack.java) | `ArrayDeque` as a stack: `push`, `pop`, `peek` |
| [M17L13_Queue](M17L13_Queue.java) | `ArrayDeque` and `LinkedList` as a queue: `offer`, `poll`, `peek`; deque ends |
| [M17L14_PriorityQueue](M17L14_PriorityQueue.java) | Min-heap, max-heap, top-k, a comparator for records |
| [M17L15_SortingCollections](M17L15_SortingCollections.java) | `Comparable`, `Comparator`, the `Collections` class |
| [M17L16_EqualsHashCodeInCollections](M17L16_EqualsHashCodeInCollections.java) | `equals` / `hashCode` for keys; how `HashMap` works |
| [M17L17_Optional](M17L17_Optional.java) | `Optional`: `of`, `ofNullable`, `map`, `orElse`, `ifPresent`, `orElseThrow` |
| [M17L18_StreamsBasics](M17L18_StreamsBasics.java) | `filter` / `map` / `toList`, `reduce`, `IntStream`, one-time use |
| [M17L19_Collectors](M17L19_Collectors.java) | `groupingBy`, `counting`, `partitioningBy`, `toMap`, `joining` |

Practice problems: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here (look them up when you need them): `EnumSet`/`EnumMap`, `IdentityHashMap`, `WeakHashMap`,
`BitSet`, `Spliterator`, `BlockingQueue`, concurrent collections, stream gatherers, and the legacy `Vector`/`Stack`.

## C++ STL to Java

| C++ | Java |
|---|---|
| `vector` | `ArrayList` |
| `list` | `LinkedList` |
| `deque`, `stack`, `queue` | `ArrayDeque` |
| `priority_queue` | `PriorityQueue` (a **min**-heap by default) |
| `set` / `unordered_set` | `TreeSet` / `HashSet` |
| `map` / `unordered_map` | `TreeMap` / `HashMap` |
| `lower_bound` / `upper_bound` | `ceiling` / `higher` |
| `<algorithm>` | `Collections`, `Arrays`, streams |

## Big-O

| Operation | ArrayList | LinkedList | ArrayDeque | HashSet / HashMap | TreeSet / TreeMap | PriorityQueue |
|---|---|---|---|---|---|---|
| add | O(1)* at end | O(1) at ends | O(1)* at ends | O(1) avg | O(log n) | O(log n) |
| get by index | O(1) | O(n) | - | - | - | - |
| contains / get by key | O(n) | O(n) | O(n) | O(1) avg | O(log n) | O(n) |
| remove first / poll | O(n) | O(1) | O(1) | - | O(log n) | O(log n) |

\* amortized: sometimes O(n) when the array grows.

## Key points

- Declare with the interface: `List<String> names = new ArrayList<>();`
- Collections hold objects: use `Integer`, not `int`, and compare with `equals`.
- `list.remove(1)` removes **index** 1; use `list.remove(Integer.valueOf(1))` for the value.
- Do not change a list inside a for-each loop; use `removeIf` or an `Iterator`.
- `List.of` cannot change; `Arrays.asList` has a fixed size; `subList` is a view.
- `HashSet`/`HashMap` have no order; `LinkedHash...` keeps insertion order; `Tree...` keeps sorted order.
- Hash keys need matching `equals` and `hashCode` and must not change. Records make good keys.
- Use `Integer.compare`, never `a - b`, in comparators.
- `PriorityQueue` is a min-heap; use `ArrayDeque` for stacks and queues.
- A stream runs only at its terminal operation and can be used once. Use `Optional` for return values.
