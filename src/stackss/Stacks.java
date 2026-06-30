package stackss;

import java.util.ArrayList;
import java.util.Stack;

public class Stacks {

    /**
     * Inserts an element at the last position (bottom) of the stack using recursion.
     * 
     * Approach: Recursively pops elements from stack, inserts the new element at the bottom,
     * and then pushes all popped elements back on top.
     * 
     * Example: Stack [10, 20, 30] with element=5
     *          Result: [5, 10, 20, 30]
     * 
     * Time Complexity: O(n) where n is stack size
     * Space Complexity: O(n) for recursion stack
     * 
     * @param stack - Stack in which element is to be inserted
     * @param element - Element to be inserted at the bottom of stack
     */
    static void InsertElementAtLast(Stack<Integer> stack, int element) {
        if(stack.isEmpty()) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        InsertElementAtLast(stack , element);
        stack.push(top);
    }

    /**
     * Checks if parentheses/brackets in a string are balanced.
     * 
     * A string is balanced if:
     * - Every opening bracket has a corresponding closing bracket
     * - Brackets are in correct order (no crossing)
     * - Types match correctly: ( with ), { with }, [ with ]
     * 
     * Approach: Use stack to track opening brackets. When closing bracket found,
     * check if it matches the most recent opening bracket.
     * 
     * Example: "{[()]}" → true (balanced)
     *          "{[(])}" → false (brackets cross)
     * 
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for stack
     * 
     * @param s - String containing brackets to check
     * @return true if brackets are balanced, false otherwise
     */
    static boolean isBalanced(String s){
        Stack<Character> st = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') st.push(ch);
            else{
                if(st.isEmpty()) return false;
                char top = st.peek();
                if(sameStyle(top,ch)) st.pop();
                else return false;
            }
        }
        return st.isEmpty();
    }

    /**
     * Helper method to check if two brackets are of the same type and matching.
     * 
     * Checks if opening bracket 'a' matches with closing bracket 'b'.
     * 
     * @param a - Opening bracket: '(', '{', or '['
     * @param b - Closing bracket: ')', '}', or ']'
     * @return true if brackets match, false otherwise
     */
    private static boolean sameStyle(char a, char b) {
        if(a == '(' && b == ')') return true;

        if(a == '{' && b == '}') return true;

        if(a == '[' && b == ']') return true;

        return false;
    }

    /**
     * Removes all consecutive/adjacent duplicate characters from a string.
     * 
     * Approach: Use stack to track characters. Push character only if it's different
     * from the top of stack. This removes consecutive duplicates.
     * 
     * Example: "aabbcc" → "abc"
     *          "aabaa" → "aba" (only consecutive duplicates removed)
     *          "abbaca" → "ca"
     * 
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for stack
     * 
     * @param s - Input string with potential consecutive duplicates
     * @return String with consecutive duplicates removed
     */
    public String removeConsecutiveCharacter(String s) {
        Stack<Character> stack = new Stack<>();
        Stack<Character> stack2 = new Stack<>();
        for(int i=0; i<s.length(); i++){
           char currentEle = s.charAt(i);
           if(stack.isEmpty())stack.push(currentEle);
           else if(stack.peek() != currentEle)stack.push(currentEle);
        }

        while(!stack.isEmpty()){
            stack2.push(stack.pop());
        }
        StringBuilder result = new StringBuilder();
        while(!stack2.isEmpty()){
            result.append(stack2.pop());
        }

        return result.toString();
    }

    /**
     * Removes all adjacent duplicate characters from a string (LeetCode 1544).
     * 
     * Unlike removeConsecutiveCharacter, this removes duplicates recursively.
     * When two adjacent characters are removed, new adjacencies may form that also need removal.
     * 
     * Approach: Use stack. If current character equals top of stack, pop (remove both).
     * Otherwise, push current character.
     * 
     * Example: "abbaca" → "ca"
     *          Iteration: a→a, b→ab, b→a (pop), a→removed, c→c, a→ca
     * 
     * Time Complexity: O(n) where n is string length
     * Space Complexity: O(n) for stack
     * 
     * @param s - Input string with potential duplicate characters
     * @return String with all adjacent duplicates removed recursively
     */
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(!stack.isEmpty() && stack.peek() == ch) stack.pop();
            else{
                stack.push(ch);
            }
        }
        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()){
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    /**
     * Calculates total baseball game score based on special operations (LeetCode 682).
     * 
     * Operations:
     * - Number string: Add that score to record
     * - "C": Cancel last score (remove it)
     * - "D": Double the last score
     * - "+": Add sum of last two scores
     * 
     * Approach: Use stack to store scores. Process operations one by one.
     * 
     * Example: ["5","2","C","D","+"]
     *          5→[5], 2→[5,2], C→[5], D→[5,4], +→[5,4,9]
     *          Sum = 5+4+9 = 18
     * 
     * Time Complexity: O(n) where n is number of operations
     * Space Complexity: O(n) for stack
     * 
     * @param arr - Array of operations and scores
     * @return Total score after all operations
     */
    public int calPoints(String[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){
            String s = arr[i];
            if(s.equals("C")) stack.pop();
            else if(s.equals("D")) stack.push(2*stack.peek());
            else if(s.equals("+")){
                int top = stack.peek();
                stack.pop();
                int secTop = stack.peek();
                int sum = top + secTop;
                stack.push(top);
                stack.push(sum);
            }else{
                stack.push(Integer.parseInt(s));
            }
        }
        int sum = 0;
        while(!stack.isEmpty()){
            sum += stack.pop();
        }
        return sum;
    }

    /**
     * Finds the next larger element for each element in the array (LeetCode 496/1019).
     * 
     * For each element, find the next element to its right that is greater than it.
     * If no such element exists, return -1.
     * 
     * Approach: Iterate from right to left. Use stack to maintain elements in decreasing order.
     * When current element is smaller than stack top, stack top is the next larger element.
     * 
     * Example: [1, 5, 0, 3, 4, 5]
     *          1→5, 5→-1, 0→3, 3→4, 4→5, 5→-1
     *          Output: [5, -1, 3, 4, 5, -1]
     * 
     * Time Complexity: O(n) - each element pushed and popped once
     * Space Complexity: O(n) for stack and result array
     * 
     * @param arr - Input array
     * @return ArrayList where each element is next larger element or -1
     */
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(arr[n-1]);  // ✅ Push VALUE, not index
        int arrr[] = new int[n];
        arrr[n-1] = -1;  // ✅ Modify result array, not input

        for(int i=n-2; i>=0; i--){
            while(!stack.isEmpty() && arr[i] >= stack.peek()) stack.pop();
            if(stack.size() == 0) arrr[i] = -1;
            else arrr[i] = stack.peek();
            stack.push(arr[i]);
        }

        ArrayList<Integer> ans = new ArrayList<>(n);
        for(int i=0; i<n; i++){
            ans.add(arrr[i]);  // ✅ Add from result array
        }
        return ans;
    }

    /**
     * Finds the next greater element in a circular array (LeetCode 503).
     * 
     * Similar to nextLargerElement, but the array is circular - after the last element,
     * we wrap around to the first element.
     * 
     * Approach: Push all elements to stack in reverse order, then iterate twice through array
     * (to simulate circular behavior). Pop elements from stack that are smaller than current.
     * 
     * Example: [1, 2, 1]
     *          Circular: 1, 2, 1, 1, 2, 1...
     *          1→2, 2→-1, 1→1
     *          Output: [2, -1, 2]
     * 
     * Time Complexity: O(n) - each element processed once
     * Space Complexity: O(n) for stack
     * 
     * @param nums - Input circular array
     * @return Array where each element is next greater element or -1
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> stack = new Stack<>();
        int arr[] = new int[n];
        for(int i=n-1; i>=0; i--){
            stack.push(nums[i]);
        }

        for(int i=n-1; i>=0; i--){
            while(!stack.isEmpty() && nums[i] >= stack.peek()) stack.pop();
            if(stack.isEmpty()) arr[i] = -1;
            else arr[i] = stack.peek();
            stack.push(nums[i]);
        }
        return arr;
    }

    /**
     * Inner class Pair to store a value and its index in the array.
     * 
     * Used in stack-based problems where we need to track both the element value
     * and its position in the array.
     * 
     * Used in: calculateSpan() method
     */
    public class Pair{
        int val;   // Value of the element
        int idx;   // Index of the element

        /**
         * Constructor to create a Pair object.
         * 
         * @param val - Value of the element
         * @param idx - Index of the element in array
         */
        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    /**
     * Calculates the span of stock prices for each day (LeetCode 901).
     * 
     * Span of a price on a day is defined as the maximum number of consecutive days
     * just before the given day, where the price of the stock was less than or equal
     * to its price on the given day.
     * 
     * Approach: Use stack to store Pair(value, index). For each price, pop elements
     * from stack that are smaller than current price. The span is the difference
     * between current index and previous greater element's index.
     * 
     * Example: [100, 80, 60, 70, 60, 75, 85]
     *          Spans: [1, 1, 1, 2, 1, 4, 6]
     * 
     * Time Complexity: O(n) - each element pushed and popped once
     * Space Complexity: O(n) for stack
     * 
     * @param arr - Array of stock prices
     * @return ArrayList where each element is the span for that day
     */
    public ArrayList<Integer> calculateSpan(int[] arr) {
        int n = arr.length;
        Stack<Pair> stack = new Stack<>();
        int span[] = new int[n];
        span[0] = 1;
        stack.push(new Pair(arr[0] , 0));

        for(int i=1; i<n; i++){
            while(!stack.isEmpty() && stack.peek().val <= arr[i]) stack.pop();
            if(stack.size() == 0) span[i] = i-(-1);
            else span[i] = i - stack.peek().idx;
            stack.push(new Pair(arr[i] , i));
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<span.length; i++){
            ans.add(span[i]);
        }
        return ans;
    }

    /**
     * Counts how many people a person can see based on their heights (LeetCode 1944).
     * 
     * Given heights array where index 0 is the front (left) and last index is the back (right).
     * A person at position i can see position j if:
     * - All people between i and j are shorter than person at j
     * 
     * Approach: Iterate from back to front. Use stack to maintain people in decreasing height order.
     * Count how many people are shorter than current person (all popped from stack).
     * If someone taller is found, count them too.
     * 
     * Example: [10, 6, 8, 5, 11, 9]
     *          Person at index 0 (height 10) can see: 6, 8, 5 = 3 people
     *          Person at index 4 (height 11) can see: 9, 8, 6, 10 = 4 people
     * 
     * Time Complexity: O(n) - each person pushed and popped once
     * Space Complexity: O(n) for stack
     * 
     * @param arr - Array of heights from front to back
     * @return Array where each element is the count of people that person can see
     */
    public int[] canSeePersonsCount(int[] arr) {
        int n = arr.length;
        int ans[] = new int[n];
        Stack<Integer> stack = new Stack<>();
        ans[n-1] = 0;
        stack.push(arr[n-1]);

        for(int i=n-2; i>=0; i++) {
            int count = 0;
            while(stack.size() >= 0 && stack.peek() <= arr[i]){
                count++;
                stack.pop();
            }
            if(stack.size() > 0) count++;
            ans[i] = count;
            stack.push(arr[i]);
        }
        return ans;
    }

    /**
     * Finds the largest rectangle area that can be formed in a histogram (LeetCode 84).
     * 
     * Given heights of bars in a histogram, find the largest rectangular area that can
     * be formed using consecutive bars.
     * 
     * Approach: For each bar, find:
     * 1. Previous Smaller Element (PSE) - nearest smaller element to the left
     * 2. Next Smaller Element (NSE) - nearest smaller element to the right
     * 
     * The width of rectangle with this bar as height = NSE - PSE - 1
     * Area = height * width
     * 
     * Example: [2, 1, 5, 6, 2, 3]
     *          Bar 5 can form rectangle of height 5 with width 2 = area 10
     *          Bars 5,6 together form rectangle of height 5 with width 2 = area 10
     * 
     * Time Complexity: O(n) - each element pushed and popped once
     * Space Complexity: O(n) for stacks
     * 
     * @param arr - Array of histogram heights
     * @return Maximum rectangular area that can be formed
     */
    public int largestRectangleArea(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        
        // Find Next Smaller Element (NSE) for each bar
        int nse[] = new int [n];
        nse[n-1] = n;
        stack.push(n-1);

        for(int i=n-2; i>=0; i--){
            while(stack.size() > 0 && arr[stack.peek()] >= arr[i]) stack.pop();
            if(stack.size() == 0) nse[i] = n;
            else nse[i] = stack.peek();
            stack.push(i);
        }

        // Clear stack for next iteration
        while(stack.size() > 0) stack.pop();

        // Find Previous Smaller Element (PSE) for each bar
        int pse[] = new int[n];
        pse[0] = -1;
        stack.push(0);

        for(int i=1; i<n; i++){
            while(stack.size() > 0 && arr[stack.peek()] >= arr[i]) stack.pop();
            if(stack.size() == 0) pse[i] = -1;
            else pse[i] = stack.peek();
            stack.push(i);
        }

        // Calculate maximum area
        int maxArea = 0;
        for(int i=0; i<n; i++){
            int area = arr[i] * (nse[i] - pse[i] -1);
            maxArea = Math.max(maxArea , area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

    }
}
