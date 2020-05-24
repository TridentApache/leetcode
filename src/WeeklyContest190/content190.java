package WeeklyContest190;

import java.util.*;

public class content190 {
    public static void main(String[] args) {
//        Solution1 s1 = new Solution1();
//        System.out.println(s1.isPrefixOfWord("dumb dream duck duck i","drea"));

//        Solution2 s2 = new Solution2();
//        System.out.println(s2.maxVowels("rhythms", 4));

        Solution3 s3 = new Solution3();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(9);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        root.right.right.left.left = new TreeNode(5);
        root.right.right.left.right = new TreeNode(4);
        root.right.right.left.right.left = new TreeNode(8);
        System.out.println(s3.pseudoPalindromicPaths(root));
    }
}

class Solution1 {
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] s = sentence.split(" ");
        int len = s.length;
        label:
        for (int i = 0; i < len; i++) {
            if (searchWord.charAt(0) == s[i].charAt(0)) {
                for (int j = 0; j < searchWord.length(); j++) {
                    if (searchWord.length() > s[i].length() || searchWord.charAt(j) != s[i].charAt(j))
                        continue label;
                }
                return i + 1;
            }
        }
        return -1;
    }
}

/**
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 * <p>
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 */
class Solution2 {
    public int maxVowels(String s, int k) {
        int len = s.length();
        HashSet<Character> set = new HashSet<>();//Collection List????
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int res = 0;
        LinkedList<Integer> list = new LinkedList<>();
        int i = 0;
        while (i < s.length() && (!set.contains(s.charAt(i)))) {
            i++;
        }
        if (i == s.length()) return 0;
        for (; i < len; i++) {
            if (!set.contains(s.charAt(i))) {
                continue;
            }
            list.addLast(i);
            while (i - list.get(0) + 1 > k) {
                list.removeFirst();
            }
            res = res > list.size() ? res : list.size();
        }
        return res;
    }
}


/**
 * 给你一棵二叉树，每个节点的值为 1 到 9 。我们称二叉树中的一条路径是 「伪回文」的，当它满足：路径经过的所有节点值的排列中，存在一个回文序列。
 * 请你返回从根到叶子节点的所有路径中 伪回文 路径的数目。
 */

class Solution3 {
    int count;
    public int pseudoPalindromicPaths(TreeNode root) {
        HashMap<Integer,Integer> map = new HashMap<>();
        count=0;
        preorder(root,map);
        return count;
    }
    private void preorder(TreeNode root,HashMap<Integer,Integer> map){
        if(root==null) {
            return;
        }

        map.put(root.val,map.getOrDefault(root.val,0)+1);

//        if(map.containsKey(root.val)) {
//            map.put(root.val, map.get(root.val) + 1);
//        }else{
//            map.put(root.val, 1);
//        }

        preorder(root.left,map);
        update(root.left,map);
        preorder(root.right,map);
        update(root.right,map);

        if(root.left==null&&root.right==null){
            cal(map);
        }
    }

    private void update(TreeNode root,HashMap<Integer,Integer> map){
        if(root!=null&&map.get(root.val)==1){
            map.remove(root.val);
        }else if(root!=null&&map.get(root.val)!=1){
            map.put(root.val,map.get(root.val)-1);
        }
    }
    private void cal(HashMap<Integer,Integer> map){
        //计算回文
        int flag = 0;
        for (int i = 1; i <= 9; i++) {
            if (map.containsKey(i) && map.get(i) % 2 == 1 && flag <= 1)
                flag++;
            if (flag > 1){
                return;
            }
        }
        count++;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
