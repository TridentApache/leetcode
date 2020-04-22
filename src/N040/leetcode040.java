package N040;

import java.util.*;
/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * */
public class leetcode040 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = new int[]{1};
        List<List<Integer>> l = new ArrayList();
        l=s.combinationSum2(nums,1);
        Iterator it = l.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> ls = new ArrayList<>();
        if(len==0) return ls;
        Deque<Integer> path =new ArrayDeque<>();
        if(len==1&&target==candidates[0]) {
            path.addLast(candidates[0]);
            ls.add(new ArrayList<>(path));
            return ls;//不能return ls.add()，因为返回的指针而不是add()执行后返回的boolean
        }
        Arrays.sort(candidates);
        findSum(len,candidates,ls,0 , target,path);
        return ls;
    }
    public void findSum(int len, int[] candidates, List<List<Integer>> ls,int begin, int target, Deque<Integer> path){
        if(target==0){
            ls.add(new ArrayList<>(path));
            return;
        }
        if(begin==len)
            return;
        for(int i =begin;i<len;i++){
            if(target-candidates[i]<0) break;
            if(i>begin && candidates[i]==candidates[i-1]) continue;
            path.addLast(candidates[i]);
            findSum(len,candidates,ls,i+1 , target-candidates[i],path);
            path.removeLast();
        }
    }
}