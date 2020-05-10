package WeeklyContest188;

import java.util.*;

public class contest188 {
    public static void main(String[] args){
        Solution2 s2 = new Solution2();
//        System.out.println(s2.countTriplets(new int[]{1,1,1,1,1,1,1,1,1,1}));
//        System.out.println(s2.countTriplets(new int[]{7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7}));
//        System.out.println(s2.countTriplets(new int[]{2,3,1,6,7}));

        Solution3 s3 = new Solution3();
        int y=s3.minTime(7, new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, Arrays.asList(false,false,true,false,true,true,false));
    }
}


/**
 * 给你一个目标数组 target 和一个整数 n。每次迭代，需要从  list = {1,2,3..., n} 中依序读取一个数字。
 *
 * 请使用下述操作来构建目标数组 target ：
 *
 * Push：从 list 中读取一个新元素， 并将其推入数组中。
 * Pop：删除数组中的最后一个元素。
 * 如果目标数组构建完成，就停止读取更多元素。
 * 题目数据保证目标数组严格递增，并且只包含 1 到 n 之间的数字。
 *
 * 请返回构建目标数组所用的操作序列。
 *
 * 题目数据保证答案是唯一的。
 * */
class Solution1 {
    public List<String> buildArray(int[] target, int n) {
        List<String> res= new ArrayList<>();
        int k=1;
        for(int i=0;i<target.length;i++){
            while(k<=target[i]) {
                if (target[i] == k) {
                    res.add("Push");
                } else {
                    res.add("Push");
                    res.add("Pop");
                }
                k++;
            }
        }
        return res;
    }
}

/**
 * 给你一个整数数组 arr 。
 *
 * 现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。
 *
 * a 和 b 定义如下：
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * 注意：^ 表示 按位异或 操作。
 *
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * */
/**
 * 第二题一拍脑袋想到双指针，类似15题做法，结果写半天感觉题目意思理解错了。这是异或，不是求和什么的
 * 想来想去觉得还是3层for靠谱，但是肯定得加速，结果对每层都加速就错了,只能对最内层加速
 * 下面Solution2是别人的解法，Solution2_1是自己的解法
 * 先做一遍异或，然后三层循环，用的异或运算的性质，x^y^y=x
 * */
class Solution2 {
    int countTriplets(int[] arr) {
        int n = arr.length;
        int[] s=new int[n+1];
        for (int i = 1; i <= n; ++ i)
            s[i] = s[i-1]^arr[i-1];
        int ret = 0;
        for (int i = 1; i <= n; ++ i)
            for (int j = i+1; j <= n; ++ j)
                for (int k = j; k <= n; ++ k)
                {
                    if ((s[j-1]^s[i-1]) == (s[k]^s[j-1])) ret ++;
                }
        return ret;
    }
};

class Solution2_1 {
    public int countTriplets(int[] arr) {
        int count=0;
        int i=0,j=0,k=0;
        for(;i<arr.length;) {
            j=i+1;
            for(;j<arr.length;){
                k=j;
                for(;k<arr.length;){
                    int a=cal(arr,i,j-1);
                    int b=cal(arr,j,k);
                    if(a==b){
                        count++;
                        while((k+1<arr.length-1)&&(b==(b^arr[k+1]))){
                            k++;
                            count++;continue;
                        }
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        return count;
    }
    public int cal(int[] arr,int x, int y){
        int res=0;
        for(int i=x;i<=y;i++){
            res=res^arr[i];
        }
        return res;
    }
}

/**
 * 给你一棵有 n 个节点的无向树，节点编号为 0 到 n-1 ，它们中有一些节点有苹果。通过树上的一条边，需要花费 1 秒钟。你从 节点 0 出发，请你返回最少需要多少秒，可以收集到所有苹果，并回到节点 0 。
 * 无向树的边由 edges 给出，其中 edges[i] = [fromi, toi] ，表示有一条边连接 from 和 toi 。除此以外，还有一个布尔数组 hasApple ，其中 hasApple[i] = true 代表节点 i 有一个苹果，否则，节点 i 没有苹果。
 * */

class Solution3 {

    private List<Boolean> hasApple;
    private Map<Integer, List<Integer>> tree;

    private int dfs(int root) {
        List<Integer> son = tree.get(root);
        if (son == null) {
            return hasApple.get(root) ? 1 : 0;
        }
        int sum = 0;
        for (Integer i : son) {
            sum += dfs(i);
        }
        if (sum == 0) {
            return hasApple.get(root) ? 1 : 0;
        } else {
            return sum + 1;
        }
    }

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        this.hasApple = hasApple;
        this.tree = new HashMap<>();
        for (int[] edge : edges) {
            if (!tree.containsKey(edge[0]))
                tree.put(edge[0], new ArrayList<>());
            tree.get(edge[0]).add(edge[1]);
        }
        int sum = dfs(0);
        return sum == 0 ? 0 : (sum - 1) * 2;
    }
}
class Solution3_1{
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] e : edges) {
            if (!map.containsKey(e[0])) {
                map.put(e[0], new ArrayList<>());
            }
            map.get(e[0]).add(e[1]);
        }
        return Math.max(dfs(0, map, hasApple) - 2, 0);
    }

    public int dfs(int start, Map<Integer, List<Integer>> map, List<Boolean> hasApple) {
        int ans = 0;
        for (int son : map.getOrDefault(start, new ArrayList<>())) {
            ans += dfs(son, map, hasApple);
        }
        if (ans == 0) {
            return hasApple.get(start) ? 2 : 0;
        }
        return ans + 2;
    }
}