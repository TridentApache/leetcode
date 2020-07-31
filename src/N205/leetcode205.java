package N205;

import java.util.HashMap;

public class leetcode205 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.isIsomorphic("aa","ab"));
    }
}

class Solution {
    public boolean isIsomorphic(String s, String t) {
        return helper(s,t)&&helper(t,s);
    }

    public boolean helper(String s, String t) {
        int len = t.length();
        if(len!=s.length()) return false;
        StringBuilder sb = new StringBuilder();
        HashMap<Character,Character> map = new HashMap<>();
        for(int i=0;i<len;i++){
            if(map.containsKey(s.charAt(i))){
                sb.append(map.get(s.charAt(i)));
            }else{
                map.put(s.charAt(i),t.charAt(i));
                sb.append(t.charAt(i));
            }
        }
        return sb.toString().equals(t);
    }
}