package Offer24;

public class Offer24 {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        s.reverseList(head);
    }
}

 class ListNode {
    int val;
   ListNode next;
   ListNode(int x) { val = x; }
}

class Solution {
    private ListNode dummy;
    public ListNode reverseList(ListNode head) {
        dummy = new ListNode(0);
        dummy.next=head;
        helper(head);
        return dummy.next;
    }
    private ListNode helper(ListNode head){
        if(head==null) return dummy;
        helper(head.next).next=head;
        head.next=null;
        return head;
    }
}