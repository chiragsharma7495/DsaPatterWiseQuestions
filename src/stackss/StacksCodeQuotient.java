package stackss;

import java.util.LinkedList;
import java.util.Stack;

public class StacksCodeQuotient {

    static boolean isBalanced(String s){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '(' || ch =='{' || ch == '[') stack.push(ch);
            else {
                if(stack.isEmpty()) return false;
                char top = stack.pop();
                if(sameStyle(top , ch)) stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
    private static boolean sameStyle(char a , char b){
        if(a == '(' && b == ')') return true;

        if(a == '{' && b == '}') return true;

        if(a == '[' && b == ']') return true;

        return false;
    }
    public String removeConsecutiveCharacter(String s) {
        Stack<Character> stack1 = new Stack<>();
        Stack<Character> stack2 = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(stack1.isEmpty()) stack1.push(ch);
            else if(stack1.peek() != ch )stack1.push(ch);
        }

        while(stack1.isEmpty()){
            stack2.push(stack1.pop());
        }

        StringBuilder result = new StringBuilder();
        while(stack2.isEmpty()){
            result.append(stack2.pop());
        }
        return result.toString();
    }
    static int minReversal(String str){
        if(str.length()%2 != 0) return -1;
        Stack<Character> stack = new Stack<>();
        int closeCount = 0;

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            if(ch == '['){
                stack.push(ch);
            }else{
                if(!stack.isEmpty()){
                    stack.pop();
                }else{
                    closeCount++;
                }
            }
        }

        int opencount = stack.size();
        int reversals = (opencount + 1)/2 + (closeCount +1)/2;
        return reversals;
    }

    public static void main(String[] args) {
    }
}
