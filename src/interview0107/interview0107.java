package interview0107;

import java.util.HashSet;
import java.util.LinkedList;

public class interview0107 {
}

/**
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 * 不占用额外内存空间能否做到？
 * */
/**
 * 矩阵四个角分别为[0，0]，[0,N-1],[N-1,0],[N-1.N-1]
 * */
class Solution {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        // 角矩形长宽
        int height = N / 2;
        int width = (N + 1) / 2;
        // 换角
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[N - 1 - j][i]; // 左下角对应的位置
                matrix[N - 1 - j][i] = matrix[N - 1 - i][N - 1 - j]; // 右下角对应的位置
                matrix[N - 1 - i][N - 1 - j] = matrix[j][N - 1 - i]; // 右上角对应的位置
                matrix[j][N - 1 - i] = temp;
            }
        }
    }
}
