package N150;

import java.util.Stack;

public class leetcode150 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
//        System.out.println(s.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}

/**
 * 根据逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 *
 * */
class Solution {
    //需要考虑负数
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int len = tokens.length;
        for(int i=0;i<len;i++){
            if(tokens[i].equals("+")){
                if(stack.size()>=2){
                    int x=stack.pop();
                    int y=stack.pop();
                    stack.push(x+y);
                }
            }else if(tokens[i].equals("-")){
                if(stack.size()>=2){
                    int x=stack.pop();
                    int y=stack.pop();
                    stack.push(y-x);
                }
            }else if(tokens[i].equals("*")){
                if(stack.size()>=2){
                    int x=stack.pop();
                    int y=stack.pop();
                    stack.push(x*y);
                }
            }else if(tokens[i].equals("/")){
                if(stack.size()>=2){
                    int x=stack.pop();
                    int y=stack.pop();
                    stack.push(y/x);
                }
            }else{
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.peek();
    }
}