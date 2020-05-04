package N622;

import java.util.ArrayList;
import java.util.List;



public class leetcode622 {
    public static void main(String[] args){
        int k=8;
        int value=0;
        MyCircularQueue obj = new MyCircularQueue(k);
        obj.enQueue(1);
        obj.enQueue(2);
        obj.enQueue(3);
        obj.enQueue(4);
        obj.deQueue();
        obj.deQueue();
        obj.isEmpty();
        obj.isEmpty();
        obj.Rear();
        obj.Rear();
        obj.deQueue();

    }
}
/**
 * 设计循环队列
 * */
class MyCircularQueue {

    private int[] queue;
    private int headIndex;
    private int count;
    private int capacity;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.capacity = k;
        this.queue = new int[k];
        this.headIndex = 0;
        this.count = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (this.count == this.capacity)
            return false;
        this.queue[(this.headIndex + this.count) % this.capacity] = value;
        this.count += 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (this.count == 0)
            return false;
        this.headIndex = (this.headIndex + 1) % this.capacity;
        this.count -= 1;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (this.count == 0)
            return -1;
        return this.queue[this.headIndex];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (this.count == 0)
            return -1;
        int tailIndex = (this.headIndex + this.count - 1) % this.capacity;
        return this.queue[tailIndex];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return (this.count == 0);
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (this.count == this.capacity);
    }
}

/**
 * 错误代码：
 * 用tail不方便
 * 用链表，deQueue时连续remove会出错，第二次remove根据head序号就不对了
 * */
class MyCircularQueue0 {
    private int size=0;
    private List<Integer> l;
    private int head;
    private int tail;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue0(int k) {
        this.size=k;
        this.l = new ArrayList<>();
        this.head=-1;
        this.tail=-1;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(!isFull()){
            if(head==-1) head=0;
            this.tail=(this.tail+1)%this.size;
            l.add(tail,value);
            return true;
        }
        return false;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(!isEmpty()){
            l.remove(head);
            if(this.head+1==this.tail){
                this.head=-1;
                this.tail=-1;
            }else{
                this.head = (this.head + 1) % this.size;
            }
            return true;
        }
        return false;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return l.get(head);
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return l.get(tail);
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(tail==-1)
            return true;
        return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if((this.tail+1)%this.size==this.head)
            return true;
        return false;
    }
}