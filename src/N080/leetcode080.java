package N080;

public class leetcode080 {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        int len=nums.length;
        if(len<=2) return len;
        int i=0;
        int flag=1;
        for(int j=1;j<len;j++){
            if(nums[j-1]==nums[j]){
                flag++;
                if(flag==2){
                    i=j+1;
                }
            }else if(i!=0){
                flag=1;
                nums[i]=nums[j];
                nums[j]=-1;
                i=j;
                i++;
            }
        }
        if(i==0)
            return len;
        else
            return i;
    }
}

class Solution1 {

    public int removeDuplicates(int[] nums) {

        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}