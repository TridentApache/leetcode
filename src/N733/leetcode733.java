package N733;

import java.util.Arrays;

public class leetcode733 {
    public static void main(String[] args){
        Solution s = new Solution();
        s.floodFill(new int[][]{{1,1,1},{1,1,0},{1,0,1}},1,1,2);
    }
}

/**
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * 最后返回经过上色渲染后的图像。
 *
 * 注意：
 * 1. Boolean数组创建后不会初始化，而boolean数组创建后会初始化
 * 2. dfs不要忘记visited矩阵
 * */
class Solution {
    private int color;
    private boolean[][] visited;
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        color = image[sr][sc];
        visited = new boolean[image.length][image[0].length];
//        for(int i=0;i<image.length;i++){
//            Arrays.fill(visited,false);
//        }
        if(image.length==0) return image;
        dfs(image,  sr,  sc,  newColor);
        return image;

    }
    private void dfs(int[][] image, int sr, int sc, int newColor){
        if(sr<0 || sr>=image.length || sc<0 || sc>=image[0].length ||image[sr][sc]!=color || visited[sr][sc]==true)
            return;
        image[sr][sc]=newColor;
        visited[sr][sc]=true;
        dfs(image,sr-1,sc,newColor);
        dfs(image,sr+1,sc,newColor);
        dfs(image,sr,sc-1,newColor);
        dfs(image,sr,sc+1,newColor);
    }
}