package N153;

public class leetcode153 {
    public static void main(String[] args){
        Solution s = new Solution();
        int ls = s.findMin(new int[]{1,2,3,3,0,1});
        System.out.println(ls);
    }
}

class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        if(len==2) return nums[0]>nums[1]?nums[1]:nums[0];

        int i=0;
        int j=len-1;
        int mid;
        while(true){
            mid=i+(j-i)/2;//防止溢出
            if(i==j) return nums[i];
            if(i+1==j) return nums[i]>nums[i+1]?nums[i+1]:nums[i];
            if(nums[mid]<nums[mid-1]) return nums[mid];
            if(nums[mid+1]<nums[mid]) return nums[mid+1];

            if(nums[mid]>nums[j]){
                i=mid+1;
            }else if(nums[mid]<nums[j]){
                j=mid;
            }else{ //排除相同项
                j--;
            }
        }
    }
    public int findMin0(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        if(len==2) return nums[0]>nums[1]?nums[1]:nums[0];

        int i=0;
        int j=len-1;
        int mid;
        while(true){
            mid=(i+j)/2;
            if(i==j) return nums[i];
            if(i+1==j) return nums[i]>nums[i+1]?nums[i+1]:nums[i];
            if(nums[i]<nums[mid]){
                if(nums[i]>nums[j]) i=mid+1;
                else if(nums[mid]<nums[mid+1]) j=mid;
            }else{
                j=mid;
            }
        }
    }
}