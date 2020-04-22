package N122;

public class leetcode122 {
    public static void main(String[] args){
        Solution s = new Solution();
//        int ls = s.maxProfit(new int[]{7,1,5,3,6,4});
//        int ls = s.maxProfit(new int[]{1,2,3,4,5});
//        int ls = s.maxProfit(new int[]{7,6,4,3,1});
//        int ls = s.maxProfit(new int[]{1,2});
          int ls = s.maxProfit(new int[]{2,1,2,1,0,0,1});
//        int ls = s.maxProfit(new int[]{2,1,4});
        System.out.println(ls);
    }
}
/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 写代码一定要保证所有情况考虑到，比如for循环截取了一部分1...n-1，那么首尾0和n都要额外写
 * */
class Solution {
    public int maxProfit(int[] prices){
        int profit=0;
        for(int i=1;i<prices.length;i++){
            int tmpprofit = prices[i]-prices[i-1];
            if(tmpprofit>0) profit=profit+tmpprofit;
        }
        return profit;
    }

    public int maxProfit1(int[] prices) {
        if(prices.length==0||prices.length==1){
            return 0;
        }
        if(prices.length==2){
            return prices[1]>prices[0]?prices[1]-prices[0]:0;
        }
        int profit = 0;
        int flag=0; //没买入
        int minprice=Integer.MAX_VALUE;
        if(prices[0]<=prices[1]){
            flag=1;
            minprice=prices[0];
        }
        int i=1;
        for(;i<prices.length-1;i++){
            if(flag==0 && prices[i-1]>prices[i] && prices[i]<=prices[i+1]){ //比前一个小，一定是低谷
                flag=1;//买入了
                minprice=prices[i];
                continue;
            }
            if(flag==1 && prices[i-1]<=prices[i] && prices[i]>prices[i+1]){ //比后一个大，一定是高峰
                flag=0;
                profit=profit+prices[i]-minprice;
                continue;
            }
        }

        if(flag==1){ //最后一天
            profit=prices[i]-minprice>0?profit+prices[i]-minprice:profit;
        }

        return profit;
    }
}