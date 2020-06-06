package N014;

public class leetcode014 {
    public static void main(String[] args){
        Solution s = new Solution();
//        System.out.println(s.longestCommonPrefix(new String[] {"flower","flow","flight"}));
        System.out.println(s.longestCommonPrefix(new String[] {"c","c"}));

    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int len = strs.length;
        if(len==0) return "";
        if(len==1) return strs[0];
        if(strs[0].equals("")) return "";//比较值尽量用equals
        int k=0;
        while(true){
            if(k>=strs[0].length()){ //用来比较的strs[0]也要记得判断是否超出长度
                return strs[0].substring(0,k);
            }
            char tmp = strs[0].charAt(k);
            for(int i=1;i<len;i++){
                if(k>=strs[i].length()||strs[i].charAt(k)!=tmp){
                    return strs[0].substring(0,k);
                }
            }
            k++;
        }
    }
}