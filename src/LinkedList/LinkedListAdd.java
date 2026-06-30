package LinkedList;

/**
 * LinkedListAdd - A comprehensive implementation of a singly linked list with various operations.
 *
 * <p><b>LinkedList Structure:</b>
 * A linked list is a linear data structure where elements (nodes) are connected via references (pointers).
 * Each node contains:
 * <ul>
 *   <li>data: The actual value stored in the node</li>
 *   <li>next: Reference to the next node in the list</li>
 * </ul>
 *
 * <p><b>Visual Representation:</b>
 * <pre>
 * head → [1 | next] → [2 | next] → [3 | next] → [4 | null] ← tail
 *
 * Operations in this class:
 * - addAtTail(): Insert at end - O(1)
 * - addAtHead(): Insert at beginning - O(1)
 * - insert(): Insert at specific position - O(n)
 * - deleteAtHead(): Remove first element - O(1)
 * - deleteAtMiddle(): Remove at position - O(n)
 * - deleteMid(): Remove middle node - O(n)
 * - get(): Retrieve value at index - O(n)
 * - Search(): Find element by value - O(n)
 * - SearchIdx(): Find index of element - O(n)
 * - display(): Print all elements - O(n)
 * - detectLoop(): Detect cycle in list - O(n)
 * - removeDuplicates(): Remove consecutive duplicates - O(n)
 * </pre>
 *
 * @author DSA Learner
 */
public class LinkedListAdd {
    /**
     * Node class - Represents a single node in the linked list.
     *
     * <p>Each node contains:
     * <ul>
     *   <li>data: Integer value to store</li>
     *   <li>next: Reference to the next node (null if last node)</li>
     * </ul>
     */
    static class Node {
        int data;
        Node next;

        /**
         * Constructor to create a new node with given data.
         *
         * @param data The integer value to store in the node
         */
        Node(int data) {
            this.data = data;
        }
    }

    /**
     * Linkedlist class - Main implementation of singly linked list operations.
     *
     * <p>Maintains references to head and tail nodes for O(1) operations at both ends.
     * Tracks size for efficient boundary checking.
     */
    static class Linkedlist{
        Node head;  // Reference to first node
        Node tail;  // Reference to last node
        int size = 0;  // Number of elements in the list

        /**
         * Adds an element at the end (tail) of the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Create a new node with the given value</li>
         *   <li>If list is empty, set both head and tail to the new node</li>
         *   <li>Otherwise, link the current tail to the new node</li>
         *   <li>Update tail to point to the new node</li>
         *   <li>Increment size counter</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Initial list: 10 → 20 → null
         * addAtTail(30)
         * Result: 10 → 20 → 30 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(1) - Direct access via tail pointer
         * <p><b>Space Complexity:</b> O(1) - Only one new node created
         * <p><b>Edge Cases:</b> Works correctly when list is empty (first insertion)
         *
         * @param val The integer value to add at the end
         */
        void addAtTail(int val){
            Node temp = new Node(val);
            if(tail == null) tail = head = temp;
            else{
                tail.next = temp;
                tail = temp;
            }
            size++;
        }
        /**
         * Displays all elements in the linked list, one per line.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Start from head node</li>
         *   <li>Traverse to each node using next pointer</li>
         *   <li>Print data of each node</li>
         *   <li>Stop when null is encountered</li>
         *   <li>Print empty line after displaying all elements</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 10 → 20 → 30 → null
         * Output:
         * 10
         * 20
         * 30
         * (empty line)
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Visit each node once
         * <p><b>Space Complexity:</b> O(1) - Only uses one temporary pointer
         * <p><b>Edge Cases:</b> Returns immediately if list is empty (head is null)
         */
        void display(){
            if(head == null) return;
            Node temp = head;
            while(temp != null){
                System.out.println(temp.data);
                temp = temp.next;
            }
            System.out.println();
        }
        /**
         * Adds an element at the beginning (head) of the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Create a new node with the given value</li>
         *   <li>If list is empty, set both head and tail to the new node</li>
         *   <li>Otherwise, link new node's next to current head</li>
         *   <li>Update head to point to the new node</li>
         *   <li>Increment size counter</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Initial list: 20 → 30 → null
         * addAtHead(10)
         * Result: 10 → 20 → 30 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(1) - Direct manipulation of head pointer
         * <p><b>Space Complexity:</b> O(1) - Only one new node created
         * <p><b>Edge Cases:</b> Works correctly when list is empty (first insertion)
         *
         * @param val The integer value to add at the beginning
         */
        void addAtHead(int val) {
            Node temp = new Node(val);
            if(head == null) head = tail = temp;
            else {
                temp.next = head;
                head = temp;
            }
            size++;
        }
        /**
         * Searches for a specific value in the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Start from head node</li>
         *   <li>Compare each node's data with search value</li>
         *   <li>Return true if value found</li>
         *   <li>Traverse to next node if not found</li>
         *   <li>Return false if end of list reached without finding value</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 10 → 20 → 30 → null
         * Search(20) returns: true
         * Search(25) returns: false
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - May need to traverse entire list
         * <p><b>Space Complexity:</b> O(1) - Only uses one temporary pointer
         * <p><b>Edge Cases:</b> Returns false if list is empty; correctly finds element at head or tail
         *
         * @param val The integer value to search for
         * @return true if value is found, false otherwise
         */
        boolean Search(int val) {
            Node temp = head;
            while (temp != null) {
                if (temp.data == val) return true;
                temp = temp.next;
            }
            return false;
        }
        /**
         * Searches for a value and returns its index/position in the list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Initialize index counter to 0</li>
         *   <li>Start from head node</li>
         *   <li>Compare each node's data with search value</li>
         *   <li>Return index if value found</li>
         *   <li>Increment index and traverse to next node</li>
         *   <li>Return -1 if value not found in entire list</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 10 → 20 → 30 → 40 → null
         *       0    1    2    3
         * SearchIdx(30) returns: 2
         * SearchIdx(50) returns: -1
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - May traverse entire list
         * <p><b>Space Complexity:</b> O(1) - Only uses counters and temporary pointer
         * <p><b>Edge Cases:</b> Returns -1 if list is empty or element not found; returns 0 for head element
         *
         * @param val The integer value to search for
         * @return Index (0-based) of the element, or -1 if not found
         */
        int SearchIdx(int val) {
            Node temp = head;
            int idx = 0;
            while (temp != null) {
                if (temp.data == val) return idx;
                temp = temp.next;
                idx++;
            }
            return -1;
        }

        /**
         * Retrieves the element at a specific index/position in the list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Start from head node</li>
         *   <li>Loop forward idx times to reach target node</li>
         *   <li>Return the data of the node at position idx</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 10 → 20 → 30 → 40 → null
         *       0    1    2    3
         * get(0) returns: 10
         * get(2) returns: 30
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Need to traverse to index position
         * <p><b>Space Complexity:</b> O(1) - Only uses temporary pointer
         * <p><b>Edge Cases:</b> ⚠️ WARNING: No bounds checking - will throw NullPointerException if idx >= size
         *
         * @param idx The index (0-based) of the element to retrieve
         * @return The integer value at the specified index
         * @throws NullPointerException if index is out of bounds
         */
        int get(int idx){
            Node temp = head;
            for(int i=0; i<idx; i++){
                temp = temp.next;
            }
            return temp.data;
        }

        /**
         * Inserts a value at the middle of the linked list.
         *
         * <p><b>Current Implementation:</b> Stub method that returns head without modification.
         * This method is designed to be used with the LeetCode problem that provides
         * a node reference rather than using instance variables.
         *
         * @param head The head node of the linked list (may be null)
         * @param x The integer value to insert in the middle
         * @return The head node of the modified list
         *
         * @deprecated This is a stub implementation. Use insert() method for general insertion.
         */
        public Node insertInMiddle(Node head, int x) {
            return head;
        }

        /**
         * Inserts an element at a specific position in the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Validate index is within valid range [0, size]</li>
         *   <li>If index is 0, call addAtHead()</li>
         *   <li>If index is size, call addAtTail()</li>
         *   <li>Otherwise, traverse to position idx-1</li>
         *   <li>Create new node and insert between nodes at idx-1 and idx</li>
         *   <li>Update size counter</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Initial list: 10 → 20 → 40 → null (size=3)
         * insert(30, 2)
         * Result: 10 → 20 → 30 → 40 → null (size=4)
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Need to traverse to insertion point
         * <p><b>Space Complexity:</b> O(1) - Only one new node created
         * <p><b>Edge Cases:</b> Handles insertion at head (idx=0) and tail (idx=size); returns silently if index invalid
         *
         * @param val The integer value to insert
         * @param idx The index (0-based) where to insert the element
         */
        void insert(int val , int idx){
            if(idx<0 || idx > size) return;
            if(idx == 0) addAtHead(val);
            else if(idx == size) addAtTail(val);
            else{
                Node temp = head;
                for(int i=1; i<idx; i++){
                    temp = temp.next;
                }
                Node newNode = new Node(val);
                newNode.next = temp.next;
                temp.next = newNode;
                size++;
            }
        }

        /**
         * Deletes the first element (head) from the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>If list is empty, return immediately</li>
         *   <li>If only one element exists, set both head and tail to null</li>
         *   <li>Otherwise, move head pointer to the next node</li>
         *   <li>Decrement size counter</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Initial list: 10 → 20 → 30 → null
         * deleteAtHead()
         * Result: 20 → 30 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(1) - Direct head pointer update
         * <p><b>Space Complexity:</b> O(1) - No extra space used
         * <p><b>Edge Cases:</b> Handles empty list gracefully; handles single-element list (updates both head and tail)
         */
        void deleteAtHead() {
            if(head == null) return;
            if(head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            size--;
        }

        /**
         * Deletes the element at a specific index/position in the linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Validate index is within valid range [0, size-1]</li>
         *   <li>If index is 0, call deleteAtHead()</li>
         *   <li>Otherwise, traverse to position idx-1</li>
         *   <li>Unlink node at idx by updating next pointers</li>
         *   <li>If deleting last element, update tail to previous node</li>
         *   <li>Decrement size counter</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Initial list: 10 → 20 → 30 → 40 → null (size=4)
         * deleteAtMiddle(2)
         * Result: 10 → 20 → 40 → null (size=3)
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Need to traverse to deletion point
         * <p><b>Space Complexity:</b> O(1) - Only uses temporary pointer
         * <p><b>Edge Cases:</b> Validates index; properly updates tail when deleting last element; handles head deletion
         *
         * @param idx The index (0-based) of the element to delete
         */
        public void deleteAtMiddle(int idx) {
            if(idx < 0 || idx >= size) return;
            if(idx == 0) {
                deleteAtHead();
                return;
            }
            Node temp = head;
            for(int i=1; i<idx; i++){
                temp = temp.next;
            }
            temp.next = temp.next.next;
            if(idx == size-1) tail = temp;
            size--;
        }

        /**
         * Deletes the middle node of the linked list using fast and slow pointers.
         *
         * <p><b>Algorithm: Two-Pointer Technique</b>
         * <ol>
         *   <li>If list is empty or has only 1 node, return null (cannot delete)</li>
         *   <li>If list has exactly 2 nodes, delete second node (next of head)</li>
         *   <li>Initialize slow pointer at head, fast pointer 2 steps ahead</li>
         *   <li>Move both pointers until fast reaches end</li>
         *   <li>When fast reaches last node, slow is at node before middle</li>
         *   <li>Delete middle by updating slow.next = slow.next.next</li>
         *   <li>Return modified head</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 1 → 2 → 3 → 4 → 5 → null
         *            ↑        ↑        (middle = 3)
         *          slow      fast
         * After deletion: 1 → 2 → 4 → 5 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Single pass with two pointers
         * <p><b>Space Complexity:</b> O(1) - Only uses two pointers
         * <p><b>Edge Cases:</b> Handles lists of size 1, 2, and even-length lists (deletes upper-middle)
         *
         * @param head The head node of the linked list (may be null)
         * @return The head of the modified list (null if original list had 0 or 1 nodes)
         */
        Node deleteMid(Node head) {
            if(head == null || head.next == null) return null;
            if(head.next.next == null){
                head.next = null;
                return head;
            }
            Node slow = head;
            Node fast = head.next.next;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }
            slow.next = slow.next.next;
            return head;
        }

        /**
         * Returns the k-th node from the end (or last) of the linked list.
         *
         * <p><b>Algorithm: Two-Pointer Technique</b>
         * <ol>
         *   <li>Initialize both slow and fast pointers at head</li>
         *   <li>Move fast pointer k steps ahead</li>
         *   <li>If fast becomes null before k steps, return -1 (k exceeds list length)</li>
         *   <li>Move both pointers until fast reaches the last node</li>
         *   <li>When fast reaches end, slow is at k-th node from last</li>
         *   <li>Return the data of that node</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 1 → 2 → 3 → 4 → 5 → null
         * k = 2
         * Counting from end: 5(1st), 4(2nd) ← target
         * getKthFromLast(head, 2) returns: 4
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Single pass through list
         * <p><b>Space Complexity:</b> O(1) - Only uses two pointers
         * <p><b>Edge Cases:</b> Returns -1 if k is greater than list length; works for k=1 (last element)
         *
         * @param head The head node of the linked list
         * @param k The position from the end (1-based: 1 = last element)
         * @return The data of the k-th node from end, or -1 if k exceeds list length
         */
        int getKthFromLast(Node head, int k) {
            Node slow = head;
            Node fast = head;

            for(int i=1; i<=k; i++){
                if(fast == null) return -1;
                fast = fast.next;
            }
            while(fast != null){
                slow = slow.next;
                fast = fast.next;
            }
            return slow.data;
        }

        /**
         * Swaps the values of the k-th node from the beginning with the k-th node from the end.
         *
         * <p><b>Algorithm: Two-Pointer Technique</b>
         * <ol>
         *   <li>Find k-th node from beginning (first pointer)</li>
         *   <li>Find k-th node from end by moving second pointer</li>
         *   <li>Swap the data values of these two nodes</li>
         *   <li>Return the modified head</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 1 → 2 → 3 → 4 → 5 → null (k=2)
         *            ↑              ↑
         *       1st(value=2)  2nd(value=4)
         * After swap: 1 → 4 → 3 → 2 → 5 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Two passes through list
         * <p><b>Space Complexity:</b> O(1) - Only uses pointers, swaps values not nodes
         * <p><b>Edge Cases:</b> Handles k=1 correctly; if same node (k = list.size+1-k), swaps with itself (no effect)
         *
         * @param head The head node of the linked list
         * @param k The position (1-based) from beginning and from end to swap
         * @return The head of the modified list
         */
        public Node swapNodes(Node head, int k) {
            Node first = head;
            Node second = head;
            Node kthNode = null;

            for(int i=1; i<k; i++){
                first = first.next;
            }
            kthNode = first;
            first = first.next;

            while(first != null){
                first = first.next;
                second = second.next;
            }

            int temp = kthNode.data;
            kthNode.data = second.data;
            second.data = temp;

            return head;
        }

        /**
         * Removes the n-th node from the end of the linked list.
         *
         * <p><b>Algorithm: Two-Pointer Technique with Dummy Node</b>
         * <ol>
         *   <li>Create a dummy node pointing to head (handles head deletion edge case)</li>
         *   <li>Initialize slow pointer at dummy, fast pointer at dummy</li>
         *   <li>Move fast pointer n+1 steps ahead</li>
         *   <li>If fast reaches null before n+1 steps, return head (safety check)</li>
         *   <li>Move both pointers until fast reaches null</li>
         *   <li>Remove node at position n by updating next pointers</li>
         *   <li>Return dummy.next (which is the new head)</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 1 → 2 → 3 → 4 → 5 → null (n=2)
         * Position from end: 5(1st), 4(2nd) ← target to remove
         * Result: 1 → 2 → 3 → 5 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Single pass through list
         * <p><b>Space Complexity:</b> O(1) - Only uses pointers and dummy node
         * <p><b>Edge Cases:</b> Correctly removes head node; uses dummy node to avoid null pointer issues
         *
         * @param head The head node of the linked list
         * @param n The position from the end to remove (1-based)
         * @return The head of the modified list
         */
        public Node removeNthFromEnd(Node head, int n) {
            Node dummy = new Node(0);
            dummy.next = head;

            Node slow = dummy;
            Node fast = dummy;

            // Move fast n+1 steps ahead
            for(int i = 0; i <= n; i++) {
                if(fast == null) return head;
                fast = fast.next;
            }

            // Move both until fast reaches end
            while(fast != null) {
                slow = slow.next;
                fast = fast.next;
            }

            if(slow.next != null) {
                slow.next = slow.next.next;
            }

            return dummy.next;
        }
        /**
         * Detects if a cycle (loop) exists in the linked list.
         *
         * <p><b>Algorithm: Floyd's Cycle Detection (Tortoise and Hare)</b>
         * <ol>
         *   <li>Initialize slow pointer at head (moves 1 step per iteration)</li>
         *   <li>Initialize fast pointer at head (moves 2 steps per iteration)</li>
         *   <li>Move both pointers until they meet or fast reaches null</li>
         *   <li>If they meet, cycle exists (return true)</li>
         *   <li>If fast reaches null, no cycle exists (return false)</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Acyclic list: 1 → 2 → 3 → null
         * detectLoop(head) returns: false
         *
         * Cyclic list: 1 → 2 → 3 → 4 ↓
         *              ↑_______________↓
         * detectLoop(head) returns: true
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - At most one full traversal
         * <p><b>Space Complexity:</b> O(1) - Only uses two pointers
         * <p><b>Edge Cases:</b> Works for empty lists; handles single-node self-loop; handles immediately cyclic lists
         *
         * @param head The head node of the linked list
         * @return true if cycle is detected, false otherwise
         */
        public boolean detectLoop(Node head) {
            Node slow = head;
            Node fast = head;
            while(fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(fast == slow) return true;
            }
            return false;
        }
        /**
         * Detects a cycle in the linked list and returns the node where the cycle begins.
         *
         * <p><b>Algorithm: Floyd's Cycle Detection with Entry Point Finding</b>
         * <ol>
         *   <li>Use two pointers (slow and fast) to detect if cycle exists</li>
         *   <li>Fast moves 2 steps, slow moves 1 step per iteration</li>
         *   <li>If they meet, cycle exists; if fast reaches null, no cycle</li>
         *   <li>When cycle detected, initialize third pointer at head</li>
         *   <li>Move slow and extra pointers one step at a time</li>
         *   <li>When they meet, that node is the cycle start</li>
         *   <li>Return the cycle start node or null if no cycle</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * List: 1 → 2 → 3 → 4
         *            ↑       ↓
         *            └───────┘ (cycle starts at node 2)
         * detectCycle(head) returns: Node with value 2
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Two passes through cyclic portion
         * <p><b>Space Complexity:</b> O(1) - Only uses three pointers
         * <p><b>Edge Cases:</b> Returns null for acyclic lists; correctly identifies start node of any cycle position
         *
         * @param head The head node of the linked list (may be null)
         * @return The node where cycle begins, or null if no cycle exists
         */
        public Node detectCycle(Node head) {
            Node slow = head;
            Node fast = head;
            Node extra = head;

            while(fast != null && fast.next != null && fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(fast == slow){
                    while(extra != slow){
                        extra = extra.next;
                        slow = slow.next;
                    }
                    return extra;
                }
            }
            return null;
        }
        /**
         * Removes consecutive duplicate nodes from a sorted linked list.
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Return null if list is empty</li>
         *   <li>Initialize two pointers (i and j) at head</li>
         *   <li>For each node i, advance j until value changes</li>
         *   <li>Link node i directly to node j, skipping duplicates</li>
         *   <li>Move both pointers forward and repeat</li>
         *   <li>Properly handle last node (set to null or next)</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Input list:  1 → 1 → 1 → 2 → 2 → 3 → null
         * Output list: 1 → 2 → 3 → null
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Single pass through list
         * <p><b>Space Complexity:</b> O(1) - Only uses two pointers
         * <p><b>Edge Cases:</b> Assumes sorted list; works with all duplicates; handles empty lists
         *
         * @param head The head node of a sorted linked list with possible consecutive duplicates
         * @return The head of the list with consecutive duplicates removed
         */
        public Node deleteDuplicatess(Node head) {
            if(head == null) return null;

            Node i = head;
            Node j = head;

            while(j != null){
                while(j != null && i.data == j.data){
                    j = j.next;
                }
                i.next = j;
                i = j;
                if(j != null) j = j.next;
            }
            return head;
        }
        /**
         * Removes all duplicate nodes from a sorted linked list (including ALL occurrences of duplicates).
         *
         * <p><b>Operation Details:</b>
         * <ol>
         *   <li>Create dummy node to handle head deletion edge case</li>
         *   <li>Dummy points to original head</li>
         *   <li>Use prev pointer at dummy, curr at head</li>
         *   <li>If curr and curr.next have same value:</li>
         *   <li>   - Identify the duplicate value</li>
         *   <li>   - Skip all nodes with that value</li>
         *   <li>   - Link prev directly to next different value</li>
         *   <li>Otherwise move prev forward</li>
         *   <li>Return dummy.next as new head</li>
         * </ol>
         *
         * <p><b>Example:</b>
         * <pre>
         * Input list:  1 → 2 → 2 → 2 → 3 → null
         * Output list: 1 → 3 → null
         * (All nodes with value 2 are removed, not just duplicates)
         * </pre>
         *
         * <p><b>Time Complexity:</b> O(n) - Single pass through list
         * <p><b>Space Complexity:</b> O(1) - Only uses pointers
         * <p><b>Edge Cases:</b> Handles empty/single-element lists; removes head if it's duplicate; works when all values are duplicates
         *
         * @param head The head node of a sorted linked list with possible duplicates
         * @return The head of the list with all duplicate nodes removed
         */
        public Node deleteDuplicates(Node head) {
            if(head == null || head.next == null) return head;
            Node dummy = new Node(0);
            dummy.next = head;
            Node prev = dummy;
            Node curr = head;

            while(curr != null){
                if(curr.next != null && curr.data == curr.next.data){
                    int duplicateVal = curr.data;
                    while(curr != null && curr.data == duplicateVal){
                        curr = curr.next;
                    }
                    prev.next = curr;
                }else{
                    prev = curr;
                    curr = curr.next;
                }
            }
            return dummy.next;
        }
    }

    public static void main(String[] args) {
        Linkedlist ll = new Linkedlist();
        ll.addAtTail(1);
        ll.addAtTail(2);
        ll.addAtTail(3);
        ll.addAtTail(4);
        ll.addAtTail(5);
        ll.insert(99, 3);
        System.out.println("After inserting 99 at index 3:");
        ll.display();
        ll.deleteAtMiddle(3);
    }
}
