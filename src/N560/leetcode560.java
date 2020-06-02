package N560;

import java.util.HashMap;

public class leetcode560 {
}
/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * 示例 1 :
 *
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 *
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]
 * */

/**
 * 前缀和，找差是k的，双层for循环，勉强通过
 * */
class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            preSum[i+1]=preSum[i]+nums[i];
        }
        int count=0;
        for(int i=0;i<preSum.length-1;i++){
            for(int j=i+1;j<preSum.length;j++){
                if(preSum[j]-preSum[i]==k) count++;
            }
        }
        return count;
    }
}

/**
 * 前缀和优化后的思路，只有一层for循环
 * 优化思路和leetcode第一题“两数之和”类似，通过HashMap
 * */
class Solution1 {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        HashMap< Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k))
                count += mp.get(pre - k);
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
