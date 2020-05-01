package N066;

import java.util.Arrays;

public class leetcode066 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums1=new int[]{1,1};
        System.out.println(Arrays.toString(s.plusOne1(nums1)));
    }
}

class Solution {
    public int[] plusOne1(int[] digits) {
        /**
         * 思路和第67题的二进制相加的位操作有点像，第67题2个步骤循环操作直到无进位，这题也相加直到没有进位
         * */
        int i=digits.length-1;
        int flag=1;//加一看成进位
        while(i>=0 && flag!=0){
            digits[i]+=flag;
            flag=(digits[i])/10;
            digits[i]%=10;
            i--;
        }
        if(i<=0&&flag!=0){
            digits = new int [digits.length+1];
            digits[0]=1;
        }
        return digits;
    }

    /**
     * 别人更精炼的代码！
     *     作者：guanpengchn
     *     链接：https://leetcode-cn.com/problems/plus-one/solution/hua-jie-suan-fa-66-jia-yi-by-guanpengchn/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * */
    public int[] plusOne2(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0)
                return digits;
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }


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
        if(digits[0]==0){
            digits = new int[len+1];
            digits[0]=1;
        }

        return digits;

    }
}