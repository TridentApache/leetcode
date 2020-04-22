package N032;

import java.util.Stack;


/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 *
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * 注意：
 * 方法一： 暴力循环，入栈出栈
 *          该方法考虑有效括号子串始终为偶数, 因此一个大循环遍历一遍，内部一个小循环每次2位2位的判断是否为括号可以抵消（如果是正括号push，否则如果栈不为空且栈顶为正括号则pop，再者如果一开始就反括号直接pass），
 *          该方法超过时间限制
 * 方法二：动态规划
 *          状态方程：定义一维数组保存最长字串长度，那么只有")"结尾才有值，如果当前是")",前一位也是")"，则dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2],注意这里最后一项考虑如下这种情况“()()”，即前面有可能也是字串，要一起加起来
 *          如果当前是")"，前面是"(",则dp[i]=dp[i-1]+2
 * 方法三：栈，始终放一个守门员（即存放有效括号串前一个，一开始是-1，后面只要是右括号就pop，如果空了就再存进去，如果pop完不空就说明有匹配成功，就算最大长度）
 * 方法四：左右两遍扫描
 * */


public class leetcode032 {
    public static void main(String[] args){
        MySolution3 s = new MySolution3();
        System.out.println(s.longestValidParentheses(")))())"));
    }
}

//方法三：栈
class MySolution3{
    public int longestValidParentheses(String s) {
        int strlen = s.length();
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for(int i=0;i<strlen;i++){
            if(s.charAt(i)=='('){
                stack.push(i);
            }else {
                stack.pop();
                if(stack.empty()){
                    stack.push(i);
                }else{
                    maxLen = Math.max(maxLen,i-stack.peek());
                }
            }
        }
        return maxLen;
    }
}


//方法二
class MySolution{
    private boolean isValid(String s){
        Stack<Character> stack = new Stack<>();
        int strlen=s.length();
        for(int i=0;i<strlen;i++){
            if(s.charAt(i)=='('){
                stack.push('(');
            }else if(!stack.empty() && stack.peek()=='('){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.empty(); //最后匹配完栈内还剩下的一定是false，只有空的才是true
    }
    public int longestValidParentheses(String s) {
        int strlen = s.length();
        if(strlen<2) return 0;
        int maxlen=0;
        for(int i=0;i<strlen;i++){
            for(int j=i+2;j<=strlen;j+=2){//注意substring时j要小于等于strlen
                if(isValid(s.substring(i,j))){
                    maxlen=maxlen<(j-i)?(j-i):maxlen;
                }
            }
        }
        return maxlen;
    }
}

//方法一
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push('(');
            } else if (!stack.empty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.empty();
    }
    public int longestValidParentheses(String s) {
        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j+=2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;
    }
}
