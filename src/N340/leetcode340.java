package N340;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class leetcode340 {
    public static void main(String[] args){

    }
}

class Solution {
    /**
     *  滑动窗口+有序字典
     *
     * */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n*k == 0) return 0;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);

        int max_len = 1;

        while (right < n) {
            Character character = s.charAt(right);
            // if character is already in the hashmap -
            // delete it, so that after insert it becomes
            // the rightmost element in the hashmap
            if (hashmap.containsKey(character))
                hashmap.remove(character);
            hashmap.put(character, right++);

            // slidewindow contains k + 1 characters
            if (hashmap.size() == k + 1) {
                // delete the leftmost character
                Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();//直接get，会影响默认的插入顺序为访问顺序，而迭代器不会影响
                hashmap.remove(leftmost.getKey());
                // move left pointer of the slidewindow
                left = leftmost.getValue() + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }

    public int lengthOfLongestSubstringKDistinct0(String s, int k) {
        /**
         * 滑动窗口+HashMap
         * 一遍遍历，思路同第159题。
         * 如果HashMap中有当前String中的key,put，更新长度
         * 如果HashMap中没有当前String中的key，判断是否为第k+1个元素，如果是则remove然后put更新start，如果不是就put进HashMap
         * 时间复杂度分析：
         *      对于最好情况，如果字符串不超过 k 个不同字符，答案是肯定的。因为只需要一次遍历就可以得到结果，时间复杂度是 O(N)O(N)。
         *      对于最坏情况，当输入字符串包含 n 个不同字符，答案是不能。因为每一步都需要花费 O(k)O(k) 时间找到哈希表中的最小值，所以总的复杂度是 O(Nk)O(Nk)
         */
        if(k==0) return 0;
        if(k>=s.length()) return s.length();
        HashMap<Character,Integer> map = new HashMap<>();
        int start=-1;
        int i=0;
        int maxlen=0;
        for(;i<s.length();i++){
            char tmp = s.charAt(i);
            if(!map.containsKey(tmp)&&map.size()==k){//
                start = Collections.min(map.values());
                map.remove(s.charAt(start));
            }
            map.put(tmp,i);
            maxlen = Math.max(maxlen,i-start);
        }
        return maxlen;
    }
}