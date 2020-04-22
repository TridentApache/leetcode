package alibaba;

import java.util.Arrays;
import java.util.Stack;

public class ali1 {
    public static void main(String[] args){
        int n = 3;
        int k = 2;
        int[][] map = new int[][]{{1,2,5},{10,11,6},{12,12,7}};
        Solution s= new Solution();
        System.out.println(s.MaxPath(n,k,map));
    }

}

class Solution{
    boolean[][] matrix;
    int maxValue=0;
    Stack<Integer> curValue= new Stack<>();
    int[] dir_x = new int[]{1,-1,0,0};
    int[] dir_y = new int[]{0,0,1,-1};

    public int MaxPath(int n, int k, int[][] map){
        if(n==1) return map[0][0];
        matrix = new boolean[n][n];
        matrix[0][0]=true;
        curValue.push(0);
        dfs(0,0, n, k, map);
        return maxValue;
    }

    private void dfs(int x, int y, int n, int k, int[][] map){
        int newx;
        int newy;
        int test=0;

        for(int i=0;i<4;i++){
            for(int j=1;j<=k;j++){
                newx = x + dir_x[i]*k;
                newy = y + dir_y[i]*k;
                if(newx < 0 || newx >= n || newy < 0 || newy >= n || map[newx][newy] <= map[x][y]){
                    test++;
                }
            }
        }
        curValue.push(curValue.peek() + map[x][y]);
        maxValue = maxValue<curValue.peek()?curValue.peek():maxValue;
        if(test==4*k){
            test=0;
            if(!curValue.empty())
                curValue.pop();
            return;
        }

        for(int i=0;i<4;i++){
            for(int j=1;j<=k;j++) {
                newx = x + dir_x[i]*k;
                newy = y + dir_y[i]*k;
                if (newx >= 0 && newx < n && newy >= 0 && newy < n && !matrix[newx][newy]
                        && map[newx][newy] > map[x][y]) {
                    matrix[newx][newy]=true;
                    dfs(newx, newy, n, k, map);
                    matrix[newx][newy]=false;
                }
            }
        }
        curValue.pop();
    }
}