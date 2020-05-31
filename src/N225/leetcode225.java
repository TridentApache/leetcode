package N225;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class leetcode225 {
    public static void main(String[] args){
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        System.out.println(s.top());
        System.out.println(s.pop());
        System.out.println(s.empty());
    }
}
class MyStack {
    private Queue<Integer> queue;
    private Queue<Integer> queue2;
    private Queue<Integer> tmp;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while(queue.size()>1){
            queue2.add(queue.poll());
        }
        if(queue.size()==1) {
            int res = queue.poll();
            tmp = queue;
            queue = queue2;
            queue2 = tmp;
            return res;
        }else{
            return -1;
        }
    }

    /** Get the top element. */
    public int top() {
        while(queue.size()>1){
            queue2.add(queue.poll());
        }
        if(queue.size()==1) {
            int res = queue.poll();
            queue2.add(res);
            tmp = queue;
            queue = queue2;
            queue2 = tmp;
            return res;
        }else{
            return -1;
        }
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        if(queue.size()==0 && queue2.size()==0)
            return true;
        return false;
    }
}