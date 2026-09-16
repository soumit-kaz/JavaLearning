# 03 - Trees: Problems

Each solution runs its own tests and prints one PASS or FAIL line per test.

## P01 - Symmetric Tree (Easy)
Return true if a binary tree is a mirror image of itself around its center.
Example: `[1,2,2,3,4,4,3]` -> `true`
Approach: compare (left, right) pairs crosswise, recursively or with a queue of pairs. Time O(n), space O(h).
Solution: [M1803P01_SymmetricTree.java](P01_SymmetricTree/M1803P01_SymmetricTree.java)

## P02 - Sorted Array to BST (Easy)
Turn a strictly increasing array into a height-balanced binary search tree.
Example: `[-10,-3,0,5,9]` -> `[0,-10,5,null,-3,null,9]`
Approach: the middle element is the root; build both halves recursively. Time O(n), space O(log n).
Solution: [M1803P02_SortedArrayToBST.java](P02_SortedArrayToBST/M1803P02_SortedArrayToBST.java)

## P03 - Kth Smallest in BST (Medium)
Return the k-th smallest value (1-based) stored in a BST.
Example: `[5,3,6,2,4,null,null,1]`, k=3 -> `3`
Approach: iterative inorder with a stack, stop at the k-th node (or subtree sizes for O(h) queries). Time O(h + k), space O(h).
Solution: [M1803P03_KthSmallestBST.java](P03_KthSmallestBST/M1803P03_KthSmallestBST.java)

## P04 - Build Tree from Preorder + Inorder (Medium)
Rebuild a binary tree with unique values from its preorder and inorder traversals.
Example: pre `[3,9,20,15,7]`, in `[9,3,15,20,7]` -> `[3,9,20,null,null,15,7]`
Approach: next preorder value is the root; a HashMap of inorder positions splits left and right. Time O(n), space O(n).
Solution: [M1803P04_BuildFromPreIn.java](P04_BuildFromPreIn/M1803P04_BuildFromPreIn.java)

## P05 - All Nodes Distance K (Medium)
Return the values of all nodes exactly k edges away from a target node.
Example: `[3,5,1,6,2,0,8,null,null,7,4]`, target 5, k 2 -> `[1,4,7]`
Approach: map each node to its parent, then BFS k levels from the target with a visited set. Time O(n), space O(n).
Solution: [M1803P05_DistanceK.java](P05_DistanceK/M1803P05_DistanceK.java)

## P06 - Maximum Path Sum (Hard)
Return the largest sum of any path (at least one node, need not pass the root).
Example: `[-10,9,20,null,null,15,7]` -> `42`
Approach: post-order gain = val + max(0, best child gain); a path turning at a node uses both sides. Time O(n), space O(h).
Solution: [M1803P06_MaxPathSum.java](P06_MaxPathSum/M1803P06_MaxPathSum.java)
