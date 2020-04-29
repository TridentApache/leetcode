package ZhaoShang;

import java.util.*;

public class ZhaoShang1 {
    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[][] money = new int[n][n];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<=i;j++){
//                money[i][j]=sc.nextInt();
//            }
//        }
//        int[] row = new int[2*n];
//        for(int i=0;i<2*n;i++){
//            row[i]=sc.nextInt();
//        }

        Solution3 s = new Solution3();
        s.TowerGame(new int[]{1,2,2,4,2,3}, 6,3);
    }
}

/**
 * C题
 * 链接：https://ac.nowcoder.com/acm/contest/5246/C
 * 来源：牛客网
 *
 *        小招正在玩一款修塔游戏：系统中有n座高塔，每座高塔由若干个高度相同的方块堆砌而成。修塔游戏的规则为：
 *        （1）每次从最高塔的塔尖拿走一个方块
 *        （2）每次在最低塔的塔尖堆砌一个方块
 *        小招每次只能完成上述两个动作中的一个动作。游戏的目标是使n座高塔中至少有k座高塔的高度相同，请问小招最少需要多少次才能完成游戏?
 * */
class Solution3 {
    public void TowerGame(int[]data,int n,int k) {
        Arrays.sort(data);
        // cal result from two paths
        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < k; i++) {
            // 若把所有较小高度补到第 k 大的高度，需要的步数
            res1 += (data[k-1] - data[i]);
            // 若把所有较大高度削到倒数第 k 大的高度，需要的步数
            res2 += (data[n-1-i] - data[n-1-k+1]);
        }
        int index1=k, index2=n-1-k;
        // 如果第 k 大的高度 h1 在后面的数值中还重复出现了 m 次
        // 则说明多进行了 m 次操作
        while(index1<n && data[index1++]==data[k-1]) res1--;
        // 如果倒数第 k 大的高度 h2 在后面的数值中还重复出现了 l 次
        // 则说明多进行了 l 次操作
        while(index2>=0 && data[index2--]==data[n-1-k+1]) res2--;
        // 如果 res1 和 res2 中有负值
        // 则说明在一开始第 k 大或倒数第 k 大的高度就已经有了 k 个
        // 此时返回0
        System.out.println(Math.max(0, Math.min(res1, res2)));
    }

}

/**
 * B题
 * 链接：https://ac.nowcoder.com/acm/contest/5246/B
 * 来源：牛客网
 *
 * 在一场集体婚礼上，有n对新人需要坐在连续排列的 2n个座位上合影，同一对新人彼此挨着。由于进场时各对新人并未按序入座，请计算最少交换座位的次数，以便使每对新人均可并肩坐在一起。一次交换可选择任意两人，让他们互换座位。
 *        全部新人的序号可用 0 到 2n-1 的整数表示，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2n-2, 2n-1)。
 *        row[i]指最初坐在第 i 个座位上的新人编号，i是从0到（2n-1）的一个升序全排列，row不存在重复值。
 * */
class Solution2{
    public int minSwapsCouples(int[] row){
        int ans=0;
        for(int i=0;i<row.length;i+=2){
            int x = row[i];
            if(row[i+1]==(x^1))
                continue;
            ans++;
            for(int j=i+1;j<row.length;j++){
                if(row[j]==(x^1)){
                    row[j]=row[i+1];
                    row[i+1]=x^1;
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution1{
    /**
     * A题
     * 链接：https://ac.nowcoder.com/acm/contest/5246/A
     * 来源：牛客网
     *
     *  小招在玩一款游戏：在一个N层高的金字塔上，以金字塔顶为第一层，第i层有i个落点，每个落点有若干枚金币，
     *  在落点可以跳向左斜向下或向右斜向下的落点。若知道金字塔的层数N及每层的金币数量分布，请计算小招在本次游戏中可以获得的最多金币数量。
     * */
    public int findMax(int n, int[][] money){
        if(n==0) return 0;
        if(n==1) return money[0][0];
        for(int i=n-2;i>=0;i--){
            for(int j=i;j>=0;j--){
                money[i][j]=Math.max(money[i+1][j+1]+money[i][j],money[i+1][j]+money[i][j]);
            }
        }
        return money[0][0];
    }
}