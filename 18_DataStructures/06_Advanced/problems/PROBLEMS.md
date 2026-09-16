# 06 - Advanced Structures: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Number of Provinces (Medium)
`isConnected[i][j] = 1` means city i and city j are connected. Count the groups of connected cities.
Example: `[[1,1,0],[1,1,0],[0,0,1]]` -> `2`
Approach: union-find; each union of two different sets lowers the count by one. Time O(n^2 * alpha(n)), space O(n).
Solution: [M1806P01_NumberOfProvinces.java](P01_NumberOfProvinces/M1806P01_NumberOfProvinces.java)

## P02 - Redundant Connection (Medium)
A tree on nodes 1..n got one extra edge. Return the edge to remove to get a tree back; if several work, return the last one in the input.
Example: `[[1,2],[1,3],[2,3]]` -> `[2,3]`
Approach: add edges to a union-find in order; the first edge whose ends already share a root is the answer. Time O(n * alpha(n)), space O(n).
Solution: [M1806P02_RedundantConnection.java](P02_RedundantConnection/M1806P02_RedundantConnection.java)

## P03 - LRU Cache (Medium)
Build a cache with `get` and `put` in O(1). When it is full, evict the least recently used key.
Example: `cap=2: put(1,1) put(2,2) get(1)=1 put(3,3) get(2)=-1`
Approach: HashMap to nodes plus a doubly linked list with sentinels; evict `tail.prev`. Time O(1) average per operation, space O(capacity).
Solution: [M1806P03_LRUCache.java](P03_LRUCache/M1806P03_LRUCache.java)

## P04 - LFU Cache (Hard)
Build an O(1) cache that evicts the least frequently used key; ties are broken by least recently used.
Example: `cap=2: put1 put2 get1 put3 (evicts 2) get3 put4 (evicts 1)`
Approach: maps for value and count, plus count -> LinkedHashSet of keys and a `minCount` reset to 1 on insert. Time O(1) average per operation, space O(capacity).
Solution: [M1806P04_LFUCache.java](P04_LFUCache/M1806P04_LFUCache.java)

## P05 - Longest Duplicate Substring (Hard)
Return the longest substring that appears at least twice. The copies may overlap.
Example: `"banana"` -> `"ana"`
Approach: suffix array by prefix doubling, then Kasai's LCP array; the largest LCP value gives the answer. Time O(n log n), space O(n).
Solution: [M1806P05_LongestDuplicateSubstring.java](P05_LongestDuplicateSubstring/M1806P05_LongestDuplicateSubstring.java)
