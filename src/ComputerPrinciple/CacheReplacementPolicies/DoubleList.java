package ComputerPrinciple.CacheReplacementPolicies;

/**
 * 计算机基础中的缓存置换策略(FIFO, LRU, LFU)，通过双向链表实现
 * 1. 构造函数初始化一个Node，head和tail都指向这个Node
 * 2. addLast()
 * 3. addFirst()
 * 4. remove()
 * 5. removeLast()
 * 6. removeFirst()
 * */

public class DoubleList {
    Node head,tail;
    int size;
    DoubleList(){
        head = new Node(0,0);
        tail = head;
        size=1;
    }
    DoubleList(Node node){
        head = node;
        tail = node;
        size=1;
    }
    public void addLast(Node node){
        if(node==null) return;
        tail.next=node;
        node.pre=tail;
        tail=node;
        size++;
    }
    public void addFirst(Node node){
        if(node==null) return;
        node.next=head;
        head.pre=node;
        head=node;
        size++;
    }
    public Node removeLast(){
        if(tail.pre==null){
            return null;
        }
        Node pre = tail.pre;
        pre.next=null;
        tail=pre;
        return pre;

    }
    public Node removeFirst(){
        if(head.next==null) return null;
        Node firstnode=head;
        head=head.next;
        head.pre=null;
        return firstnode;

    }
    public void remove(Node node){
        node.pre.next=node.next;
        node.next.pre = node.pre;
        size--;
    }

    public void print(){
        Node tmp=this.head;
        while(tmp!=null){
            System.out.print("("+tmp.key+","+tmp.get(tmp.key)+")");
            if(tmp.next!=null){
                System.out.print("=>");
            }
            tmp=tmp.next;
        }
        System.out.println();
    }
}

class Node{
    int key,value;
    Node next,pre;

    Node(int key, int value){
        this.key=key;
        this.value=value;
    }
    public int get(int key){
        return value;
    }
}