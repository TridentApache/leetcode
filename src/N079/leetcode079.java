package N079;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例:
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 *
 * 注意：
 * 本题思路其实就是dfs，在写递归dfs的过程中，常犯的错误如下：
 * 1. 喜欢瞎return
 * 2. 判断递归结束的东西很乱，如果一直递归到最后一步而不是提前一步结束，就把条件全部放到最后一步的判断部分！！
 * */

public class leetcode079 {
    public static void main(String[] args){
        Solution s = new Solution();
        char[][] board  = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        String word = "ABCB";
//        char[][] board  = new char[][]{
//                {'a','a'}};
//        String word = "aa";
        System.out.println(s.exist(board,word));
    }
}

class Solution {
    private int row;
    private int col;
    private int flag=0;
    private int[][] s;
    private int curs;

    public boolean exist(char[][] board, String word) {
        this.row = board.length;
        this.col = board[0].length;
        this.s=new int[row][col];
        if(row==0||col==0) return false;
        if(row==1&&col==1&&word.length()==1){
            if(board[0][0]==word.charAt(0)) return true;
            else return false;
        }
        if((row==1&& word.length()>col)||(col==1&& word.length()>row)){
            return false;
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(dfs(i,j,board,word,0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char[][] board, String word, int k){
        if(flag==1) return true;
        if (k==word.length()){
            flag=1;
            return true;
        }

        if(i<0||i>=row||j<0||j>=col)
                return false;
        if(s[i][j]==1)
                return false;

        if(board[i][j]==word.charAt(k)){
            s[i][j]=1;
            dfs(i-1,j,board,word,k+1); //栈顶是相反方向
            dfs(i+1,j,board,word,k+1);
            dfs(i,j-1,board,word,k+1);
            dfs(i,j+1,board,word,k+1);
        }else{
            s[i][j]=0;
        }
        if(flag==1) return true;
        s[i][j]=0;
        return false;
    }
}


class Solution1 {
    private int row;
    private int col;
    private int flag=0;
    private int[][] s;
    private int curs;

    public boolean exist(char[][] board, String word) {
        this.row = board.length;
        this.col = board[0].length;
        this.s=new int[row][col];
        if(row==0||col==0) return false;
        if(row==1&&col==1&&word.length()==1){
            if(board[0][0]==word.charAt(0)) return true;
            else return false;
        }
        if((row==1&& word.length()>col)||(col==1&& word.length()>row)){
            return false;
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(dfs(i,j,board,word,0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, char[][] board, String word, int k){
        if(flag==1) return true;
        if (k==word.length()){
            flag=1;
            return true;
        }
        if(board[i][j]==word.charAt(k)){
            if(i-1>=0 && s[i-1][j]!=1){ s[i][j]=1; dfs(i-1,j,board,word,k+1);} //条件放在倒数一步判断，导致如果是一维数组会出错
            if(i+1<row && s[i+1][j]!=1 ){ s[i][j]=1; dfs(i+1,j,board,word,k+1);}
            if(j-1>=0 && s[i][j-1]!=1){ s[i][j]=1; dfs(i,j-1,board,word,k+1);}
            if(j+1<col && s[i][j+1]!=1){ s[i][j]=1;  dfs(i,j+1,board,word,k+1);}
        }else{
            s[i][j]=0;//这里不能瞎return
        }
        if(flag==1) return true;
        s[i][j]=0;
        return false;
    }
}