package LinkedListImplementationOfAStack;

import java.util.Stack;

class LinkNode{
    int val;
    LinkNode next;
    LinkNode (int val){
        this.val = val;
    }
}

public class RemoveNodeFromLinkedList {
    public LinkNode removeNodes (LinkNode head){
        Stack<LinkNode> stack = new Stack<>();
        LinkNode temp = head;
        while(temp != null){
            while(stack.size() > 0 && stack.peek().val < temp.val) {
                stack.pop();
            }
            stack.push(temp);
            temp = temp.next;
        }
        LinkNode result = null;
        while(stack.size() > 0){
            LinkNode top = stack.pop();
            top.next = result;
            result = top;
        }
        return result;
    }
}
