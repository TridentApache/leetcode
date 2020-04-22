package N430;

public class leetcode430 {
    public static void main(String[] args){
        Node n1 = new Node();
        n1.val=1;

        n1.next = new Node();
        n1.next.val = 2;
        n1.next.child = new Node();
        n1.next.child.val=5;

        n1.next.child.next = new Node();
        n1.next.child.next.val=6;
        n1.next.child.next.next = new Node();
        n1.next.child.next.next.val=7;

        n1.next.next = new Node();
        n1.next.next.val = 3;

        n1.next.next.next = new Node();
        n1.next.next.next.val=4;

        Solution s = new Solution();
        Node n2 = s.flatten(n1);
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child=null;
};

class Solution {
    private Node prev = null;
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    private void dfs(Node head) {
        if (head == null) return;
        Node next = head.next;
        if (prev != null) {//目的是为了分支处（child递归进去）和child分支最后（child递归弹出）建立链接
            prev.next = head;
            head.prev = prev;
        }
        prev = head;
        dfs(head.child);
        head.child = null; //递归遍历过child分支了，反弹后，prev为child分支最后一个，head为分支出口，child至空去掉分支
        dfs(next);
    }
}
