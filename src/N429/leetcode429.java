package N429;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class leetcode429 {
    public static void main(String[] args){
//        Solution s = new Solution();
        Node root = new Node(1,Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))),
                new Node(2), new Node(4)));
        Solution1 s1 = new Solution1();
        System.out.println(s1.maxDepth(root));
//        List<List<Integer>> res = s.levelOrder(root);
//        for(int i=0;i<res.size();i++) {
//            System.out.println(res.get(i));
//        }
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

/* // 递归判断root.children
class Solution1 {
    int ans=0;
    public int maxDepth(Node root) {
        if(root.children==null) return 1;

        int tmp=0;
        for(Node n:root.children){
            tmp = maxDepth(n);
            ans = tmp>ans?tmp:ans;
        }
        return ans+1;
    }
}*/

class Solution1 {
    public int maxDepth(Node root) {
        if(root==null) return 0;

        int ans=0;
        if(root.children!=null) {
            for (Node n : root.children) {
                int tmp = maxDepth(n);
                ans = tmp > ans ? tmp : ans;
            }
        }
        return ans+1;
    }
}

/*
class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root==null) return res;

        List<Node> list = new LinkedList<Node>();
        list.add(root);
        int len;

        while(list.size()!=0){
            List<Integer> l = new LinkedList<Integer>();
            len = list.size();
            for(int i=0;i<len;i++){
                Node node = list.remove(0);
                l.add(node.val);
                for(Node n:node.children){
                    list.add(n);
                }
            }
            res.add(l);
        }
        return res;
    }
}*/
