package N286;

import java.util.*;

/**
 * 你被给定一个 m × n 的二维网格，网格中有以下三种可能的初始化值：
 * -1 表示墙或是障碍物
 * 0 表示一扇门
 * INF 无限表示一个空的房间。然后，我们用 231 - 1 = 2147483647 代表 INF。你可以认为通往门的距离总是小于 2147483647 的。
 * 你要给每个空房间位上填上该房间到 最近门的距离，如果无法到达门，则填 INF 即可。
 * **/

/**
 * 注意：
 * 1. col row，不要用m，n这种
 * 2. 二维数组，if(rooms.length==0) return;//if(rooms==null) return;
 * 3. 层次遍历，不用再add到queue中了，第二层一定比第一层大
 * */

public class leetcode286 {
    public static void main(String[] agrs){
        Solution s = new Solution();
//        int[][] rooms = new int[][]{{Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},
//                {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
//                {Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},
//                {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}};
//        int[][] rooms = new int[][]{{2147483647,0,2147483647,2147483647,0,2147483647,-1,2147483647}};
        int[][] rooms = new int[][]{};
        s.wallsAndGates(rooms);
    }
}

class Solution {
    Deque<int[]> queue;
    int n;
    int m;
    public void wallsAndGates(int[][] rooms) {
        if(rooms.length==0) return;//if(rooms==null) return;
        n = rooms.length;
        m = rooms[0].length;
        queue = new ArrayDeque<int[]>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(rooms[i][j]==0){
                    queue.add(new int[]{i,j});
                }
            }
        }
        if(queue.size()!=0){
            BFS(rooms);
        }else{
            return;
        }
    }
    public void BFS(int[][] rooms){
        int count=0;
        while(queue.size()!=0) {
            int len=queue.size();
            for (int k = 0; k < len; k++) {
                int[] tmp = queue.removeFirst();
                int i = tmp[0];
                int j = tmp[1];

                if (i + 1 < n && rooms[i + 1][j] != 0 && rooms[i + 1][j] != -1) {
                    if(rooms[i + 1][j]==Integer.MAX_VALUE) queue.addLast(new int[]{i + 1, j});
//                    else rooms[i+1][j] = Math.min(rooms[i+1][j], count+1);
                }
                if (i - 1 >= 0 && rooms[i - 1][j] != 0 && rooms[i - 1][j] != -1) {
                    if(rooms[i - 1][j]==Integer.MAX_VALUE)  queue.addLast(new int[]{i - 1, j});
//                    else rooms[i-1][j] = Math.min(rooms[i-1][j], count+1);
                }
                if (j + 1 < m && rooms[i][j + 1] != 0 && rooms[i][j + 1] != -1) {
                    if(rooms[i][j+1]==Integer.MAX_VALUE)  queue.addLast(new int[]{i, j+1});
//                    else rooms[i][j+1] = Math.min(rooms[i][j+1], count+1);
                }
                if (j - 1 >= 0 && rooms[i][j - 1] != 0 && rooms[i][j - 1] != -1) {
                    if(rooms[i][j-1]==Integer.MAX_VALUE)  queue.addLast(new int[]{i, j-1});
//                    else rooms[i][j-1] = Math.min(rooms[i][j-1], count+1);
                }

                if(rooms[i][j]==0){
                    continue;
                }else if (rooms[i][j] != -1) {
                    rooms[i][j] = Math.min(rooms[i][j], count);
                }
            }
            count++;
        }
        return;
    }
}