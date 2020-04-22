package MeiTuan;

import java.util.Scanner;

public class twopath {
    static int count=0;
    static int res=0;
    public static int findpath(int n, String s1, String s2){
        if(n==0){return 0;}
        subfind(n, 0, 0, s1, s2);
        return count==0?-1:count;
    }
    public static void subfind(int n, int i, int j, String s1, String s2){
        if(i<0||i>1||j>=n){return;}
        char ch='.';
        if(i==0){
            ch=s1.charAt(j);
        }else{
            ch=s2.charAt(j);
        }
        if(j==n-1&&ch=='.'){
            count++;
            return;
        }
        if(ch=='x'){return;}
        subfind(n, i+1, j+1, s1, s2);
        subfind(n, i-1, j+1, s1, s2);
        subfind(n, i, j+1, s1, s2);
    }
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        int n=sc.nextInt();
//        String s1=sc.next();
//        String s2=sc.next();
//        int count = twopath.findpath(n, s1, s2);
//        System.out.println(count);
//        sc.close();
//
//    }
}