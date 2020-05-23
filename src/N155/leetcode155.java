package N155;

import java.util.Stack;

public class leetcode155 {
    public static void main(String[] args){
        MinStack s = new MinStack();
        s.push(512);
        s.push(-1024);
        s.push(-1024);
        s.push(512);
        s.pop();
        s.getMin();
        s.pop();
        s.getMin();
        s.pop();
        s.getMin();
    }
}

class MinStack {
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    /** initialize your data structure here. */
    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        if(s1.size()==0){
            s1 = new Stack<>();
            s2 = new Stack<>();
        }
        s1.push(x);
        if(s2.size()==0 || s2.peek()>=x){
            s2.push(x);
        }
    }

    public void pop() {
        if(s1.size()!=0){
            if(s1.peek().equals(s2.peek())){//!!!!!!!Stack<Integer>栈中虽然值一样，但是地址不同，不能用==
                s2.pop();
            }
            s1.pop();
        }
    }

    public int top() {
        return s1.peek();
    }

    public int getMin() {
        return s2.peek();
    }
}