package N004;
import java.util.Arrays;
public class leetcode004 {
    public static void main(String[] args){
        Solution s = new Solution();
        int[] nums1 = {1,3,4,6};
        int[] nums2= {0,2};

        System.out.println(s.findMedianSortedArrays(nums1,nums2));
    }
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*对每个数组设置各设置一个从左向右移动的指针left1和left2。
        * 把找2个有序数组中位数的问题转化为从当前指针开始找第k个数的问题。
        * 那么，可以通过递归查找第k个数。核心思想就是每次判断每个数组，在当前对应指针下从左向右移k/2有没有超过数组长度。
        * 如果没有超过哪个数组长度，就在哪个数组上偏移。
        * 这里要证明的一点是，每次反复偏移某一个数组k/2是否可行？
        * 如果有一个数组长度很小，比如nums1长度为100，nums2长度为10，找中位数第k=55和56的平均数，
        * 可以保证的是，无论nums2中的数大小，如果nums1当前第k/2个数比num2当前第k/2数小，nums1中前k/2个数一定在总共前k个数中，因为k/2+nums2.length<k
        * 这个可以多种方法证明，我们讨论一下，nums1当前第k/2个数要么在前k个数中、要么不在，nums2也是一样，我们只要判断其中一个一定在，做偏移就好。
        * 给定nums1[left1+k/2-1] < nums2[left2+k/2-1]，如果nums2的在前k个中，nums1的一定在；如果nums2的不在前k个中，那么nums2中占有前k个数就少于k/2个，即nums1中占有前k个数大于k/2个数，所以nums1[left2+k/2-1]一定在总共前k个中，证毕。
        * 拓展：如果共有n个数组，nums1,nums2,...,numsn，比较起来也是类似的，分类讨论前k/n个数
        * */
        int sum = nums1.length+nums2.length;
        if (sum%2==0){
            return (findKthNum(nums1,0,nums2,0,sum/2)+findKthNum(nums1,0,nums2,0,sum/2+1))/2.0;
        }else return (findKthNum(nums1, 0,nums2,0,sum/2+1));
    }
    // 找第k个数
    public double findKthNum(int[] nums1, int left1, int[] nums2, int left2, int k) {
        //如果left1>nums1的长度，直接返回在nums2中第k个数
        //如果left2>nums2的长度，直接返回在nums1中第k个数
        //如果k==1，找nums1和nums2中当前指针中最小的一个数
        //否则比较nums1和nums2的两个指针left1和left2，如果找第k个数时，left1+k/2超过nums1的长度而left2+k/2没超过nums2的长度，就在nums2中找k/2个数，反之亦然（不考虑k大于sum/2的情况）
        if(left1>=nums1.length) return nums2[left2+k-1];
        if(left2>=nums2.length) return nums1[left1+k-1];
        if(k==1) return Math.min(nums1[left1],nums2[left2]);
        int mid1 = left1+k/2-1 < nums1.length ? nums1[left1+k/2-1] : Integer.MAX_VALUE;
        int mid2 = left2+k/2-1 < nums2.length ? nums2[left2+k/2-1] : Integer.MAX_VALUE;
        if(mid1<=mid2){
            return findKthNum(nums1, left1+k/2, nums2, left2, k-k/2);
        }
        return findKthNum(nums1, left1, nums2, left2+k/2, k-k/2);
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] merge = new int[l1+l2];
        // 合并nums1和nums2
        if (l1==0 && l2!=0) merge = nums2;
        else if (l2==0 && l1!=0) merge = nums1;
        else if (l1!=0 && l2!=0){
            for (int i=0,j=0;i<l1 || j<l2;){
                if( (j<l2 && i==l1) || (j<l2 && i<l1 && nums1[i]>nums2[j]) ) {
                    merge[i+j]=nums2[j];
                    j++;
                }else if ( (i<l1 && j==l2) || (j<l2 && i<l1 && nums1[i]<nums2[j]) ){
                    merge[i+j]=nums1[i];
                    i++;
                }
            }
        }

        if ((l1+l2)%2==0){
            int i = (l1+l2)/2;
            return (merge[i-1]+merge[i])/2.0;
        }else
            return merge[(l1+l2-1)/2];
        //System.out.println(Arrays.toString(merge));
    }
}