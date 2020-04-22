package N102;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * */
public class leetcode102 {
    public static void main(String[] args){
        TreeNode tree = new TreeNode(3);
        tree.left = new TreeNode(9);
        tree.right = new TreeNode(20);
        tree.right.left = new TreeNode(15);
        tree.right.right = new TreeNode(7);

//        tree.right = new TreeNode(2);
//        tree.right.left = new TreeNode(3);

        Solution s = new Solution();
        System.out.println(Arrays.toString(s.levelOrder(tree).toArray()));
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
/**
 * 每次从队列的头部抽出节点，（如果不为null）将其left、right加入队列，
 * 核心思想就是遍历一层的节点后，加入空指针null在队列中，通过检测null来区分层：
 * 如果碰到上一层的null，则说明遍历完了上一层的left、right，就把null加入队列作为分隔符，
 * 如果抽出队列头部节点后队且在加入null分隔符之前队列为空，则return结果。
 * */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ls = new ArrayList<List<Integer>>(); //ls用来保存结果
        if(root==null) return ls;
        List<Integer> l = new ArrayList<Integer>();//用来保存每一层的结果的list，在遍历完每一层后加入ls中
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>(); //设置一个队列用来辅助

        /**先将root和空指针放入队列中，作为第一层*/
        queue.addLast(root);
        queue.addLast(null);

        /**当队列大小为空，则return*/
        while(!queue.isEmpty()){
            TreeNode cur = queue.pollFirst(); //cur为当前从队头抽取节点
            if(cur!=null){//如果cur没检测到分隔符，即不是null空指针，则说明还在这一层，继续向l中加
                l.add(cur.val);
            }else{
                ls.add(l);//否则cur为空指针，则把当前结果全部加入ls中
                if(queue.size()==0) return ls;//如果队列已经空了，就return
                l = new ArrayList<Integer>();//否则说明后面还有其他层，新建一个空list，用来保存下一层的结果
                queue.addLast(null);//由于当前一层所有节点都出队，说明他们的left和right都遍历完，即下一层已全部入队，所以加入null空指针分隔符
                continue;
            }
            //如果cur不是到空指针分隔符，说明队列中当前一层的left、right还没遍历完
            if(cur.left!=null){
                queue.addLast(cur.left);
            }
            if(cur.right!=null){
                queue.addLast(cur.right);
            }
        }
        return ls;
    }
}