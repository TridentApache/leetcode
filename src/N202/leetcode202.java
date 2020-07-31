package N202;

import java.util.HashMap;

public class leetcode202 {
    public static void main(String[] args){
        Solution1 s = new Solution1();
        System.out.println(s.isHappy(19));
    }
}

/**代码写的太烂了*/
class Solution {
    public boolean isHappy(int n) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int fast=happy(n);
        int slow=n;
        if(fast==1||slow==1) return true;
        while(fast!=slow){
            if(map.containsKey(fast)) return false;
            int next = happy(slow);
            map.put(slow,next);slow=next;
            int nnext = happy(happy(fast));
            map.put(fast,nnext);fast=nnext;
            if(fast==1||slow==1) return true;
        }
        return false;
    }
    public int happy(int n){
        int sum=0;
        while(n!=0){
            int tmp=n%10;
            sum+=(tmp*tmp);
            n/=10;
        }
        return sum;
    }
}

/***/
class Solution1 {
    public boolean isHappy(int n) {
        int fast=happy(n);
        int slow=n;
        if(fast==1||slow==1) return true;
        while(fast!=1 && fast!=slow){
            slow = happy(slow);
            fast = happy(happy(fast));
            if(fast==1||slow==1) return true;
        }
        return false;
    }
    public int happy(int n){
        int sum=0;
        while(n!=0){
            int tmp=n%10;
            sum+=(tmp*tmp);
            n/=10;
        }
        return sum;
    }
}