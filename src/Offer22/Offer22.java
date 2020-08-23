package Offer22;

public class Offer22 {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        s.getKthFromEnd(head,2);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    private int n;
    private ListNode res=null;
    public ListNode getKthFromEnd(ListNode head, int k) {
        n=k;
        helper(head);
        return res;
    }
    private ListNode helper(ListNode head){
        if(head!=null && helper(head.next)==null)
            n--;
        if(n==0) {res=head;return null;}
        return null;
    }
}