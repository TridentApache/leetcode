package N110;

public class leetcode110 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        root.right.right.right = new TreeNode(1);

        Solution s = new Solution();
        s.isBalanced(root);
    }
}
/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * */


/**
 * 自底而上的递归
 * */
class Solution {
    private boolean flag=false;
    public boolean isBalanced(TreeNode root) {
        isBalanced(root,0);
        if(flag)
            return false;
        return true;
    }
    private long isBalanced(TreeNode root,int h) {
        if(root==null) return h;

        long h1 = isBalanced(root.left,h+1);
        long h2 = isBalanced(root.right,h+1);
        if(Math.abs(h1-h2)>1){
            flag=true;
            return 0;
        }
        return h1>h2?h1:h2;
    }
}
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * 自顶而下的解法：
 * 每个高度为h的节点，算是否平衡的时候，都会遍历一次所有子节点。根据斐波拉契数列，因为平衡树的高度不大于O(log_1.5(n))，
 * 因此保证方法 height 在每个节点上调用不超过 O(logn) 次，
 * 一共有n个节点，每个节点调用不超过O(logn)次，所以该算法复杂度一共O(nlogn)
 * */
/***
 * 知识点：
 * 一棵平衡二叉树最少节点数量：
 * 令 f(h) 表示一棵高度为 h 的平衡二叉树需要的最少节点数量。
 * f(h) = f(h - 1) + f(h - 2) + 1
 * */
class Solution1 {
    // Recursively obtain the height of a tree. An empty tree has -1 height
    private int height(TreeNode root) {
        // An empty tree has height -1
        if (root == null) {
            return -1;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    public boolean isBalanced(TreeNode root) {
        // An empty tree satisfies the definition of a balanced tree
        if (root == null) {
            return true;
        }

        // Check if subtrees have height within 1. If they do, check if the
        // subtrees are balanced
        return Math.abs(height(root.left) - height(root.right)) < 2
                && isBalanced(root.left)
                && isBalanced(root.right);
    }
};
