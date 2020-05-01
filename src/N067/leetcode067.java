package N067;

import java.math.BigInteger;
import java.util.Scanner;

public class leetcode067 {
    public static void main(String[] args){
        Solution s = new Solution();
        Scanner sc = new Scanner(System.in);
        String str1 = new String();
        str1 = sc.next();
        String str2 = new String();
        str2 = sc.next();
        System.out.println(s.addBinary2(str1,str2));
    }
}
/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * */
class Solution {
    /**
     * 思路和第2题链表（两数）相加思路类似，设置len1，len2和flag，然而要3ms，39.9MB
     * */
    public String addBinary(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        int len=len1>len2?len1:len2;
        int flag=0;
        int val1,val2=0;
        StringBuilder sb = new StringBuilder();
        while(!(len1<=0&&len2<=0&&flag==0)){
            if(len1<=0) val1=0;
            else {val1=a.charAt(len1-1)-'0';len1--;}
            if(len2<=0) val2=0;
            else {val2=b.charAt(len2-1)-'0';len2--;}

            sb.insert(0,(val1+val2+flag)%2);
            flag=(val1+val2+flag)/2;

            if(len1<=0&&len2<=0&&flag!=0){
                sb.insert(0,1);
                flag=0;
            }
        }
        return sb.toString();
    }
    /**
     * 思路：
     * 注意大数的情况
     * 1. a XOR b : 得到两个数字无进位相加的结果。
     * 2. (a&b)<<1 : 得到两个数组相加的进位
     * 循环步骤1和2直到没有进位
     * */
        public String addBinary2(String a, String b) {
            BigInteger x = new BigInteger(a, 2); //radix=2
            BigInteger y = new BigInteger(b, 2);
            BigInteger zero = new BigInteger("0", 2);
            BigInteger carry, answer;
            while (y.compareTo(zero) != 0) {
                answer = x.xor(y);
                carry = x.and(y).shiftLeft(1);
                x = answer;
                y = carry;
            }
            return x.toString(2);
        }
}