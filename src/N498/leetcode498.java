package N498;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class leetcode498 {
    public static void main(String[] args){
        Solution1 s = new Solution1();
        s.findDiagonalOrder(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
//        s.findDiagonalOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}});
//        s.findDiagonalOrder(new int[][]{{1,2},{3,4}});
    }
}
/**
 * 注意：toArray要求里面的参数是集合或者String，
 *  the component type of the array to contain the collection
 * */
class Solution {//错误代码，
    public int[] findDiagonalOrder(int[][] matrix) {
        ArrayList<Integer> ls = new ArrayList<Integer>();
        int row = matrix.length;
        if(row==0) return new int[0];
        int col = matrix[0].length;
        int K = row+col-1;//i+j的总和
        int flag=-1;
        int i=0,j=0;
        for(int k=0;k<K;k++){
            while(i>=0 && i<row && j>=0 && j<col && i+j==k){
                ls.add(matrix[i][j]);
                i=i+flag;
                j=j-flag;
            }
            //边界处理
            if(i<0||j<0) {//对于正方形，出现的负数，可能另一个也会超出边界
                i = i < 0 ? 0 : i;
                j = j < 0 ? 0 : j;
                if(i>=row){i=row-1;j++;}
                if(j>=col){j=col-1;i++;}
            }else if(i>=row){i=row-1;j=j+2;}//只有边界超出的情况
            else if(j>=col){i=i+2;j=col-1;}
            //方向反转
            flag=flag*(-1);
        }
        Integer[] res = new Integer[ls.size()];
        ls.toArray(res);
        return new int[0];
    }
}

class Solution1 {
    public int[] findDiagonalOrder(int[][] matrix) {
        int row = matrix.length;
        if(row==0) return new int[0];
        int col = matrix[0].length;
        int[] res = new int[row*col];
        int K = row+col-1;//i+j的总和
        int flag=-1;
        int i=0,j=0;
        int n=0;
        for(int k=0;k<K;k++){
            while(i>=0 && i<row && j>=0 && j<col && i+j==k){
                res[n++]=matrix[i][j];
                i=i+flag;
                j=j-flag;
            }
            //边界处理
            if(i<0||j<0) {//对于正方形，出现的负数，可能另一个也会超出边界
                i = i < 0 ? 0 : i;
                j = j < 0 ? 0 : j;
                if(i>=row){i=row-1;j++;}
                if(j>=col){j=col-1;i++;}
            }else if(i>=row){i=row-1;j=j+2;}//只有边界超出的情况
           else if(j>=col){i=i+2;j=col-1;}

            //方向反转
            flag=flag*(-1);
        }
        return res;
    }
}