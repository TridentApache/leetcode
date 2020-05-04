package N346;

public class leetcode346 {
    public static void main(String[] args){
        MovingAverage m = new MovingAverage(1);
        m.next(1);
        m.next(10);
        m.next(3);
        m.next(5);
    }
}
/**
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算其所有整数的移动平均值。
 *
 * 思路：循环队列的一个题，类似第622题，设计capacity、count、head、queue等基本元素；
 * 在不超过容量时直接存储；超过容量时减掉head加上当前流入值，并更新queue和head。
 * */

class MovingAverage {
    private int capacity;
    private int count;
    private int[] queue;
    private int head;
    private int sum;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        capacity = size;
        count=0;
        head=0;
        sum=0;
        queue = new int[size];
    }

    public double next(int val) {
        //if(capacity==1) return val;
        if(count<capacity){
            sum=sum+val;
            queue[count++]=val;
        }else{
            sum=sum-queue[head%capacity]+val;
            queue[head%capacity]=val;
            head++;
        }
        return 1.0*sum/count;
    }
}