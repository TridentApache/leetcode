package N454;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * */

/**
 * 思路：和第18题不同，该题有4个数组，而18题一个数组里找，可以先排序然后双指针。这一题如果把4个数组都先排序，再双指针（遍历A、B，再调整C、D），复杂度O(n^4)
 * 因此，考虑第1题“两数之和”思路，固定AB，存到HashMap中，遍历CD，复杂度为O(n^2)
 * */

public class leetcode454 {
    public static void main(String[] args){
        Solution s1 = new Solution();
        System.out.println(s1.fourSumCount(new int[]{1, 0, -1, 0, -2, 2},new int[]{1, 0, -1, 3},new int[]{-2, 2},new int[]{1, 2}));
    }
}

class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res=0;
        if(A.length==0&&B.length==0&&C.length==0&&D.length==0) return res;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<A.length;i++){
            for(int j=0;j<B.length;j++){
                int sum = A[i]+B[j];
                if(!map.containsKey(sum)){
                    map.put(sum,1);
                }else{
                    map.put(sum,map.get(sum)+1);
                }
            }
        }
        for(int i=0;i<C.length;i++){
            for(int j=0;j<D.length;j++){
                int sum = -(C[i]+D[j]);
                if(map.containsKey(sum)) res+=map.get(sum);
            }
        }
        return res;
    }
}