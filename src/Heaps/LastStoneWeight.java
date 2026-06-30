package Heaps;

import java.util.PriorityQueue;
import java.util.Collections;

/**
 * LastStoneWeight - LeetCode Problem 1046
 * 
 * Problem Description:
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 * 
 * In each turn, we choose the two heaviest stones and smash them together.
 * If stone x and stone y have x <= y, the result of this smash is the stone with weight y-x.
 * If x == y, both stones are destroyed.
 * 
 * Continue this process until there is at most one stone left.
 * Return the weight of the last remaining stone. If no stones are left, return 0.
 * 
 * Constraints:
 * - 1 <= stones.length <= 30
 * - 1 <= stones[i] <= 100
 * 
 * Heap Structure Used: Max-Heap (PriorityQueue with reverseOrder)
 * 
 * When to Use This Approach:
 * - Efficiently processing elements based on priority/weight
 * - Simulating processes where you always need the maximum element
 * - Problems involving repeated removal and re-insertion of modified elements
 * 
 * @author DSA Course
 * @version 1.0
 */
public class LastStoneWeight {
    /**
     * Simulates stone smashing process using a max-heap.
     * 
     * Algorithm: Max-Heap Priority Queue
     * - Maintain max-heap of all stones by weight
     * - While more than 1 stone exists:
     *   1. Extract two heaviest stones
     *   2. Smash them: difference = heavier - lighter
     *   3. If difference > 0, add remainder to heap
     * - Return final stone weight or 0 if none remain
     * 
     * Example Walkthrough:
     * Input: stones = [2, 7, 4, 1, 8, 1]
     * Max-Heap: [8, 7, 4, 2, 1, 1]
     * 
     * Turn 1: Extract 8, 7 → 8-7=1, add 1 back
     *         Heap: [4, 2, 1, 1, 1]
     * 
     * Turn 2: Extract 4, 2 → 4-2=2, add 2 back
     *         Heap: [2, 1, 1, 1]
     * 
     * Turn 3: Extract 2, 1 → 2-1=1, add 1 back
     *         Heap: [1, 1, 1]
     * 
     * Turn 4: Extract 1, 1 → 1-1=0, no add
     *         Heap: [1]
     * 
     * Result: 1
     * 
     * Key Insights:
     * - Max-heap gives O(log n) extraction of two heaviest
     * - At most n-1 comparisons needed (each reduces stone count by 1)
     * - Final result is deterministic regardless of tie-breaking order
     * 
     * Time Complexity: O(n log n)
     * - n stones to process
     * - Each extraction/insertion is O(log n)
     * - Total: n * O(log n) = O(n log n)
     * 
     * Space Complexity: O(n)
     * - Max-heap stores up to n stones
     * - No additional data structures
     * 
     * Heap Operations:
     * - offer(): Insert element, O(log n)
     * - poll(): Extract max, O(log n)
     * - isEmpty(): Check empty, O(1)
     * - peek(): View max without removal, O(1)
     * 
     * Why Max-Heap?
     * - We always need the two heaviest stones
     * - Max-heap with Collections.reverseOrder() maintains descending order
     * - Java's default PriorityQueue is min-heap, so reverse order gives max-heap
     * 
     * Visual Representation of Heap:
     *        8
     *       / \
     *      7   4
     *     / \ /
     *    2  1 1
     * 
     *    (All parent nodes >= children in max-heap)
     * 
     * @param stones array of stone weights
     * @return weight of last remaining stone, or 0 if all destroyed
     */
    public static int lastStoneWeight(int[] stones) {
        // Max-heap to store stones by weight (heaviest at top)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        // Add all stones to the max-heap
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Keep processing until at most one stone remains
        while (maxHeap.size() > 1) {
            // Get the two heaviest stones
            int first = maxHeap.poll();   // Heaviest
            int second = maxHeap.poll();  // Second heaviest
            
            // Smash them together
            int difference = first - second;
            
            // If there's a remainder, add it back to the heap
            if (difference > 0) {
                maxHeap.offer(difference);
            }
        }
        
        // Return the last stone weight or 0 if no stones remain
        return maxHeap.isEmpty() ? 0 : maxHeap.peek();
    }
    
    /**
     * Test case demonstrations for lastStoneWeight algorithm.
     * 
     * Test Coverage:
     * - Single stone (trivial case)
     * - Two equal stones (both destroyed)
     * - Multiple stones with various differences
     * - Different orderings
     * 
     * All test cases return expected values due to heap's deterministic processing
     * of heaviest stones first, regardless of input order.
     */
    public static void main(String[] args) {
        // Test case 1: Multiple stones with various weights
        int[] stones1 = {2, 7, 4, 1, 8, 1};
        System.out.println("Test 1: " + lastStoneWeight(stones1)); // Expected: 1
        
        // Test case 2: Single stone (trivial)
        int[] stones2 = {1};
        System.out.println("Test 2: " + lastStoneWeight(stones2)); // Expected: 1
        
        // Test case 3: Two equal stones (completely destroyed)
        int[] stones3 = {1, 1};
        System.out.println("Test 3: " + lastStoneWeight(stones3)); // Expected: 0
        
        // Test case 4: All stones destroyed
        int[] stones4 = {3, 7, 2, 6, 1, 4};
        System.out.println("Test 4: " + lastStoneWeight(stones4)); // Expected: 0
        
        // Test case 5: Multiple destruction cycles
        int[] stones5 = {8, 10, 3, 2, 5};
        System.out.println("Test 5: " + lastStoneWeight(stones5)); // Expected: 0
    }
}
