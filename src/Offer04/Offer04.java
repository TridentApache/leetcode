package Offer04;

public class Offer04 {
    public static void main(String[] args){
//        Solution s = new Solution();
//        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int[][] matrix = {{1,4,7,11,15},{22,25,28,32,39},{43,46,49,56,62},{70,73,74,77,84},{88,91,93,96,100}};
//        s.findNumberIn2DArray(matrix,62);
    }
}
/**题目审错，仅仅每行递增，可以用逐行二分法*/
/**
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int row = matrix.length;
        if(row==0) return false;
        int col = matrix[0].length;
        if(matrix[0][0]>target) return false;
        if(matrix[row-1][col-1]<target) return false;

        int low=0;
        int high=row-1;
        while(low+1<high){
            int mid=low+((high-low)>>>1);
            if(matrix[mid][0]==target) return true;
            else if(matrix[mid][0]<target){
                low=mid;
            }else if(matrix[mid][0]>target){
                high=mid;
            }
        }

        int i=0;
        if(matrix[low][0]<target && target<matrix[low+1][0]) i=low;
        if(low-1>=0 && matrix[low-1][0]<target && target<matrix[low][0]) i=low-1;
        if(low+2<row && matrix[low+1][0]<target && target<matrix[low+2][0]) i=low+1;

        low=0;
        high=col-1;
        while(low+1<high){
            int mid=low+((high-low)>>>1);
            if(matrix[i][mid]==target) return true;
            else if(matrix[i][mid]<target){
                low=mid;
            }else if(matrix[i][mid]>target){
                high=mid;
            }
        }

        //...对行的判断
        return false;

    }
}
*/