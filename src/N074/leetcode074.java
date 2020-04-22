package N074;

public class leetcode074 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}};
        int target=3;
        System.out.println(s.searchMatrix(matrix,target));
    }
}

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //整体看成一维数组做二分查找，其实等于先列二分再行二分
        if(matrix.length==0||matrix[0].length==0) return false;
        if(matrix[0][0]==target) return true;
        int m = matrix.length;
        int n = matrix[0].length;
        if(target<matrix[0][0] || matrix[m-1][n-1]<target) return false;

        for(int i=0,j=m*n-1;i<j;){
            int row_1 = i/n;
            int col_1 = i%n;
            int row_2 = j/n;
            int col_2 = j%n;
            int mid = (i+j)/2;
            int row_mid=mid/n;
            int col_mid = mid%n;
            if(matrix[row_1][col_1]==target || matrix[row_2][col_2]==target || matrix[row_mid][col_mid]==target) return true;
            if(i==j-1){
                return false;
            }
            if(matrix[row_mid][col_mid]<target){
                i=mid;
            }else{
                j=mid;
            }
        }
        return false;

    }
}