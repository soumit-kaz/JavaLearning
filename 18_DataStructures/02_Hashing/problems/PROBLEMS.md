# 02 - Hashing: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Longest Consecutive Sequence (Medium)
In an unsorted array, find the length of the longest run of consecutive integers (`x, x+1, x+2, ...`) in O(n).
Example: `[100, 4, 200, 1, 3, 2]` -> `4`
Approach: hash set; count only from run starts (where `x - 1` is absent). Time O(n), space O(n).
Solution: [M1802P01_LongestConsecutive.java](P01_LongestConsecutive/M1802P01_LongestConsecutive.java)

## P02 - Subarray Sum Equals K (Medium)
Count the contiguous, non-empty subarrays whose sum equals `k`. Values can be negative.
Example: `[1, -1, 5, -2, 3]`, k = 3 -> `3`
Approach: prefix sums with a map `prefix sum -> count`, seeded with `0 -> 1`. Time O(n), space O(n).
Solution: [M1802P02_SubarraySumK.java](P02_SubarraySumK/M1802P02_SubarraySumK.java)

## P03 - Insert Delete GetRandom O(1) (Medium)
Build a set with `insert`, `remove` and `getRandom` (all values equally likely), each in average O(1).
Example: `insert(1), remove(2), insert(2), remove(1), getRandom()` -> `true, false, true, true, 2`
Approach: array list of values + hash map value -> index; remove by moving the last value into the hole. Time O(1) average, space O(n).
Solution: [M1802P03_RandomizedSet.java](P03_RandomizedSet/M1802P03_RandomizedSet.java)

## P04 - Minimum Window Substring (Hard)
Return the shortest (leftmost on ties) substring of `s` containing every character of `t`, counting duplicates, or `""`.
Example: `s = "ADOBECODEBANC"`, `t = "ABC"` -> `"BANC"`
Approach: sliding window with need/have count maps and a covered counter. Time O(|s| + |t|), space O(alphabet).
Solution: [M1802P04_MinimumWindow.java](P04_MinimumWindow/M1802P04_MinimumWindow.java)
