package Trees;

import java.util.ArrayList;
import java.util.Collections;

// Pair class to store max and min values
class Pair {
    int max;
    int min;

    Pair(int max, int min) {
        this.max = max;
        this.min = min;
    }
}

class quad{
    int min;
    int max;
    int size;
    boolean isBST;

    quad(int min , int max , int size , boolean isBST){
        this.max = max;
        this.min = min;
        this.size = size;
        this.isBST = isBST;
    }
}

class quadd{
    int min;
    int max;
    int sum;
    boolean isBST;

    quadd(int min , int max , int sum , boolean isBST){
        this.max = max;
        this.min = min;
        this.sum = sum;
        this.isBST = isBST;
    }
}

public class BinarySearchPart1 {
    public static void main(String[] args) {
        // Test the maxMin method with detailed examples
        testMaxMin();
    }

    /**
     * QUESTION: Find Minimum Value in Binary Search Tree
     * ================================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a Binary Search Tree, find and return the minimum value present in the tree.
     * 
     * KEY CONCEPT - BST PROPERTY:
     * In a BST:
     * - All values in the left subtree < root value
     * - All values in the right subtree > root value
     * Therefore, the minimum value is always the leftmost node.
     * 
     * APPROACH:
     * Iterative traversal to the leftmost node
     * - Start from root
     * - Keep moving left until no left child exists
     * - The node where left is null contains the minimum value
     * 
     * TIME COMPLEXITY: O(h) where h = height of tree
     * - In balanced BST: O(log n)
     * - In skewed BST: O(n)
     * 
     * SPACE COMPLEXITY: O(1) - only using a temp pointer
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Execution: 50 → 30 → 20 (left is null) → return 20
     * Minimum Value = 20
     */
    public int minValue(Node root) {
        Node temp = root;
        while(temp.left != null) temp = temp.left;
        return temp.val;
    }

    /**
     * QUESTION: Find Maximum Value in Binary Search Tree
     * ================================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a Binary Search Tree, find and return the maximum value present in the tree.
     * 
     * KEY CONCEPT - BST PROPERTY:
     * In a BST, the maximum value is always the rightmost node because all right subtree values > root.
     * 
     * APPROACH:
     * Iterative traversal to the rightmost node
     * - Start from root
     * - Keep moving right until no right child exists
     * - The node where right is null contains the maximum value
     * 
     * TIME COMPLEXITY: O(h) where h = height of tree
     * SPACE COMPLEXITY: O(1)
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Execution: 50 → 70 → 80 (right is null) → return 80
     * Maximum Value = 80
     */
    public int maxValue(Node root) {
        Node temp = root;
        while(temp.right != null) temp = temp.right;
        return temp.val;
    }

    /**
     * QUESTION: Search a Value in Binary Search Tree
     * ===============================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a BST and a target value, determine if the value exists in the tree.
     * Return true if found, false otherwise.
     * 
     * APPROACH:
     * Recursive Binary Search utilizing BST property
     * - If current node is null → value not found (return false)
     * - If current node value equals target → found (return true)
     * - If target < current node value → search in left subtree (search(root.left, x))
     * - If target > current node value → search in right subtree (search(root.right, x))
     * 
     * TIME COMPLEXITY: O(h) where h = height
     * - Best case (balanced): O(log n)
     * - Worst case (skewed): O(n)
     * 
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Search for 40:
     * 40 < 50 → Go left (30)
     * 40 > 30 → Go right (40)
     * 40 == 40 → Found! Return true
     * 
     * Search for 55:
     * 55 < 50 → Go left (30)
     * 55 > 30 → Go right (40)
     * 55 > 40 → Go right (null)
     * Null reached → Not found! Return false
     */
    public boolean search(Node root, int x) {
        if(root == null) return false;
        if(root.val == x) return true;
        else if(root.val > x) return search(root.left , x);
        else return search(root.right , x);
    }

    /**
     * QUESTION: Range Sum in Binary Search Tree (LeetCode 938)
     * =========================================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a BST and two integers low and high,
     * return the sum of all node values in the BST that lie in the range [low, high].
     * 
     * APPROACH:
     * Recursive traversal with range pruning
     * - If node value is in range [low, high] → Add its value to sum
     * - If node value > low → Recursively check left subtree
     * - If node value < high → Recursively check right subtree
     * 
     * KEY OPTIMIZATION (Pruning):
     * We don't need to visit entire tree. Using BST property:
     * - If node.val > high: entire right subtree is > high (skip right)
     * - If node.val < low: entire left subtree is < low (skip left)
     * 
     * TIME COMPLEXITY: O(n) in worst case, but typically O(log n) due to pruning
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Range [30, 70]:
     * - Node 50: In range [30,70] ✓ → Add 50, check left (30 <= 70?) and right (50 < 70?)
     * - Node 30: In range ✓ → Add 30, check left (20 < 30?) and right (30 < 70? ✓)
     * - Node 20: 20 < 30 ✗ → Skip (out of range, and entire left is < 30)
     * - Node 40: In range ✓ → Add 40
     * - Node 70: In range ✓ → Add 70, skip right (80 > 70)
     * - Node 60: In range ✓ → Add 60
     * 
     * Sum = 50 + 30 + 40 + 70 + 60 = 250
     */
    public int rangeSumBST(Node root, int low, int high) {
        if(root == null) return 0;

        int sum = 0;

        if(root.val >= low && root.val <= high){
            sum += root.val;
        }

        if(root.val > low){
            sum += rangeSumBST(root.left , low , high);
        }

        if(root.val < high){
            sum += rangeSumBST(root.right , low , high);
        }
        return sum;
    }

    /**
     * QUESTION: Count Nodes in Range [l, h] in Binary Search Tree
     * ============================================================
     * 
     * PROBLEM STATEMENT:
     * Given a BST and a range [l, h], count how many nodes have values within this range.
     * 
     * APPROACH:
     * Recursive traversal with pruning
     * - If node.val < l: Skip left subtree entirely, go right only
     * - If node.val > h: Skip right subtree entirely, go left only
     * - Otherwise: Count this node + count from both subtrees
     * 
     * TIME COMPLEXITY: O(n) worst case, O(log n) average case with pruning
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Count nodes in range [35, 75]:
     * - Node 50: 35 <= 50 <= 75 ✓ → Count 1
     *   - Go left: Node 30: 30 < 35 → Go right only
     *     - Node 40: 35 <= 40 <= 75 ✓ → Count 1
     *   - Go right: Node 70: 35 <= 70 <= 75 ✓ → Count 1
     *     - Node 60: 35 <= 60 <= 75 ✓ → Count 1
     *     - Node 80: 80 > 75 → Go left only (but no left)
     * 
     * Total Count = 1 (50) + 1 (40) + 1 (70) + 1 (60) = 4
     */
    int getCount(Node root, int l, int h) {
        if(root == null) return 0;
        if(root.val < l) return getCount(root.right , l , h);
        else if(root.val > h) return getCount(root.left , l , h);
        else {
            return 1 + getCount(root.left , l , h) + getCount(root.right , l , h);
        }
    }

    /**
     * QUESTION: Insert a Value in Binary Search Tree
     * ===============================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a BST and a key value, insert the key into the BST
     * while maintaining the BST property. If key already exists, do nothing.
     * 
     * APPROACH:
     * Recursive insertion maintaining BST property
     * - If key equals current node's value → Duplicate, don't insert (return)
     * - If key > current node value:
     *   - If right child is null → Insert here (create new node)
     *   - Otherwise → Recursively insert in right subtree
     * - If key < current node value:
     *   - If left child is null → Insert here (create new node)
     *   - Otherwise → Recursively insert in left subtree
     * 
     * TIME COMPLEXITY: O(h) where h = height
     * - Best case (balanced): O(log n)
     * - Worst case (skewed): O(n)
     * 
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE:
     *         50                  50
     *        /  \                /  \
     *       30   70      -->    30   70
     *      / \   / \          / \   / \
     *     20 40 60 80        20 40 60 80
     *                              \
     *                              45
     * 
     * Insert 45:
     * 45 < 50 → Go left (30)
     * 45 > 30 → Go right (40)
     * 45 > 40 → Right is null → Insert 45 here!
     */
    public Node insert(Node root, int key) {
        attach(root , key);
        return root;
    }

    private void attach(Node root , int key){
        if(root == null) return;
        if(root.val == key) return;
        if(root.val < key){
            if(root.right == null) root.right = new Node(key);
            else attach(root.right , key);
        }

        else{
            if(root.left == null) root.left = new Node(key);
            else attach(root.left , key);
        }
    }

    /**
     * QUESTION: Find Kth Smallest Element in Binary Search Tree (LeetCode 230)
     * ========================================================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a BST and an integer k, return the kth smallest value (1-indexed).
     * 
     * KEY CONCEPT:
     * InOrder traversal of a BST gives elements in ascending (sorted) order!
     * So we can collect all elements in sorted order and return the kth element.
     * 
     * APPROACH:
     * 1. Perform InOrder traversal (Left → Root → Right)
     * 2. Collect all node values in ArrayList
     * 3. Return the element at index (k-1)
     * 
     * TIME COMPLEXITY: O(n) - must traverse all n nodes to get sorted order
     * SPACE COMPLEXITY: O(n) - storing all elements in ArrayList
     * 
     * OPTIMIZATION TIP:
     * For large k values, we can early stop after finding kth element using a counter.
     * This would reduce average time complexity.
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * InOrder traversal: [20, 30, 40, 50, 60, 70, 80]
     * k=1 → return 20 (1st smallest)
     * k=3 → return 40 (3rd smallest)
     * k=7 → return 80 (7th smallest)
     * k=10 → return -1 (doesn't exist)
     */
    private void inOrder(Node root ,  ArrayList<Integer> arr){
        if(root == null) return;
        inOrder(root.left , arr);
        arr.add(root.val);
        inOrder(root.right , arr);
    }

    public int kthSmallest(Node root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        inOrder(root , arr);
        if(arr.size() < k) return -1;
        return arr.get(k-1);
    }

    /**
     * CORRECT WAY: isValidBST() - Validates if a tree is a Binary Search Tree
     *
     * Issues:
     * 1. Only checks immediate children (doesn't validate entire subtree)
     * 2. Doesn't enforce range constraints (ancestor constraints violated)
     * 3. Compares int with Node object (compilation error)
     *
     * Why Range Check is Necessary
     * Consider this INVALID tree that looks deceptively correct:
     *         10
     *        /  \
     *       5    15
     *      / \
     *     3   12  ← PROBLEM!
     * Without Range Check (WRONG ❌):
     * If you only checked 12 > 5 (its parent), it would pass. But 12 shouldn't be in the left subtree at all because it violates the root's constraint (12 > 10).
     *
     * CORRECT APPROACH: Use Min/Max bounds
     * 
     * Example:
     *        10
     *       /  \
     *      5    15
     *     / \   / \
     *    3   7 12  20
     * 
     * isBST(10) → isBSTHelper(10, MIN, MAX)
     *   ├─ 10 in range? YES (MIN < 10 < MAX)
     *   ├─ Left subtree: isBSTHelper(5, MIN, 10)
     *   │   ├─ 5 in range? YES (MIN < 5 < 10)
     *   │   ├─ Left subtree: isBSTHelper(3, MIN, 5) → YES
     *   │   └─ Right subtree: isBSTHelper(7, 5, 10) → YES
     *   └─ Right subtree: isBSTHelper(15, 10, MAX)
     *       ├─ Right subtree: isBSTHelper(20, 15, MAX) → YES
     * 
     * Result: true (Valid BST)
     */
    public boolean isValidBST(Node root) {
        return isBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isBSTHelper(Node root, long min, long max) {
        if (root == null) return true;

        // Check if current node violates BST property
        if (root.val <= min || root.val >= max) return false;

        // Recursively check left and right subtrees
        // Left subtree: all values must be less than root.val
        // Right subtree: all values must be greater than root.val
        return isBSTHelper(root.left, min, root.val) &&
                isBSTHelper(root.right, root.val, max);
    }

    /**
     * APPROACH 2: Using InOrder Traversal
     * In a valid BST, inOrder traversal produces sorted ascending order
     * 
     * Example:
     *        10
     *       /  \
     *      5    15
     *     / \   / \
     *    3   7 12  20
     * 
     * InOrder traversal: [3, 5, 7, 10, 12, 15, 20] → Sorted ✓
     * 
     * If tree is invalid:
     *        5
     *       / \
     *      7   3
     * InOrder: [7, 5, 3] → NOT sorted ✗
     */
    private boolean isSorted(ArrayList<Integer> ans){
        for(int i=0; i<ans.size()-1; i++){
            if(ans.get(i) >= ans.get(i+1)) return false;
        }
        return true;
    }

    public boolean isBST_InOrderApproach(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return true;
        inOrder(root , ans);
        return isSorted(ans);
    }

    /**
     * QUESTION: Lowest Common Ancestor (LCA) of Two Nodes in BST (LeetCode 235)
     * ===========================================================================
     * 
     * PROBLEM STATEMENT:
     * Given the root of a BST and two nodes p and q, find their lowest common ancestor (LCA).
     * The LCA is the lowest node that has both p and q as descendants.
     * 
     * KEY INSIGHT - BST PROPERTY:
     * Unlike a general binary tree, we can use the BST property directly:
     * - If both p and q are < root → LCA is in left subtree
     * - If both p and q are > root → LCA is in right subtree
     * - Otherwise (one on each side or is root) → root is the LCA
     * 
     * APPROACH:
     * Recursive search using BST property for efficient pruning
     * - Compare root value with both p and q values
     * - Direct to appropriate subtree based on BST property
     * - When search paths split (one left, one right), current node is LCA
     * 
     * TIME COMPLEXITY: O(h) where h = height
     * - Best case (balanced): O(log n)
     * - Worst case (skewed): O(n)
     * 
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE 1: Both nodes in left subtree
     *         50                     p=20, q=30
     *        /  \
     *       30   70                  50 > p and 50 > q → Go left
     *      / \   / \                 30: 30 > p but 30 >= q → LCA = 30 (or continue based on requirement)
     *     20 40 60 80
     * 
     * EXAMPLE 2: Nodes on opposite sides
     *         50
     *        /  \
     *       30   70                  p=20, q=75
     *      / \   / \
     *     20 40 60 80
     *
     * 50: NOT(50 > 20 AND 50 > 75) AND NOT(50 < 20 AND 50 < 75) → LCA = 50
     * 
     * EXAMPLE 3: One node is the LCA itself
     *         50
     *        /  \
     *       30   70                  p=30, q=40
     *      / \   / \
     *     20 40 60 80
     *
     * 50 > 30 and 50 > 40 → Go left
     * 30: NOT > 30 and NOT > 40 (instead 30 < 40) → LCA = 30 (node itself)
     */
    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestor( root.left, p, q);
        else if(root.val < p.val && root.val < q.val) return lowestCommonAncestor( root.right, p, q);
        else return root;
    }

    /**
     * QUESTION: Binary Search Tree to Greater Sum Tree (LeetCode 1038)
     * ================================================================
     * 
     * PROBLEM STATEMENT:
     * Given a Binary Search Tree, convert it to a Greater Sum Tree where
     * each node's value is updated to be the sum of itself and all nodes greater than it.
     * 
     * KEY INSIGHT - REVERSE INORDER TRAVERSAL:
     * Regular InOrder: Left → Root → Right (gives ascending order)
     * Reverse InOrder: Right → Root → Left (gives descending order - largest first!)
     * 
     * By traversing in reverse inorder and maintaining a running sum,
     * each node becomes: running_sum + node_value, and we update running_sum afterward.
     * 
     * APPROACH:
     * 1. Perform InOrder traversal (Left → Root → Right)
     * 2. Collect all nodes in ArrayList (InOrder gives ascending order)
     * 3. Reverse the ArrayList (now in descending order)
     * 4. Traverse reversed list, updating each node with cumulative sum
     * 
     * TIME COMPLEXITY: O(n) - visit all nodes, collect, reverse, and update
     * SPACE COMPLEXITY: O(n) - storing all nodes in ArrayList
     * 
     * EXAMPLE:
     * Input BST:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * InOrder: [20, 30, 40, 50, 60, 70, 80]
     * Reversed: [80, 70, 60, 50, 40, 30, 20]
     * 
     * Processing (Running Sum):
     * - 80: sum=0 → node.val=0, sum becomes 0+80=80
     * - 70: sum=80 → node.val=80, sum becomes 80+70=150
     * - 60: sum=150 → node.val=150, sum becomes 150+60=210
     * - 50: sum=210 → node.val=210, sum becomes 210+50=260
     * - 40: sum=260 → node.val=260, sum becomes 260+40=300
     * - 30: sum=300 → node.val=300, sum becomes 300+30=330
     * - 20: sum=330 → node.val=330, sum becomes 330+20=350
     * 
     * Output BST:
     *         260
     *        /   \
     *       300   210
     *       / \    / \
     *      330 300 210 80
     * 
     * Each node now contains: itself + sum of all nodes with value > it
     */
    private static void inorder(Node root , ArrayList<Node> ans){
        if(root == null) return;
        inorder(root.left , ans);
        ans.add(root);
        inorder(root.right , ans);
    }

    public static void transformTree(Node root) {
        ArrayList<Node> ans = new ArrayList<>();
        inorder(root , ans);
        Collections.reverse(ans);
        int sum = 0;
        for(int i=0; i<ans.size(); i++){
            int val = ans.get(i).val;
            ans.get(i).val = sum;
            sum += val;
        }
    }

    /**
     * APPROACH 3: Find Min and Max using Math.min/max + BST Validation
     * 
     * This method finds the minimum and maximum values in a Binary Tree
     * AND validates if it's a valid BST in a single traversal.
     * 
     * Time Complexity: O(n) - visits each node once
     * Space Complexity: O(h) - recursion stack (h = height)
     * 
     * Example 1: VALID BST
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * Execution Flow (Post-order: Left → Right → Root):
     * 
     * 1. Node 20 (leaf):
     *    - Left subtree: null → Pair(MIN_VALUE, MAX_VALUE)
     *    - Right subtree: null → Pair(MIN_VALUE, MAX_VALUE)
     *    - max = Math.max(20, Math.max(MIN_VALUE, MIN_VALUE)) = 20
     *    - min = Math.min(20, Math.min(MAX_VALUE, MAX_VALUE)) = 20
     *    - BST Check: No children, valid ✓
     *    - Returns: Pair(20, 20)
     * 
     * 2. Node 40 (leaf):
     *    - Returns: Pair(40, 40)
     * 
     * 3. Node 30:
     *    - Left subtree: Pair(20, 20)  [max=20, min=20]
     *    - Right subtree: Pair(40, 40) [max=40, min=40]
     *    - max = Math.max(30, Math.max(20, 40)) = 40
     *    - min = Math.min(30, Math.min(20, 40)) = 20
     *    - BST Check: 20 < 30 ✓, 40 > 30 ✓ → Valid!
     *    - Returns: Pair(40, 20)
     * 
     * 4. Node 60 (leaf): Returns: Pair(60, 60)
     * 5. Node 80 (leaf): Returns: Pair(80, 80)
     * 
     * 6. Node 70:
     *    - Left subtree: Pair(60, 60)
     *    - Right subtree: Pair(80, 80)
     *    - max = Math.max(70, Math.max(60, 80)) = 80
     *    - min = Math.min(70, Math.min(60, 80)) = 60
     *    - BST Check: 60 < 70 ✓, 80 > 70 ✓ → Valid!
     *    - Returns: Pair(80, 60)
     * 
     * 7. Node 50 (root):
     *    - Left subtree: Pair(40, 20)  [max=40, min=20]
     *    - Right subtree: Pair(80, 60) [max=80, min=60]
     *    - max = Math.max(50, Math.max(40, 80)) = 80
     *    - min = Math.min(50, Math.min(20, 60)) = 20
     *    - BST Check: 40 < 50 ✓, 60 > 50 ✓ → Valid!
     *    - Returns: Pair(80, 20)
     * 
     * Result: Min = 20, Max = 80, Valid BST = true ✓
     * 
     * 
     * Example 2: INVALID BST
     *         50
     *        /  \
     *       30   70
     *      / \
     *     20 55  ← INVALID! 55 > 50 (violates BST property)
     * 
     * Execution at Node 30:
     *    - Left: Pair(20, 20)
     *    - Right: Pair(55, 55)
     *    - max = 55, min = 20
     *    - BST Check: lst.max(20) < 30 ✓, rst.min(55) > 30 ✓
     *    - Returns: Pair(55, 20)
     * 
     * Execution at Node 50:
     *    - Left: Pair(55, 20)  [max=55, min=20]
     *    - Right: Pair(70, 70)
     *    - max = 70, min = 20
     *    - BST Check: lst.max(55) >= 50 → FAILS! ✗
     *    - flag = false (Invalid BST detected)
     * 
     * Result: Invalid BST because 55 in left subtree is greater than root 50
     */
    
    // Flag to track BST validity
    boolean flag = true;
    
    public Pair maxMin(Node root) {
        if(root == null) 
            return new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        // Get min/max from left subtree
        Pair lst = maxMin(root.left);
        
        // Get min/max from right subtree
        Pair rst = maxMin(root.right);
        
        // Calculate max: current node vs left max vs right max
        int max = Math.max(root.val, Math.max(lst.max, rst.max));
        
        // Calculate min: current node vs left min vs right min
        int min = Math.min(root.val, Math.min(lst.min, rst.min));
        
        // BST Validation:
        // Left subtree's max should be < current node
        // Right subtree's min should be > current node
        if(lst.max >= root.val || rst.min <= root.val) {
            flag = false;  // Invalid BST
        }
        
        return new Pair(max, min);
    }

    /**
     * QUESTION: Largest BST Subtree in Binary Tree (LeetCode 333)
     * ============================================================
     * 
     * PROBLEM STATEMENT:
     * Given a Binary Tree (NOT necessarily a BST), find the size (number of nodes)
     * of the largest subtree that IS a valid Binary Search Tree.
     * 
     * CHALLENGE:
     * Not all subtrees are valid BSTs. We need to find which subtrees satisfy
     * the BST property AND return the one with maximum number of nodes.
     * 
     * APPROACH - POST-ORDER TRAVERSAL WITH VALIDATION:
     * For each node, return: (min, max, size, isBST)
     * 
     * 1. Base Case: If node is null → return (MAX_VALUE, MIN_VALUE, 0, true)
     * 2. Recursively get info from left and right subtrees
     * 3. Calculate:
     *    - max = maximum of (root, left.max, right.max)
     *    - min = minimum of (root, left.min, right.min)
     *    - size = 1 + left.size + right.size (if current is BST)
     * 4. Check if current subtree is BST:
     *    - isBST = left.isBST && right.isBST && (left.max < root.val) && (right.min > root.val)
     * 5. Update maxSize if current is a valid BST
     * 
     * TIME COMPLEXITY: O(n) - visits each node once
     * SPACE COMPLEXITY: O(h) - recursion stack, where h = height
     * 
     * KEY CONCEPT - WHY MIN/MAX AND SIZE NEEDED:
     * - min/max: To validate BST property at ancestors
     * - size: To track largest valid BST
     * - isBST: To know if current subtree is valid
     * 
     * EXAMPLE:
     *         10
     *        /  \
     *       5    15
     *      / \      \\
     *     1   8     7
     *    
     * Node 1 (leaf): isBST=true, size=1
     * Node 8 (leaf): isBST=true, size=1
     * Node 5: left(1,1,1,T) & right(8,8,1,T)
     *         1 < 5 ✓, 8 > 5 ✓ → isBST=true, size=3
     * Node 7 (leaf): isBST=true, size=1
     * Node 15: left is null, right(7,7,1,T)
     *          7 > 15 ✗ → isBST=false, size=N/A
     * Node 10: left(1,8,3,T) & right(7,15,2,F)
     *          right is not BST → isBST=false
     *
     * Result: Largest BST = subtree at node 5 with size 3
     */
    static int maxSize;
    static int largestBst(Node root) {
        maxSize = 0;
        helper(root);
        return maxSize;

    }

    private static quad helper(Node root){
        if(root == null) return new quad(Integer.MAX_VALUE , Integer.MIN_VALUE , 0 , true);
        quad lst = helper(root.left);
        quad rst = helper(root.right);
        int max = Math.max(root.val , Math.max(lst.max , rst.max));
        int min = Math.min(root.val , Math.min(lst.min , rst.min));
        int size = 1 + lst.size + rst.size;
        boolean isBST = lst.isBST && rst.isBST && (lst.max < root.val) && (rst.min > root.val);
        if(isBST) maxSize = Math.max(size , maxSize);
        return new quad(min , max , size , isBST);
    }

    /**
     * QUESTION: Maximum Sum of a BST in Binary Tree (LeetCode 1373)
     * ================================================================
     * 
     * PROBLEM STATEMENT:
     * Given a Binary Tree, find the maximum sum of all node values in any subtree
     * that is also a valid Binary Search Tree.
     * 
     * DIFFERENCE FROM largestBst():
     * largestBst() returns the SIZE (count) of largest BST
     * maxSumBST() returns the SUM of node values in the BST with maximum sum
     * 
     * APPROACH - POST-ORDER TRAVERSAL WITH VALIDATION:
     * Similar to largestBst but instead of counting nodes, we sum their values.
     * 
     * For each node, track: (min, max, sum, isBST)
     * 
     * 1. If current subtree is a valid BST:
     *    - Calculate sum = left.sum + right.sum + root.val
     *    - Update maxsum if this is greater
     * 2. If current subtree is NOT a valid BST:
     *    - Return marker values (isBST=false)
     *    - Parent nodes use this to know not to include in their calculation
     * 
     * TIME COMPLEXITY: O(n) - visit each node once
     * SPACE COMPLEXITY: O(h) - recursion stack
     * 
     * EXAMPLE:
     *         1
     *        / \
     *       4   3
     *      /
     *     2
     *    
     * Analysis:
     * - Subtree at node 2: {2}, isBST=true, sum=2
     * - Subtree at node 4: {4, 2}, 2 < 4 ✓, isBST=true, sum=6
     * - Subtree at node 3: {3}, isBST=true, sum=3
     * - Subtree at node 1: left(1,2,6,T) & right(3,3,3,T)
     *   - Check: 2 < 1? NO ✗ → isBST=false
     * - Subtree at root: isBST=false
     * 
     * Result: Maximum sum of any valid BST = 6 (subtree {2, 4})
     * 
     * ANOTHER EXAMPLE:
     *         10
     *        /  \
     *       5    15
     *      / \      \\
     *     1   8      7
     * 
     * - Subtree at 1: sum=1, isBST=true
     * - Subtree at 8: sum=8, isBST=true
     * - Subtree at 5: sum=14 (1+5+8), isBST=true ✓
     * - Subtree at 7: sum=7, isBST=true
     * - Subtree at 15: right not BST, isBST=false
     * - Subtree at 10: isBST=false (right subtree fails)
     * 
     * Result: Maximum sum = 14 (subtree {1, 5, 8})
     */
    static int maxsum;
    public int maxSumBST(Node root) {
        maxsum = 0;
        sumHelper(root);
        return maxsum;
    }

    private static quadd sumHelper(Node root){
        if(root == null) return new quadd(Integer.MAX_VALUE , Integer.MIN_VALUE , 0 , true);
        quadd lst = sumHelper(root.left);
        quadd rst = sumHelper(root.right);
        boolean isBST = lst.isBST && rst.isBST && (lst.max < root.val) && (rst.min > root.val);
        if (isBST) {
            int sum = lst.sum + rst.sum + root.val;
            maxsum = Math.max(maxsum, sum);

            int min = Math.min(root.val, lst.min);
            int max = Math.max(root.val, rst.max);

            return new quadd(min, max, sum, true);
        }
        return new quadd(Integer.MIN_VALUE , Integer.MAX_VALUE , 0 , false);
    }

    /**
     * QUESTION: Morris InOrder Traversal (LeetCode 94)
     * ================================================
     * 
     * PROBLEM STATEMENT:
     * Perform InOrder traversal of a Binary Tree WITHOUT using extra space for recursion stack.
     * Use the tree's structure itself (threading) to traverse.
     * 
     * CHALLENGE:
     * Traditional InOrder uses O(h) stack space for recursion.
     * Morris traversal achieves O(1) space by temporarily modifying tree structure.
     * 
     * KEY CONCEPT - THREADED TREE:
     * - For a node with a left subtree, we find the rightmost node in left subtree (predecessor)
     * - Set predecessor's right pointer to current node (creates a thread back)
     * - Use these threads for efficient traversal without recursion
     * 
     * ALGORITHM:
     * 1. Start at root (curr = root)
     * 2. While curr is not null:
     *    a. If curr has no left child:
     *       - Add curr.val to result
     *       - Move right (curr = curr.right)
     *    b. If curr has left child:
     *       - Find predecessor (rightmost node in left subtree)
     *       - If predecessor.right is null:
     *         * Thread it back: pred.right = curr
     *         * Move left: curr = curr.left
     *       - If predecessor.right equals curr (second visit):
     *         * Unthread: pred.right = null
     *         * Add curr.val to result
     *         * Move right: curr = curr.right
     * 
     * TIME COMPLEXITY: O(n) - each node visited at most twice
     * SPACE COMPLEXITY: O(1) - only using curr and pred pointers
     * 
     * EXAMPLE:
     *         50
     *        /  \
     *       30   70
     *      / \   / \
     *     20 40 60 80
     * 
     * InOrder Result: [20, 30, 40, 50, 60, 70, 80]
     * 
     * Step-by-step execution:
     * 1. curr=50, has left child (30)
     *    - Find pred=40 (rightmost in left subtree)
     *    - pred.right is null → Thread it: 40.right=50
     *    - Move left: curr=30
     * 
     * 2. curr=30, has left child (20)
     *    - Find pred=20 (rightmost in left subtree)
     *    - pred.right is null → Thread it: 20.right=30
     *    - Move left: curr=20
     * 
     * 3. curr=20, no left child
     *    - Add 20 to result
     *    - Move right: curr=30
     * 
     * 4. curr=30, has left child (20)
     *    - Find pred=20
     *    - pred.right==curr (30) → Already threaded! (Second visit)
     *    - Unthread: 20.right=null
     *    - Add 30 to result
     *    - Move right: curr=40
     * 
     * 5. curr=40, no left child
     *    - Add 40 to result
     *    - Move right: curr=50 (following thread)
     * 
     * 6. curr=50, has left child (30)
     *    - Find pred=40
     *    - pred.right==curr (50) → Already threaded!
     *    - Unthread: 40.right=null
     *    - Add 50 to result
     *    - Move right: curr=70
     * 
     * 7. curr=70, has left child (60)
     *    - Find pred=60
     *    - pred.right is null → Thread it: 60.right=70
     *    - Move left: curr=60
     * 
     * 8. curr=60, no left child
     *    - Add 60 to result
     *    - Move right: curr=70 (following thread)
     * 
     * 9. curr=70, has left child (60)
     *    - Find pred=60
     *    - pred.right==curr → Already threaded!
     *    - Unthread: 60.right=null
     *    - Add 70 to result
     *    - Move right: curr=80
     * 
     * 10. curr=80, no left child
     *     - Add 80 to result
     *     - Move right: curr=null
     * 
     * Final Result: [20, 30, 40, 50, 60, 70, 80] ✓
     * 
     * ADVANTAGES:
     * - O(1) space complexity (vs O(h) for recursive inorder)
     * - Good for external sorting or when stack space is limited
     * 
     * DISADVANTAGES:
     * - Temporarily modifies tree structure (though it restores it)
     * - More complex logic compared to recursive approach
     */
    public ArrayList<Integer> Morris(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr = root;
        while(curr != null){
            if(curr.left != null){
                Node pred = curr.left;
                while(pred.right != null && pred.right != curr)
                    pred = pred.right;
                if(pred.right== null){
                    pred.right = curr;
                    curr = curr.left;
                }else{
                    pred.right = null;
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }else{
                ans.add(curr.val);
                curr = curr.right;
            }
        }
        return ans;
    }
    
    /**
     * TEST METHOD: Comprehensive Testing of maxMin() Function
     * ========================================================
     * 
     * This test method demonstrates the maxMin() function with 3 different scenarios:
     * 1. Valid BST - shows correct behavior when tree is a proper BST
     * 2. Invalid BST - demonstrates detection of BST violations
     * 3. Single node - edge case with minimal tree
     * 
     * Purpose: Verify that maxMin() correctly:
     * - Finds minimum and maximum values
     * - Validates BST property
     * - Detects violations in tree structure
     */
    public static void testMaxMin() {
        BinarySearchPart1 bst = new BinarySearchPart1();
        
        // Example 1: Valid BST
        System.out.println("========== Example 1: Valid BST ==========");
        System.out.println("Tree Structure:");
        System.out.println("        50");
        System.out.println("       /  \\");
        System.out.println("      30   70");
        System.out.println("     / \\   / \\");
        System.out.println("    20 40 60 80");
        System.out.println();
        
        Node root1 = new Node(50);
        root1.left = new Node(30);
        root1.right = new Node(70);
        root1.left.left = new Node(20);
        root1.left.right = new Node(40);
        root1.right.left = new Node(60);
        root1.right.right = new Node(80);
        
        bst.flag = true;  // Reset flag
        Pair result1 = bst.maxMin(root1);
        System.out.println("Minimum Value: " + result1.min);
        System.out.println("Maximum Value: " + result1.max);
        System.out.println("Is Valid BST: " + bst.flag);
        System.out.println();
        
        // Example 2: Invalid BST (55 in wrong position)
        System.out.println("========== Example 2: Invalid BST ==========");
        System.out.println("Tree Structure:");
        System.out.println("        50");
        System.out.println("       /  \\");
        System.out.println("      30   70");
        System.out.println("     / \\");
        System.out.println("    20 55  ← INVALID! (55 > 50)");
        System.out.println();
        
        Node root2 = new Node(50);
        root2.left = new Node(30);
        root2.right = new Node(70);
        root2.left.left = new Node(20);
        root2.left.right = new Node(55);  // INVALID position!
        
        bst.flag = true;  // Reset flag
        Pair result2 = bst.maxMin(root2);
        System.out.println("Minimum Value: " + result2.min);
        System.out.println("Maximum Value: " + result2.max);
        System.out.println("Is Valid BST: " + bst.flag);
        System.out.println("Reason: 55 in left subtree is >= root (50)");
        System.out.println();
        
        // Example 3: Single node
        System.out.println("========== Example 3: Single Node ==========");
        System.out.println("Tree Structure: 100");
        Node root3 = new Node(100);
        
        bst.flag = true;
        Pair result3 = bst.maxMin(root3);
        System.out.println("Minimum Value: " + result3.min);
        System.out.println("Maximum Value: " + result3.max);
        System.out.println("Is Valid BST: " + bst.flag);
    }
}


