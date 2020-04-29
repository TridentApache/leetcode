package N002;

class ListNode{
    int val;
    ListNode next=null;
    ListNode(int x) {
        val = x;
    }
}

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 思路：逐个遍历，3个终止条件，链表1、链表2、flag，!(p1==null&&p2==null&&flag==0)
 * 1. 先val处理指针为空的情况
 * 2. 处理加和值和进位flag
 * 3. 处理结果链表1和链表2的指针以及结果链表，注意结果链表要new同样需要考虑3个条件，链表1、链表2、flag
 * */

class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1==null) return l2;
        if(l2==null) return l1;

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode p3 = new ListNode(0);
        ListNode res = p3;
        int flag=0;
        int val=0;
        int val1=0;
        int val2=0;
        while(!(p1==null&&p2==null&&flag==0)){
            if(p1!=null){
                val1=p1.val;
            }else{
                val1=0;
            }
            if(p2!=null){
                val2=p2.val;
            }else{
                val2=0;
            }

            val=val1+val2+flag;
            flag=val/10;
            val=val%10;
            p3.val=val;

            if(p1!=null){p1=p1.next;}
            if(p2!=null){p2=p2.next;}

            if(p1!=null||p2!=null||flag!=0){p3.next=new ListNode(0);p3=p3.next;}
        }
        return res;
    }
}

class Solution{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        int val1=0;
        int val2=0;
        ListNode l3 = new ListNode(0);
        ListNode p3 = l3;
        int flag=0;
        while(p1!=null || p2!=null || flag!=0){
            if(p1!=null){
                val1=p1.val;
            }else{val1=0;}
            if(p2!=null){
                val2=p2.val;
            }else{val2=0;}

            if(val1+val2+flag>=10){
                p3.val = val1+val2-10+flag;
                flag=1;
            }else{
                p3.val = val1+val2+flag;
                flag=0;
            }
            if(p1!=null)  p1=p1.next;
            if(p2!=null) p2=p2.next;

            if(p1!=null || p2!=null || flag!=0){
                p3.next = new ListNode(0);
                p3=p3.next;
            }
        }
        return l3;
    }
}

public class leetcode002 {
    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);

        Solution s = new Solution();
        ListNode l3 = s.addTwoNumbers(l1,l2);
        for(ListNode p = l3;p!=null;p=p.next){
            System.out.print(p.val);
            if(p.next!=null) System.out.print("->");
        }
    }
}