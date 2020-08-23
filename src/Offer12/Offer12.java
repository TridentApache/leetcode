package Offer12;

public class Offer12 {
    public static void main(String[] args){
        Solution s = new Solution();
//        char[][] k = new char[][]{
//                {'a','b','c','e'},
//                {'s','f','e','s'},
//                {'a','d','e','e'}};
        char[][] k = new char[][]{
                {'a','a','a'},
                {'a','b','b'},
                {'a','b','b'},
                {'b','b','b'},
                {'b','b','b'},
                {'a','a','a'},
                {'b','b','b'},
                {'a','b','b'},
                {'a','a','b'},
                {'a','b','a'}};
        System.out.println(s.exist(k,"aabaaaabbb"));
    }
}
/**
 * boolean flag变量记录dfs走过的路径，之前一直出错，
 * 1. 深拷贝、浅拷贝的问题，倒置flag为全局变量，无法对应当前的栈帧另外生成一份副本；
 * 2. 什么时候保存/恢复现场？在出栈返回上一级调用的栈帧时恢复现场
 * **/
class Solution {
    char[][] board;
    int row;
    int col;
    public boolean exist(char[][] board, String word) {
        row = board.length;
        if(row==0) return false;
        col = board[0].length;
        if(word.length()>row*col) return false;

        this.board = board;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                boolean[][] flag = new boolean[row][col];
                if(dfs(i,j,word,0,flag)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, String word, int k, boolean[][] flag){

        if(k>word.length()) return false;
        if(flag[i][j]==true) return false;
        if(k<word.length()&&board[i][j]!=word.charAt(k)) return false;
        ///////////////
        if(k<word.length()&&board[i][j]==word.charAt(k)) flag[i][j]=true;
        if(k==word.length()-1&&board[i][j]==word.charAt(k))
            return true;

        boolean flag1=false,flag2=false,flag3=false,flag4=false;


        if(i-1>=0){
            flag1 = dfs(i-1,j,word,k+1,flag);
            if(flag1) return true;
//            flag[i-1][j]=false;
        }

        if(j-1>=0){
            flag2 = dfs(i,j-1,word,k+1,flag);
            if(flag2) return true;
//            flag[i][j-1]=false;
        }

        if(i+1<row){
            flag3 = dfs(i+1,j,word,k+1,flag);
            if(flag3) return true;
//            flag[i+1][j]=false;
        }

        if(j+1<col){
            flag4 = dfs(i,j+1,word,k+1,flag);
            if(flag4) return true;
//            flag[i][j+1]=false;
        }
        if(k<word.length()&&board[i][j]==word.charAt(k))
            flag[i][j]=false;
        return false;
    }
}

class Solution_meng {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(matrix==null||matrix.length==0||rows==0||cols==0||str==null||str.length==0){
            return false;
        }
        int[] flag = new int[matrix.length];
        for(int k=0; k<matrix.length; k++){
            flag[k] = 1;
        }
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(matrix[i*cols+j]==str[0]){
                    flag[i*cols+j] = -1;
                    if(path(matrix, flag, rows, cols, i, j, str, 1)){
                        return true;
                    }
                    flag[i*cols+j] = 1;
                }
            }
        }
        return false;
    }
    public boolean path(char[] matrix, int[] flag, int rows, int cols, int i, int j, char[] str, int index){
        if(index==str.length){
            return true;
        }
        if(i-1>=0){
            if(matrix[(i-1)*cols+j]==str[index]&&flag[(i-1)*cols+j]!=-1){
                flag[(i-1)*cols+j] = -1;
                if(path(matrix, flag, rows, cols, i-1, j, str, index+1)){
                    return true;
                }
                flag[(i-1)*cols+j] = 1;
            }
        }
        if(i+1<rows){
            if(matrix[(i+1)*cols+j]==str[index]&&flag[(i+1)*cols+j]!=-1){
                flag[(i+1)*cols+j] = -1;
                if(path(matrix, flag, rows, cols, i+1, j, str, index+1)){
                    return true;
                }
                flag[(i+1)*cols+j] = 1;
            }
        }
        if(j-1>=0){
            if(matrix[i*cols+j-1]==str[index]&&flag[i*cols+j-1]!=-1){
                flag[i*cols+j-1] = -1;
                if(path(matrix, flag, rows, cols, i, j-1, str, index+1)){
                    return true;
                }
                flag[i*cols+j-1] = 1;
            }
        }
        if(j+1<cols){
            if(matrix[i*cols+j+1]==str[index]&&flag[i*cols+j+1]!=-1){
                flag[i*cols+j+1] = -1;
                if(path(matrix, flag, rows, cols, i, j+1, str, index+1)){
                    return true;
                }
                flag[i*cols+j+1] = 1;
            }
        }
        return false;
    }

}