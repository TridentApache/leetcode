package N145;

import java.util.*;

public class leetcode145 {
    public static void main(String[] args){
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.left.right.left = new TreeNode(7);
        tree.left.right.right = new TreeNode(8);
        tree.right.right = new TreeNode(6);

//        tree.right = new TreeNode(2);
//        tree.right.left = new TreeNode(3);

        Solution1 s = new Solution1();
        s.postOrderIteration2(tree);
//        List<Integer> l= s.postorderTraversal(tree);
//        System.out.println(l);
    }


}

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> treeList = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            //与中序不同，不直接pop出来，而是先看看有没有右节点
            TreeNode temp = stack.peek();
            //没有右节点就pop出来进表
            //不要root=stack.pop()，这样root就是最后的null，下一轮就会跳过while循环
            if (temp.right == null) {
                treeList.add(stack.pop().val);
            }
            //有的话就去遍历右节点，
            //并把当前节点的右节点设为空，否则就无尽循环了，
            //所以要用临时变量temp，
            //root=temp.right后root已变为当前节点的右节点
            //直接root.right=null就是把右节点的右节点设为空了
            else {
                root = temp.right;
                temp.right = null;
            }
        }
        return treeList;
    }
}

/**
 * leetcode官方解析，通过一个LinkedList每次从后面抽出最后一个元素，另一个LinkedList从队头进入，完成树的后序遍历
 * 其实就是先序遍历的逆向思维
 * */
class leSolution{
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast(); //每次从栈顶取出
            output.addFirst(node.val);//先压入中间节点
            if (node.left != null) { //将left先压入，再压入right，这样从栈顶出去就是right
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
    }

}


class Solution1{
    /**
     * 通过2个栈来实现后序遍历，先序遍历是中左右，把转化为中右左，再通过一个栈逆向打印
     * */
    public static void postOrderIteration(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }
    /**
     *
     * */
    public static void postOrderIteration2(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur = head;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null && peek.left != cur && peek.right != cur) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != cur) {
                stack.push(peek.right);
            } else {
                System.out.print(stack.pop().val + " ");
                cur = peek;
            }
        }
    }

}
/**
 * 自己写的，有bug
 * */
class mySolution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ls = new ArrayList<>();
        LinkedList<List<Object>> s = new LinkedList<>();
        while(!s.isEmpty() || root!=null ){
            while(root.left!=null|| root.right!=null){
                if(root.left!=null) {
                    s.addLast(Arrays.asList(root, false));
                    root = root.left;
                }

                while(root.left==null && root.right!=null){
                    s.addLast(Arrays.asList(root,true));
                    root=root.right;
                }
            }

            ls.add(root.val);

            if(root.left==null && root.right==null && (boolean)s.getLast().get(1)==true){ //添加中间节点
                while(!s.isEmpty()&&(boolean)s.getLast().get(1)==true){
                    root=(TreeNode)s.removeLast().get(0);
                    ls.add(root.val);
                }

            }

            if(s.isEmpty() || (s.size()==1&&(boolean)s.getFirst().get(1)==true)){
                return ls;
            }

            root=(TreeNode)s.getLast().get(0);
            List<Object> l = s.removeLast();
            l.set(1,true);
            s.addLast(l);


            if(root.right!=null) {
                root = root.right;
            }
        }
        return ls;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
