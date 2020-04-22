package MyList;

public class MyList {
    public static void main(String[] args){
        MyLinkedList linkedList = new MyLinkedList();
        ListNode node = new ListNode(0);
        node.next = new ListNode(1);
        Solution s = new Solution();
        s.removeNthFromEnd(node,1);
//        linkedList.addAtHead(2);
//        linkedList.deleteAtIndex(1);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(3);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(5);
//        linkedList.addAtTail(5);
//        linkedList.get(5);            //返回2
//        linkedList.deleteAtIndex(6);  //现在链表是1-> 3
//        linkedList.deleteAtIndex(4);  //现在链表是1-> 3

    }
}
class ListNode1{
    int val;
    ListNode1 next;
    public ListNode1(int x){
        this.val=x;
    }
}
class ListNode{
    int val;
    ListNode next;
    public ListNode(int x){
        this.val=x;
    }
}
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        if(head==null) return null;
        if(n==0) return head;
        for(int i=0;i<n;i++){
            p1=p1.next;
        }
        ListNode p2=head;
        while(p1.next!=null){
            p1=p1.next;
            p2=p2.next;
        }
        p2.next=p2.next.next;
        return head;
    }
}

class MyLinkedList {
    int size;
    ListNode1 head;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        size=0;
        this.head = new ListNode1(0);
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index<0||index>=this.size) return -1;
        ListNode1 temp = this.head;
        for(int i=0;i<index+1;i++){ //链表实际上get从（head之后）第一个元素为0开始算
            temp = temp.next;
        }
        return temp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
//        ListNode cur = this.head;
//        ListNode toAdd = new ListNode(val);
//        toAdd.next = cur.next;
//        cur.next = toAdd;
        addAtIndex(0,val);
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        addAtIndex(this.size,val);
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index>size) return;
        if(index<0) index=0;
        this.size++;

        ListNode1 temp = this.head;
        for(int i=0;i<index;i++){ //temp停在index前一位，比如index=0，就停在head
            temp = temp.next;
        }
        ListNode1 toAdd = new ListNode1(val);
        toAdd.next=temp.next;
        temp.next = toAdd;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index<0||index>=this.size) //index>=size
            return;
        this.size--;

        ListNode1 temp = this.head;
        for(int i=0;i<index;i++){//temp停在index前一位，比如index=0，就停在head
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */