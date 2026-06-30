package Queuess;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Queue Data Structure Implementation using LinkedList.
 * 
 * <p><b>Overview:</b> This class implements various Queue operations and algorithms.
 * A Queue follows the <b>FIFO (First In First Out)</b> principle, where elements 
 * are added at the rear and removed from the front.
 * 
 * <p><b>Queue Visualization:</b>
 * <pre>
 *     Front → [1 | 2 | 3 | 4 | 5] ← Rear
 *     remove() from front    add() at rear
 * </pre>
 * 
 * <p><b>Core Operations:</b>
 * <ul>
 *   <li><b>add(E):</b> Insert element at rear - O(1)</li>
 *   <li><b>remove():</b> Remove element from front - O(1)</li>
 *   <li><b>peek():</b> View front element - O(1)</li>
 *   <li><b>poll():</b> Remove and return front element - O(1)</li>
 * </ul>
 * 
 * <p><b>Advanced Operations Implemented:</b>
 * <ul>
 *   <li>Display queue elements</li>
 *   <li>Insert at specific index</li>
 *   <li>Peek at specific index</li>
 *   <li>Remove at specific index</li>
 *   <li>Reverse entire queue</li>
 *   <li>Reverse first K elements</li>
 *   <li>Find winner in Josephus problem</li>
 * </ul>
 * 
 * @author DSA Implementation
 * @version 1.0
 */
public class Queues {

    /**
     * Displays all elements in the queue without modifying it.
     * 
     * <p><b>Algorithm:</b>
     * The method rotates the queue by removing from front and adding to rear,
     * printing each element before rotation. After the loop completes, the queue
     * is restored to its original state.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Get the size of queue (n)</li>
     *   <li>For each element (n times):
     *     <ul>
     *       <li>Print the front element using peek()</li>
     *       <li>Rotate: remove from front and add to rear</li>
     *     </ul>
     *   </li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Input Queue: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   
     *   Execution:
     *   Step 1: Print 10, rotate → [20 | 30 | 40 | 50 | 10]
     *   Step 2: Print 20, rotate → [30 | 40 | 50 | 10 | 20]
     *   Step 3: Print 30, rotate → [40 | 50 | 10 | 20 | 30]
     *   Step 4: Print 40, rotate → [50 | 10 | 20 | 30 | 40]
     *   Step 5: Print 50, rotate → [10 | 20 | 30 | 40 | 50]
     *   
     *   Output:
     *   10 
     *   20 
     *   30 
     *   40 
     *   50 
     *   
     *   Final Queue: [10 ← front | 20 | 30 | 40 | 50 ← rear] (unchanged)
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We iterate through each element and perform constant operations.
     * 
     * <p><b>Space Complexity:</b> O(1), only using the input queue.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Empty queue:</b> Loop doesn't execute; nothing is printed</li>
     *   <li><b>Single element:</b> Prints the element and queue remains unchanged</li>
     * </ul>
     * 
     * @param q the queue to display (must not be null)
     * @throws NullPointerException if q is null
     */
    private static void Display(Queue<Integer> q){
        int n = q.size();
        for(int i=0; i<n; i++){
            System.out.println(q.peek() + " ");
            q.add(q.remove());
        }
    }

    /**
     * Inserts an element at a specific index in the queue.
     * 
     * <p><b>Algorithm:</b>
     * The method rotates elements to bring the target index to the front,
     * inserts the value, then rotates remaining elements back to restore order.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Get the current size (n)</li>
     *   <li>Rotate queue idx times: remove from front and add to rear</li>
     *   <li>Add the new value at the front (which corresponds to index idx)</li>
     *   <li>Rotate remaining (n - idx) elements back to original positions</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Initial: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   Insert 99 at index 2
     *   
     *   Step 1: Rotate 2 times
     *   After 1st: [20 | 30 | 40 | 50 | 10]
     *   After 2nd: [30 | 40 | 50 | 10 | 20]
     *   
     *   Step 2: Add 99
     *   After add: [99 | 40 | 50 | 10 | 20 | 30]
     *   
     *   Step 3: Rotate remaining (5-2=3) times
     *   After 1st: [40 | 50 | 10 | 20 | 30 | 99]
     *   After 2nd: [50 | 10 | 20 | 30 | 99 | 40]
     *   After 3rd: [10 | 20 | 30 | 99 | 40 | 50]
     *   
     *   Final: [10 ← front | 20 | 30 | 99 | 40 | 50 ← rear]
     *   Index: 0     1    2   3   4   5
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We perform rotation operations on all elements (idx + (n-idx) = n rotations).
     * 
     * <p><b>Space Complexity:</b> O(1), no extra data structures used.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Index 0:</b> Add at front - queue becomes [val | original_queue]</li>
     *   <li><b>Index = n-1:</b> Add at end - requires full rotation</li>
     *   <li><b>Single element (n=1):</b> Handles correctly for idx=0 and idx=1 (invalid)</li>
     *   <li><b>Note:</b> No validation of idx bounds; caller must ensure 0 ≤ idx ≤ n</li>
     * </ul>
     * 
     * @param q the queue to modify (must not be null)
     * @param val the value to insert
     * @param idx the index where value should be inserted (0 ≤ idx ≤ size)
     * @throws NullPointerException if q is null
     */
    private static void addAtSpecificIndex( Queue<Integer> q , int val , int idx){
        int n = q.size();
            for (int i = 0; i < idx; i++) {
                q.add(q.remove());
            }
        q.add(val);
        for(int i=0; i<n-idx; i++){
            q.add(q.remove());
        }
    }

    /**
     * Retrieves the element at a specific index without removing it.
     * 
     * <p><b>Algorithm:</b>
     * The method rotates the queue to bring the target index to the front,
     * peeks at it, then rotates back to restore the original order.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Validate queue is not empty and index is within bounds [0, n-1]</li>
     *   <li>Rotate queue idx times: remove from front and add to rear</li>
     *   <li>The element at index idx is now at the front - return via peek()</li>
     *   <li>Rotate remaining (n - idx) elements back to restore order</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Input: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   peekAtSpecificIndex(q, 2)  → returns 30
     *   
     *   Step 1: Rotate 2 times to bring index 2 to front
     *   After 1st: [20 | 30 | 40 | 50 | 10]
     *   After 2nd: [30 | 40 | 50 | 10 | 20]
     *   
     *   Step 2: Peek at front → 30
     *   
     *   Step 3: Rotate remaining (5-2=3) times to restore
     *   After 1st: [40 | 50 | 10 | 20 | 30]
     *   After 2nd: [50 | 10 | 20 | 30 | 40]
     *   After 3rd: [10 | 20 | 30 | 40 | 50]
     *   
     *   Final: [10 ← front | 20 | 30 | 40 | 50 ← rear] (restored)
     *   Return: 30
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We rotate idx elements forward and (n-idx) elements backward.
     * 
     * <p><b>Space Complexity:</b> O(1), no extra data structures used.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Empty queue:</b> Throws IllegalArgumentException</li>
     *   <li><b>Negative index:</b> Throws IllegalArgumentException</li>
     *   <li><b>Index >= n:</b> Throws IllegalArgumentException</li>
     *   <li><b>Index 0:</b> Returns front element after no rotation</li>
     *   <li><b>Index n-1:</b> Returns rear element after n-1 rotations</li>
     * </ul>
     * 
     * @param q the queue to peek into (must not be null)
     * @param idx the index to peek at (0 ≤ idx < size)
     * @throws NullPointerException if q is null
     * @throws IllegalArgumentException if queue is empty or index is out of bounds
     */
    private static void peekAtSpecificIndex(Queue<Integer> q, int idx) {
    int n = q.size();

    if (n == 0 || idx < 0 || idx >= n) {
        throw new IllegalArgumentException("Invalid index");
    }

    for (int i = 0; i < idx; i++) {
        q.add(q.remove());
    }

    for (int i = 0; i < n - idx; i++) {
        q.add(q.remove());
    }
}

    /**
     * Removes the element at a specific index from the queue.
     * 
     * <p><b>Algorithm:</b>
     * The method rotates elements to bring the target index to the front,
     * removes it, then rotates remaining elements back to restore order.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Validate queue is not empty and index is within bounds [0, n-1]</li>
     *   <li>Rotate queue idx times: remove from front and add to rear</li>
     *   <li>Remove the front element (which corresponds to index idx)</li>
     *   <li>Rotate remaining (n - idx - 1) elements back to restore order</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Initial: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   removeAtSpecificIndex(q, 2)  → removes 30
     *   
     *   Step 1: Rotate 2 times
     *   After 1st: [20 | 30 | 40 | 50 | 10]
     *   After 2nd: [30 | 40 | 50 | 10 | 20]
     *   
     *   Step 2: Remove front (30)
     *   After remove: [40 | 50 | 10 | 20]
     *   
     *   Step 3: Rotate remaining (5-2-1=2) times
     *   After 1st: [50 | 10 | 20 | 40]
     *   After 2nd: [10 | 20 | 40 | 50]
     *   
     *   Final: [10 ← front | 20 | 40 | 50 ← rear]
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We perform idx + (n-idx-1) + 1 operations = n total operations.
     * 
     * <p><b>Space Complexity:</b> O(1), no extra data structures used.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Empty queue:</b> Throws IllegalArgumentException</li>
     *   <li><b>Negative index:</b> Throws IllegalArgumentException</li>
     *   <li><b>Index >= n:</b> Throws IllegalArgumentException</li>
     *   <li><b>Index 0:</b> Removes front element directly</li>
     *   <li><b>Index n-1:</b> Removes rear element after n-1 rotations</li>
     *   <li><b>Single element queue:</b> Removes the element, queue becomes empty</li>
     * </ul>
     * 
     * @param q the queue to modify (must not be null)
     * @param idx the index of element to remove (0 ≤ idx < size)
     * @throws NullPointerException if q is null
     * @throws IllegalArgumentException if queue is empty or index is out of bounds
     */
     private static void removeAtSpecificIndex(Queue<Integer> q , int idx){
        int n = q.size();

        if(n == 0 || idx < 0 || idx >= n){
            throw new IllegalArgumentException("Invalid index");
        }

        for(int i=0; i<idx; i++){
            q.add(q.remove());
        }
        q.remove(q.peek());

        for(int i=0; i<n-idx-1; i++){
            q.add(q.remove());
        }
}

    /**
     * Reverses all elements in the queue.
     * 
     * <p><b>Algorithm:</b>
     * Uses a stack as an auxiliary data structure. Elements are popped from the queue
     * and pushed onto the stack (reversing their order), then popped from the stack
     * and added back to the queue.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Create an empty stack</li>
     *   <li>Pop all elements from queue and push onto stack
     *       (This reverses the order: FIFO becomes LIFO)</li>
     *   <li>Pop all elements from stack and add back to queue
     *       (Elements are now in reversed order)</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Initial Queue: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   
     *   Step 1: Transfer queue to stack
     *   After each poll() and push():
     *   Step 1a: Poll 10, Push 10 → Stack: [10]
     *   Step 1b: Poll 20, Push 20 → Stack: [10, 20]
     *   Step 1c: Poll 30, Push 30 → Stack: [10, 20, 30]
     *   Step 1d: Poll 40, Push 40 → Stack: [10, 20, 30, 40]
     *   Step 1e: Poll 50, Push 50 → Stack: [10, 20, 30, 40, 50]
     *   Queue is now empty
     *   
     *   Step 2: Transfer stack back to queue
     *   After each pop() and add():
     *   Step 2a: Pop 50, Add 50 → Queue: [50]
     *   Step 2b: Pop 40, Add 40 → Queue: [50, 40]
     *   Step 2c: Pop 30, Add 30 → Queue: [50, 40, 30]
     *   Step 2d: Pop 20, Add 20 → Queue: [50, 40, 30, 20]
     *   Step 2e: Pop 10, Add 10 → Queue: [50, 40, 30, 20, 10]
     *   
     *   Final Queue: [50 ← front | 40 | 30 | 20 | 10 ← rear] (reversed!)
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We push each element once (O(n)) and pop each element once (O(n)) = O(2n) = O(n).
     * 
     * <p><b>Space Complexity:</b> O(n), we use a stack to store all n elements.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Empty queue:</b> Loop doesn't execute; queue remains empty</li>
     *   <li><b>Single element:</b> Element is pushed and popped back; queue unchanged</li>
     *   <li><b>Two elements [a, b]:</b> Becomes [b, a]</li>
     *   <li><b>Duplicate elements:</b> Handled correctly; all duplicates are reversed</li>
     * </ul>
     * 
     * @param q the queue to reverse (must not be null)
     * @throws NullPointerException if q is null
     */
    public static void reverseAQueue(Queue<Integer> q){
        Stack<Integer> stack = new Stack<>();
        int n = q.size();

        for(int i=0; i<n; i++){
            int push = stack.push(q.poll());
        }
        while(!stack.isEmpty()){
            q.add(stack.pop());
        }
    }

    /**
     * Reverses only the first K elements of the queue.
     * 
     * <p><b>Algorithm:</b>
     * Uses a stack to reverse the first k elements, then rotates the remaining
     * elements back to their original relative positions.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Validate: queue not null, k is positive and ≤ queue size</li>
     *   <li>Remove first k elements from queue and push onto stack</li>
     *   <li>Pop all elements from stack and add back to queue
     *       (First k elements are now reversed)</li>
     *   <li>Rotate remaining (n-k) elements: remove from front and add to rear
     *       to restore their order</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   Initial: [10 ← front | 20 | 30 | 40 | 50 ← rear]
     *   Reverse first K=3 elements
     *   
     *   Step 1: Remove first 3 and push to stack
     *   Remove 10, 20, 30 → Stack: [10, 20, 30], Queue: [40, 50]
     *   
     *   Step 2: Pop from stack and add to queue
     *   Pop 30, 20, 10 → Queue: [40, 50, 30, 20, 10]
     *   
     *   Step 3: Rotate remaining (5-3=2) elements
     *   Rotate 1st: [50, 30, 20, 10, 40]
     *   Rotate 2nd: [30, 20, 10, 40, 50]
     *   
     *   Final: [30 ← front | 20 | 10 | 40 | 50 ← rear]
     *   (First 3 elements reversed: 10,20,30 → 30,20,10)
     *   (Last 2 elements unchanged at end: 40, 50)
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n), where n is the size of the queue.
     * We push k elements (O(k)), pop k elements (O(k)), and rotate (n-k) elements (O(n-k))
     * = O(k + k + n - k) = O(n).
     * 
     * <p><b>Space Complexity:</b> O(k), we use a stack to store k elements.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>Queue is null:</b> Returns null</li>
     *   <li><b>k ≤ 0:</b> Returns original queue unchanged</li>
     *   <li><b>k > queue.size():</b> Returns original queue unchanged</li>
     *   <li><b>k = 1:</b> No reversal happens (single element)</li>
     *   <li><b>k = n:</b> Entire queue is reversed (like reverseAQueue())</li>
     *   <li><b>k = n-1:</b> First n-1 elements reversed; last element stays at end</li>
     * </ul>
     * 
     * @param q the queue to modify (can be null)
     * @param k the number of elements to reverse from the front (k > 0, k ≤ size)
     * @return the modified queue (q if valid input) or original q (if invalid)
     */
    public Queue<Integer> reverseFirstK(Queue<Integer> q, int k) {
        if (q == null || k > q.size() || k <= 0) {
            return q; // Return original queue
        }
        int n = q.size();

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            stack.push(q.remove());
        }

        while (!stack.isEmpty()) {
            q.add(stack.pop());
        }

        for (int i = 0; i < n - k; i++) {
            q.add(q.remove());
        }

        return q;
    }

    /**
     * Finds the winner in the Josephus problem variant.
     * 
     * <p><b>Problem Description:</b>
     * Given n people sitting in a circle (numbered 1 to n), starting from person 1,
     * count k people clockwise. The k-th person is eliminated. Repeat this process
     * until only one person remains. Find the survivor's number.
     * 
     * <p><b>Algorithm:</b>
     * Uses a queue to simulate the elimination process. Queue represents people
     * standing in a circle. We skip (k-1) people by rotating them to the back,
     * then eliminate the person at the front. Repeat until one person remains.
     * 
     * <p><b>Approach:</b>
     * <ol>
     *   <li>Create a queue with people numbered 1 to n</li>
     *   <li>While more than 1 person remains:
     *       <ul>
     *         <li>Rotate (k-1) people from front to back (skip k-1)</li>
     *         <li>Remove the person at the front (eliminate)</li>
     *       </ul>
     *   </li>
     *   <li>The last person in queue is the winner</li>
     * </ol>
     * 
     * <p><b>Example:</b>
     * <pre>
     *   n=5, k=2  (5 people, count every 2nd person)
     *   
     *   Initial: [1 ← front | 2 | 3 | 4 | 5 ← rear]
     *   
     *   Round 1: Eliminate position 2
     *   Rotate 1: [2 | 3 | 4 | 5 | 1]
     *   Remove 2: [3 | 4 | 5 | 1]
     *   
     *   Round 2: Eliminate position 2 (from person 3)
     *   Rotate 1: [4 | 5 | 1 | 3]
     *   Remove 4: [5 | 1 | 3]
     *   
     *   Round 3: Eliminate position 2 (from person 5)
     *   Rotate 1: [1 | 3 | 5]
     *   Remove 1: [3 | 5]
     *   
     *   Round 4: Eliminate position 2 (from person 3)
     *   Rotate 1: [5 | 3]
     *   Remove 5: [3]
     *   
     *   Winner: 3
     * </pre>
     * 
     * <p><b>Time Complexity:</b> O(n²).
     * Outer loop runs (n-1) times. Inner loop (skip k-1) runs O(n) times in worst case.
     * Total: O((n-1) × k) = O(n²) when k is close to n.
     * 
     * <p><b>Space Complexity:</b> O(n), the queue stores all n people.
     * 
     * <p><b>Edge Cases:</b>
     * <ul>
     *   <li><b>n=1:</b> Person 1 is the winner (no elimination)</li>
     *   <li><b>k=1:</b> Eliminate in order: 1, 2, 3, ... Winner is n</li>
     *   <li><b>k > n:</b> k is effectively k % n (modular behavior)</li>
     *   <li><b>k=2, n=1,2,3,...</b>: Winners follow a pattern</li>
     * </ul>
     * 
     * @param n the number of people in the circle (n ≥ 1)
     * @param k the count interval for elimination (k ≥ 1)
     * @return the number of the survivor (1 ≤ return ≤ n)
     */
    public int findTheWinner(int n, int k) {
        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++){
            q.add(i);
        }

        while(q.size() > 1){
            for(int i=1; i<=k-1; i++){
                q.add(q.remove());
            }
            q.remove();
        }
        return q.peek();
    }

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        q.add(10); q.add(20); q.add(30); q.add(40); q.add(50);
//        addAtSpecificIndex(q,60,3);
//        peekAtSpecificIndex(q,3);
        removeAtSpecificIndex(q,2);
        reverseAQueue(q);
        Display(q);
    }
}
