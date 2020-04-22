package N031;

import java.util.Arrays;
/**
 *
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * 注意：
 * 1. 双指针数组逆序基础太差！i<=j-1时交换，比如i=0,j=2中间夹着最后1个数时要交换，然后i=1,j=1;比如i=0,j=1中间没有数要停止
 * 2. 这题思路仍然时双指针，从后往前找到不满足逆序的数，然后将后面的部分升序排列
 * 3. 但是之前都是降序找过来的，并且换掉的数字也是仍然满足降序性质，所以直接反转就行了
 * */

public class leetcode031 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = new int[]{1,4,3,2,2};
        s.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums.length<=1) return;
        int flag=0;
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]>=nums[i+1]){
                flag++;
                continue;
            }else{
                for(int j=i+1;j<nums.length;j++){
                    if(nums[j]>nums[i]){
                        continue;
                    }
                    int tmp = nums[j-1];
                    nums[j-1]=nums[i];
                    nums[i]=tmp;
                    for(int k=i+1,l=nums.length-1;k<=l-1;k++,l--){
                        tmp = nums[l];
                        nums[l]=nums[k];
                        nums[k]=tmp;
                    }
                    return;
                }
                int tmp = nums[nums.length-1];
                nums[nums.length-1]=nums[i];
                nums[i]=tmp;
                for(int k=i+1,l=nums.length-1;k<=l-1;k++,l--){
                    tmp = nums[l];
                    nums[l]=nums[k];
                    nums[k]=tmp;
                }
                return;
            }
        }
        if(flag==nums.length-1){
            for(int i=0,j=nums.length-1;i<j;i++,j--){
                int tmp = nums[j];
                nums[j]=nums[i];
                nums[i]=tmp;
            }
        }
        return;
    }
}