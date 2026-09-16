# 17 - Collections, Generics and Streams: Problems

Run a solution from its folder, for example `java M17P01_ContainsDuplicate.java`.

## P01 - Contains Duplicate (Easy)

Return `true` if any value in an integer array appears at least twice. Use a `HashSet`.

Example: `[1,2,3,1]` -> `true`

Solution: [M17P01_ContainsDuplicate.java](P01_ContainsDuplicate/M17P01_ContainsDuplicate.java)

## P02 - Two Sum (Easy)

Return the indices of the first pair (scanning left to right) that adds up to the target,
or an empty list. Use a `HashMap` from value to index.

Example: `[2,7,11,15]`, target `9` -> `[0, 1]`

Solution: [M17P02_TwoSum.java](P02_TwoSum/M17P02_TwoSum.java)

## P03 - Top K Frequent Words (Medium)

Return the `k` most frequent words, most frequent first; equal counts go in alphabetical order.
Count with `merge`, then sort with a comparator.

Example: `[i, love, code, i, love, java]`, `k=2` -> `[i, love]`

Solution: [M17P03_TopKFrequent.java](P03_TopKFrequent/M17P03_TopKFrequent.java)

## P04 - Group Anagrams (Medium)

Group words that have the same letters. Groups are ordered by their sorted letters.
Use the sorted letters as a `TreeMap` key and `computeIfAbsent`.

Example: `[eat, tea, tan, ate, nat, bat]` -> `[[bat], [eat, tea, ate], [tan, nat]]`

Solution: [M17P04_GroupAnagrams.java](P04_GroupAnagrams/M17P04_GroupAnagrams.java)

## P05 - Merge Intervals (Medium)

Merge all overlapping intervals (touching ones too) and return them sorted by start.

Example: `[[1,3],[2,6],[8,10]]` -> `[[1,6],[8,10]]`

Solution: [M17P05_MergeIntervals.java](P05_MergeIntervals/M17P05_MergeIntervals.java)

## P06 - Longest Substring Without Repeats (Medium)

Return the length of the longest substring with no repeated character.
Use a sliding window and a `HashMap` of where each character was last seen.

Example: `"abcabcbb"` -> `3` (`"abc"`)

Solution: [M17P06_LongestUniqueSubstring.java](P06_LongestUniqueSubstring/M17P06_LongestUniqueSubstring.java)

## P07 - Time-Based Key-Value Store (Medium)

`set(key, value, time)` stores a value; `get(key, time)` returns the value at the latest time
at or before `time`, or `""`. Use a `HashMap` of `TreeMap`s and `floorEntry`.

Example: `set(foo, bar, 1)`, `set(foo, bar2, 4)`, `get(foo, 3)` -> `"bar"`

Solution: [M17P07_TimeMap.java](P07_TimeMap/M17P07_TimeMap.java)

## P08 - Sales Report (Medium, streams)

For a list of `Sale(rep, region, product, amount)`, write stream queries: total per region,
sales count per rep, top rep (`Optional`), sorted product list, and the biggest sale.

Example: `totalByRegion` -> `{east=1180, north=2700, south=1500, west=200}`

Solution: [M17P08_SalesReport.java](P08_SalesReport/M17P08_SalesReport.java)
