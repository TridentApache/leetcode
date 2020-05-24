package N739;

import java.util.Arrays;
import java.util.Stack;

public class leetcode739 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.dailyTemperatures(new int[]{89,62,70,58,47,47,46,76,100,70})));
    }

}

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * */

class Solution {
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        if(len==0) return null;
        if(len==1) return new int[]{0};

        int[] res = new int[len];
        Stack<int[]> stack = new Stack<int[]>();
        stack.push(new int[]{T[len-1],len-1});
        res[len-1]=0;

        for(int i=len-2;i>=0;i--){
            if(T[i]>=stack.peek()[0]){
                while(stack.size()!=0 && stack.peek()[0]<=T[i]){
                    stack.pop();
                }
                if(stack.size()==0) res[i]=0;
                else res[i]=stack.peek()[1]-i;
                stack.push(new int[]{T[i],i});
            }else{
                res[i]=stack.peek()[1]-i;
                stack.push(new int[]{T[i],i});
            }
        }
        return res;
    }
}


/**
 * 官方解答：和自己写的不同，自己每次压入栈的是<T[i],i>，其实只用将i压入栈，在T中找就行
 * */
class Solution1 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack();
        for (int i = T.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
}