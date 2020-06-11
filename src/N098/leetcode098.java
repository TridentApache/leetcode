package N098;

import java.util.Stack;

public class leetcode098 {
}

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * */

/**
 * 递归版本
 * 注意需要考虑道TreeNode中val是int型，如果正好只有一个节点，且存储的为Integer.MAX_VALUE，upper或low设置为Integer的边界值就不行了，要用Long的
 * */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean isValidBST(TreeNode root, long low, long upper){
        if(root==null) return true;
        int val=root.val;
        if(val<=low||val>=upper) return false;
        return isValidBST(root.left,low,val)&&isValidBST(root.right,val,upper);
    }
}

/**
 * 中序遍历版本
 * */
class Solution1{
    public boolean isValidBST(TreeNode root) {
        if(root==null) return true;
        /**中序遍历*/
        Stack<TreeNode> stack = new Stack<>();
        long inorder = Long.MIN_VALUE;

        stack.push(root);
        while(stack.size()!=0||root!=null){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(root.val<=inorder) return false;
            inorder=root.val;
            root=root.right;
        }
        return true;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}