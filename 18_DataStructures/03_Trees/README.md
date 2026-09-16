# 03 - Trees

A tree is a set of nodes joined by parent-child links, with exactly one path from the root to each node.
These lessons cover binary tree basics, search trees, tries and range-query trees.

## Lessons

| # | Lesson | What it shows |
|---|--------|---------------|
| 1 | [M1803L01_BinaryTree](M1803L01_BinaryTree.java) | Nodes, building from level order, size, height, diameter, lowest common ancestor |
| 2 | [M1803L02_TreeTraversals](M1803L02_TreeTraversals.java) | Pre/in/post order recursively and with a stack, level order with a queue |
| 3 | [M1803L03_BinarySearchTree](M1803L03_BinarySearchTree.java) | BST insert, search, min/max, delete (including two children), sorted inorder |
| 4 | [M1803L04_AVLTree](M1803L04_AVLTree.java) | Rotations, insert with rebalancing, AVL height check |
| 5 | [M1803L05_Trie](M1803L05_Trie.java) | Trie insert, search, startsWith, words with a prefix |
| 6 | [M1803L06_SegmentTree](M1803L06_SegmentTree.java) | Range sum query and point update in O(log n) |
| 7 | [M1803L07_FenwickTree](M1803L07_FenwickTree.java) | Prefix and range sums with the lowest-set-bit trick |

Run a lesson with `java M1803L01_BinaryTree.java`. Practice: [problems/PROBLEMS.md](problems/PROBLEMS.md)

Not covered here: Morris traversal, tree views, serialization, n-ary trees, lazy propagation, red-black trees, B-trees.

## Big-O

n = number of nodes (or array size), h = height, L = word length.

| Operation | Time | Space |
|-----------|------|-------|
| Size, height, diameter, LCA, traversals | O(n) | O(h) |
| Level order | O(n) | O(width) |
| BST insert / search / delete | O(h), O(n) worst case | O(n) |
| AVL insert / search | O(log n) | O(n) |
| Trie insert / search / startsWith | O(L) | O(total characters) |
| Segment tree build | O(n) | O(n) |
| Segment tree query / update | O(log n) | O(n) |
| Fenwick update / prefix sum | O(log n) | O(n) |

## Key points

- Preorder = node, left, right; inorder = left, node, right; postorder = left, right, node.
- Deep, skewed trees overflow recursion; use an explicit stack.
- Inorder of a BST is sorted. Sorted inserts turn a plain BST into a chain.
- BST delete with two children: copy the successor's key, then delete the successor.
- AVL trees rotate to keep heights within 1, so height stays O(log n).
- A trie lookup costs the word length, not the number of words.
- Segment and Fenwick trees answer range sums in O(log n); use `long` for sums.
