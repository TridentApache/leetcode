package N200;

import java.util.HashMap;
import java.util.LinkedList;

public class leetcode200 {
    public static void main(String[] args){
        Solution1_1 s = new Solution1_1();
        char[][] test=new char[][]{{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
        System.out.println(s.numIslands(test));
    }
}
/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 *
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * */

class Solution {
    int count=0;
    int row;
    int col;
    boolean [][] visited;
    LinkedList<int[]> queue;
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        return BFS(grid);
    }
    public int BFS(char[][] grid){
        queue = new LinkedList<int[]>();
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1' && visited[i][j]==false) {
                    queue.add(new int[]{i, j});
                    visited[i][j]=true;
                    count++;
                    tmp(grid);
                }
            }
        }
        return count;
    }
    public void tmp(char[][] grid){
        while(queue.size()!=0){
            int len = queue.size();
            for(int k=0;k<len;k++){
                int[] tmp = queue.removeFirst();
                int i = tmp[0];
                int j= tmp[1];

                if(i-1>=0 && grid[i-1][j]=='1' && visited[i-1][j]==false){
                        queue.addLast(new int[]{i - 1, j});
                        visited[i - 1][j] = true;

                }

                if(j-1>=0 && grid[i][j-1]=='1' && visited[i][j-1]==false){
                        queue.addLast(new int[]{i,j-1});
                        visited[i][j-1]=true;
                }

                if(i+1<row && grid[i+1][j]=='1' && visited[i+1][j]==false){
                        queue.addLast(new int[]{i+1,j});
                        visited[i+1][j]=true;
                }

                if(j+1<col && grid[i][j+1]=='1' && visited[i][j+1]==false){
                        queue.addLast(new int[]{i,j+1});
                        visited[i][j+1]=true;
                }
            }
        }
    }
}

/**
 * 修改，去掉visited数组
 * */
class Solution0_1 {
    int count=0;
    int row;
    int col;
    LinkedList<int[]> queue;
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        return BFS(grid);
    }
    public int BFS(char[][] grid){
        queue = new LinkedList<int[]>();
        row = grid.length;
        col = grid[0].length;
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    queue.add(new int[]{i, j});
                    grid[i][j]='0';
                    count++;
                    tmp(grid);
                }
            }
        }
        return count;
    }
    public void tmp(char[][] grid){
        while(queue.size()!=0){
            int len = queue.size();
            for(int k=0;k<len;k++){
                int[] tmp = queue.removeFirst();
                int i = tmp[0];
                int j= tmp[1];

                if(i-1>=0 && grid[i-1][j]=='1'){
                    queue.addLast(new int[]{i - 1, j});
                    grid[i - 1][j] = '0';

                }

                if(j-1>=0 && grid[i][j-1]=='1'){
                    queue.addLast(new int[]{i,j-1});
                    grid[i][j-1]='0';
                }

                if(i+1<row && grid[i+1][j]=='1'){
                    queue.addLast(new int[]{i+1,j});
                    grid[i+1][j]='0';
                }

                if(j+1<col && grid[i][j+1]=='1'){
                    queue.addLast(new int[]{i,j+1});
                    grid[i][j+1]='0';
                }
            }
        }
    }
}


/**
 * DFS解法
 * */
class Solution1_1 {
    int count=0;
    int row;
    int col;
    public int numIslands(char[][] grid) {
        if(grid.length==0) return 0;
        row = grid.length;
        col = grid[0].length;
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }
    public void dfs(char[][] grid,int i,int j){
        if(i<0||i>=row||j<0||j>=col||grid[i][j]=='0') return;
        grid[i][j]='0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
}