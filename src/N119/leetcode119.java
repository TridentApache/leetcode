package N119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class leetcode119 {
    public static void main(String[] args){
        Solution s = new Solution();
        s.getRow0(3);
    }
}

class Solution {
    public List<Integer> getRow0(int rowIndex) {
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(1);
        nums.add(1);
        List<Integer> res=new ArrayList<Integer>();
        for(int i=1;i<rowIndex;i++){
            res=new ArrayList<Integer>();
            res.add(1);
            for(int j=1;j<nums.size();j++){
                int resu = nums.get(j-1)+nums.get(j);
                res.add(j,resu);
            }
            res.add(1);
            nums=res;
            //if(i==rowIndex-1) return res;
        }
        return res;
        //return Arrays.asList(1);
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(pre.get(j - 1) + pre.get(j));
                }
            }
            pre = cur;
        }
        return cur;
    }

}
