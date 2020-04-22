package SortMy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 2020.03.25 Java对数组的升序降序
 * */
public class mySort {
    public static void main(String[] args){
        /**
         * 1. 数组升序
         * */
        int[] nums = new int[] {2,1,4,6,3};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        /**
         * 2. 数组降序1
         * */
        Integer[] nums2 = new Integer[] {2,1,4,6,3};
        Arrays.sort(nums2, Collections.reverseOrder());
        System.out.println(Arrays.toString(nums2));
        /**
         * 3. 数组降序2
         * */
        Integer[] nums3 = new Integer[] {2,1,4,6,3};
        Comparator cmp = new MyComparator();
        Arrays.sort(nums3,cmp);
        System.out.println(Arrays.toString(nums3));
    }

}
class MyComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1,Integer o2){
        return o2-o1;
    }
}