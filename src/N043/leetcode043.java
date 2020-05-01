package N043;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * 注意：写for循环递减--的时候，不要误写成++！！
 * */
public class leetcode043 {
    public static void main(String[] args){
        Solution s = new Solution();
        /**读取字符串，空格或换行结束**/
        Scanner sc = new Scanner(System.in);
        String str1 = new String();
        str1=sc.next();//包括空格则用sc.nextLine();
        String str2 = new String();
        str2=sc.next();
        System.out.println(s.multiply(str1,str2));
    }
}
/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * */

class Solution {
    /**
     * 两个指针 i，j 在 num1 和 num2 上游走，计算乘积，同时将乘积叠加到 res 的正确位置
     * https://leetcode-cn.com/problems/multiply-strings/solution/gao-pin-mian-shi-xi-lie-zi-fu-chuan-cheng-fa-by-la/
     * */
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1+len2];//最长为len1+len2位
        StringBuilder sb = new StringBuilder();
        for(int i=len1-1;i>=0;i--){
            for(int j=len2-1;j>=0;j--){
                int mul = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int p1=i+j;//叠加的位置
                int p2=p1+1;
                int sum = mul+res[p2];
                res[p2]=sum%10;
                res[p1]=res[p1]+sum/10;
            }
        }
        //设置一个指针，移动到第一个非0位，开始转化为字符串
        int i=0;
        while(i<res.length&&res[i]==0) i++;
        for(;i<res.length;i++){
            sb.append(String.valueOf(res[i]));
        }
        return sb.length()==0? "0" :sb.toString(); //注意0的情况
    }

    /**BigInteger直接求解*/
    public String multiplyByBigInteger(String num1, String num2) {
        BigInteger n1 = new BigInteger(num1);
        n1 = n1.multiply(new BigInteger(num2));
        return n1.toString();
    }
}