package N494;

public class leetcode494 {
    public static void main(String[] args){
        Solution1 s = new Solution1();
        System.out.println(s.findTargetSumWays(new int[]{1,1,1,1,1,}, 3));
    }
}

/**
 * dfs
 * */
class Solution {
    int len;
    int count=0;
    public int findTargetSumWays(int[] nums, int S) {
        len = nums.length;
        if(len==0) return 0;
        if(len==1 && nums[0]==S) return 1;
        dfs(nums,0,S);
        return count;
    }
    private void dfs(int[] nums, int i, int S){
        if(i==len && S==0 ) count++;
        if(i==len) return;
        dfs(nums, i+1, S-nums[i]);
        dfs(nums, i+1, S+nums[i]);
    }
}

/**
 * dp
 * */
class Solution1 {
    public int findTargetSumWays(int[] nums, int S) {
        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1;
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
    }
}
