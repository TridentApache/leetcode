package N700;

public class leetcode700 {
    public static void main(String[] args){
        Solution1 s = new Solution1();
        TreeNode root = new TreeNode(4);
        root.left=new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        s.searchBST(root,2);
    }
}
/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 注意：
 * 1. 注意return只能返回一层
 * 2. 这题是二叉搜索树，不仅仅是二叉树
 * */

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
}

class Solution0 {
    TreeNode res=null;
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null) return null;
        if(root.val==val) {res = root; return root;}
        searchBST(root.left,val);
        if(res!=null) return res;//递归只能return一层，可以在出递归后多加一层判断
        searchBST(root.right,val);
        return res;
    }
}

class Solution1 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) return root;

        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}

/**
 * 迭代版本
 * */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && val != root.val)
            root = val < root.val ? root.left : root.right;
        return root;
    }
}