package Trees;

//class Node{
//    int val;
//    Node left;
//    Node right;
//
//    Node(int val){
//        this.val = val;
//    }
//}

import javax.swing.tree.TreeNode;
import java.util.*;

public class TreesPart2Practice {
    public static void main(String[] args) {

    }

    /*
     * DRY RUN EXAMPLE:
     * 
     * Consider the following tree:
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     *    /
     *   6
     * 
     * Call: isBalanced(root) where root = Node(1)
     * 
     * Step 1: isBalanced(1)
     *   - root is not null
     *   - flag = true
     *   - Call levels(1)
     * 
     * Step 2: levels(1)
     *   - root = 1 (not null)
     *   - Call levels(2) for left child
     * 
     * Step 3: levels(2)
     *   - root = 2 (not null)
     *   - Call levels(4) for left child
     * 
     * Step 4: levels(4)
     *   - root = 4 (not null)
     *   - Call levels(6) for left child
     * 
     * Step 5: levels(6)
     *   - root = 6 (not null)
     *   - Call levels(null) for left child → returns 0
     *   - leftlevel = 0
     *   - Call levels(null) for right child → returns 0
     *   - rightlevel = 0
     *   - |0 - 0| = 0 ≤ 1, so flag remains true
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 4: levels(4)
     *   - leftlevel = 1
     *   - Call levels(null) for right child → returns 0
     *   - rightlevel = 0
     *   - |1 - 0| = 1 ≤ 1, so flag remains true
     *   - return 1 + max(1, 0) = 2
     * 
     * Back to Step 3: levels(2)
     *   - leftlevel = 2
     *   - Call levels(5) for right child
     * 
     * Step 6: levels(5)
     *   - root = 5 (not null)
     *   - Call levels(null) for left child → returns 0
     *   - leftlevel = 0
     *   - Call levels(null) for right child → returns 0
     *   - rightlevel = 0
     *   - |0 - 0| = 0 ≤ 1, so flag remains true
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 3: levels(2)
     *   - leftlevel = 2, rightlevel = 1
     *   - |2 - 1| = 1 ≤ 1, so flag remains true
     *   - return 1 + max(2, 1) = 3
     * 
     * Back to Step 2: levels(1)
     *   - leftlevel = 3
     *   - Call levels(3) for right child
     * 
     * Step 7: levels(3)
     *   - root = 3 (not null)
     *   - Call levels(null) for left child → returns 0
     *   - leftlevel = 0
     *   - Call levels(null) for right child → returns 0
     *   - rightlevel = 0
     *   - |0 - 0| = 0 ≤ 1, so flag remains true
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 2: levels(1)
     *   - leftlevel = 3, rightlevel = 1
     *   - |3 - 1| = 2 > 1, so flag = false ❌
     *   - return 1 + max(3, 1) = 4
     * 
     * Back to Step 1: isBalanced(1)
     *   - levels(1) completed
     *   - return flag = false
     * 
     * RESULT: The tree is NOT balanced because the left subtree has height 3
     *         and the right subtree has height 1 (difference = 2 > 1)
     */
    
    static boolean flag;
    private int levelsForBalance(Node root){
        if(root == null) return 0;
        int leftlevel = levelsForBalance(root.left);
        int rightlevel = levelsForBalance(root.right);
        if(Math.abs(leftlevel - rightlevel) > 1) flag = false;
        return 1 + Math.max( leftlevel , rightlevel );
    }

    public boolean isBalanced(Node root) {
        if(root == null) return true;
        flag = true;
        levelsForBalance(root);
        return flag;
    }

    /*
     * DRY RUN EXAMPLE - DIAMETER OF BINARY TREE:
     * 
     * Consider the tree:
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     * 
     * Call: diameterOfBinaryTree(root) where root = Node(1)
     * 
     * Step 1: diameterOfBinaryTree(1)
     *   - max = 0
     *   - Call levelsForDiameter(1)
     * 
     * Step 2: levelsForDiameter(1)
     *   - root = 1 (not null)
     *   - Call levelsForDiameter(2) for left
     * 
     * Step 3: levelsForDiameter(2)
     *   - root = 2 (not null)
     *   - Call levelsForDiameter(4) for left
     * 
     * Step 4: levelsForDiameter(4)
     *   - root = 4 (not null)
     *   - Call levelsForDiameter(null) for left → returns 0
     *   - leftlevel = 0
     *   - Call levelsForDiameter(null) for right → returns 0
     *   - rightlevel = 0
     *   - max = Math.max(0, 0 + 0) = 0
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 3: levelsForDiameter(2)
     *   - leftlevel = 1
     *   - Call levelsForDiameter(5) for right
     * 
     * Step 5: levelsForDiameter(5)
     *   - root = 5 (not null)
     *   - Call levelsForDiameter(null) for left → returns 0
     *   - leftlevel = 0
     *   - Call levelsForDiameter(null) for right → returns 0
     *   - rightlevel = 0
     *   - max = Math.max(0, 0 + 0) = 0
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 3: levelsForDiameter(2)
     *   - leftlevel = 1, rightlevel = 1
     *   - max = Math.max(0, 1 + 1) = 2 ✓
     *   - return 1 + max(1, 1) = 2
     * 
     * Back to Step 2: levelsForDiameter(1)
     *   - leftlevel = 2
     *   - Call levelsForDiameter(3) for right
     * 
     * Step 6: levelsForDiameter(3)
     *   - root = 3 (not null)
     *   - Call levelsForDiameter(null) for left → returns 0
     *   - leftlevel = 0
     *   - Call levelsForDiameter(null) for right → returns 0
     *   - rightlevel = 0
     *   - max = Math.max(2, 0 + 0) = 2
     *   - return 1 + max(0, 0) = 1
     * 
     * Back to Step 2: levelsForDiameter(1)
     *   - leftlevel = 2, rightlevel = 1
     *   - max = Math.max(2, 2 + 1) = 3 ✓
     *   - return 1 + max(2, 1) = 3
     * 
     * Back to Step 1: diameterOfBinaryTree(1)
     *   - levelsForDiameter(1) completed
     *   - return max = 3
     * 
     * RESULT: Diameter = 3 (path: 4 → 2 → 5 or 4 → 2 → 1 → 3)
     */
    
    int max;
    public int diameterOfBinaryTree(Node root) {
        max = 0;
        levelsForDiameter(root);
        return max;
    }
    private int levelsForDiameter(Node root){
        if(root == null) return 0;
        int leftlevel = levelsForDiameter(root.left);
        int rightlevel = levelsForDiameter(root.right);
        max = Math.max(max , leftlevel + rightlevel);
        return 1 + Math.max( leftlevel , rightlevel );
    }

    /*
     * DRY RUN EXAMPLE - RIGHT SIDE VIEW (BFS approach):
     * 
     * Consider the tree:
     *         1
     *        / \
     *       2   3
     *      / \   \
     *     4   5   6
     * 
     * Call: rightSideView(root) where root = Node(1)
     * 
     * Initial: result = [], q = [1]
     * 
     * Iteration 1: Level 0
     *   - q = [1], levelSize = 1
     *   - i=0: current = 1, i == levelSize-1 (0 == 0) ✓ → result.add(1)
     *   - Add children: q = [2, 3]
     *   - result = [1]
     * 
     * Iteration 2: Level 1
     *   - q = [2, 3], levelSize = 2
     *   - i=0: current = 2, i == levelSize-1 (0 == 1) ✗
     *     - Add children: q = [3, 4, 5]
     *   - i=1: current = 3, i == levelSize-1 (1 == 1) ✓ → result.add(3)
     *     - Add children: q = [4, 5, 6]
     *   - result = [1, 3]
     * 
     * Iteration 3: Level 2
     *   - q = [4, 5, 6], levelSize = 3
     *   - i=0: current = 4, i == levelSize-1 (0 == 2) ✗
     *     - No children
     *   - i=1: current = 5, i == levelSize-1 (1 == 2) ✗
     *     - No children
     *   - i=2: current = 6, i == levelSize-1 (2 == 2) ✓ → result.add(6)
     *     - No children
     *   - result = [1, 3, 6]
     * 
     * Queue is empty, return result = [1, 3, 6]
     * 
     * RESULT: Right side view = [1, 3, 6] (rightmost node at each level)
     */
    
    public List<Integer> rightSideView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelSize = q.size();

            for(int i=0; i<levelSize; i++){
                Node current = q.poll();

                if(i == levelSize-1){
                    result.add(current.val);
                }

                if(current.left != null) q.offer(current.left);
                if(current.right != null) q.offer(current.right);
            }
        }
        return result;
    }

    /*
     * DRY RUN EXAMPLE - RIGHT VIEW (DFS approach):
     * 
     * Consider the tree:
     *         1
     *        / \
     *       2   3
     *      / \   \
     *     4   5   6
     * 
     * Call: rightView(root) where root = Node(1)
     * 
     * Step 1: rightView(1)
     *   - ans = []
     *   - Call view(1, 0, ans)
     * 
     * Step 2: view(1, 0, ans)
     *   - root = 1, level = 0
     *   - level >= ans.size() (0 >= 0) ✓ → ans.add(1) → ans = [1]
     *   - Call view(2, 1, ans) [left child]
     * 
     * Step 3: view(2, 1, ans)
     *   - root = 2, level = 1
     *   - level >= ans.size() (1 >= 1) ✓ → ans.add(2) → ans = [1, 2]
     *   - Call view(4, 2, ans) [left child]
     * 
     * Step 4: view(4, 2, ans)
     *   - root = 4, level = 2
     *   - level >= ans.size() (2 >= 2) ✓ → ans.add(4) → ans = [1, 2, 4]
     *   - Call view(null, 3, ans) [left child] → returns
     *   - Call view(null, 3, ans) [right child] → returns
     * 
     * Back to Step 3: view(2, 1, ans)
     *   - Call view(5, 2, ans) [right child]
     * 
     * Step 5: view(5, 2, ans)
     *   - root = 5, level = 2
     *   - level >= ans.size() (2 >= 3) ✗ → ans.set(2, 5) → ans = [1, 2, 5]
     *   - Call view(null, 3, ans) [left child] → returns
     *   - Call view(null, 3, ans) [right child] → returns
     * 
     * Back to Step 2: view(1, 0, ans)
     *   - Call view(3, 1, ans) [right child]
     * 
     * Step 6: view(3, 1, ans)
     *   - root = 3, level = 1
     *   - level >= ans.size() (1 >= 3) ✗ → ans.set(1, 3) → ans = [1, 3, 5]
     *   - Call view(null, 2, ans) [left child] → returns
     *   - Call view(6, 2, ans) [right child]
     * 
     * Step 7: view(6, 2, ans)
     *   - root = 6, level = 2
     *   - level >= ans.size() (2 >= 3) ✗ → ans.set(2, 6) → ans = [1, 3, 6]
     *   - Call view(null, 3, ans) [left child] → returns
     *   - Call view(null, 3, ans) [right child] → returns
     * 
     * Back to Step 1: rightView(1)
     *   - return ans = [1, 3, 6]
     * 
     * RESULT: Right view = [1, 3, 6]
     * NOTE: This DFS approach processes left first, then overwrites with right,
     *       ensuring rightmost node at each level is stored
     */
    
    public ArrayList<Integer> rightView(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        view(root , 0 , ans);
        return ans;
    }

    private void view(Node root , int level , ArrayList<Integer> ans){
        if(root == null) return;
        if(level >= ans.size()) ans.add(root.val);
        else ans.set(level , root.val);
        view(root.left , level+1, ans);
        view(root.right , level+1, ans);
    }

    /*
     * DRY RUN EXAMPLE - LEFT SIDE VIEW (BFS approach):
     * 
     * Consider the tree:
     *         1
     *        / \
     *       2   3
     *      / \   \
     *     4   5   6
     * 
     * Call: leftSideView(root) where root = Node(1)
     * 
     * Initial: result = [], q = [1]
     * 
     * Iteration 1: Level 0
     *   - q = [1], levelSize = 1
     *   - i=0: current = 1, i == 0 ✓ → result.add(1)
     *   - Add children: q = [2, 3]
     *   - result = [1]
     * 
     * Iteration 2: Level 1
     *   - q = [2, 3], levelSize = 2
     *   - i=0: current = 2, i == 0 ✓ → result.add(2)
     *     - Add children: q = [3, 4, 5]
     *   - i=1: current = 3, i == 0 ✗
     *     - Add children: q = [4, 5, 6]
     *   - result = [1, 2]
     * 
     * Iteration 3: Level 2
     *   - q = [4, 5, 6], levelSize = 3
     *   - i=0: current = 4, i == 0 ✓ → result.add(4)
     *     - No children
     *   - i=1: current = 5, i == 0 ✗
     *     - No children
     *   - i=2: current = 6, i == 0 ✗
     *     - No children
     *   - result = [1, 2, 4]
     * 
     * Queue is empty, return result = [1, 2, 4]
     * 
     * RESULT: Left side view = [1, 2, 4] (leftmost node at each level)
     */
    
    public List<Integer> leftSideView(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int levelSize = q.size();

            for(int i=0; i<levelSize; i++){
                Node current = q.poll();

                if(i == 0){
                    result.add(current.val);
                }

                if(current.left != null) q.offer(current.left);
                if(current.right != null) q.offer(current.right);
            }
        }
        return result;
    }

    public Node lowestCommonAncestor(Node root, Node p, Node q) {
        if (root == null) return null;
        if (root == p || root == q) return root;

        boolean pLiesInLeft = exist(root.left, p);
        boolean qLiesInLeft = exist(root.left, q);

        if (pLiesInLeft && qLiesInLeft) return lowestCommonAncestor(root.left, p, q);
        else if (!pLiesInLeft && !qLiesInLeft) return lowestCommonAncestor(root.right, p, q);
        else return root;
    }

    private boolean exist(Node root, Node target) {
        if (root == null) return false;
        if (root == target) return true;
        return exist(root.left, target) || exist(root.right, target);
    }

    /*
     * EXECUTION EXAMPLE 1:
     *
     * Tree:        3
     *            /   \
     *           5     1
     *          / \   / \
     *         6   2 0   8
     *            / \
     *           7   4
     *
     * Find LCA of p=5 and q=1
     *
     * Call: lowestCommonAncestor(3, 5, 1)
     *
     * Step 1: LCA(3, 5, 1)
     *   - root=3, not p or q
    *   - Search left: LCA(5, 5, 1)
 *
         * Step 2: LCA(5, 5, 1)
 *   - root=5, equals p ✓
            *   - return 5 (found p, don't need to search further)
            *
            * Back to Step 1:
            *   - left = 5
            *   - Search right: LCA(1, 5, 1)
 *
         * Step 3: LCA(1, 5, 1)
 *   - root=1, equals q ✓
            *   - return 1
            *
            * Back to Step 1:
            *   - left = 5, right = 1
            *   - Both non-null ✓
            *   - return 3 (current root is LCA)
            *
            * RESULT: LCA(5, 1) = 3
            */

    /*
     * EXECUTION EXAMPLE 2:
     *
     * Same tree, Find LCA of p=5 and q=4
     *
     * Call: lowestCommonAncestor(3, 5, 4)
     *
     * LCA(3, 5, 4):
     *   - left = LCA(5, 5, 4)
     *     - root=5, equals p ✓, return 5
     *   - right = LCA(1, 5, 4)
     *     - LCA(0, 5, 4): return null
     *     - LCA(8, 5, 4): return null
     *     - left=null, right=null, return null
     *   - left=5, right=null
     *   - return 5
     *
     * RESULT: LCA(5, 4) = 5
     * (5 is ancestor of 4, and a node can be ancestor of itself)
     */

    public Node lowestCommonAncestorr(Node root, Node p, Node q) {
        if(root == null || root == p || root == q) return root;

        Node left = lowestCommonAncestorr(root.left , p , q);
        Node right = lowestCommonAncestorr(root.right , p , q);

        if(left != null && right != null) return root;
        return left != null ? left : right;

    }

    public static void flatten(Node root) {
        if (root == null) return;

        Node lst = root.left;
        Node rst = root.right;

        root.left = null;
        root.right = null;

        flatten(lst);
        flatten(rst);

        root.right = lst;

        Node last = root;
        while (last.right != null) {
            last = last.right;
        }
        last.right = rst;
    }

    public List<Integer> IterativePreOrder(Node root){
        List<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node top = stack.pop();
            ans.add(top.val);
            if(top.right != null) stack.push(top.right);
            if(top.left != null) stack.push(top.left);
        }
        return ans;
    }

    public ArrayList<Integer> inOrder(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while(!stack.isEmpty() || curr != null){
            if(curr != null){
                if(curr.left != null){
                    stack.push(curr);
                    curr = curr.left;
                }else{
                    ans.add(curr.val);
                    curr = curr.right;
                }
            }else{
                Node top = stack.pop();
                ans.add(top.val);
                curr = top.right;
            }
        }
        return ans;
    }

    ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(root == null) return ans;

        ans.add(root.val); // root

        leftBoundary(root.left , ans);
        leafNode(root.left , ans);
        leafNode(root.right , ans);
        rightBoundary(root.right , ans);

        return ans;
    }

    private void leftBoundary(Node root , ArrayList<Integer> ans){
        if(root == null) return;
        if(root.left == null && root.right == null) return;

        ans.add(root.val);

        if(root.left != null) leftBoundary(root.left , ans);
        else leftBoundary(root.right , ans);
    }

    private void leafNode(Node root , ArrayList<Integer> ans){
        if(root == null) return;

        if(root.left == null && root.right == null){
            ans.add(root.val);
            return;
        }

        leafNode(root.left , ans);
        leafNode(root.right , ans);
    }

     private void rightBoundary(Node root , ArrayList<Integer> ans){
        if(root == null) return;
        if(root.left == null && root.right == null) return;

        if(root.right != null) rightBoundary(root.right , ans);
        else rightBoundary(root.left , ans);

        ans.add(root.val); // reverse order
    }
}
