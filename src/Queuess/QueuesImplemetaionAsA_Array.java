package Queuess;

class CircularQueue{
    int []arr;
    int f;
    int r;
    int size;

    CircularQueue(int size){
        arr = new int[size];
        this.size = 0;
        f = 0;
        r = 0;
    }

    void add(int val){
        if(size == arr.length){
            System.out.println("queue is full");
            return;
        }
        arr[r] = val;
        r++;
        if(r == arr.length) r = 0;
        size++;
    }

    void remove(){
        if(size == 0){
            System.out.println("queue is empty");
            return;
        }
        int fronval = arr[f];
        f++;
        if(f == arr.length) f = 0;
        size--;
    }

    int peek(){
        if(size == 0){
            System.out.println("queue is empty");
            return -1;
        }
        return arr[f];
    }

    void display(){
        if(size == 0){
            System.out.println("queue is empty");
            return;
        }
        if(f >= r){
            for(int i=f; i<arr.length; i++){
                System.out.print(arr[i] + " ");
            }
            for(int i=0; i<r; i++){
                System.out.print(arr[i] + " ");
            }
        }else{
            for(int i=f; i<r; i++){
                System.out.print(arr[i] + " ");
            }
        }
        System.out.println();
    }
}

public class QueuesImplemetaionAsA_Array {
    public static void main(String[] args) {
        CircularQueue q = new CircularQueue(4);
        q.add(10);
        q.add(20);
        q.add(30);
        q.add(40);
        q.display();
    }
}
