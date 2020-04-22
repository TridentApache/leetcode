package N169;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * */
public class leetcode169 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.majorityElement(new int[]{3,2,3}));
    }
}

class Solution {
    public int majorityElement(int[] nums) {
        int len = nums.length;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<len;i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }else{
                map.put(nums[i],1);
            }
        }
        Iterator it = map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,Integer> e = (Map.Entry)it.next();
            if(e.getValue()>len/2){
                return e.getKey();
            }
        }
        return 0;
    }
}