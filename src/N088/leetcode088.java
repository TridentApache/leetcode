package N088;

import java.util.Arrays;

public class leetcode088 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums1=new int[]{4,5,6,0,0,0};
        s.merge1(nums1,3,new int[]{1,2,3},3);
        System.out.println(Arrays.toString(nums1));
    }
}

class Solution {
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        if(m==0) {
            for(int i=0;i<n;i++){
                nums1[i]=nums2[i];
            }
            return;
        }
        if(n==0) {return;}
        for(int i=m-1,j=n-1;i>=0&&j>=0;){
            if(nums1[i]>nums2[j]){
                nums1[i+j+1]=nums1[i];
                i--;
            }else{
                nums1[i+j+1]=nums2[j];
                j--;
            }
            if(j>=0&&i<0)
                System.arraycopy(nums2, 0, nums1, 0, j+1);
        }

    }
    public void merge(int[] nums1, int m, int[] nums2, int n){
        int len1 = m-1;
        int len2 = n-1;
        int len = m+n-1;
        while(len1>=0 && len2>=0){
            nums1[len--] = nums1[len1] > nums2[len2] ? nums1[len1--] : nums2[len2--];
        }
        System.arraycopy(nums2, 0, nums1, 0, len2 + 1);
    }
}