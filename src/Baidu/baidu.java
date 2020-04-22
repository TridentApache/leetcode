package Baidu;

import java.util.Arrays;

public class baidu {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.findk2(3,new long[]{1,1,(long)Math.pow(10,18)}));

    }
}
class Solution{
    public long findk(int n,long[] a){
        long k=0;
        while(true){
            Arrays.sort(a);
            if(a[n-2]<n){
                long q=Math.min((a[n-1]-n)/n,n-a[n-2]);
                for(int i=0;i<n-1;i++){
                    a[i]=a[i]+q;
                }
                a[n-1]=a[n-1]-q*n;
                k=k+q;
            }

            if(a[n-1]<n) return k;
            for(int i=0;i<n-1;i++){
                a[i]++;
            }
            a[n-1]=a[n-1]-n;
            k++;
        }
    }

    public long findk1(int n,long[] a){
        long k=0;
        while(true){
            long maxnum=a[0];
            int maxi=0;
            int flag=0;
            for(int i=0;i<n;i++){
                a[i]++;
                if(maxnum<a[i]){
                    maxi=i;
                    maxnum=a[i];
                }
                if(a[i]<n) flag++;
            }
            a[maxi]=a[maxi]-n-1;
            flag=a[maxi]<n?flag+1:flag;

            k++;
            if(flag==n) return k;
        }
    }
    public long findk2(int n,long[] a){
        Arrays.sort(a);
        return 1L;
    }

}
