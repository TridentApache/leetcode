package N705;


/**
 * 不使用任何内建的哈希表库设计一个哈希集合
 * 具体地说，你的设计应该包含以下的功能
 *
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * 示例:
 *
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);        
 * hashSet.add(2);        
 * hashSet.contains(1);    // 返回 true
 * hashSet.contains(3);    // 返回 false (未找到)
 * hashSet.add(2);          
 * hashSet.contains(2);    // 返回 true
 * hashSet.remove(2);          
 * hashSet.contains(2);    // 返回  false (已经被删除)
 *
 * */
public class leetcode705 {
    public static void main(String[] args){
         MyHashSet obj = new MyHashSet();
         obj.add(1);
        obj.remove(1);
        boolean param_3 = obj.contains(2);
    }
}

class MyHashSet {
    private Bucket[] bucketArray;
    private int keyRange;//随便设置的一个长度，模拟HashCode

    /** Initialize your data structure here. */
    public MyHashSet() {
        this.keyRange=769;
        this.bucketArray = new Bucket[this.keyRange];
        for(int i=0;i<this.keyRange;i++){
            this.bucketArray[i]=new Bucket();
        }
    }

    protected int _hash(int key){
        return (key%this.keyRange);
    }

    public void add(int key) {
        int bucketIndex = _hash(key);
        this.bucketArray[bucketIndex].insert(key);
    }

    public void remove(int key) {
        int bucketIndex = _hash(key);
        this.bucketArray[bucketIndex].delete(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int bucketIndex = _hash(key);
        return this.bucketArray[bucketIndex].exists(key);
    }

    /**
     * 桶
     * */
    class Bucket{
        private BSTree tree;
        public Bucket(){
            tree = new BSTree();
        }
        public void insert(Integer key) {
            this.tree.root = tree.insertIntoBST(this.tree.root,key);
        }
        public void delete(Integer key){
            this.tree.root = tree.deleteNode(this.tree.root,key);
        }
        public boolean exists(Integer key){
            TreeNode node = this.tree.searchBST(this.tree.root,key);
            return (node!=null);
        }
    }

    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val=val;
        }
    }
    /**搜索二叉树
     * insertIntoBST
     * deleteNode
     * searchBST
     * */
    class BSTree{
        TreeNode root = null;
        public TreeNode searchBST(TreeNode root, int val){
            if(root==null||val==root.val)
                return root;
            return val<root.val?searchBST(root.left,val):searchBST(root.right,val);
        }
        public TreeNode insertIntoBST(TreeNode root, int val){
            if(root==null) return new TreeNode(val);
            if(val>root.val)
                root.right=insertIntoBST(root.right,val);
            else if(val==root.val)
                return root;
            else
                root.left=insertIntoBST(root.left,val);
            return root;
        }
        public TreeNode deleteNode(TreeNode root, int key){
            if(root==null) return null;
            if(key>root.val)
                root.right=deleteNode(root.right,key);
            else if(key<root.val)
                root.left=deleteNode(root.left,key);
            else{
                //待删除节点是叶子
                if(root.left==null && root.right==null)
                    root=null;
                //待删除节点不是叶子节点并且有right child
                else if(root.right!=null){
                    root.val=successor(root);//调用successor赋值右子树中最小数给现在的root
                    root.right=deleteNode(root.right,root.val);
                }
                //待删除节点不是叶子节点但只有left child
                else{
                    root.val=predecessor(root);//调用predecessor赋值左子树中最大的数
                    root.left=deleteNode(root.left,root.val);
                }
            }
            return root;
        }
        public int successor(TreeNode root){
            root=root.right;
            while(root.left!=null)
                root=root.left;
            return root.val;
        }
        public int predecessor(TreeNode root){
            root = root.left;
            while(root.right!=null)
                root=root.right;
            return root.val;
        }
    }
}