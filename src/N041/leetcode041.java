package N041;

import java.util.HashMap;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 *
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 *
 * 注意：考这种是否出现的问题，我们很直观的想到哈希表。
 * 但是要求空间复杂度为常数，因此需要考虑直接在原数组上建立哈希表
 * */
public class leetcode041 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{3,-1,23,7,21,12,8,9,18,21,-1,16,1,13,-3,22,23,13,7,14,3,6,4,-3};
        System.out.println(s.firstMissingPositive(nums));
    }
}

class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while(nums[i] != i + 1 && nums[i] > 0 && nums[i] < nums.length && nums[i] != nums[nums[i] - 1]){
                if(nums[nums[i]-1]<=0)
                    nums[nums[i]-1]=nums[i];
                else
                    swap(nums,i,nums[i]-1);
            }
        }
        for(int i=0;i<nums.length;i++){
            if(i+1!=nums[i]) return i+1;
        }
        return nums.length+1;
    }
    public void swap(int[] nums, int a, int b){
        int tmp = nums[b];
        nums[b]=nums[a];
        nums[a]=tmp;
    }

    public int firstMissingPositive1(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(nums[i]<=0) continue;
            map.put(nums[i],1);
        }
        for(int i=1;i<nums.length+1;i++){ //1~n+1是否出现
            if(!map.containsKey(i)){
                return i;
            }
        }
        return nums.length+1;
    }
}