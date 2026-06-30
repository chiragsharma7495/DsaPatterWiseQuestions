package Heaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

//import static java.nio.file.Files.size;

/**
 * Helper class to store element-frequency pair for heap operations.
 * 
 * Comparable Implementation: Sorts by frequency (ascending), ties broken by element value.
 * This creates a min-heap based on frequency, allowing efficient tracking of top-k frequent elements.
 * 
 * Usage: Used in topKFreq() to maintain min-heap of k most frequent elements.
 * 
 * Comparator Logic:
 * - Primary: Sort by frequency (lower frequency comes first in min-heap)
 * - Secondary: If frequencies equal, sort by element value (natural ordering)
 */
class pair implements Comparable<pair>{
    int ele; 
    int freq;

    /**
     * Constructs a frequency pair.
     * @param ele the element value
     * @param freq the frequency count of this element
     */
    pair(int ele , int freq){
        this.ele = ele;
        this.freq = freq;
    }

    /**
     * Compares two pairs for heap ordering.
     * Min-heap: lower frequency has higher priority (comes first).
     * 
     * @param p another pair to compare with
     * @return negative if this < p (this has lower frequency or lower element value)
     */
    public int compareTo(pair p){
        if(this.freq == p.freq) return this.ele - p.ele;
        return this.freq - p.freq;
    }
}

/**
 * Binary tree node for heap validation and conversion problems.
 * 
 * Used in:
 * - isHeap(): Validates if a binary tree is a max heap
 * - convertToMaxHeapUtil(): Converts binary search tree to max heap
 */
class Node {
    int data;
    Node left, right;

    /**
     * Constructs a binary tree node with given data.
     * @param d the data value for this node
     */
    Node(int d){
        data = d;
        left = right = null;
    }
}


/**
 * HeapsPart1 - Comprehensive Heap Problem Solutions
 * 
 * This class demonstrates various heap-based algorithms and problem-solving techniques.
 * Heaps are complete binary trees where each node satisfies the heap property.
 * 
 * Heap Concepts:
 * - Min-Heap: Parent <= Children (smallest element at root)
 * - Max-Heap: Parent >= Children (largest element at root)
 * - Complete Binary Tree: All levels filled except possibly the last
 * - Time Complexity: O(log n) for insertion and deletion, O(1) for peek
 * 
 * When to Use Heaps:
 * - Finding k smallest/largest elements efficiently
 * - Priority-based processing (scheduling, Dijkstra's algorithm)
 * - Finding median in a stream
 * - Top K frequent elements
 * - Merge K sorted lists
 * - Huffman coding for compression
 * 
 * @author DSA Course
 * @version 1.0
 */
public class HeapsPart1 {

    /**
     * Finds the kth smallest element in an array.
     * 
     * Algorithm: Uses Max-Heap with size k
     * - Maintain a max-heap of size k containing k smallest elements
     * - For each new element, if heap size exceeds k, remove max (largest of k elements)
     * - Final heap root is the kth smallest element
     * 
     * Example:
     * Input: arr = [7, 10, 4, 3, 20, 15], k = 3
     * Process:
     *   - Add 7: heap = [7]
     *   - Add 10: heap = [10, 7]
     *   - Add 4: heap = [10, 7, 4]
     *   - Add 3: heap = [10, 7, 4, 3], size > k, remove 10 → [7, 4, 3]
     *   - Continue for remaining elements...
     * Output: 7 (3rd smallest)
     * 
     * Time Complexity: O(n log k) - n elements, each log k insertion/deletion
     * Space Complexity: O(k) - heap stores at most k elements
     * 
     * Heap Structure: Max-Heap of size k
     * 
     * @param arr input array
     * @param k position to find (1-indexed)
     * @return kth smallest element
     */
    public int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int ele : arr){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        return pq.peek();
    }

    /**
     * Finds the kth largest element in an array.
     * 
     * Algorithm: Uses Min-Heap with size k
     * - Maintain a min-heap of size k containing k largest elements
     * - For each new element, if heap size exceeds k, remove min (smallest of k elements)
     * - Final heap root is the kth largest element
     * 
     * Example:
     * Input: arr = [3, 2, 1, 5, 6, 4], k = 2
     * Process:
     *   - Add 3: heap = [3]
     *   - Add 2: heap = [2, 3]
     *   - Add 1: heap = [1, 3, 2], size > k, remove 1 → [2, 3]
     *   - Add 5: heap = [2, 3, 5], size > k, remove 2 → [3, 5]
     *   - Add 6: heap = [3, 5, 6], size > k, remove 3 → [5, 6]
     *   - Add 4: heap = [4, 6, 5], size > k, remove 4 → [5, 6]
     * Output: 5 (2nd largest)
     * 
     * Time Complexity: O(n log k) - n elements, each log k insertion/deletion
     * Space Complexity: O(k) - heap stores at most k elements
     * 
     * Heap Structure: Min-Heap of size k
     * 
     * @param arr input array
     * @param k position to find (1-indexed)
     * @return kth largest element
     */
    public int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : arr){
            pq.add(ele);
            if(pq.size() > k) pq.remove();
        }
        return pq.peek();
    }

    /**
     * Sorts a nearly sorted array where each element is at most k positions away from its target.
     * 
     * Algorithm: Maintains a Min-Heap of size k+1
     * - Since each element is at most k positions from target, the correct smallest element
     *   is guaranteed to be within the first k elements
     * - Keep heap of size <= k, extract minimum when heap exceeds k size
     * - Extract remaining elements after all input is processed
     * 
     * Example:
     * Input: arr = [6, 5, 3, 2, 8, 10, 9], k = 3
     * Explanation: 6 should be at index 4-5, 5 at index 3-4, etc. (max 3 positions away)
     * Process:
     *   - Add 6: heap = [6]
     *   - Add 5: heap = [5, 6]
     *   - Add 3: heap = [3, 6, 5]
     *   - Add 2: heap = [2, 6, 5, 3], size > k, arr[0] = 2 → heap = [3, 5, 6]
     *   - Continue extracting and filling...
     * Output: [2, 3, 5, 6, 8, 9, 10] (sorted)
     * 
     * Time Complexity: O(n log k) - n elements, each log k heap operations
     * Space Complexity: O(k) - heap stores at most k+1 elements
     * 
     * Heap Structure: Min-Heap
     * Use Case: Nearly sorted arrays, real-time data streams
     * 
     * @param arr array to sort in-place, nearly sorted with k distance guarantee
     * @param k maximum distance each element can be from its sorted position
     */
    public void nearlySorted(int[] arr, int k) {
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : arr){
            pq.add(ele);
            if(pq.size() > k){
                arr[idx++] = pq.remove();
            }
        }
        while(!pq.isEmpty()) arr[idx++] = pq.remove();
    }

    /**
     * Finds the minimum cost to connect all ropes/sticks together.
     * 
     * LeetCode Problem: 1167 - Minimum Cost to Connect Sticks
     * 
     * Algorithm: Always connect the two smallest elements
     * - Every connection adds a new element (combined weight) to the remaining set
     * - To minimize total cost, always merge the two smallest weights
     * - Use Min-Heap to efficiently get the two smallest elements
     * 
     * Example:
     * Input: sticks = [2, 4, 3]
     * Process (Min-Heap approach):
     *   - Heap = [2, 4, 3] → [2, 3, 4]
     *   - Connect 2 + 3 = 5, cost += 5, heap = [4, 5]
     *   - Connect 4 + 5 = 9, cost += 9, heap = [9]
     *   - Total cost = 14
     * 
     * Greedy Proof: Connecting smallest elements first minimizes intermediate sums,
     * which are added back to the heap and participate in future connections.
     * 
     * Time Complexity: O(n log n) - n-1 merges, each takes O(log n) heap operations
     * Space Complexity: O(n) - heap stores all elements
     * 
     * Heap Structure: Min-Heap
     * 
     * @param arr array of rope/stick lengths
     * @return minimum total cost to connect all ropes
     */
    public static int minCost(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int ele : arr){
            pq.add(ele);
        }
        int cost = 0;

        while(pq.size() > 1){
            int first = pq.poll();
            int second = pq.poll();

            int sum = first + second;
            cost += sum;

            pq.offer(sum);
        }
        return cost;
    }

    /**
     * Finds the k most frequent elements in an array.
     * 
     * LeetCode Problem: 347 - Top K Frequent Elements
     * 
     * Algorithm: Min-Heap based on frequency
     * - Count frequency of all elements using HashMap
     * - Maintain a min-heap of size k with elements sorted by frequency
     * - For each unique element, if heap size exceeds k, remove element with min frequency
     * - Elements with min frequency in the k-heap are the top k frequent elements
     * 
     * Example:
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Process:
     *   - Frequencies: {1: 3, 2: 2, 3: 1}
     *   - Build heap with pair(element, frequency):
     *     - Add pair(1, 3): heap = [pair(1, 3)]
     *     - Add pair(2, 2): heap = [pair(2, 2), pair(1, 3)]
     *     - Add pair(3, 1): heap = [pair(1, 3), pair(3, 1), pair(2, 2)], size > k, remove min → [pair(2, 2), pair(1, 3)]
     * Output: [1, 2] (most frequent 2 elements)
     * 
     * Time Complexity: O(n log k) - n unique elements, each log k heap ops + HashMap iteration
     * Space Complexity: O(n) - HashMap stores all unique elements, heap stores k elements
     * 
     * Heap Structure: Min-Heap based on frequency (custom comparator in pair class)
     * 
     * @param arr input array
     * @param k number of most frequent elements to return
     * @return list of k most frequent elements
     */
    public ArrayList<Integer> topKFreq(int[] arr, int k) {
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int ele : arr){
            map.put(ele , map.getOrDefault(ele , 0) +1);
        }

        PriorityQueue<pair> pq = new PriorityQueue<>();
        for(int ele : map.keySet()){
            int freq = map.get(ele);
            pq.add(new pair(ele , freq));
            if(pq.size() > k) pq.remove();
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while(pq.size() > 0){
            pair top = pq.remove();
            ans.add(top.ele);
        }
        Collections.reverse(ans);
        return ans;
    }

    static  int s;
    /**
     * Validates if a binary tree is a max heap.
     * 
     * Algorithm: Check two conditions
     * 1. Max Heap Property: Each parent >= both children (recursively)
     * 2. Complete Binary Tree (CBT): All levels filled except possibly the last,
     *    which is filled from left to right
     * 
     * Example:
     * Valid Max Heap:
     *       10
     *      /  \
     *     9    8
     *    / \  /
     *   7  6 5
     * 
     * Invalid (not max heap, 8 > 10):
     *       10
     *      /  \
     *     9    8    <- Violates max heap property
     * 
     * Invalid (not CBT, gap in last level):
     *       10
     *      /  \
     *     9    8
     *    /      \
     *   7        6  <- Violates CBT property (gap)
     * 
     * Time Complexity: O(n) - visits all nodes once
     * Space Complexity: O(h) - recursion stack, h is height
     * 
     * Heap Structure: Max-Heap
     * 
     * @param root root node of binary tree
     * @return true if tree is a valid max heap, false otherwise
     */
    public boolean isHeap(Node root) {
        s = size(root);
        return isMaxHeap(root) && isCBT(root, 1);
    }

    /**
     * Calculates the total number of nodes in the binary tree.
     * 
     * @param root root node
     * @return number of nodes in tree
     */
    private int size(Node root) {
        if(root == null) return 0;
        return 1 + size(root.left) + size(root.right);
    }

    /**
     * Recursively checks if binary tree satisfies max heap property.
     * Every node must be >= its children.
     * 
     * @param root current node
     * @return true if subtree satisfies max heap property
     */
    private static boolean isMaxHeap(Node root){
        if(root == null) return true;
        int leftVal = (root.left != null) ? root.left.data : Integer.MIN_VALUE;
        int rightVal = (root.right != null) ? root.right.data : Integer.MIN_VALUE;
        if(root.data <= leftVal || root.data <= rightVal) return false;
        return isMaxHeap(root.left) && isMaxHeap(root.right);
    }

    /**
     * Checks if binary tree is a Complete Binary Tree (CBT).
     * Uses array indexing: for node at index i, left child is 2*i, right child is 2*i+1.
     * If any index exceeds total node count, tree is not a CBT.
     * 
     * @param root current node
     * @param idx index of current node in array representation
     * @return true if subtree forms a valid CBT
     */
    private static boolean isCBT(Node root , int idx){
        if(root == null) return true;
        if(idx > s) return false;
        return isCBT(root.left , 2*idx) && isCBT(root.right , 2*idx+1);
    }

    static int idx;
    /**
     * Converts a binary search tree to a max heap in-place.
     * 
     * Algorithm: In-Order Traversal + Post-Order Placement
     * - In-order traversal of BST gives sorted sequence
     * - Post-order traversal places nodes such that subtrees are filled level by level
     * - Place sorted elements back in post-order sequence to create max heap
     * 
     * Example:
     * BST:        Binary Tree (after conversion):
     *     4              10
     *    / \            /  \
     *   2   6          9    8
     *  / \ / \        / \  /
     * 1  3 5  7      7  6 5
     * 
     * In-order gives: [1, 2, 3, 4, 5, 6, 7] (sorted)
     * Post-order placement fills tree level by level for max heap.
     * 
     * Time Complexity: O(n) - traverse all nodes three times (inorder + postorder)
     * Space Complexity: O(n) - ArrayList for sorted elements + O(h) recursion
     * 
     * @param root root of tree to convert
     */
    public static void convertToMaxHeapUtil(Node root) {
        idx = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        inorder(root , arr);
        postOrder(root , arr);
    }

    /**
     * Post-order traversal to place sorted elements back into tree.
     * Post-order (Left, Right, Root) ensures subtrees are filled first,
     * then the root gets its position, creating proper complete binary tree structure.
     * 
     * @param root current node
     * @param arr sorted array of values
     */
    private static void postOrder(Node root, ArrayList<Integer> arr) {
        if(root == null) return;
        postOrder(root.left, arr);
        postOrder(root.right, arr);
        root.data = arr.get(idx++);
    }

    /**
     * In-order traversal to collect all elements in sorted order.
     * For BST, in-order traversal gives ascending order sequence.
     * 
     * @param root current node
     * @param arr list to store elements
     */
    private static void inorder(Node root, ArrayList<Integer> arr) {
        if(root == null) return;
        inorder(root.left , arr);
        arr.add(root.data);
        inorder(root.right ,arr);
    }

    public static void main(String[] args) {

    }
}
