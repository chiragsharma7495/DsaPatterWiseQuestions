package Heaps;

import java.util.Arrays;

/**
 * Heap - Implementation of a Min-Heap using Array Representation
 * 
 * This class provides a custom min-heap implementation with standard heap operations.
 * 
 * Heap Structure:
 * - Array-based representation with 1-indexed elements (arr[0] unused)
 * - Complete binary tree property: all levels filled except possibly the last
 * - Index relationships:
 *   * Parent of node at index i: i/2
 *   * Left child of node at i: 2*i
 *   * Right child of node at i: 2*i+1
 * 
 * Min-Heap Property:
 * - Each parent <= both children
 * - Smallest element always at root (arr[1])
 * - Efficient for priority-based operations
 * 
 * Time Complexity:
 * - Add (insertion): O(log n) - sift up operation
 * - Remove (deletion): O(log n) - sift down operation
 * - Peek (access min): O(1)
 * - Size: O(1)
 */
class Heap{
    int arr[];
    int idx = 1;
    int peek(){
        return arr[1];
    }

    /**
     * Adds a new element to the heap while maintaining heap property.
     * 
     * Algorithm: Sift-Up (Bubble-Up)
     * 1. Insert element at the end (next available position)
     * 2. Compare with parent: if smaller, swap and move to parent
     * 3. Repeat until heap property satisfied or reached root
     * 
     * Example (add 2 to heap [3, 5, 7, 9]):
     *       3                  3              2
     *      / \               /  \            / \
     *     5   7             5    7          3   7
     *    /               /  \               / \
     *   9   → (add 2)   9    2  → (sift)  5   9
     * 
     * Time Complexity: O(log n) - height of tree
     * Space Complexity: O(1) - in-place operation
     * 
     * @param ele element to insert
     */
    void add(int ele){
        arr[idx++] = ele;
        //rearrangment

        int root = idx-1;
        while(root != 1){
            int parent = root/2;
            if(arr[root] < arr[parent]){
               int temp = arr[root];
               arr[root] = arr[parent];
               arr[parent] = temp;
               root = parent;
            }else break;
        }
    }

    /**
     * Removes and returns the minimum element (root) from the heap.
     * 
     * Algorithm: Sift-Down (Bubble-Down)
     * 1. Store root element (minimum)
     * 2. Move last element to root
     * 3. Decrease heap size
     * 4. Sift-down: compare with children, swap with smaller child if necessary
     * 5. Repeat until heap property satisfied
     * 
     * Example (remove from heap [2, 5, 7, 9]):
     *       2                9              5
     *      / \             /  \            / \
     *     5   7           5    7          9   7
     *    /           →             →
     *   9             (swap 9 up) (sift down)
     * 
     * Time Complexity: O(log n) - height of tree
     * Space Complexity: O(1) - in-place operation
     * 
     * @return minimum element, or -1 if heap is empty
     */
    int remove(){
        if(idx == 1){
            System.out.println("heat is empty");
            return -1;
        }
        int min = arr[1];
        arr[1] = arr[idx-1];
        idx--;

        int root = 1;
        while(root <= size()){
            int left = root*2; 
            int right = root*2+1;
            int smallest = root;
            
            // Find the smallest among root, left, and right
            if(left <= size() && arr[left] < arr[smallest]){
                smallest = left;
            }
            if(right <= size() && arr[right] < arr[smallest]){
                smallest = right;
            }
            
            // If root is already smallest, heap property is satisfied
            if(smallest == root){
                break;
            } else {
                // Swap with the smaller child
                int temp = arr[root];
                arr[root] = arr[smallest];
                arr[smallest] = temp;
                root = smallest;
            }
        }
        
        return min;
    }

    /**
     * Returns the current number of elements in the heap.
     * 
     * @return number of elements (excluding arr[0])
     */
    int size(){
        return idx-1;
    }

    /**
     * Prints all elements in the heap (array representation).
     * Note: arr[0] is unused and should not be printed.
     */
    void display(){
        for(int i=0; i<idx; i++){
            System.out.println(arr[i] + " ");
        }
    }

    /**
     * Constructs a min-heap with given capacity.
     * 
     * Initializes array with size capacity+1 (index 0 unused).
     * All elements filled with Integer.MAX_VALUE as sentinel.
     * 
     * @param capacity maximum number of elements the heap can hold
     */
    Heap(int capacity){
        arr = new int[capacity+1];
        Arrays.fill(arr , Integer.MAX_VALUE);
    }
}

/**
 * HeapPart2 - Min-Heap Implementation Study
 * 
 * This class demonstrates a complete array-based min-heap implementation with
 * manual sift-up and sift-down operations. It serves as an educational tool
 * to understand the internal mechanics of heap data structures.
 * 
 * Key Concepts Demonstrated:
 * - Array representation of complete binary tree (1-indexed)
 * - Min-heap property maintenance
 * - Sift-up operation (heapify-up) during insertion
 * - Sift-down operation (heapify-down) during deletion
 * - Binary tree relationships using index arithmetic
 * 
 * When to Use Custom Heaps:
 * - Educational purposes: Understanding heap internals
 * - When you need specific custom comparators or operations
 * - Performance-critical code where standard libraries add overhead
 * 
 * Standard Alternative:
 * - Java's PriorityQueue (simpler, optimized implementation)
 * 
 * @author DSA Course
 * @version 1.0
 * @see Heap
 */
public class HeapPart2 {
    public static void main(String[] args) {

    }
}
