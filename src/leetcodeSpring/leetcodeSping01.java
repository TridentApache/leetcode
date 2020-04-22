package leetcodeSpring;

import java.util.Arrays;

public class leetcodeSping01 {
    public static void main(String[] args){
//        Solution1 s1 = new Solution1();
//        System.out.println(s1.minCount(new int[] {3}));

//        Solution2 s2 = new Solution2();
//
//        int n=5;
//        int[][] x = new int[][]{{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
//        int k=3;
//        System.out.println(s2.numWays(n,x,k));

        /**s3*/
        Solution3 s3 = new Solution3();
//        int[][] increase = new int[][]{{1,1,1}};
//        int[][] req = {{0,0,0}};

//        int[][] increase = new int[][]{{0,4,5},{4,8,8},{8,6,1},{10,10,0}};
//        int[][] req = {{12,11,16},{20,2,6},{9,2,6},{10,18,3},{8,14,9}};

//        int[][] increase = new int[][]{{2,8,4},{2,5,0},{10,9,8}};
//        int[][] req = {{2,11,3},{15,10,7},{9,17,12},{8,1,14}};
//        System.out.println(Arrays.toString(s3.getTriggerTime(increase,req)));

        /**s4*/
//        Solution4_1 s4 = new Solution4_1();
//        System.out.println(s4.minJump(new int[]{2,5,1,1,1,1}));
        long x=-2147483648;
        long y=-x;
        Solution6 s6 = new Solution6();
        System.out.println(s6.getHappyString(4,2));
    }
}


class Solution6 {
    public String getHappyString(int n, int k) {
        int x=0;
        int y=-1;
        StringBuilder s = new StringBuilder();
        char lasts='a';
        int len = (int)Math.pow(2,n-1);
        if(k>3*len) return s.toString();

        while(!(x==1&&y==0)){
            int z = (int)Math.pow(2,n-1);
            x=k/z;
            y=k%z;
            if(s.length()!=0){
                lasts = s.charAt(s.length() - 1);
            }
            if(k<=z){
                if(s.length()==0){
                    s.append("a");
                }else if(lasts=='a'){
                    if(x==0)
                    s.append("b");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='b'){
//                    s.append("a");
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='c'){
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("b");
                }
            }else if(k<=2*z){
                if(s.length()==0){
                    s.append("b");
                }else if(lasts=='a'){
                    if(x==0)
                        s.append("b");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='b'){
//                    s.append("a");
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='c'){
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("b");
                }
            }else {
                if(s.length()==0){
                    s.append("c");
                }else if(lasts=='a'){
                    if(x==0)
                        s.append("b");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='b'){
//                    s.append("a");
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("c");
                }else if(lasts=='c'){
                    if(x==0)
                        s.append("a");
                    else if(x==1)
                        s.append("b");
                }
            }
            n=n-1;
            k=k-x*z;
        }
        return s.toString();
    }
}

class Solution5 {
    public int findMinFibonacciNumbers(int k) {
        int count=0;
        int x=0;
        while(k!=0){
            x = MaxFib(k);
            k=k-x;
            count++;
        }
        return count;
    }
    public int MaxFib(int k){
        int f1 = 1;
        int  f2 = 1;
        while(f2<k){
            int tmp = f1+f2;
            f1=f2;
            f2=tmp;
        }
        if(f2==k) return f2;
        return f1;
    }
}

/**
 * 第4题：
 * 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机。游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1。初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处，通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置。也就是说，在编号为 i 弹簧处按动弹簧，小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）。小球位于编号 0 处的弹簧时不能再向左弹。
 * 为了获得奖励，你需要将小球弹出机器。请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
 *
 * 示例 1：
 * 输入：jump = [2, 5, 1, 1, 1, 1]
 * 输出：3
 * 解释：小 Z 最少需要按动 3 次弹簧，小球依次到达的顺序为 0 -> 2 -> 1 -> 6，最终小球弹出了机器。
 *
 * 限制：
 * 1 <= jump.length <= 10^6
 * 1 <= jump[i] <= 10000
 * */



class Solution4_1 {
    public int minJump(int[] jump) {
        boolean[] dp = new boolean[jump.length + 1];
        dp[jump.length] = true;
        int count = 0;
        while (true) {
            boolean prev = false;
            for (int i = 0; i < jump.length; ++i) {
                if (dp[i]) {
                    prev = true;
                    continue;
                }
                if (prev) {
                    dp[i] = true;
                    continue;
                }
                if (i + jump[i] >= jump.length || dp[i + jump[i]]) {
                    dp[i] = true;
                }
            }
            ++count;
            if (dp[0]) {
                break;
            }
        }
        return count;
    }
}

class Solution4{
    int row;
    int col;
    int[][] array;

    public int minJump(int[] jump) {
        int n = jump.length+1;
        if(jump.length==1) return 1;

        int[][] X = new int[n][n];

        for(int i=0;i<n;i++){
            if(i==jump[0]){
                X[0][i]=1;
            }else{
                X[0][i]=0;
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<n-1;j++){
                if(j==i){
                    X[i][j]=0;
                }else if(j<i){
                    X[i][j]=1;
                }else if(jump[i]+i<=n-1 && j==jump[i]+i) {
                    X[i][j]=1;
                }else if(jump[i]+i>=n-1){
                    X[i][n-1]=1;
                }else{
                    X[i][j]=0;
                }
            }
        }
        Arrays.fill(X[n-1],0);
        row=n;
        col=n;
        array = X;
        int k=1;
        while(X[0][n-1]<1){
            X=multip(X);
            k++;
        }

        return k;
    }

    private int[][] multip(int[][] aim) {
        if (this.col != aim.length) {
            return null;
        }
        int[][] result = new int[this.row][aim[0].length];
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < aim[0].length; col++) {
                int num = 0;
                for (int i = 0; i < this.col; i++) {
                    num += array[row][i] * aim[i][col];
                }

                result[row][col] = num;
            }
        }
        return result;
    }
}

/**
 * 第3题：
 * 在战略游戏中，玩家往往需要发展自己的势力来触发各种新的剧情。一个势力的主要属性有三种，分别是文明等级（C），资源储备（R）以及人口数量（H）。在游戏开始时（第 0 天），三种属性的值均为 0。
 * 随着游戏进程的进行，每一天玩家的三种属性都会对应增加，我们用一个二维数组 increase 来表示每天的增加情况。这个二维数组的每个元素是一个长度为 3 的一维数组，例如 [[1,2,1],[3,4,2]] 表示第一天三种属性分别增加 1,2,1 而第二天分别增加 3,4,2。
 * 所有剧情的触发条件也用一个二维数组 requirements 表示。这个二维数组的每个元素是一个长度为 3 的一维数组，对于某个剧情的触发条件 c[i], r[i], h[i]，如果当前 C >= c[i] 且 R >= r[i] 且 H >= h[i] ，则剧情会被触发。
 * 根据所给信息，请计算每个剧情的触发时间，并以一个数组返回。如果某个剧情不会被触发，则该剧情对应的触发时间为 -1 。
 *
 * 示例 1：
 * 输入： increase = [[2,8,4],[2,5,0],[10,9,8]] requirements = [[2,11,3],[15,10,7],[9,17,12],[8,1,14]]
 * 输出: [2,-1,3,-1]
 *
 * 解释：
 * 初始时，C = 0，R = 0，H = 0
 * 第 1 天，C = 2，R = 8，H = 4
 * 第 2 天，C = 4，R = 13，H = 4，此时触发剧情 0
 * 第 3 天，C = 14，R = 22，H = 12，此时触发剧情 2
 * 剧情 1 和 3 无法触发。
 *
 * 示例 2：
 * 输入： increase = [[0,4,5],[4,8,8],[8,6,1],[10,10,0]] requirements = [[12,11,16],[20,2,6],[9,2,6],[10,18,3],[8,14,9]]
 * 输出: [-1,4,3,3,3]
 *
 * 示例 3：
 * 输入： increase = [[1,1,1]] requirements = [[0,0,0]]
 * 输出: [0]
 *
 * 限制：
 * 1 <= increase.length <= 10000
 * 1 <= requirements.length <= 100000
 * 0 <= increase[i] <= 10
 * 0 <= requirements[i] <= 100000
 *
 * */
class Solution3{
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {

        int time =increase.length;
        int n = requirements.length;
        if(n==0&&time==0) return null;


        int[] req = new int[n];

        if(time==0){
            Arrays.fill(req,-1);
            return req;
        }

        int[][] day = new int[time][3];
        day[0][0]=increase[0][0];
        day[0][1]=increase[0][1];
        day[0][2]=increase[0][2];

        for(int i=1;i<time;i++){
            day[i][0]=day[i-1][0]+increase[i][0];
            day[i][1]=day[i-1][1]+increase[i][1];
            day[i][2]=day[i-1][2]+increase[i][2];
        }


        for(int i=0;i<n;i++){
            if(requirements[i][0]==0 && 0==requirements[i][1]
                    && 0==requirements[i][2]){
                req[i]=0;
            }else {
                for (int j = 0; j < time; j++) {
                    if (day[j][0] >= requirements[i][0] && day[j][1] >= requirements[i][1]
                            && day[j][2] >= requirements[i][2]) {
                        req[i] = j + 1;
                        break;
                    } else {
                        if (j == time - 1)
                            req[i] = -1;
                    }
                }
            }
        }
        return req;
    }
}

class Solution3_1 {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int day = 0;
        int[] ans = new int[requirements.length];
        //将increase中的三元组的含义变为每一天的属性值
        for(int i = 1;i<increase.length;i++){
            increase[i][0] += increase[i-1][0];
            increase[i][1] += increase[i-1][1];
            increase[i][2] += increase[i-1][2];
        }
        for(int i = 0;i<requirements.length;i++){
            if(requirements[i][0]==0&&requirements[i][1]==0&&requirements[i][2]==0) ans[i] = 0;
            else{
                int left = 0;
                int right = increase.length-1;
                //如果最后一天仍不满足，设为-1
                if(!(increase[right][0]>=requirements[i][0]&&increase[right][1]>=requirements[i][1]&&increase[right][2]>=requirements[i][2])){
                    ans[i] = -1;
                    continue;
                }
                //二分查找
                while(left <right){
                    int mid = (left+right)/2;
                    if(!(increase[mid][0]>=requirements[i][0]&&increase[mid][1]>=requirements[i][1]&&increase[mid][2]>=requirements[i][2])){
                        left = mid+1;
                    }else{
                        right = mid;
                    }
                }
                ans[i] = left+1;
            }
        }
        return ans;
    }
}


class Solution1 {
    public int minCount(int[] coins) {
        if(coins==null) return 0;
        int sum=0;
        for(int i=0;i<coins.length;i++){
            sum=sum+coins[i]/2+coins[i]%2;
        }
        return sum;
    }
}
class Solution2{
    int row;
    int col;
    int[][] array;

    public int numWays(int n, int[][] relation, int k) {
        if(relation==null) return 0;
        int[][] X = new int[n][n];
        for(int i=0;i<relation.length;i++){
                X[relation[i][0]][relation[i][1]]=1;
        }
        row=n;
        col=n;
        array = X;
        for(int i=1;i<k;i++){
            X=multip(X);
        }
        return X[0][n-1];
    }

    private int[][] multip(int[][] aim) {
        if (this.col != aim.length) {
            return null;
        }
        int[][] result = new int[this.row][aim[0].length];
        for (int row = 0; row < this.row; row++) {
            for (int col = 0; col < aim[0].length; col++) {
                int num = 0;
                for (int i = 0; i < this.col; i++) {
                    num += array[row][i] * aim[i][col];
                }

                result[row][col] = num;
            }
        }
        return result;
    }
}


