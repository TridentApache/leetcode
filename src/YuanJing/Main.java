package YuanJing;


import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str1 = sc.next();
            String str2 = sc.next();
            System.out.println(calculateStringDistance(str1,str2));
        }
    }
    private static String calculateStringDistance(String
                                                          str1, String str2){
        if(str1 == null || str2 == null || str1.length() <= 0
                || str2.length() <= 0)
            return null;
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int dp = getDp(chs1,chs2);
        System.out.println(dp);
        return String.valueOf("1/" + (dp + 1));
    }
    private static int getDp(char[] chs1,char[] chs2){
        int[][]dp = new int[chs1.length + 1][chs2.length + 1];
        for(int i = 1;i<=chs1.length;i++){
            dp[i][0] = i;
        }
        for(int j = 1;j<=chs2.length;j++){
            dp[0][j] = j;
        }
        for(int i = 1;i<=chs1.length;i++){
            for(int j = 1;j<=chs2.length;j++){
                if(chs1[i - 1] == chs2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + 1);
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
            }
        }
        return dp[chs1.length][chs2.length];
    }
}