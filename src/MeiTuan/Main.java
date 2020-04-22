package MeiTuan;
import java.math.*;
import java.util.Scanner;
import java.util.Arrays;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            long m = sc.nextLong();
            int[] steps = new int[n];
            for(int i=0; i<n; i++){
                steps[i] = sc.nextInt();
            }
            System.out.println(moreStep(n,m,steps));

        }
    }

    public static long moreStep(int n, long m, int[] steps){
        int count = 0;
        long all = 0;
        for(int i=0; i<n; i++){
            all += steps[i];
        }
        long circle = m/all;
        m = m-(circle*all);
        for(int i=0; i<n; i++){
            if(m>=steps[i]){
                count++;
                m = m-steps[i];
            }
        }
        return count+n*circle;
    }

}