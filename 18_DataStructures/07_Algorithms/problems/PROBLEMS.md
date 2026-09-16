# 07 - Algorithms: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Search Insert Position (Easy)
Given a sorted array of distinct numbers and a `target`, return the index of `target`, or the index where it would be inserted to keep the array sorted. Use O(log n) time.
Example: `[1,3,5,6], target 2` -> `1`
Approach: binary search for the lower bound (first value `>= target`) on `[0, n)`. Time O(log n), space O(1).
Solution: [M1807P01_SearchInsert.java](P01_SearchInsert/M1807P01_SearchInsert.java)

## P02 - Sort Colors (Medium)
An array holds only 0, 1 and 2. Sort it in place in one pass, without a library sort.
Example: `[2,0,2,1,1,0]` -> `[0,0,1,1,2,2]`
Approach: Dutch national flag with three pointers; after swapping a 2 to the end, do not move `mid`. Time O(n), space O(1).
Solution: [M1807P02_SortColors.java](P02_SortColors/M1807P02_SortColors.java)

## P03 - Koko Eating Bananas (Medium)
Koko eats up to `k` bananas per hour from one pile. Find the smallest `k` that lets her finish all piles within `h` hours.
Example: `piles=[3,6,7,11], h=8` -> `4`
Approach: binary search on the answer `k` in `[1, max pile]`; hours are the sum of `ceil(pile / k)`, computed in `long`. Time O(n log max), space O(1).
Solution: [M1807P03_KokoBananas.java](P03_KokoBananas/M1807P03_KokoBananas.java)

## P04 - Coin Change (Medium)
Return the fewest coins that add up to `amount`, or -1 if impossible. Each coin value can be used any number of times.
Example: `coins=[1,3,4], amount=6` -> `2` (3+3; greedy would use 3 coins)
Approach: DP `fewest[a] = 1 + min(fewest[a - coin])`, with `amount + 1` as "impossible". Time O(amount * coins), space O(amount).
Solution: [M1807P04_CoinChange.java](P04_CoinChange/M1807P04_CoinChange.java)

## P05 - Word Break (Medium)
Can `s` be split into words from a dictionary? Words may be reused.
Example: `"applepenapple", [apple, pen]` -> `true`
Approach: DP over prefixes: `canSplit[end]` if some `canSplit[start]` and `s[start, end)` is a word; only check pieces up to the longest word. Time O(n * L^2), space O(n).
Solution: [M1807P05_WordBreak.java](P05_WordBreak/M1807P05_WordBreak.java)

## P06 - Trapping Rain Water (Hard)
Given bar heights, compute how much rain water stays trapped between the bars.
Example: `[4,2,0,3,2,5]` -> `9`
Approach: two pointers moving inward; the lower side is limited only by its own running max. Time O(n), space O(1).
Solution: [M1807P06_TrappingRainWater.java](P06_TrappingRainWater/M1807P06_TrappingRainWater.java)
