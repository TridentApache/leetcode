package Futu;

public class Futu {
}


class Solution {
    public int MaxLen(String[] string) {
        return 0;

    }
}

class Solution1 {
    public int binarySearch(int[] a, int goal) {
        int low = 0;
        int high = a.length-1;
        while(low <= high) {
            int mid = low+(high-low)/2;
            if(a[mid] == goal)
                return mid;
            else if(a[mid] > goal)
                high = mid;
            else
                low = mid + 1;
        }
        return -1;
    }
}