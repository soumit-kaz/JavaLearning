# 02 - Hashing

A hash table turns a key into an array index (a bucket) with a hash function, so lookups do not need to
search the whole collection.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1802L01_HashFunctions](M1802L01_HashFunctions.java) | Polynomial string hash, collisions, index from a hash, bit spreading, load factor |
| 2 | [M1802L02_HashMapChaining](M1802L02_HashMapChaining.java) | Buckets of linked nodes; put / get / remove; resize at load factor 0.75 |
| 3 | [M1802L03_HashMapOpenAddressing](M1802L03_HashMapOpenAddressing.java) | One array with linear probing; tombstones on remove; rehash |
| 4 | [M1802L04_HashSet](M1802L04_HashSet.java) | A chaining hash set; union, intersection, difference; the mutated-key pitfall |

Run a lesson with `java M1802L01_HashFunctions.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

## Big-O

| Operation | Average | Worst (all keys collide) |
|-----------|---------|--------------------------|
| put / get / remove / contains | O(1) | O(n) |
| resize (rehash all) | O(n), O(1) amortized per put | O(n) |
| space | O(n + capacity) | O(n + capacity) |

## Key points

- Index = `hash & (n - 1)` for a power-of-two capacity; spread high bits first with `h ^ (h >>> 16)`.
- Chaining: each bucket is a small linked list; simple and tolerant of high load.
- Linear probing: try the next slot on collision; a removed slot must become a tombstone.
- Grow before the load factor gets high (about 0.75 for chaining, 0.5 for probing).
- Equal objects must have equal hash codes; never mutate a key after inserting it.
- `String.hashCode()` is the polynomial hash `h = 31*h + c`; collisions like "Aa"/"BB" still happen.

Not covered here: cuckoo hashing, Robin Hood hashing, rolling hashes (used in the 06_Advanced problems).
