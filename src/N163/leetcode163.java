package N163;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
 * */
public class leetcode163 {
    public static void main(String[] args){
        Solution s = new Solution();
        List<String> ls = s.findMissingRanges(new int[]{0, 1, 3, 50, 75}, 0, 99);
        System.out.println(Arrays.toString(ls.toArray()));
    }
}
class Solution{
    private String range(int lower, int upper){
        if(lower==upper){
            return Integer.toString(lower);
        }else{
            return Integer.toString(lower)+"->"+Integer.toString(upper);
        }
    }
    public List<String> findMissingRanges(int[] nums, int lower, int upper){
        List<String> list = new ArrayList<>();
        if(nums==null||nums.length==0){ //如果数组是空的，给定lower和upper就直接补全
            list.add(range(lower,upper));
            return list;
        }
        if(lower<nums[0]){
            list.add(range(lower,nums[0]-1));
        }
        for(int i=0;i<nums.length-1;i++){
            if(nums[i+1]>nums[i]+1){
                list.add(range(nums[i]+1,nums[i+1]-1));
            }
        }
        if(nums[nums.length-1]<upper){
            list.add(range(nums[nums.length-1]+1,upper));
        }
        return list;
    }
}