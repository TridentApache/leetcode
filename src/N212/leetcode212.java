package N212;

import java.util.ArrayList;
import java.util.List;

public class leetcode212 {
    public static void main(String[] args){
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        Solution s = new Solution();
        s.findWords(board,words);
    }
}


class Solution {
    private TrieNode root;
    private int row;
    private int col;
    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        for(String word:words){
            insert(word);
        }
        List<String> res = new ArrayList<String>();
        row = board.length;
        if(row==0) return res;
        col = board[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                search(board, i,j,root,res);
            }
        }
        return res;
    }

    private void search(char[][] board, int i, int j, TrieNode node, List<String> res){
        if(i<0||i>=row||j<0||j>=col||node==null) return;

        char c =board[i][j];
        if(c=='$'||node.children[c-'a']==null) return;
        node = node.children[c-'a'];

        if(node.word!=null){
            res.add(node.word);
            node.word=null;
            /**
             * 为什么不能写return！！！apple applepen
             * */
            //return;
        }


        //!这个占位操作很值得学习
        char temp = board[i][j];
        board[i][j] = '$';
        search(board, i-1,j,node,res);
        search(board, i+1,j,node,res);
        search(board, i,j-1,node,res);
        search(board, i,j+1,node,res);
        board[i][j]= temp;
    }

    private void insert(String word){
        TrieNode node = root;
        char[] arr = word.toCharArray();
        for(char c:arr){
            if(node.children[c-'a']==null){
                node.children[c-'a']=new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.word=word;
    }
    class TrieNode{
        TrieNode[] children;
        String word;
        public TrieNode(){
            children = new TrieNode[26];
            word=null;
        }
    }
}