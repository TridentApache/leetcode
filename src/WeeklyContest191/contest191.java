package WeeklyContest191;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class contest191 {
    public static void main(String[] args){
//        Solution1 s1 = new Solution1();
//        System.out.println(s1.maxProduct(new int[]{3,4,5,2}));

        Solution2 s2 = new Solution2();
        s2.maxArea(5,4,new int[]{1,2},new int[]{3});
    }
}

/**
 *
 * 注意： 找数组中两个最大值，max1更新后，自己原来的值要给max2
 * */
class Solution1 {
    public int maxProduct(int[] nums) {
        if(nums.length<2) return 0;
        if(nums.length==2) return (nums[0]-1)*(nums[1]-1);
        int max1=1;
        int max2=1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max1) {
                max2=max1; //!!!!
                max1=nums[i];
            }
            else if(nums[i]>max2) max2=nums[i];
        }
        return (max1-1)*(max2-1);
    }
}

/**
 * 矩形蛋糕的高度为 h 且宽度为 w，给你两个整数数组 horizontalCuts 和 verticalCuts，其中 horizontalCuts[i] 是从矩形蛋糕顶部到第  i 个水平切口的距离，类似地， verticalCuts[j] 是从矩形蛋糕的左侧到第 j 个竖直切口的距离。
 * 请你按数组 horizontalCuts 和 verticalCuts 中提供的水平和竖直位置切割后，请你找出 面积最大 的那份蛋糕，并返回其 面积 。由于答案可能是一个很大的数字，因此需要将结果对 10^9 + 7 取余后返回。
 *
 * 注意：
 * 1. 做题前，看清数据范围，考虑数据类型，int 还是long？？？
 * 2. 当数据比较大，看清题目，是否要求求余再上传
 * */
class Solution2 {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int hcut = horizontalCuts.length;
        int vcut = verticalCuts.length;
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        int[] deltaV = new int[vcut+1];
        int[] deltaH = new int[hcut+1];
        deltaH[0] = horizontalCuts[0];
        deltaV[0] = verticalCuts[0];
        long maxH=deltaH[0];
        long maxV=deltaV[0];
        for(int i=1;i<vcut;i++) {
            deltaV[i]= verticalCuts[i]-verticalCuts[i-1];
            if(deltaV[i]>maxV) maxV=deltaV[i];
        }
        deltaV[vcut]=w-verticalCuts[vcut-1];
        if(deltaV[vcut]>maxV) maxV=deltaV[vcut];

        for(int j=1;j<hcut;j++){
            deltaH[j]= horizontalCuts[j]-horizontalCuts[j-1];
            if(deltaH[j]>maxH) maxH=deltaH[j];
        }
        deltaH[hcut]=h-horizontalCuts[hcut-1];
        if(deltaH[hcut]>maxH) maxH=deltaH[hcut];
       return (int) (maxH*maxV%1000000007);
    }
}


/**
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 *
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 *
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 *
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 *
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 * */
class Solution3 {

    public int minReorder(int n, int[][] connections) {
        ArrayList<int[]>[] list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] connection : connections) {
            list[connection[0]].add(new int[] { connection[1], 1 });
            list[connection[1]].add(new int[] { connection[0], 0 });
        }
        boolean[] visited = new boolean[n];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.add(0);
        int count = 0;
        visited[0] = true;
        while (!deque.isEmpty()) {
            int head = deque.remove();
            for (int[] i : list[head]) {
                if (!visited[i[0]]) {
                    visited[i[0]] = true;
                    count += i[1];
                    deque.add(i[0]);
                }
            }
        }
        return count;
    }
}