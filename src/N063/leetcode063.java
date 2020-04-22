package N063;

import java.util.Arrays;

public class leetcode063 {
    public static void main(String[] args){
        Solution s =new Solution();
        int[][] ob = new int[][]{{0,0,0},{1,1,1},{0,0,0}};
        System.out.println(s.uniquePathsWithObstacles(ob));
    }
}
// 边上的路被封了就一直不可能过去了
// 不要瞎几把在if中添加判断，可能你的else又错了
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[0][0]==1) return 0;
        int[][] dp = new int[m][n];
        int iflag = 0;
        int jflag=0;
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==1){
                if(iflag==0){
                    if(n>=2){
                        dp[i][0]=0;
                    }else{
                        return 0;
                    }
                }else dp[i][0]=0;
                iflag=1;
            }else{
                dp[i][0]=iflag==1?0:1;
            }
        }
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==1){
                if(jflag==0) {
                    if (m >= 2) {
                        dp[0][i] = 0;
                    } else {
                        return 0;
                    }
                }else dp[0][i] = 0;
                jflag=1;
            }else{
                dp[0][i]=jflag==1?0:1;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    dp[i][j]=0;
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}