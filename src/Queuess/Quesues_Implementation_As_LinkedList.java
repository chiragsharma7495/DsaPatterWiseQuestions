package Queuess;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class MyQueue {
    private Node head;
    private Node tail;
    private int size;

    public MyQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    void add(int val) {
        Node temp = new Node(val);
        if (tail == null) {
            head = tail = temp;
        } else {
            tail.next = temp;
            tail = temp;
        }
        size++;
    }

    int remove() {
        if (size == 0) {
            System.out.println("Queue is already empty!");
            return -1;
        }
        int front = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return front;
    }

    int peek() {
        if (size == 0) {
            System.out.println("There is no element in the queue!");
            return -1;
        }
        return head.data;
    }

    void display() {
        if (size == 0) {
            System.out.println("Queue is empty");
            return;
        }
        Node temp = head;
        System.out.print("Queue: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }
}

public class Quesues_Implementation_As_LinkedList {
    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        
        System.out.println("Adding elements: 10, 20, 30, 40");
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.display();
        
        System.out.println("\nPeek: " + q.peek());
        System.out.println("Size: " + q.size());
        
        System.out.println("\nRemoving element: " + q.remove());
        q.display();
        
        System.out.println("\nRemoving element: " + q.remove());
        q.display();
        
        System.out.println("\nAdding element: 50");
        q.add(50);
        q.display();
    }
}
