package N450;

public class leetcode450 {
    public static void main(String[] args){
        Solution s = new Solution();
        TreeNode root = new TreeNode(5);
        root.left=new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        s.deleteNode(root,5);
    }
}
/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
 * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 注意：
 * 1. 注意return只能返回一层
 * 2. 这题是二叉搜索树，不仅仅是二叉树
 * 3. root=root.left 不会将该节点置为空
 * 4. 主要方法都是保存一个前驱pre和当时left还是right的boolean值，然后用pre.left=pre.left.left的这种删除中间节点
 * */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
/**
 * 错误代码，通过82/85
 * */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null) return null;
        boolean left=false;
        TreeNode pre = new TreeNode(-1);pre.right=root;//前驱
        TreeNode res = pre;
        TreeNode pre1;//前驱

        // 查找
        while(root!=null && root.val!=key){
            if(key<root.val){
                pre=root;
                root=root.left;
                left=true;
            }else{
                pre=root;
                root=root.right;
                left=false;
            }
        }
        if(root==null) return res.right;//找不到该节点，直接返回

        //没有子节点
        if(root.left==null&&root.right==null) {
            if(pre==res) return null;//根节点，直接找到
            if(left) pre.left=null; else pre.right=null;
        }
        else if(root.left==null){//只有左节点
            if(left) pre.left=root.right; else pre.right=root.right;
        }else if(root.right==null){//只有右节点
            if(left) pre.left=root.left; else pre.right=root.left;
        }else{//左右都有节点
            pre1=root;
            boolean right=true;
            root=root.right;
            while(root.left!=null){
                pre1=root;//保存前驱
                root=root.left;
                right=false;
            }

                if (left) {
                    pre.left.val = root.val;
                } else {
                    pre.right.val = root.val;
                }
                if (right) {
                    pre1.right = root.right;
                } else {
                    pre1.left = null;
                }

        }
        return res.right;
    }
}
/**
 * 官方解答，递归
 * */
class Solution1 {
    /*
    One step right and then always left
    */
    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    /*
    One step left and then always right
    */
    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // delete from the right subtree
        if (key > root.val) root.right = deleteNode(root.right, key);
            // delete from the left subtree
        else if (key < root.val) root.left = deleteNode(root.left, key);
            // delete the current node
        else {
            // the node is a leaf
            if (root.left == null && root.right == null) root = null;
                // the node is not a leaf and has a right child
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            // the node is not a leaf, has no right child, and has a left child
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }
}
