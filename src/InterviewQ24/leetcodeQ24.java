package InterviewQ24;

public class leetcodeQ24 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        Solution s = new Solution();
        ListNode res = s.swapPairs(head);
    }
}


class ListNode {
    int val;
     ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
public ListNode swapPairs(ListNode head) {
    if (head==null) return null;
    ListNode dummy = new ListNode(0);
    dummy.next=head;
    helper(dummy,head,head.next);
    return dummy.next;
}
private void helper(ListNode prepre,ListNode pre,ListNode cur){
    if(pre==null||cur==null){
        return;
    }
    if(cur.next!=null){
        helper(prepre.next.next,pre.next.next,cur.next.next);
    }
    pre.next=cur.next;
    cur.next=pre;
    prepre.next=cur;
}
}

