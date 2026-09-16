# 05 - Graphs

A graph is a set of vertices joined by edges, which can be directed or undirected and weighted or not.
These lessons build the core graph algorithms from scratch.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1805L01_GraphRepresentation](M1805L01_GraphRepresentation.java) | Adjacency matrix, adjacency list, edge list; directed vs undirected, weighted |
| 2 | [M1805L02_BreadthFirstSearch](M1805L02_BreadthFirstSearch.java) | BFS visit order, hop distances, fewest steps in a grid |
| 3 | [M1805L03_DepthFirstSearch](M1805L03_DepthFirstSearch.java) | Recursive and iterative DFS, connected components |
| 4 | [M1805L04_TopologicalSort](M1805L04_TopologicalSort.java) | Kahn's algorithm and DFS finish order, both detecting cycles |
| 5 | [M1805L05_Dijkstra](M1805L05_Dijkstra.java) | Shortest paths with a heap for non-negative weights, rebuilding the path |
| 6 | [M1805L06_BellmanFord](M1805L06_BellmanFord.java) | Shortest paths with negative weights, negative-cycle check |
| 7 | [M1805L07_UnionFind](M1805L07_UnionFind.java) | Path compression, union by rank, set sizes, cycle detection |
| 8 | [M1805L08_KruskalMST](M1805L08_KruskalMST.java) | Minimum spanning tree: cheapest edges first, union-find skips cycles |
| 9 | [M1805L09_PrimMST](M1805L09_PrimMST.java) | Minimum spanning tree grown from one vertex with a heap |

Run a lesson with `java M1805L01_GraphRepresentation.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here: 0-1 BFS, Floyd-Warshall, bipartite check, SCC (Kosaraju/Tarjan), bridges and articulation points.

## Big-O

V = vertices, E = edges.

| Algorithm / structure | Time | Extra space |
|-----------------------|------|-------------|
| Adjacency matrix | edge check O(1), neighbors O(V) | O(V^2) |
| Adjacency list | edge check O(degree) | O(V + E) |
| BFS / DFS / components | O(V + E) | O(V) |
| Topological sort | O(V + E) | O(V) |
| Dijkstra (binary heap) | O((V + E) log V) | O(V + E) |
| Bellman-Ford | O(V * E) | O(V) |
| Kruskal | O(E log E) | O(V + E) |
| Prim (heap) | O(E log E) | O(V + E) |
| Union-find | about O(1) per operation | O(V) |

## Key points

- Use an adjacency list for sparse graphs, a matrix for dense ones, an edge list for Kruskal and Bellman-Ford.
- In BFS, mark a vertex visited when you add it to the queue, not when you remove it.
- Recursive DFS can overflow the call stack on long paths; use an explicit stack.
- A directed cycle is an edge back to a vertex still on the current DFS path (gray), not just any visited vertex.
- Dijkstra needs non-negative weights; Bellman-Ford handles negative ones and detects negative cycles.
- Kruskal adds cheapest edges first; Prim grows one tree outward. Both give the same MST weight.
