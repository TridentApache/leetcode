package Offer07;

import java.util.HashMap;
import java.util.Map;

public class Offer07 {
    public static void main(String[] args){
        Solution s = new Solution();
//        s.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        s.buildTree(new int[]{3,9,7,5,6,12}, new int[]{9,7,3,6,5,12});
    }
}

/**
 * Definition for a binary tree node.*/
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

//根据preorder中的根ps开始，在inorder中找到根，并将inorder分成左子树、根、右子树三部分
//自顶向下的递归
class Solution {
    HashMap<Integer,Integer> map = new HashMap<>();
    int[] preor;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preor = preorder;
        int len = inorder.length;
        for(int i=0;i<len;i++) this.map.put(inorder[i],i);
        return buildTree(0,len-1,0,len-1);
    }
    public TreeNode buildTree(int ps, int pe, int is, int ie){
        if(pe<ps||ie<is){return null;}

        int root=preor[ps];
        TreeNode node = new TreeNode(root);
        int ir = map.get(root);

        node.left = buildTree(ps+1,ps+ir-is,is,ir-1);
        node.right = buildTree(ps+ir-is+1,pe,ir+1,ie);
        return node;
    }
}

class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        int length = preorder.length;
        for (int i = 0; i < length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, length - 1, inorder, 0, length - 1, indexMap);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int preorderStart, int preorderEnd, int[] inorder, int inorderStart, int inorderEnd, Map<Integer, Integer> indexMap) {
        if (preorderStart > preorderEnd) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        TreeNode root = new TreeNode(rootVal);
        if (preorderStart == preorderEnd) {
            return root;
        } else {
            int rootIndex = indexMap.get(rootVal);
            int leftNodes = rootIndex - inorderStart, rightNodes = inorderEnd - rootIndex;
            TreeNode leftSubtree = buildTree(preorder, preorderStart + 1, preorderStart + leftNodes, inorder, inorderStart, rootIndex - 1, indexMap);
            TreeNode rightSubtree = buildTree(preorder, preorderEnd - rightNodes + 1, preorderEnd, inorder, rootIndex + 1, inorderEnd, indexMap);
            root.left = leftSubtree;
            root.right = rightSubtree;
            return root;
        }
    }
}

