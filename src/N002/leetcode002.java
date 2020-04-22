package N002;

class ListNode{
    int val;
    ListNode next=null;
    ListNode(int x) {
        val = x;
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