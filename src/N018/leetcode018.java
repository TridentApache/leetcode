package N018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 * */

/**
 * 思路：和三数之和几乎一样，固定前2个遍历，最后2个作为双指针
 * */
public class leetcode018 {
    public static void main(String[] args){
        Solution1 s1 = new Solution1();
        System.out.println(s1.fourSum(new int[]{1, 0, -1, 0, -2, 2},0));
    }
}

class Solution1{
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ls = new ArrayList<>();
        if(nums.length<=3) return ls;
        Arrays.sort(nums);
        for(int a=0;a<nums.length;a++){
            if(a!=0 && nums[a]==nums[a-1]) continue; //去重a
            for(int b=a+1;b<nums.length;b++){
                if(b>a+1 && nums[b]==nums[b-1]) continue; //去重b
                int c=b+1;
                int d=nums.length-1;
                while(c<d){
                    if(nums[a]+nums[b]+nums[c]+nums[d]==target){
                        ls.add(Arrays.asList(nums[a],nums[b],nums[c],nums[d]));
                        while(c<d&&nums[c]==nums[c+1]){c++;}
                        while(c<d&&nums[d]==nums[d-1]){d--;}
                        c++;
                        d--;
                    }
                    else if(nums[a]+nums[b]+nums[c]+nums[d]<target){
                        c++;
                    }else{
                        d--;
                    }
                }
            }
        }
        return ls;
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length < 4)
            return lists;
        int a, b, c, d;
        for (a = 0; a <= nums.length - 4; a++) {
            if (a > 0 && nums[a] == nums[a - 1])
                continue;
            for (b = a + 1; b <= nums.length - 3; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1])
                    continue;
                c = b + 1;
                d = nums.length - 1;
                while (c < d) {
                    if (nums[a] + nums[b] + nums[c] + nums[d] < target)
                        c++;
                    else if (nums[a] + nums[b] + nums[c] + nums[d] > target) {
                        d--;
                    } else {
                        List<Integer> list = new ArrayList<>();
                        lists.add(Arrays.asList(nums[a],nums[b],nums[c],nums[d]));
                        while (c < d && nums[c + 1] == nums[c])      //确保nums[c] 改变了
                            c++;
                        while (c < d && nums[d - 1] == nums[d])      //确保nums[d] 改变了
                            d--;
                        c++;
                        d--;
                    }
                }
            }
        }
        return lists;
    }
}