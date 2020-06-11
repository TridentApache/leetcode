package N703;

import java.util.PriorityQueue;

public class leetcode703 {
    public static void main(String[] args){
        int k = 3;
        int[] arr =new int[] {4,5,8,2};
        KthLargest kthLargest = new KthLargest(3, arr);
        kthLargest.add(3);// returns 4
        kthLargest.add(5);// returns 5
        kthLargest.add(10);// returns 5
        kthLargest.add(9);// returns 8
        kthLargest.add(4);// returns 8

    }
}
/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素
 *
 * */

/**
 * 思路： * 二叉搜索树方法，构造BST类
 * 1. KthLargest类初始化（即加入一个array），实际上是通过BST类进行add，并保存要找的第k大的数的k；
 * 2. KthLargest类add，实际上是BST调用add后再search第k大的数。
 * 构建BST的add和search的private方法，并提供对外开发的public方法，private方法维护一个private的TreeNode
 * BST.add(TreeNode node, int val) 通过递归并更新count，如果发现递归已经null就new一个
 * BST.search(TreeNode node, int k) 判断kth在哪颗子树上，kth比右子树+当前节点数大，则说明在左子树（kth越大越在小的上搜），
 * k > 当前结点数加右子树的结点数，则搜索左子树
 * k <= 右子树的结点数，则搜索右子树
 * k == 当前结点数加右子树的结点数，则找到第k大的结点
 * */
class KthLargest {

    private class BST {

        private class TreeNode {

            private int val;
            // 结点的count包含自己，所以默认是1
            private int count = 1;
            private TreeNode left;
            private TreeNode right;

            TreeNode(int x) { val = x; }
        }

        private TreeNode root;

        public void add(int val) {
            root = add(root, val);
        }

        private TreeNode add(TreeNode node, int val) {
            if (node == null) {
                return new TreeNode(val);
            }
            if (node.val > val) {
                node.left = add(node.left, val);
            } else if (node.val < val) {
                node.right = add(node.right, val);
            }
            // 元素重复 不添加进树但是count++
            node.count++;
            return node;
        }

        public TreeNode search(int k) {
            return search(root, k);
        }

        private TreeNode search(TreeNode node, int k) {
            if (node == null) {
                return node;
            }
            int leftNodeCount = node.left != null ? node.left.count : 0;
            int rightNodeCount = node.right != null ? node.right.count : 0;
            int currNodeCount = node.count - leftNodeCount - rightNodeCount;
            if (k > currNodeCount + rightNodeCount ) {
                // k > 当前结点数加右子树的结点数，则搜索左子树
                return search(node.left, k - currNodeCount - rightNodeCount);
            } else if (k <= rightNodeCount) {
                // k <= 右子树的结点数，则搜索右子树
                return search(node.right, k);
            } else {
                // k == 当前结点数加右子树的结点数，则找到第k大的结点
                return node;
            }
        }
    }

    private int k;
    private BST bst = new BST();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            bst.add(n);
        }
    }

    public int add(int val) {
        bst.add(val);
        return bst.search(k).val;
    }
}

/**
 * 小顶堆实现
 * */
class Solution1 {
    class KthLargest {
        final PriorityQueue<Integer> pq;
        final int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            pq = new PriorityQueue<>(k);

            for (int i : nums) {//对传进来的int数组遍历
                add(i);
            }
        }

        public int add(int val) {
            if (pq.size() < k)//如果队列中的数量少于K，直接添加入优先队列，优先队列会自动维持小顶堆
                pq.offer(val);
            else {
                if (pq.peek() < val) {//否则队列中的数量大于或者等于K，优先队列中的最小数字小于新的数据，优先队列中的顶堆要被移除，并且添加入新的数据进优先队列
                    pq.poll();
                    pq.offer(val);

                }

            }
            return pq.peek();//返回当前第K大的数

        }
    }
}