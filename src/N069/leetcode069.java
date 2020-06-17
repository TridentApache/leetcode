package N069;

public class leetcode069 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.mySqrt(2147395599));
    }
}

class Solution {
    public int mySqrt(int x) {
        int i=0;
        int k=x;
        int ans=-1;
        while(i<=k){
            int mid=i+((k-i)>>>1); //注意，移位运算优先级小于加减运算优先级
            if((long)mid*mid<=x){//注意mid*mid会超出Integer范围
                ans=mid;
                i=mid+1;
            }else{
                k=mid-1;
            }
        }
        return ans;
    }
}

class Solution1{
    public int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {

            int mid = l + (r - l) / 2;
            if ((long)mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }
        return ans;
    }
}
