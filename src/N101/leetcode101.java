package N101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode101 {
    public static void main(String[] args){
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.left.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.right.left = new TreeNode(2);

//        tree.right = new TreeNode(2);
//        tree.right.left = new TreeNode(3);

        Solution s = new Solution();
        System.out.println(s.isSymmetric(tree));
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**树的对称判断，本来想用先序遍历，但是有case是不对的，又想用层序遍历，觉得复杂度太高，递归掌握的不熟！*/
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return is_Symmetic(root,root);

    }
    public boolean is_Symmetic(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null){
            return true;
        }else if(root1==null || root2==null){
            return false;
        }
        return root1.val==root2.val && is_Symmetic(root1.left,root2.right) && is_Symmetic(root1.right,root2.left);

    }
}
class Solution1 {
    List<Integer> orderres=new ArrayList<>();
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;
        orderTree(root);
        int len = this.orderres.size();
        for(int i=0,j=len-1;i<j;i++,j--){
            if(this.orderres.get(i)!=this.orderres.get(j)){
                return false;
            }
        }
        return true;
    }
    private List<Integer> orderTree(TreeNode root){
        if(root==null){
            orderres.add(null);
            return orderres;
        }

        orderTree(root.left);
        orderres.add(root.val);
        orderTree(root.right);

        return orderres;
    }
}