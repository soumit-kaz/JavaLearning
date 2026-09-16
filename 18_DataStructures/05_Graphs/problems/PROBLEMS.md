# 05 - Graphs: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Flood Fill (Easy)
Starting from pixel `(sr, sc)`, recolor it and every 4-directionally connected pixel of the same original color to `color`.
Example: `[[1,1,1],[1,1,0],[1,0,1]], (1,1), color 2` -> `[[2,2,2],[2,2,0],[2,0,1]]`
Approach: grid BFS, recolor on enqueue (return early if the color is unchanged). Time O(R*C), space O(R*C).
Solution: [M1805P01_FloodFill.java](P01_FloodFill/M1805P01_FloodFill.java)

## P02 - Number of Islands (Medium)
Count the groups of `'1'` cells that are connected up/down/left/right.
Example: `["11000","11000","00100","00011"]` -> `3`
Approach: each unvisited land cell starts an island that BFS marks visited (union-find as a cross-check). Time O(R*C), space O(R*C).
Solution: [M1805P02_NumberOfIslands.java](P02_NumberOfIslands/M1805P02_NumberOfIslands.java)

## P03 - Course Schedule II (Medium)
`[a, b]` means course `b` must come before course `a`. Return an order that takes all courses, or `[]` if that is impossible.
Example: `n=4, [[1,0],[2,0],[3,1],[3,2]]` -> `[0,1,2,3]`
Approach: Kahn's algorithm on edges `b -> a`; fewer than n courses taken means a cycle. Time O(V+E), space O(V+E).
Solution: [M1805P03_CourseScheduleII.java](P03_CourseScheduleII/M1805P03_CourseScheduleII.java)

## P04 - Network Delay Time (Medium)
A signal starts at node `k` and travels along directed, weighted edges. Return the time until every node has it, or -1 if some node never gets it.
Example: `times=[[2,1,1],[2,3,1],[3,4,1]], n=4, k=2` -> `2`
Approach: Dijkstra with a min-heap; answer is the largest distance. Time O(E log E), space O(V+E).
Solution: [M1805P04_NetworkDelayTime.java](P04_NetworkDelayTime/M1805P04_NetworkDelayTime.java)

## P05 - Min Cost to Connect All Points (Medium)
Connect all points so every point can reach every other; an edge costs the Manhattan distance. Return the minimum total cost.
Example: `[[0,0],[2,2],[3,10],[5,2],[7,0]]` -> `20`
Approach: MST of the complete graph with array-based Prim (no edge list). Time O(n^2), space O(n).
Solution: [M1805P05_MinCostConnectPoints.java](P05_MinCostConnectPoints/M1805P05_MinCostConnectPoints.java)

## P06 - Word Ladder (Hard)
Change `beginWord` into `endWord` one letter at a time, using only words from the list. Return the number of words in the shortest sequence, or 0.
Example: `hit -> cog, [hot,dot,dog,lot,log,cog]` -> `5`
Approach: level-by-level BFS, neighbors by trying all 26 letters at each position. Time O(N * L^2 * 26), space O(N * L).
Solution: [M1805P06_WordLadder.java](P06_WordLadder/M1805P06_WordLadder.java)
