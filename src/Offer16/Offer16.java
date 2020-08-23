package Offer16;

public class Offer16 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(Math.pow(1/8.84372,5));
        System.out.println(s.myPow(8.84372,-5));
        System.out.println(Math.pow(8.84372,-5));

    }
}

class Solution {
    public double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }
}


/**错误代码，比如n=5，实际上算的是(x^3)^2**/
//class Solution {
//    public double myPow(double x, int n) {
//        if(x==0) return 0;
//        if(n==0) return 1;
//        long b=n;
//        if(n<0){
//            x=1/x;
//            b=-b;
//        }
//        double res=x;
//        while(b!=1){
//            res=res*res;
//            if((b&1)==1) res=res*x;
//            b=(b>>>1);
//        }
//        return res;
//    }
//}