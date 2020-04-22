package N026;

import java.util.Arrays;

/*
* 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
* 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
* 你不需要考虑数组中超出新长度后面的元素。
*
* 注意：Arrays.toString()的用法，题目没有真正覆盖，只是往前挪动了
* 双指针思路，一个快一个慢实现原位
* */

public class leetcode024 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.removeDuplicates(new int[]{1,4,2,3})));
    }
}

class Solution {
    public int[] removeDuplicates(int[] nums) {
        int i=0;
        if (nums.length==0) return null;
        for(int j=1;j<nums.length;j++){
            if(nums[i]!=nums[j]){
                i++;
                nums[i]=nums[j];
            }
        }
        return nums;
    }
}