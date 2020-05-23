package N020;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class leetcode020 {
    public static void main(String[] args){
        Solution s= new Solution();
        s.isValid("()");
    }
}

/**
 * 需要遍历完String，检查栈的大小是否为空才能判断
 * */
class Solution {
    private Stack<Character> stack = new Stack<>();
    public boolean isValid(String s) {
        int len = s.length();
        for(int i=0;i<len;i++){
            Character c = s.charAt(i);
            if(stack.size()!=0 && ((stack.peek().equals('(') && c.equals(')'))
            || (stack.peek().equals('[') && c.equals(']'))
            || (stack.peek().equals('{') && c.equals('}')) )){
                stack.pop();
            }else {
                stack.push(c);
            }
        }
        return stack.size()==0?true:false;
    }
}

/**
 * 栈底压如"？"，当不匹配时，提前return false
 * HashMap存放匹配的选项
 * */
class Solution1_1 {
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }
}
