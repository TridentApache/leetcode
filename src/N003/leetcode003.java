package N003;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class leetcode003 {
    public static void main(String[] args){
        String s = "nfpdmpi";
        Solution sl = new Solution();
        System.out.println(sl.lengthOfLongestSubstring2(s));
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