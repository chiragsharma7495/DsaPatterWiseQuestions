package ArraysAndStrings;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Monotonic Stack and Monotonic Queue Problems
 * 
 * This class demonstrates the monotonic stack pattern - a technique where a stack maintains
 * elements in either increasing or decreasing order. Used to efficiently solve "next greater/smaller element"
 * and similar range-query problems in O(n) time.
 * 
 * Key Concept: As you iterate through array, pop elements from stack that don't maintain monotonicity,
 * which allows you to find relationships (greater, smaller, closer) between elements efficiently.
 */
public class Monotonic_Stack__Queue {

    /**
     * LeetCode 496 - Next Greater Element I
     * 
     * Given two arrays nums1 and nums2 (nums1 is subset of nums2), for each element in nums1,
     * find the next greater element in nums2. If it doesn't exist, return -1.
     * 
     * Approach: Monotonic Stack
     * 1. Iterate nums2 from right to left
     * 2. For each element, pop all stack elements ≤ current (they can't be next greater for anything)
     * 3. Stack top (if exists) is the next greater element
     * 4. Push current element and store mapping
     * 5. Use HashMap to answer queries from nums1
     * 
     * Example: nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
     *          4 → -1 (no element greater than 4)
     *          1 → 3 (3 is next greater)
     *          2 → -1 (no element greater than 2 after it)
     *          Output: [-1, 3, -1]
     * 
     * Time Complexity: O(n + m) where n = nums2.length, m = nums1.length
     * Space Complexity: O(n) for HashMap and stack
     * 
     * @param nums1 - Query array (subset of nums2)
     * @param nums2 - Source array where we find next greater elements
     * @return Array where result[i] is next greater element for nums1[i]
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer , Integer> nextGreater = new HashMap<>();

        Stack<Integer> stack = new Stack<>();

        for(int i=nums2.length-1; i>=0; i--){
            int current = nums2[i];

            while(!stack.isEmpty() && stack.peek() <= current){
                stack.pop();
            }

            if(!stack.isEmpty()){
                nextGreater.put(current , stack.peek());
            }else{
                nextGreater.put(current , -1);
            }
            stack.push(current);
        }

        int []result = new int[nums1.length];
        for(int i=0; i<nums1.length; i++){
            result[i] = nextGreater.get(nums1[i]);
        }
        return result;
    }

    /**
     * LeetCode 1475 - Final Prices With a Special Discount in a Shop
     * 
     * You are given an array of prices. A discount is applied if there exists an item
     * with a smaller or equal price to the right. The discount is the price of that item.
     * Find the final price after discount (or original price if no discount).
     * 
     * Approach: Monotonic Stack (decreasing order)
     * 1. Iterate from right to left
     * 2. Pop stack elements greater than current price (they don't qualify as discounts)
     * 3. If stack has element ≤ current, it's the discount amount
     * 4. Final price = current price - discount
     * 
     * Example: prices = [8, 4, 6, 2, 3]
     *          8 → discount 4 → 8-4 = 4
     *          4 → no discount → 4
     *          6 → discount 2 → 6-2 = 4
     *          2 → no discount → 2
     *          3 → no discount → 3
     *          Output: [4, 2, 4, 2, 3]
     * 
     * Time Complexity: O(n) where n is length of prices
     * Space Complexity: O(n) for stack
     * 
     * @param prices - Array of item prices
     * @return Array of final prices after discount
     */
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int result[] = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && stack.peek() > prices[i]){
                stack.pop();
            }

            if(!stack.isEmpty()){
                result[i] = prices[i] - stack.peek();
            }else{
                result[i] = prices[i];
            }
            stack.push(prices[i]);
        }
        return result;
    }

    /**
     * LeetCode 739 - Daily Temperatures
     * 
     * Given an array of daily temperatures, find for each day, how many days you have to wait
     * until a warmer temperature. If no future day has a warmer temperature, return 0.
     * 
     * Approach: Monotonic Stack (with indices)
     * 1. Maintain stack of INDICES in decreasing order of temperatures
     * 2. For each day, while current temp > stack top temp:
     *    - Pop the index from stack (found a warmer day)
     *    - answer[popped_index] = current_index - popped_index (days to wait)
     * 3. Push current index to stack
     * 
     * Example: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
     *          Day 0 (73°): Next warm in 1 day (74°) → 1
     *          Day 1 (74°): Next warm in 1 day (75°) → 1
     *          Day 2 (75°): Next warm in 3 days (76°) → 3
     *          Day 3 (71°): Next warm in 2 days (72°) → 2
     *          Day 4 (69°): Next warm in 1 day (72°) → 1
     *          Day 5 (72°): Next warm in 1 day (76°) → 1
     *          Day 6 (76°): No warmer day → 0
     *          Day 7 (73°): No warmer day → 0
     *          Output: [1, 1, 3, 2, 1, 1, 0, 0]
     * 
     * Time Complexity: O(n) - each element pushed and popped once
     * Space Complexity: O(n) for stack
     * 
     * @param temperatures - Array of daily temperatures
     * @return Array where each element is days until a warmer temperature
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int []answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]){
                int prevIndex = stack.pop();
                answer[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return answer;
    }


    public static void main(String[] args) {
        System.out.println("=== Monotonic Stack Examples ===\n");
        
        Monotonic_Stack__Queue msk = new Monotonic_Stack__Queue();
        
        // Example 1: Next Greater Element
        System.out.println("Example 1: Next Greater Element");
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result1 = msk.nextGreaterElement(nums1, nums2);
        System.out.println("nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]");
        System.out.print("Output: [");
        for (int i = 0; i < result1.length; i++) {
            System.out.print(result1[i] + (i < result1.length - 1 ? ", " : "]\n"));
        }
        
        // Example 2: Final Prices
        System.out.println("\nExample 2: Final Prices");
        int[] prices = {8, 4, 6, 2, 3};
        int[] result2 = msk.finalPrices(prices);
        System.out.println("prices = [8, 4, 6, 2, 3]");
        System.out.print("Output: [");
        for (int i = 0; i < result2.length; i++) {
            System.out.print(result2[i] + (i < result2.length - 1 ? ", " : "]\n"));
        }
        
        // Example 3: Daily Temperatures
        System.out.println("\nExample 3: Daily Temperatures");
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] result3 = msk.dailyTemperatures(temperatures);
        System.out.println("temperatures = [73, 74, 75, 71, 69, 72, 76, 73]");
        System.out.print("Output: [");
        for (int i = 0; i < result3.length; i++) {
            System.out.print(result3[i] + (i < result3.length - 1 ? ", " : "]\n"));
        }
    }
}
