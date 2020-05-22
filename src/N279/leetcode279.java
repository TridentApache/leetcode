package N279;

import java.util.HashSet;
import java.util.LinkedList;

public class leetcode279 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.numSquares(13));
    }
}

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 示例 1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 *
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * */

class Solution {
    int count=0;
    LinkedList<Integer> queue;
    HashSet<Integer> seen;
    public int numSquares(int n) {
        if(n<=0) return -1;
        if(n==1) return 1;
        return BFS(n);
    }
    private int BFS(int n){
        queue = new LinkedList<>();
        seen = new HashSet<>();
        count++;
        int res=cal(n);
        if(res!=-1){
            return res;
        }

        while(queue.size()!=0) {
            int len = queue.size();
            seen = new HashSet<>();
            count++;
            for (int i=0;i<len;i++){
                int rest = queue.removeFirst();
                int result = cal(rest);
                if(result!=-1){
                    return result;
                }
            }
        }

        return -1;
    }
    private int cal(int n){
        int k=1;

        while(k*k<=n){
            int res=n-k*k;
            if(res==0) return count;
            if(!seen.contains(res)) {
                queue.addLast(res);
                seen.add(res);
            }
            k++;
        }
        return -1;
    }
}