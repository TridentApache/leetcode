package N394;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;


/**
 *
 * 思路：数字栈、字符栈，类似逆波兰表达式题
 * 注意：
 * 1. 字符串数字转为int
 * */
public class leetcode394 {
    public static void main(String[] agrs){
        Solution2 s = new Solution2();
//        System.out.println(s.decodeString("100[leetcode]"));
        System.out.println(s.decodeString("3[a2[c]]"));
//        System.out.println(s.decodeString("2[abc]3[cd]ef"));

    }
}

/**
 * 自己的代码写的太繁琐
 * 1. 不知道用Character中判断字母isAlphabetic、数字isDigit的表达式
 * 2. 代码中又是String又是StringBuilder，很乱
 * 3. 解析长数字字符串，parseInt或者multi=multi*10+c-'0'
 * 4. 字符串相加操作，ret += str
 * */
class Solution {
    private Stack<String> stack = new Stack<String>();
    private Stack<Integer> stack1 = new Stack<>();
    private HashSet<Character> set = new HashSet<>();
    public String decodeString(String s) {
        if(s.length()==0) return "";
        set.addAll(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
        for(int i=0;i<s.length();i++){
            Character tmp = s.charAt(i);
            if(set.contains(s.charAt(i))){
                StringBuilder number = new StringBuilder();
                while(set.contains(s.charAt(i+1))){
                    number.append(s.charAt(i++));
                    continue;
                }
                number.append(s.charAt(i));
                stack1.push(Integer.parseInt(number.toString()));
            }else{
                if(tmp.equals(']')){
                    StringBuilder sb =new StringBuilder();
                    while((!stack.isEmpty())&&(!stack.peek().equals("["))){
                        sb.insert(0,stack.pop());
                    }
                    stack.pop();
                    if(stack1.size()!=0){
                        int k = stack1.pop();
                        StringBuilder sb1 =new StringBuilder();
                        while(k>0){
                            sb1.append(sb);
                            k--;
                        }
                        stack.push(sb1.toString());
                    }
                }else{
                    stack.push(tmp.toString());
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while(stack.size()!=0){
                res.insert(0,stack.pop());
        }
        return res.toString();
    }
}

/**
 * 别人的代码，也是一个数字栈、一个字符栈
 * */
class Solution1{
    public String decodeString(String s) {
        StringBuffer ans=new StringBuffer();
        Stack<Integer> multiStack=new Stack<>();
        Stack<StringBuffer> ansStack=new Stack<>();
        int multi=0;
        for(char c:s.toCharArray()){
            if(Character.isDigit(c)) multi=multi*10+c-'0';
            else if(c=='['){
                ansStack.add(ans);
                multiStack.add(multi);
                ans=new StringBuffer();
                multi=0;
            }else if(Character.isAlphabetic(c)){
                ans.append(c);
            }else{
                StringBuffer ansTmp=ansStack.pop();
                int tmp=multiStack.pop();
                for(int i=0;i<tmp;i++)ansTmp.append(ans);
                ans=ansTmp;
            }
        }
        return ans.toString();
    }
}

/**
 * 官方递归解法
 * 当检查到数字，取出数字，递归dfs，再处理重复；否则处理字符
 * */
class Solution2 {
    String src;
    int ptr;

    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits();
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString();
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }

        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }
}
