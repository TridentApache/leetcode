package N045;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class leetcode045 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.jump(new int[]{2,3,1,1,4}));
    }
}

class Solution {
    public int jump0(int[] nums) {
        if(nums.length<=1) return 0;
        Integer minstep = Integer.MAX_VALUE;
        Deque<Integer> path = new ArrayDeque<>();
        List<Integer> optpath = new ArrayList<>();
        jump00(nums,path,0, optpath);
        return optpath.size()-1;
    }
    private void jump00(int[] nums, Deque<Integer> path, int begin, List<Integer> optpath){
        /**
         * @param path 当前跳跃过的数组中位置的路径
         * @param begin 当前在数组中的位置
         * */

        if(path.size()!=0 && path.getLast()==nums.length-1){
            if(optpath.size()==0||path.size()<optpath.size()){
                optpath.removeAll(optpath);//清空optpath中所有元素
                optpath.addAll(path);
                return;
            }
            return;
        }
        if(path.size()!=0 && optpath.size()!=0 && path.size()>=optpath.size()){
            return;
        }
        if(begin>nums.length-1) return;

        path.addLast(begin);
        int k=nums[begin];
        while(k>0){
            if(path.size()!=0 && path.getLast()+k>=nums.length-1){
                path.add(path.getLast()+k);
                if(optpath.size()==0||path.size()<optpath.size()){
                    optpath.removeAll(optpath);//清空optpath中所有元素
                    optpath.addAll(path);
                    continue;
                }
            }
            if(path.size()==0 || path.getLast()+k<nums.length-1) {
                jump00(nums, path, path.getLast() + k, optpath);
            }
            k--;
            path.removeLast();
        }
        return;
    }

    public int jump(int[] nums) {
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for(int i = 0; i < nums.length - 1; i++){
            //找能跳的最远的
            maxPosition = Math.max(maxPosition, nums[i] + i);
            if( i == end){ //遇到边界，就更新边界，并且步数加一
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

}
