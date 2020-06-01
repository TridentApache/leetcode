package N542;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class leetcode542 {
    public static void main(String[] args){
        Solution s = new Solution();
        s.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
    }
}
/**
 * 自己写的传统方法BFS
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1
 * */
class Solution {
    public int[][] res;
    int row;
    int col;
    public int[][] updateMatrix(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        if(row==0) return res;
        res = new int[row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(matrix[i][j]==0){
                    continue;
                }
                BFS(matrix,i,j);
            }
        }
        return res;
    }
    private void BFS(int[][]matrix , int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i,j});
        int count=1;
        while(queue.size()!=0){
            int len = queue.size();
            for(int k=0;k<len;k++){
                int[] tmp =  queue.poll();
                int tmpi=tmp[0];
                int tmpj=tmp[1];

                //四周有1个0，就返回
                if(tmpi-1>=0 && matrix[tmpi-1][tmpj]==0) {res[i][j]=count;return;}
                if(tmpi+1<row && matrix[tmpi+1][tmpj]==0) {res[i][j]=count;return;}
                if(tmpj-1>=0 && matrix[tmpi][tmpj-1]==0) {res[i][j]=count;return;}
                if(tmpj+1<col && matrix[tmpi][tmpj+1]==0) {res[i][j]=count;return;}

                //否则把四周加入队列
                if(tmpi-1>=0 && matrix[tmpi-1][tmpj]==1 ) {queue.add(new int[]{tmpi-1,tmpj});}
                if(tmpi+1<row && matrix[tmpi+1][tmpj]==1 ) {queue.add(new int[]{tmpi+1,tmpj});}
                if(tmpj-1>=0 && matrix[tmpi][tmpj-1]==1 ) {queue.add(new int[]{tmpi,tmpj-1});}
                if(tmpj+1<col && matrix[tmpi][tmpj+1]==1 ) {queue.add(new int[]{tmpi,tmpj+1});}
            }
            count++;
        }
    }
}
/**
 * 官方解答：多源广度优先搜索，设置一个超级节点，到所有的0
 * */
class Solution2 {
    public int[][] updateMatrix(int[][] matrix) {
        // 首先将所有的 0 都入队，并且将 1 的位置设置成 -1，表示该位置是 未被访问过的 1
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }

        int[] dx = new int[] {-1, 1, 0, 0};
        int[] dy = new int[] {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int x = point[0], y = point[1];
            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];
                // 如果四邻域的点是 -1，表示这个点是未被访问过的 1
                // 所以这个点到 0 的距离就可以更新成 matrix[x][y] + 1。
                if (newX >= 0 && newX < m && newY >= 0 && newY < n
                        && matrix[newX][newY] == -1) {
                    matrix[newX][newY] = matrix[x][y] + 1;
                    queue.offer(new int[] {newX, newY});
                }
            }
        }

        return matrix;
    }
}
