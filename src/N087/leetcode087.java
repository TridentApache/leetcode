package N087;

import java.util.*;

public class leetcode087 {
    public static void main(String[] args){
//        String s= "word";
//       System.out.println(s.substring(1,3));
       TreeNode tree = new TreeNode(1);
        LinkedList<List<Object>> ls = new LinkedList<>();
        ls.addLast(Arrays.asList(tree,false));
        List<Object> l = ls.removeLast();
        l.set(1,true);

        ls.addLast(l);
        System.out.println(ls.removeLast().get(1));
    }
}
 class TreeNode {
    int val;
     TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
