package N841;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode841 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.canVisitAllRooms(Arrays.asList(
                Arrays.asList(1,3),
                Arrays.asList(3,0,1),
                Arrays.asList(2),
                Arrays.asList(0))));
    }
}

/**
 * 有 N 个房间，开始时你位于 0 号房间。每个房间有不同的号码：0，1，2，...，N-1，并且房间里可能有一些钥匙能使你进入下一个房间。
 *
 * 在形式上，对于每个房间 i 都有一个钥匙列表 rooms[i]，每个钥匙 rooms[i][j] 由 [0,1，...，N-1] 中的一个整数表示，其中 N = rooms.length。 钥匙 rooms[i][j] = v 可以打开编号为 v 的房间。
 *
 * 最初，除 0 号房间外的其余所有房间都被锁住。
 *
 * 你可以自由地在房间之间来回走动。
 *
 * 如果能进入每个房间返回 true，否则返回 false。
 *
 * */
class Solution {
    public int number;
    public boolean[] res;
    public List<List<Integer>> rooms;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        this.rooms =rooms;
        number=rooms.size();
        res = new boolean[number];
        dfs(0);
        for(int i=0;i<number;i++){
            if(res[i]==false)
                return false;
        }
        return true;
    }
    private void dfs(int i){
        if(res[i]==false) res[i]=true;
        else return;
        for(Integer k:rooms.get(i)){
            dfs(k);
        }
    }

}