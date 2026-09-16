# 06 - Advanced Structures

Special-purpose structures that solve one narrow problem very well: caching, fast sorted sets,
"have I seen this before?", and range queries on a fixed array.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1806L01_LRUCache](M1806L01_LRUCache.java) | HashMap + doubly linked list with sentinels; O(1) get/put and eviction |
| 2 | [M1806L02_SkipList](M1806L02_SkipList.java) | Sorted linked list with random express lanes; search, insert, erase |
| 3 | [M1806L03_BloomFilter](M1806L03_BloomFilter.java) | Bit array + k hashes; no false negatives, measured false positive rate |
| 4 | [M1806L04_SparseTable](M1806L04_SparseTable.java) | Range minimum query in O(1) after an O(n log n) build |

Run a lesson with `java M1806L01_LRUCache.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here: ring buffer, bit set, union-find (see 05_Graphs), weighted union-find, counting Bloom filter, LFU cache (see problem P04), suffix and LCP arrays (see problem P05).

## Big-O

| Structure | Operations | Time | Space |
|-----------|------------|------|-------|
| LRU cache | get, put | O(1) average | O(capacity) |
| Skip list | search, insert, erase | O(log n) expected, O(n) worst | O(n) expected |
| Bloom filter | add, mightContain | O(k) | O(m) bits |
| Sparse table | build / min query | O(n log n) / O(1) | O(n log n) |

## Key points

- LRU: the map finds a node in O(1), the list keeps usage order; each node stores its key so eviction can clean the map.
- Sentinel head and tail nodes remove all null checks at the list ends.
- `LinkedHashMap(cap, 0.75f, true)` with `removeEldestEntry` is the JDK's ready-made LRU.
- Skip list heights come from coin flips, so use a seeded `Random` for repeatable tests; `ConcurrentSkipListMap` is the library version.
- A Bloom filter never gives false negatives; size it with `m = -n ln p / (ln 2)^2` and `k = (m / n) ln 2`. Plain Bloom filters cannot delete.
- A sparse table needs a static array and an overlap-safe operation (min, max, gcd); for sums use prefix sums.
