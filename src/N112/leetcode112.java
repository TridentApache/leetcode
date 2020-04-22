package N112;

public class leetcode112 {
    public static void main(String[] args){
        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(4);
        tree.right = new TreeNode(8);
        tree.left.left = new TreeNode(11);
        tree.left.left.left = new TreeNode(7);
        tree.left.left.right = new TreeNode(2);
        tree.right = new TreeNode(8);
        tree.right.left = new TreeNode(13);
        tree.right.right = new TreeNode(4);
        tree.right.right.right = new TreeNode(1);

//            TreeNode tree = new TreeNode(1);
//            tree.left = new TreeNode(2);
//        tree.right = new TreeNode(2);
//        tree.right.left = new TreeNode(3);

        Solution s = new Solution();
        System.out.println(s.hasPathSum(tree,22));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {
    boolean x1=false;
    boolean x2=false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        return hasSum(root, sum);
    }

    public boolean hasSum(TreeNode root,int sum){
        if(root.left==null&&root.right==null){
            if(sum==root.val) return true;
            return false;
        }
        if(root.left!=null){
             x1= hasSum(root.left,sum-root.val);
        }
        if(x1) return true;

        if(root.right!=null){
            x2 = hasSum(root.right,sum-root.val);
        }
        if(x2) return true;

        return x1||x2;
    }
}