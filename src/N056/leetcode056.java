package N056;

import java.util.*;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 注意：集合可能是乱序的。
 * 1. List<Integer> 不用add好多次，可以直接Arrays.asList(...)一次性导入
 * 2. 本题思路，首先，先用Arrays.sort把二维数组排序（根据第一列元素大小），new Comparator，覆写compare函数
 * 3. 接着，考虑相邻两个集合大小关系，创建Linkedlist逐个讨论，addLast或removeLast
 * 4. 最后，用List.toArray将链表变成数组，注意初始化（数据类型相同就行了）
 * 5. 注意LinkedList和ArrayList都是List的实现类，List是一个接口，但是LinkedList是链表实现的，ArrayList是数组实现的
 * 6. 在写if讨论2个区间时，条件判断要写闭合，每种情况都要讨论一下
 * 7. 需要仔细研究一下Comparator的结构
 * */


public class leetcode056 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        int[][] test = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] test = {{1, 4}, {2, 3}};
        // 打印二维数组
//        for (int i = 0; i < test.length; i++) {
//            for (int j = 0; j < test[i].length; j++) {
//                System.out.print(test[i][j] + " ");
//            }
//            System.out.println();
//        }
        int[][] res = s.merge(test);
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}

class Solution {
    public int[][] merge0(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        int m=0;
        int pi=0;
        for(int i=0;i<intervals.length-1;i++){
            if(intervals[i][1]<intervals[i+1][0]){
                res[m][0] = intervals[pi][0];
                res[m][1] = intervals[i][1];
                m=m+1;
                pi=i+1;
            }
        }
        if(res[m-1][1]>=intervals[intervals.length-1][0]){
            res[m-1][1]=intervals[intervals.length-1][1];
        }else if(res[m-1][1]<intervals[intervals.length-1][0]){
            res[m][0]=intervals[intervals.length-1][0];
            res[m][1]=intervals[intervals.length-1][1];
        }
        return res;
    }

    public int[][] merge00(int[][] intervals) {
        List<List<Integer>> res = new ArrayList<>();
        if(intervals.length==0) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0]-o2[0];
            }
        });
        int m=0;
        int minnums=Integer.MAX_VALUE;
        int maxnums=Integer.MIN_VALUE;
        for(int i=0;i<intervals.length-1;i++){
            minnums = intervals[i][0]<minnums? intervals[i][0]:minnums;
            maxnums = intervals[i][1]>maxnums? intervals[i][1]:maxnums;
            if(maxnums<intervals[i+1][0]){
                res.add(Arrays.asList(minnums,maxnums));
                m=m+1;
                minnums=Integer.MAX_VALUE;
                maxnums=Integer.MIN_VALUE;
            }
        }

        if(res.size()==0&&intervals.length>=1){
            res.add(Arrays.asList(intervals[0][0],intervals[intervals.length-1][1]));
        }else if(res.get(res.size()-1).get(1)>=intervals[intervals.length-1][0]){
            res.set(m-1,Arrays.asList(res.get(m-1).get(0),intervals[intervals.length-1][1]));
        }else if(res.get(res.size()-1).get(1)<intervals[intervals.length-1][0]){
            res.add(Arrays.asList(intervals[intervals.length-1][0],intervals[intervals.length-1][1]));
        }
        //将res转化为二维数组
        int[][] res2 = new int[res.size()][2];
        for(int i=0;i<res.size();i++){
            for(int j=0;j<2;j++){
                res2[i][j]=res.get(i).get(j);
            }
        }
        return res2;
    }


    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        LinkedList<int[]> list = new LinkedList<>();
        list.addLast(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (curr[0] <= list.getLast()[1]) {
                int max = Math.max(curr[1], list.getLast()[1]);
                int start = list.getLast()[0];
                list.removeLast();
                list.addLast(new int[]{start, max});
            } else {
                list.addLast(curr);
            }
        }

        return list.toArray(new int[0][0]);
    }

}
/**
 * 先排序，再添加
 * [n1,n2]，假设添加新的[m1,m2]，分三种情况考虑
 * n2<m1：直接添加
 * n2=m1: 合并, 即[n1,m2]
 * n2>m1: 分2种，1. n2>m2,即[n1,n2]；2. n2<=m2，即[n1,m2]
 * */
class Solution2 {
    public int[][] merge(int[][] intervals) {
        if(intervals.length==0 || intervals[0].length==0) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {return o1[0]-o2[0];}
        });
        LinkedList<int[]> res = new LinkedList<>();
        res.addLast(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] newinterval = intervals[i];
            if(res.getLast()[1]<newinterval[0]){
                res.addLast(newinterval);
            }else if(res.getLast()[1]>=newinterval[0] && res.getLast()[1]<=newinterval[1]){
                int[] tmp = res.removeLast();
                res.addLast(new int[]{tmp[0],newinterval[1]});
            }
        }
        return res.toArray(new int[0][2]);
    }
}