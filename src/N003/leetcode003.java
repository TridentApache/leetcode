package N003;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class leetcode003 {
    public static void main(String[] args){
        String s = "nfpdmpi";
        Solution2 sl = new Solution2();
        System.out.println(sl.lengthOfLongestSubstring(s));
    }
}

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 思路：HashMap一遍遍历，遍历的时候有2种情况，字符在HashMap中或不在HashMap中。
 * 设立start初始值为-1，若当前字符已存在HashMap中就更新start值。
 * 最后每次更新maxlen的值和HashMap中的值。
 * */
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        if(s.length()==0) return 0;
        if(s.length()==1) return 1;
        int maxlen=0;
        int start=-1;
        for(int i=0;i<s.length();i++){
            char tmp = s.charAt(i);
            if(map.containsKey(tmp)) {
                start = Math.max(start, map.get(tmp));
            }
            maxlen = Math.max(maxlen,i-start);
            map.put(tmp,i);
        }
        return maxlen;
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        int length = s.length();
        if (length==1) return 1;
        char stmp;
        int ind=1;
        int k=0;//减去
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        for(int i=0;i<length;i++){
            stmp = s.charAt(i);
            if(!map.containsKey(stmp)){
                map.put(stmp,ind);
                if (ind>res)   res=ind;
            }else{
                if(res<ind) res=ind-1;
                k = map.get(stmp);
                HashMap<Character,Integer> maptmp = new HashMap<Character, Integer>();
                maptmp.putAll(map);
                Iterator iter = maptmp.entrySet().iterator();
                while(iter.hasNext()){
                    Map.Entry<Character,Integer> entry = (Map.Entry) iter.next();
                    if(entry.getValue()<=k){
                        map.remove(entry.getKey());
                    }else{
                        map.put(entry.getKey(),entry.getValue()-k);
                    }
                }
                ind=ind-k;
                map.put(stmp,ind);
            }
            ind++;
        }
        return res;
    }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

}