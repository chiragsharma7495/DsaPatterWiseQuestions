package Trees;

import java.util.*;

class Node{
    int val;
    Node left;
    Node right;

    Node(int val){
        this.val = val;
    }
}

class pair{
    int level;
    Node node;

    pair(Node node , int level ){
        this.level = level;
        this.node = node;
    }
}
public class TreesPart1Practice {
    public static void main(String[] args) {
        Node a = new Node(3);
        Node b = new Node(4);
        Node c = new Node(2);
        Node d = new Node(-1);
        Node e = new Node(1);
        Node f = new Node(6);
        Node g = new Node(9);
        a.left = b; a.right = c;
        b.left = d; b.right = e;
        c.left = f; c.right = g;
        display(a);
        System.out.println(size(a));
        System.out.println();
        System.out.println(sum(a));
        System.out.println();
        System.out.println(product(a));
        System.out.println();
        System.out.println(maximumElement(a));
        System.out.println();
        System.out.println(minimumElement(a));
    }

    /*
     * EXAMPLE: size() - Counts total number of nodes in the tree
     * 
     * Tree:      3
     *           / \
     *          4   2
     *         / \
     *        -1  1
     * 
     * Execution Flow:
     * size(3) → 1 + size(4) + size(2)
     *   ├─ size(4) → 1 + size(-1) + size(1)
     *   │   ├─ size(-1) → 1 + size(null) + size(null) = 1 + 0 + 0 = 1
     *   │   └─ size(1) → 1 + size(null) + size(null) = 1 + 0 + 0 = 1
     *   │   Result: 1 + 1 + 1 = 3
     *   └─ size(2) → 1 + size(null) + size(null) = 1 + 0 + 0 = 1
     * Final: 1 + 3 + 1 = 5 nodes
     */
    private static int size(Node root) {
        return (root == null) ? 0 : 1 + size(root.left) + size(root.right);
    }

    /*
     * EXAMPLE: display() - Preorder traversal (Root → Left → Right)
     * 
     * Tree:      3
     *           / \
     *          4   2
     *         / \
     *        -1  1
     * 
     * Execution Flow:
     * display(3) → print 3
     *   ├─ display(4) → print 4
     *   │   ├─ display(-1) → print -1
     *   │   │   ├─ display(null) → return
     *   │   │   └─ display(null) → return
     *   │   └─ display(1) → print 1
     *   │       ├─ display(null) → return
     *   │       └─ display(null) → return
     *   └─ display(2) → print 2
     *       ├─ display(null) → return
     *       └─ display(null) → return
     * 
     * Output: 3 4 -1 1 2
     */
    private static void display(Node root){
        if(root == null) return;
        System.out.print(root.val + " ");
        display(root.left);
        display(root.right);
    }

    /*
     * EXAMPLE: preOrder() - Root → Left → Right
     * 
     * Tree:      1
     *           / \
     *          2   3
     *         / \
     *        4   5
     * 
     * Execution Flow:
     * Visit root first, then left subtree, then right subtree
     * 
     * preOrder(1) → print 1
     *   ├─ preOrder(2) → print 2
     *   │   ├─ preOrder(4) → print 4
     *   │   └─ preOrder(5) → print 5
     *   └─ preOrder(3) → print 3
     * 
     * Output: 1 2 4 5 3
     */
    private static void preOrder(Node root){
        if(root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    /*
     * EXAMPLE: inOrder() - Left → Root → Right
     * 
     * Tree:      1
     *           / \
     *          2   3
     *         / \
     *        4   5
     * 
     * Execution Flow:
     * Visit left subtree first, then root, then right subtree
     * 
     * inOrder(1)
     *   ├─ inOrder(2)
     *   │   ├─ inOrder(4) → print 4
     *   │   ├─ print 2
     *   │   └─ inOrder(5) → print 5
     *   ├─ print 1
     *   └─ inOrder(3) → print 3
     * 
     * Output: 4 2 5 1 3
     * (For BST, this gives sorted order!)
     */
    private static void inOrder(Node root){
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    /*
     * EXAMPLE: postOrder() - Left → Right → Root
     * 
     * Tree:      1
     *           / \
     *          2   3
     *         / \
     *        4   5
     * 
     * Execution Flow:
     * Visit left subtree, then right subtree, then root (parent last)
     * 
     * postOrder(1)
     *   ├─ postOrder(2)
     *   │   ├─ postOrder(4) → print 4
     *   │   ├─ postOrder(5) → print 5
     *   │   └─ print 2
     *   ├─ postOrder(3) → print 3
     *   └─ print 1
     * 
     * Output: 4 5 2 3 1
     * (Useful for deleting trees, bottom-up processing)
     */
    private static void postOrder(Node root){
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    /*
     * EXAMPLE: sum() - Calculates sum of all node values
     * 
     * Tree:      3
     *           / \
     *          4   2
     *         / \
     *        -1  1
     * 
     * Execution Flow:
     * sum(3) → 3 + sum(4) + sum(2)
     *   ├─ sum(4) → 4 + sum(-1) + sum(1)
     *   │   ├─ sum(-1) → -1 + sum(null) + sum(null) = -1 + 0 + 0 = -1
     *   │   └─ sum(1) → 1 + sum(null) + sum(null) = 1 + 0 + 0 = 1
     *   │   Result: 4 + (-1) + 1 = 4
     *   └─ sum(2) → 2 + sum(null) + sum(null) = 2 + 0 + 0 = 2
     * Final: 3 + 4 + 2 = 9
     */
    private static int sum(Node root){
        if(root == null) return 0;
        return root.val + sum(root.left) + sum(root.right);
    }

    /*
     * EXAMPLE: product() - Calculates product of all node values
     * 
     * Tree:      2
     *           / \
     *          3   4
     *         /
     *        5
     * 
     * Execution Flow:
     * product(2) → 2 * product(3) * product(4)
     *   ├─ product(3) → 3 * product(5) * product(null)
     *   │   ├─ product(5) → 5 * product(null) * product(null) = 5 * 1 * 1 = 5
     *   │   └─ product(null) → 1
     *   │   Result: 3 * 5 * 1 = 15
     *   └─ product(4) → 4 * product(null) * product(null) = 4 * 1 * 1 = 4
     * Final: 2 * 15 * 4 = 120
     * 
     * Note: null returns 1 (identity for multiplication)
     */
    private static int product(Node root){
        if(root == null) return 1;
        return root.val * product(root.left) * product(root.right);
    }

    /*
     * EXAMPLE: maximumElement() - Finds maximum value in tree
     * 
     * Tree:      10
     *           /  \
     *          5    15
     *         / \   / \
     *        3   7 12  20
     * 
     * Execution Flow:
     * maximumElement(10) → max(10, max(left, right))
     *   ├─ maximumElement(5) → max(5, max(left, right))
     *   │   ├─ maximumElement(3) → max(3, MIN_VALUE, MIN_VALUE) = 3
     *   │   └─ maximumElement(7) → max(7, MIN_VALUE, MIN_VALUE) = 7
     *   │   Result: max(5, 3, 7) = 7
     *   └─ maximumElement(15) → max(15, max(left, right))
     *       ├─ maximumElement(12) → max(12, MIN_VALUE, MIN_VALUE) = 12
     *       └─ maximumElement(20) → max(20, MIN_VALUE, MIN_VALUE) = 20
     *       Result: max(15, 12, 20) = 20
     * Final: max(10, 7, 20) = 20
     */
    private static int maximumElement(Node root){
        if(root == null) return Integer.MIN_VALUE;
        return Math.max(root.val , Math.max(maximumElement(root.left) , maximumElement(root.right)));
    }

    /*
     * EXAMPLE: minimumElement() - Finds minimum value in tree
     * 
     * Tree:      10
     *           /  \
     *          5    15
     *         / \     \
     *        2   7    20
     * 
     * Execution Flow:
     * minimumElement(10) → min(10, min(left, right))
     *   ├─ minimumElement(5) → min(5, min(left, right))
     *   │   ├─ minimumElement(2) → min(2, MAX_VALUE, MAX_VALUE) = 2
     *   │   └─ minimumElement(7) → min(7, MAX_VALUE, MAX_VALUE) = 7
     *   │   Result: min(5, 2, 7) = 2
     *   └─ minimumElement(15) → min(15, min(left, right))
     *       ├─ minimumElement(null) → MAX_VALUE
     *       └─ minimumElement(20) → min(20, MAX_VALUE, MAX_VALUE) = 20
     *       Result: min(15, MAX_VALUE, 20) = 15
     * Final: min(10, 2, 15) = 2
     */
    private static int minimumElement(Node root){
        if(root == null) return Integer.MAX_VALUE;
        return Math.min(root.val , Math.min(minimumElement(root.left) , minimumElement(root.right)));
    }

    /*
     * EXAMPLE: levels() - Finds height/number of levels in tree
     * 
     * Tree:      1
     *           / \
     *          2   3
     *         / \
     *        4   5
     *       /
     *      6
     * 
     * Execution Flow:
     * levels(1) → 1 + max(levels(2), levels(3))
     *   ├─ levels(2) → 1 + max(levels(4), levels(5))
     *   │   ├─ levels(4) → 1 + max(levels(6), levels(null))
     *   │   │   ├─ levels(6) → 1 + max(0, 0) = 1
     *   │   │   └─ levels(null) → 0
     *   │   │   Result: 1 + max(1, 0) = 2
     *   │   └─ levels(5) → 1 + max(0, 0) = 1
     *   │   Result: 1 + max(2, 1) = 3
     *   └─ levels(3) → 1 + max(0, 0) = 1
     * Final: 1 + max(3, 1) = 4 levels
     */
    private static int levels(Node root){
        if(root == null) return 0;
        return 1 + Math.max(levels(root.left) , levels(root.right));
    }

    /*
     * EXAMPLE: isSameTree() - Checks if two trees are identical
     * 
     * Tree p:    1          Tree q:    1
     *           / \                   / \
     *          2   3                 2   3
     * 
     * Execution Flow:
     * isSameTree(p=1, q=1)
     *   ├─ p.val == q.val? 1 == 1 ✓
     *   ├─ isSameTree(p=2, q=2)
     *   │   ├─ p.val == q.val? 2 == 2 ✓
     *   │   ├─ isSameTree(p=null, q=null) → true
     *   │   └─ isSameTree(p=null, q=null) → true
     *   │   Result: true && true = true
     *   └─ isSameTree(p=3, q=3)
     *       ├─ p.val == q.val? 3 == 3 ✓
     *       ├─ isSameTree(p=null, q=null) → true
     *       └─ isSameTree(p=null, q=null) → true
     *       Result: true && true = true
     * Final: true && true = true
     * 
     * Different trees would return false at first mismatch
     */
    private boolean isSameTree(Node p, Node q) {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val != q.val) return false;
        return isSameTree(p.left , q.left) && isSameTree(p.right , q.right);
    }

    /*
     * EXAMPLE: invertTree() - Mirrors the tree (swaps left and right children)
     * 
     * Original Tree:   4              Inverted Tree:   4
     *                 / \                             / \
     *                2   7                           7   2
     *               / \ / \                         / \ / \
     *              1  3 6  9                       9  6 3  1
     * 
     * Execution Flow:
     * invertTree(4)
     *   ├─ Swap: temp=2, left=7, right=2
     *   ├─ invertTree(7) → Swap children recursively
     *   │   ├─ invertTree(9) → leaf, no swap needed
     *   │   └─ invertTree(6) → leaf, no swap needed
     *   └─ invertTree(2) → Swap children recursively
     *       ├─ invertTree(3) → leaf, no swap needed
     *       └─ invertTree(1) → leaf, no swap needed
     * 
     * Process: Postorder-like (swap at each node, then recurse)
     * Result: All left-right children are swapped throughout the tree
     */
    private Node invertTree(Node root) {
        if(root == null) return null;
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    /*
     * EXAMPLE: hasPathSum() - Checks if any root-to-leaf path sums to target
     * 
     * Tree:      5               targetSum = 22
     *           / \
     *          4   8
     *         /   / \
     *        11  13  4
     *       / \       \
     *      7   2       1
     * 
     * Execution Flow:
     * hasPathSum(5, 22)
     *   ├─ Not a leaf, check children
     *   ├─ hasPathSum(4, 22-5=17) || hasPathSum(8, 22-5=17)
     *   │   ├─ hasPathSum(4, 17)
     *   │   │   └─ hasPathSum(11, 17-4=13)
     *   │   │       ├─ hasPathSum(7, 13-11=2)
     *   │   │       │   ├─ IS LEAF! 7 == 2? No → false
     *   │   │       └─ hasPathSum(2, 13-11=2)
     *   │   │           ├─ IS LEAF! 2 == 2? Yes → true ✓
     *   │   │       Result: false || true = true
     *   │   └─ hasPathSum(8, 17) → ... = false
     *   Result: true || false = true
     * 
     * Path found: 5 → 4 → 11 → 2 = 22
     */
    public boolean hasPathSum(Node root, int targetSum) {
        if(root == null) return false;
        if(root.left == null && root.right == null){
            if(root.val == targetSum) return true;
            else return false;
        }

        return hasPathSum(root.left , targetSum - root.val) || hasPathSum(root.right , targetSum - root.val);
    }

    /*
     * EXAMPLE: binaryTreePaths() - Returns all root-to-leaf paths as strings
     * 
     * Tree:      1
     *           / \
     *          2   3
     *           \
     *            5
     * 
     * Execution Flow:
     * binaryTreePaths(1)
     *   └─ findPaths(1, "", result)
     *       ├─ path = "1", not a leaf, add "->" → path = "1->"
     *       ├─ findPaths(2, "1->", result)
     *       │   ├─ path = "1->2", not a leaf, add "->" → path = "1->2->"
     *       │   ├─ findPaths(null, "1->2->", result) → return
     *       │   └─ findPaths(5, "1->2->", result)
     *       │       ├─ path = "1->2->5"
     *       │       ├─ IS LEAF! Add "1->2->5" to result ✓
     *       └─ findPaths(3, "1->", result)
     *           ├─ path = "1->3"
     *           ├─ IS LEAF! Add "1->3" to result ✓
     * 
     * Result: ["1->2->5", "1->3"]
     */
    public List<String> binaryTreePaths(Node root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;  // Handle null case
        findPaths(root, "", result);
        return result;
    }

    /*
     * Helper for binaryTreePaths()
     * Builds path strings recursively by appending node values
     */
    private void findPaths(Node root, String path, List<String> result) {
        if (root == null) return;

        // Add current node to path
        path += root.val;

        // If it's a leaf node, add the complete path
        if (root.left == null && root.right == null) {
            result.add(path);
        } else {
            // Not a leaf, continue exploring
            path += "->";  // Add arrow separator
            findPaths(root.left, path, result);
            findPaths(root.right, path, result);
        }
    }

    /*
     * EXAMPLE: levelOrder() - BFS traversal using Queue
     * 
     * Tree:        3
     *             / \
     *            9   20
     *               /  \
     *              15   7
     * 
     * Execution Flow (Queue operations):
     * Initial: queue = [3]
     * 
     * Step 1: remove(3), print 3
     *         Add children: queue = [9, 20]
     * 
     * Step 2: remove(9), print 9
     *         No children: queue = [20]
     * 
     * Step 3: remove(20), print 20
     *         Add children: queue = [15, 7]
     * 
     * Step 4: remove(15), print 15
     *         No children: queue = [7]
     * 
     * Step 5: remove(7), print 7
     *         No children: queue = []
     * 
     * Output: 3 9 20 15 7 (all nodes level by level, left to right)
     */
    private static void levelOrder(Node root){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node front = q.remove();
            System.out.println(front.val + " ");
            if(front.left != null) q.add(front.left);
            if(front.right != null) q.add(front.right);
        }
    }

    /*
     *      * EXAMPLE: levelOrderLineWise() \- Level order with line breaks between levels
          * Uses `pair` class to track each node and its level
          *
          * Tree:        3
          *             / \
          *            9   20
          *               /  \
          *              15   7
          *
          * Execution Flow \(Queue with levels\):
          * Initial: queue \= \[\(3,0\)\], currLevel \= 0
          *
          * Step 1: remove \(3,0\), level matches currLevel
          *         Print `3` \(new line\), add children: queue \= \[\(9,1\), \(20,1\)\]
          *
          * Step 2: remove \(9,1\), level changed \(0 \-> 1\)
          *         Print blank line, then print `9`, queue \= \[\(20,1\)\]
          *
          * Step 3: remove \(20,1\), level matches currLevel
          *         Print `20`, add children: queue \= \[\(15,2\), \(7,2\)\]
          *
          * Step 4: remove \(15,2\), level changed \(1 \-> 2\)
          *         Print blank line, then print `15`, queue \= \[\(7,2\)\]
          *
          * Step 5: remove \(7,2\), level matches currLevel
          *         Print `7`, queue \= \[\]
          *
          * Output structure:
          * Level 0: 3
          * Level 1: 9, 20
          * Level 2: 15, 7
          *
          * Note: Each node is printed with `println`, so every value appears on its own line.
     */
    private static void levelOrderLineWise(Node root){
        Queue<pair> q = new LinkedList<>();
        if(root == null) return;
        int currLevel = 0;
        q.add(new pair(root , 0));
        while(!q.isEmpty()){
            pair front = q.remove();
            if(front.level != currLevel) {
                currLevel = front.level;
                System.out.println();
            }
            System.out.println(front.node.val + " ");
            if(front.node.left != null) q.add(new pair(front.node.left , front.level+1));
            if(front.node.right != null) q.add(new pair(front.node.right , front.level+1));
        }
        System.out.println();
    }

    /*
     * EXAMPLE: levelOrderr() - Returns level order as List of Lists
     * 
     * Tree:        3
     *             / \
     *            9   20
     *               /  \
     *              15   7
     * 
     * Execution Flow:
     * Initial: queue = [3], result = []
     * 
     * LEVEL 1:
     *   levelSize = 1 (one node at this level)
     *   currentLevel = []
     *   i=0: poll(3) → currentLevel = [3]
     *        Add children: queue = [9, 20]
     *   result = [[3]]
     * 
     * LEVEL 2:
     *   levelSize = 2 (two nodes at this level)
     *   currentLevel = []
     *   i=0: poll(9) → currentLevel = [9]
     *        No children, queue = [20]
     *   i=1: poll(20) → currentLevel = [9, 20]
     *        Add children: queue = [15, 7]
     *   result = [[3], [9, 20]]
     * 
     * LEVEL 3:
     *   levelSize = 2
     *   currentLevel = []
     *   i=0: poll(15) → currentLevel = [15]
     *   i=1: poll(7) → currentLevel = [15, 7]
     *   result = [[3], [9, 20], [15, 7]]
     * 
     * Output: [[3], [9, 20], [15, 7]]
     * Key: levelSize tracks how many nodes at current level
     */
    public List<List<Integer>> levelOrderr(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at current level
            for (int i = 0; i < levelSize; i++) {
                Node node = queue.poll();
                currentLevel.add(node.val);

                // Add children to queue for next level
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }

        return result;
    }

    /*
    * Given the root of a binary tree, return its level order traversal but alternate the direction at each level.
•
Level 0: left to right
•
Level 1: right to left
•
Level 2: left to right
•
and so on
So instead of normal BFS (always left to right), you “zigzag” the order every level.
Example:
        3
       / \
      9  20
         / \
        15  7

Normal level order:
[[3], [9, 20], [15, 7]]
Zigzag level order:
[[3], [20, 9], [15, 7]]
If you want, I can now explain how your method implements this step by step on this same tree.
    * */

    public List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        Queue<Node> q = new LinkedList<>();
        boolean leftToRight = true;
        q.offer(root);

        while(!q.isEmpty()){
            List<Integer> currentLevel = new ArrayList<>();
            int levelSize = q.size();

            for(int i=0; i<levelSize; i++){
                Node node = q.poll();

                if(leftToRight){
                    currentLevel.add(node.val);
                }else{
                    currentLevel.add(0,node.val);
                }

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }

            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        return result;
    }

    private static void NthLevelPrinting(Node root , int level , int k){
        if(root == null) return;
        if(level == k) System.out.println(root.val + " ");
        NthLevelPrinting(root.left, level+1,k);
        NthLevelPrinting(root.right, level+1,k);
    }
}
