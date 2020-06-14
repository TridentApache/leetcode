package N033;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。

 * */

public class leetcode033 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = new int[]{6,1,2,3,4,5};
        System.out.println(s.search(nums,2));
    }
}

 /** 有序数组a1,a2,...,ai,ai+1,...,aj.旋转后 ai+1,...,aj,a1,a2,...,ai.
         * 有min{ai+1,...,aj}>max{a1,a2,...,ai}
         * 将数组分2半，一定一半有序，一半无序，分别讨论target是否在其中，若不在有序的里面，就到递归另一半
         * */
class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        if (nums.length == 1 && target != nums[0]) return -1;
        if (nums.length == 1 && target == nums[0]) return 0;
        return search1(nums, 0, nums.length - 1, target);
    }

    public int search1(int[] nums, int i, int j, int target) {
        if (nums[j] == target) {
            return j;
        }
        if (nums[i] == target) {
            return i;
        }
        if (i == j && nums[i] != target) {
            return -1;
        }
        int mid = (j + i) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        //有序数组a1,a2,...,ai,ai+1,...,aj.旋转后 ai+1,...,aj,a1,a2,...,ai.
        //有min{ai+1,...,aj}>max{a1,a2,...,ai}
        if (nums[i] <= nums[mid]) {
            if (target >= nums[i] && target <= nums[mid]) {
                for (int k = i; k <= mid; k++) {
                    if (target == nums[k]) return k;
                }
                return -1;
            } else {
                return search1(nums, mid + 1, j, target);
            }
        } else {
            if (target >= nums[mid + 1] && target <= nums[j]) {
                for (int k = mid + 1; k <= j; k++) {
                    if (target == nums[k]) return k;
                }
                return -1;
            } else {
                return search1(nums, i, mid, target);
            }
        }
    }
}

/**
 * 将数组分2半，一定一半有序，一半无序，分别讨论target是否在其中
 * */
class Solution1 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n==0) return -1;
        if (n == 1) return nums[0] == target ? 0 : -1;
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) return mid;
            //按数组是否有序来划分
            if (nums[0] <= nums[mid]) {//前半段有序
                if (nums[0] <= target && target < nums[mid]) {//只判断有序部分target是否在其中
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {//后半段有序
                if (nums[mid] < target && target <= nums[n - 1]) {//只判断有序部分target是否在其中
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}