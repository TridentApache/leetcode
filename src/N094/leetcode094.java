package N094;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历
 * */

public class leetcode094 {
    public static void main(String[] args) {
        TreeNode tr = new TreeNode(1);
        tr.right = new TreeNode(2);
        tr.right.left = new TreeNode(3);
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.inorderTraversal3(tr).toArray()));
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){
        val = x;
    }
}

class Solution {
    List<Integer> ls = new ArrayList<>();
    /**
     * 递归版：判断后怎么递归的过程不赋值，在函数主体统一考虑
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null) return ls;
        if(root.left!=null){
            inorderTraversal(root.left);
        }
        this.ls.add(root.val);
        if(root.right!=null){//提前判断
            inorderTraversal(root.right);
        }
        return this.ls;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) {
            return ls;
        } else {
            inorderTraversal(root.left);
            ls.add(root.val);
            inorderTraversal(root.right);
        }
        return ls;
    }

    /**
     * 迭代版
     * */
    public List<Integer> inorderTraversal3(TreeNode root) {
        if (root == null) {
            return ls;
        }
        Stack<TreeNode> s = new Stack<>();
        while(root!=null||!s.isEmpty()) {
            while (root != null) {//最左边叶子节点,注意这里是root为空才退出，所以大循环里root为空退出
                s.push(root);
                root = root.left;
            }
            //叶子节点
            root = s.pop();
            ls.add(root.val);
            root = root.right;//进入右节点，如果一直只有右子树，则一边push一边pop，为了让下次返回到更上一个节点
        }
        return ls;
    }
}