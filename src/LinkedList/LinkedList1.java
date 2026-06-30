package LinkedList;

/**
 * LinkedList1 - Basic LinkedList Operations
 * 
 * Demonstrates fundamental linked list operations including node creation,
 * traversal (iterative and recursive), and index-based access.
 */
public class LinkedList1 {
    
    /**
     * Node class representing a single element in the linked list
     * 
     * Properties:
     * - data: Integer value stored in the node
     * - next: Reference to the next node (null if last node)
     */
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Displays the linked list elements iteratively
     * 
     * Traverses the list from head to tail and prints each element.
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(1)
     * 
     * Example: List: 10 → 20 → 30 → null
     *          Output:
     *          10
     *          20
     *          30
     * 
     * @param head - Reference to the first node of the linked list
     */
    static void Display(Node head){
        Node temp = head;
        while(temp != null){
            System.out.println(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    /**
     * Displays the linked list elements recursively
     * 
     * Uses recursion to traverse the list and print each element.
     * Time Complexity: O(n) where n is number of nodes
     * Space Complexity: O(n) for recursion stack
     * 
     * Example: List: 10 → 20 → 30 → null
     *          Output:
     *          10
     *          20
     *          30
     * 
     * @param head - Reference to the first node of the linked list
     */
    static void displayRec(Node head){
        if(head == null) return;
        System.out.println(head.data + " ");
        displayRec(head.next);
    }

    /**
     * Retrieves the data at a specific index in the linked list
     * 
     * Traverses to the specified index and returns the element.
     * Time Complexity: O(n) where n is the index value
     * Space Complexity: O(1)
     * 
     * Example: List: 10 → 20 → 30 → 40 → null
     *          getRandomIndex(head, 0) → 10 (first element)
     *          getRandomIndex(head, 2) → 30 (third element)
     *          getRandomIndex(head, 3) → 40 (fourth element)
     * 
     * @param head - Reference to the first node of the linked list
     * @param idx - Index of the element to retrieve (0-based)
     * @return Data value at the specified index
     */
    static int getRandomIndex(Node head , int idx){
        for(int i=0; i<idx; i++){
            head = head.next;
        }
        return head.data;
    }
    
    public static void main(String[] args) {
        System.out.println("=== LinkedList1 - Basic Operations ===\n");
        
        // Create nodes: 10 → 20 → 30 → 40
        Node head = new Node(10);
        Node second = new Node(20);
        Node third = new Node(30);
        Node fourth = new Node(40);
        
        // Link nodes
        head.next = second;
        second.next = third;
        third.next = fourth;

        System.out.println("Display Iteratively:");
        Display(head);
        
        System.out.println("\nDisplay Recursively:");
        displayRec(head);
        
        System.out.println("\nGet elements by index:");
        System.out.println("Index 0: " + getRandomIndex(head, 0)); // Output: 10
        System.out.println("Index 2: " + getRandomIndex(head, 2)); // Output: 30
        System.out.println("Index 3: " + getRandomIndex(head, 3)); // Output: 40

    }
}
