package N035;


/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 如：输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 注意：递归的终止判断条件，既要包含对原数组直接的判断，也要包含递归后的判断
 * */
public class leetcode035 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = new int[]{1,2,3,5,6};
        System.out.println(s.searchInsert(nums,5));
    }
}

class Solution {
    public int searchInsert(int[] nums, int target) {
        return search(nums,target,0,nums.length-1);
    }
    public int search(int[] nums, int target, int i,int j) {
        if (nums.length==0) return 0;
        if (nums.length==1||i==j){
            if (nums[i]==target) return i;
            else if (nums[i]>target) return i;
            else return i+1;
        }
        if (nums.length==2||i+1==j){
            if (nums[i]==target) return i;
            if (nums[j]==target) return j;
            if (nums[i]<target && target<nums[j]) return i+1;
            else if(nums[i]>target) return i;
            else if (nums[j]<target) return j+1;
        }
        int mid = (i+j)/2;
        if (nums[mid]==target) return mid;
        if(target<nums[mid]) return search(nums,target,i,mid);
        else return search(nums,target,mid+1,j);
    }
}

/**
 * 二分查找,注意是i<=j
 * */
class Solution2 {
    public int searchInsert(int[] nums, int target) {
        int i=0;
        int j=nums.length-1;
        while(i<=j){ //注意
            int mid=(i+j)/2;
            if(nums[mid]==target) return mid;
            else if(nums[mid]>target){
                j=mid-1;
            }else{
                i=mid+1;
            }
        }
        return i;
    }
}