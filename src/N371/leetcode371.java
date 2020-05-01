package N371;

public class leetcode371 {
    public static void main(String[] args){
        Solution s= new Solution();
        System.out.println(s.getSum(11,1));
    }
}
/**
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * 思路：这题和第66、67题很像，都是两种操作，异或操作：无进位加；与操作并左移1位：得到进位（当进位位0时，停止操作）.
 *
 * */
class Solution {
    public int getSum(int a, int b) {
            if(a==0) return b;
            if(b==0) return a;
            int tmp=0;
            int carrier=0;
            while(true){
                tmp=a^b;
                carrier=a&b;
                if(carrier==0) break;
                a=tmp;
                b=carrier<<1;
            }
            return tmp;
    }
}