package N057;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 *
 * 示例 1:
 * 输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出: [[1,5],[6,9]]
 *
 * 思路：
 * 正常插入res，开始重叠时，进行合并模式，判断后面的数组是否需要合并，如果需要合并，就吐出来修改最后一位，再加入；否则正常插入res中
 * */

public class leetcode057 {
    public static void main(String[] args){
        Solution s = new Solution();
        //int[][] inter = new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[][] inter = new int[][]{};
        int[] newin = new int[] {4,8};
        int[][] res = s.insert0(inter,newin);
        for(int i=0;i<res.length;i++){
            System.out.println(Arrays.toString(res[i]));
        }
    }
}

class Solution {
    public int[][] insert0(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0]; int newEnd = newInterval[1];
        LinkedList<int[]> output = new LinkedList<int[]>();
        int idx=0; int n = intervals.length;
        //添加intervals到output中直到有重合，即intervals[idx][0] < newStart
        while(idx<n && newStart>intervals[idx][0]){
            output.addLast(intervals[idx++]);
        }

        //考虑第一个是否需要合并
        int[] interval = new int[2];
        //判断output当前最后一位是否小于newStart(如果output不为空)，如果小于直接插入newInterval，否则比较一下，选大的修改后插入
        if(output.isEmpty() || output.getLast()[1]<newStart){
            output.addLast(newInterval);
        }else{
            interval = output.removeLast();
            interval[1] = interval[1]>newEnd?interval[1]:newEnd;
            output.addLast(interval);
        }

        //合并第一个后，后面考虑是否要合并或者直接插入
        //取出intervals中每一个要插入的区间interval，如果interval[0]小于等于output当前最后一位，合并，否则直接加入
        while(idx<n){
            interval = intervals[idx++];
            if(interval[0]<=output.getLast()[1]){
                int[] temp = output.removeLast();
                temp[1] = interval[1]>temp[1]?interval[1]:temp[1];
                output.addLast(temp);
            }else output.addLast(interval);
        }
        return output.toArray(new int[output.size()][2]);
    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        // init data
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>(); //list中是数组的写法

        // add all intervals starting before newInterval
        while (idx < n && newStart > intervals[idx][0]) //一直添加到第一个可能开始重合的区间，注意不能超过intervals的长度
            output.add(intervals[idx++]);

        // add newInterval
        int[] interval = new int[2];
        // if there is no overlap, just add the interval
        if (output.isEmpty() || output.getLast()[1] < newStart)
            output.add(newInterval);
            // if there is an overlap, merge with the last interval
        else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // add next intervals, merge with newInterval if needed
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            // if there is no overlap, just add an interval
            if (output.getLast()[1] < start) output.add(interval);
                // if there is an overlap, merge with the last interval
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }

}