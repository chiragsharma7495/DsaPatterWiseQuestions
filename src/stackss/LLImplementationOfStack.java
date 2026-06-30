package stackss;

class Node{
    int val;
    Node next;
    Node(int val){
        this.val = val;
    }
}

class MyStack{
    Node head;
    int length;

    int peek(){
        if(length == 0){
            System.out.println("stack is empty");
            return -1;
        }
        return head.val;
    }

    int pop(){
        if(length == 0){
            System.out.println("stack is empty");
            return -1;
        }
        int x = head.val;
        head = head.next;
        length--;
        return x;
    }

    void push(int element){
        Node temp = new Node(element);
        if(length == 0) {
        }
        else {
            temp.next = head;
        }
        head = temp;
        length++;
    }

    int size(){
        return length;
    }

    void display(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.val);
            temp = temp.next;
        }
        System.out.println();
    }

}


public class LLImplementationOfStack {
    public static void main(String[] args) {
        MyStack st = new MyStack();
        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);

        st.display();
    }
}
