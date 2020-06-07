package N209;

import java.util.Arrays;

public class leetcode209 {
    public static void main(String[] args){
        Solution s = new Solution();
        s.minSubArrayLen(15,new int[]{1,2,3,4,5});
    }

}

/**
 * Returns:
 * index of the search key, if it is contained in the array;
 * otherwise, (-(insertion point) - 1).
 * The insertion point is defined as the point at which the key would be inserted into the array:
 * the index of the first element greater than the key,
 * or a.length if all elements in the array are less than the specified key.
 * Note that this guarantees that the return value will be >= 0 if and only if the key is found.
 * 注意JDK中的BinarySearch API如果不存在这个数，返回的是负数要插入的位置的负数-1，比如返回-3，表示在位置2插入
 * */

/**
 * 自己代码写的也太冗余了！
 * */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int len = nums.length;
        if(len==0) return 0;
        if(len==1){
            if(nums[0]==s) return 1;
            return 0;
        }

        for(int i=1;i<nums.length;i++){
            nums[i]+=nums[i-1];
        }
        if(nums[len-1]<s) return 0;
        int res=Integer.MAX_VALUE;
        for(int i=1;i<len;i++){
            if(nums[i]-s==0) res=i+1; //不用插入
            if(nums[i]-s>0){
                int loc = Arrays.binarySearch(nums,nums[i]-s);
                if(loc==-1) {//插入0的位置
                    loc=-loc-1;
                    res = res<i-loc?res:i-loc;
                    continue;
                }
                if(loc>=0 && nums[i]-nums[loc]>=0){
                    res = res<i-loc?res:i-loc;
                    continue;
                }
                if(loc<-1){//插入其他位置
                    loc=-loc-1;
                    res = res<i-loc+1?res:i-loc+1;
                }
            }
        }
        return res;
    }
}