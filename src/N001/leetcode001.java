package N001;

import java.util.HashMap;

public class leetcode001 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums={2,3,4,5};
        int target=7;
        int[] res = s.addTwoNum2(nums,target);
        System.out.println(res[0]+","+res[1]);
    }
}

class Solution{
    public int[] addTwoNum1(int[] nums, int target){
        int[] res= new int[2];
        if(nums==null || nums.length<1) return nums;
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i=0;i<nums.length;i++){
            int val = target-nums[i];
            if(map.containsKey(val)){
                res[0]=map.get(val);
                res[1]=i;
                return res;
            }
            map.put(nums[i],i);
        }
        return res;
    }

    public int[] addTwoNum2(int[] nums,int target){
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target-nums[i]==nums[j])     return new int[]{i,j};
            }
        }
        throw new IllegalArgumentException("No solution");
    }
}