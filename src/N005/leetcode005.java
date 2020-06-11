package N005;


import java.util.Arrays;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 注意：这是一道典型的动态规划题。
 * 如果用遍历一遍的中心扩散法会重复很多次，比如遍历经过1个会文中，会一直重复。动态规划把这些记录下来，用空间换时间。
 * 1. 动态规划题首先要明确初始状态和状态转移方程。
 * 2. 初始状态，l=r 时，此时 dp[l][r]=true。
 *    状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true。
 * */

public class leetcode005 {
    public static void main(String[] args){
        Solution s = new Solution();
        char[] c = new char[]{'a','b'};
        System.out.println(Arrays.toString(c));
        //System.out.println(s.longestPalindrome("babad"));
    }
}

class Solution {
    public String longestPalindrome(String s) {

        if(s==null || s.length()<2) return s;
        int len = s.length();
        int maxStart = 0;
        int maxEnd = 0;
        int maxLen = 0;
        boolean[][] dp = new boolean[len][len];
        for(int r=1;r<len;r++){
            for(int l=0;l<r;l++){
                //如果l和r字符相等，且r和l距离小于等于2或者上一个状态为true
                if(s.charAt(l)==s.charAt(r)&&(r-l<=2||dp[l+1][r-1])){
                    dp[l][r]=true;
                    if(r-l+1>maxLen) {
                        maxStart = l;
                        maxEnd = r;
                        maxLen = r - l + 1;
                    }
                }

            }
        }
        return s.substring(maxStart,maxEnd+1);
    }
}

/**
 * 构建dp[left][right]的数组, 第left行第right列代表string中left到right之间的字符串是对称的。
 * 两个指针left和right，判断left和right之间的dp数组
 * */
class Solution1 {
    public String longestPalindrome(String s) {
        if(s==null||s.length()<2) return s;
        int len = s.length();
        boolean dp[][] = new boolean[len][len];
        int maxLen=0;
        int maxStart = 0;
        int maxEnd = 0;
        for(int r=1;r<len;r++){
            for(int l=0;l<r;l++){
                if(s.charAt(l)==s.charAt(r) && (r-l<=2||dp[l+1][r-1])){
                    dp[l][r]=true;
                    if(r-l+1>maxLen){
                        maxStart=l;
                        maxEnd=r;
                        maxLen=r-l+1;
                    }
                }
            }
        }
        return s.substring(maxStart,maxEnd+1);
    }
}