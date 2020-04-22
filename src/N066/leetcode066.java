package N066;

import java.util.Arrays;

public class leetcode066 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums1=new int[]{9,9};
        s.plusOne(nums1);
        System.out.println(Arrays.toString(nums1));
    }
}

class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int flag;
        if(digits[len-1]+1==10){
            flag=1;
            digits[len-1]=0;
        }else{
            flag=0;
            digits[len-1]=digits[len-1]+1;
        }
        for(int i=len-2;i>=0;i--){
            if(digits[i]+flag==10){
                digits[i]=0;
                flag=1;
            }else{
                digits[i]=digits[i]+flag;
                flag=0;
            }
        }
        // if(digits[0]==0){
        //     int[] res = new int[len+1];
        //     res[0]=1;
        //     for(int i=1;i<len+1;i++){
        //         res[i]=digits[i-1];
        //     }
        //     digits=res;
        // }
        if(digits[0]==0){
            digits = new int[len+1];
            digits[0]=1;
        }

        return digits;

    }
}