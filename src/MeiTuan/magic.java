package MeiTuan;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

//public class magic {
//    public static void main(String[] args){
//    Solution s = new Solution();
//        long n = 4; //房间数
//        long m = 21; //法力值
//        long[] nm = new long[]{4,21};
//        long[] magicValue = new long[]{2,1,4,3};
//        System.out.println(s.magiclog(nm,magicValue));
//    }
//}




class Solution{
    public int magiclog(long[] nm, long[] magicValue){
        int n = (int)nm[0]; //房间数
        long m = nm[1]; //法力值
        long sumAll = 0;
        //快速找到小于当前烙印的房间
        HashMap<Integer,Long> map = new HashMap<>();
        for(int i=0;i<n;i++){
            sumAll+=magicValue[i];
            map.put(i,sumAll);
        }

        if(m==sumAll){
            return n;
        }else if(m<sumAll){
            int begin = (int)getK(m);
            long beginValue = sumAll-map.get(begin);
            long restValue = sumAll-beginValue;
            int j=0;
            while(restValue>0){
                if(restValue>=magicValue[begin % n]){
                    restValue=restValue-magicValue[begin];
                    j++;
                }
                begin++;
            }
            return begin+1+j;
        }else{

        }
        return 0;
    }

    /**
     * 根据给定的键值k来找map中最接近k的键值所对应的value
     * */
    private Object getK(long key){
        TreeMap<Long,Object> map = new TreeMap<Long,Object>();
//        Long key = 42;
        Map.Entry<Long,Object> low = map.floorEntry(key);//lower entry
        Map.Entry<Long,Object> high = map.ceilingEntry(key);//higher entry
        Object res = null;
        if (low != null && high != null) {
            res = Math.abs(key-low.getKey()) < Math.abs(key-high.getKey())
                    ?   low.getValue()
                    :   high.getValue();
        } else if (low != null || high != null) {
            res = low != null ? low.getValue() : high.getValue();
        }
        return res;
    }
}
