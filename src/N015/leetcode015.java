package N015;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * */

/**
 * 固定一个值，然后双指针思路
 * 注意：
 * 1. 去重，比如{0,0,0,0}的情况，所以第一层for中要首先去重
 * 2. 防止双指针之间还有解，所以得到一个解后，去重，并L++和R--*
 * */

public class leetcode015 {
    public static void main(String[] args){
        Solution s = new Solution();
        List<List<Integer>> l = s.threeSum(new int[]{0,0,0,0});
        for(int i=0;i<l.size();i++){
            for(int j=0;j<l.get(i).size();j++){
                System.out.print(l.get(i).get(j)+",");
            }
            System.out.print("\n");
        }

    }
}

class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ls = new ArrayList<List<Integer>>();
        if(nums.length<=2) return ls;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue; //去重
            int L=i+1;
            int R=nums.length-1;
            while(L<R){
                if(nums[i]+nums[L]+nums[R]==0){
                    ls.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    /**防止L和R之间还有解，所以去重加上L++和R--*/
                    while(L<R&&nums[R-1]==nums[R]){
                        R--;
                    }
                    while(L<R&&nums[L]==nums[L+1]){
                        L++;
                    }
                    L++;
                    R--;
                }else if(nums[i]+nums[L]+nums[R]>0){
                    R--;
                }else{
                    L++;
                }
            }
        }
        return ls;
    }
}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        /**
         * 1. 先排序
         * 2. 判断排序后数组长度、是否都大于0
         * 3. 判断是否为全零数组
         * 3. 遍历数组，设L、R两个指针，比较和0的大小调整两指针，并跳过相同的数
         *
         * 注意：List用法，List是结构，创建对象、遍历、获取元素、添加元素的方法
         * */
        List<List<Integer>> lists = new ArrayList<>(); //便于程序代码的重构. 面向接口编程
        Arrays.sort(nums);
        int len = nums.length;
        if (len==0) return lists;
        if (nums[0]>0) return lists;
        for(int i=0;i<len;i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L=i+1,R=nums.length-1;
            while(L<R){
                if(nums[i]+nums[R]+nums[L]==0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L<R && nums[L+1] == nums[L]) L++;
                    while (L<R && nums[R-1] == nums[R]) R--;
                    ++L;
                    --R;
                }else if(nums[i]+nums[R]+nums[L]<0){
                    L++;
                }else{
                    R--;
                }
            }
        }
        return lists;
    }
}