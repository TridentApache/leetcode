package N078;

import java.util.*;

public class leetcode078 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums = new int[]{1,2,2,3};
        List<List<Integer>> ls = s.subsetsWithDup(nums);
        ListIterator it = ls.listIterator();
        while(it.hasNext()){
            System.out.println(it.next());//迭代器直接next就能打印？！！！！！
        }
    }
}

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //
        List<List<Integer>> ls = new ArrayList<List<Integer>>();
        ls.add(new ArrayList<>(0));

        int m = nums.length;
        if(m==0) return ls;
        int totalNum = (int)Math.pow(2,m)-1;
        for(int i=1;i<=totalNum;i++){
            //i转化为二进制
            String binaryStr = Integer.toBinaryString(i);
            byte[] c = binaryStr.getBytes();//注意getBytes()的顺序,c[0]为最高位
            List<Integer> l = new ArrayList<>();
            for(int j=c.length-1;j>=0;j--){
                if(c[j]==49) l.add(nums[c.length-j-1]); //"1"的ASCII码是49
            }
            ls.add(l);
        }
        return ls;
    }

    public List<List<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> lists = new ArrayList<>();
        int subsetNum = 1<<num.length;
        for(int i=0;i<subsetNum;i++){
            List<Integer> list = new ArrayList<>();
            boolean illegal=false;
            for(int j=0;j<num.length;j++){
                //当前位是 1
                if((i>>j&1)==1){
                    //当前是重复数字，并且前一位是 0，跳过这种情况
                    if(j>0&&num[j]==num[j-1]&&(i>>(j-1)&1)==0){
                        illegal=true;
                        break;
                    }else{
                        list.add(num[j]);
                    }
                }
            }
            if(!illegal){
                lists.add(list);
            }

        }
        return lists;
    }
}

