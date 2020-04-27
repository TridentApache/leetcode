package N159;

import java.util.*;

public class leetcode159 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstringTwoDistinct("ababcc"));
    }

}

/**
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t 。
 *
 * 示例 1:
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * */

/**
 *  思路：这个方法和第三题题有点像，都是维护了一个表，设置start初始为-1，
 *  不过第三题“找出其中不含有重复字符的 最长子串 的长度”，需要一次遍历并put到HashMap；
 *  但是这题我用了一个ArrayList，只保存2个不同的字符，维护他们对应的当前最大的起始位，一次遍历完成。
 *  如果遍历过程的当前元素是表中的旧元素，根据start计算当前maxlen，并更新表中旧元素的最大起始位；
 *  如果遍历过程的当前元素是表中的新元素，找到旧元素中起始位最小的，然后用新元素替换掉。
 *
 *  当然用HashMap也可以，见Solution2，本来也想用HashMap，但是对Collections不熟悉，所以写成ArrayList了，
 *  Collections.min(hashmap.values())找到map中最小的元素
 * */

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length()<=2) return s.length();
        List<List<Integer>> ls = new ArrayList<List<Integer>>();
        int maxlen=0;
        int start=-1;
        for(int i=0;i<s.length();i++){
            char tmp = s.charAt(i);
            if(ls.size()>=1 && ls.get(0).get(0)==Integer.valueOf(tmp)){//判断是否为新元素，与List中第一个比较
                ls.set(0, Arrays.asList(Integer.valueOf(tmp),i));
                maxlen = Math.max(maxlen,i-start);
            }else if(ls.size()>1 && ls.get(1).get(0)==Integer.valueOf(tmp)){//判断是否为新元素，与List中第二个比较
                ls.set(1,Arrays.asList(Integer.valueOf(tmp),i));
                maxlen = Math.max(maxlen,i-start);
            }else{//新元素，判断表中是否有2个不同元素，并用新元素替换较小的那个
                if(ls.size()<=1){//如果表中元素不到1个，说明目前只有一种元素，新的元素加入直接添加
                    ls.add(Arrays.asList(Integer.valueOf(tmp),i));
                    maxlen++;
                    continue;
                }
                if(ls.get(0).get(1)<ls.get(1).get(1)){
                    start=ls.get(0).get(1);
                    ls.remove(0);
                    ls.add(Arrays.asList(Integer.valueOf(tmp),i));
                }else{
                    start=ls.get(1).get(1);
                    ls.remove(1);
                    ls.add(Arrays.asList(Integer.valueOf(tmp),i));
                }
            }
        }
        return maxlen;
    }
}

class Solution2 {
    /**
     * 滑动窗口
     * */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length();
        if (n < 3) return n;

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

        int max_len = 2;

        while (right < n) {
            // slidewindow contains less than 3 characters
            if (hashmap.size() < 3)
                hashmap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (hashmap.size() == 3) {
                // delete the leftmost character
                int del_idx = Collections.min(hashmap.values());
                hashmap.remove(s.charAt(del_idx));
                // move left pointer of the slidewindow
                left = del_idx + 1;
            }

            max_len = Math.max(max_len, right - left);
        }
        return max_len;
    }
}
