package N011;

/*
*leetcode011
*给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
说明：你不能倾斜容器，且 n 的值至少为 2。
* */

/*
* 简单反证法证明：通过双指针方法，两个指针一定会同时经过最大面积对应的指针位置。


                  |
           |      |
           |      |
     ......|......|......
———————————————————————————————————————
           m      n
如图，若m,n之间的面积为最大面积。

双指针方法的规律是：每次都会向内移动偏矮的指针（可观察案例）。要证明两个指针一定会移动到m和n位置，只需证明：

m左侧的指针点等于或矮于n，n右侧指针等于或矮于m。

假设m左侧有一个点p，高度高于n.


        |
        |          |
        |   |      |
        |   |      |
     ...|...|......|......
———————————————————————————————————————
        p   m      n
因为:

    AreaMN = ( n - m ) * min( arr[ m ], arr[ n ] )
    AreaPN = ( n - p ) * min( arr[ p ], arr[ n ] )
又：

    (  n - m ) <= ( n - p )
    min( arr[ m ], arr[ n ] ) <= min( arr[ p ], arr[ n ] )
所以： AreaMN < AreaPN, 与m和n构成最大面积相矛盾，所以假设不成立，即m左侧的点都不高于n，即等于或矮于n。同理可证，n右侧指针等于或矮于m。所以通过双指针方法，两个指针一定会同时经过最大面积对应的指针位置。
* */
public class leetcode011 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}

class Solution {
    //
    public int maxArea1(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }


    // 时间更短，能不用类的方法就不用类的方法
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int res = 0;
        while (left < right) {
            int width = right - left;
            if (height[left] < height[right]) {
                res = res > height[left] * width ? res : height[left] * width;
                left++;
            } else {
                res = res > height[right] * width ? res : height[right] * width;
                right--;
            }
        }
        return res;

    }
}