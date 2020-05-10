package ComputerPrinciple.CacheReplacementPolicies;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FIFOCache {
    /**
     * FIFOCache通过一个队列来存储缓存，当新加入的缓存超过队列的capacity，则删除队头缓存，加入队尾；
     * 利用HashMap的性质使得操作都是O(1)时间完成
     * 1. get: 查询队列中的缓存；
     * 2. put: 向队列中存入缓存；
     *
     * */

    int capacity;
    int size;
    HashMap<Integer,Node> map;
    DoubleList cache;

    FIFOCache(Node node, int capacity){
        this.capacity=capacity;
        this.size=1;
        cache = new DoubleList(node);
        map = new HashMap<>();
        map.put(node.key,node);
    }
    public int get(int key){
        if(map.containsKey(key)){
            return map.get(key).value;
        }
        return -1;
    }

    public void put(Node node){
        if(size==capacity){
            Node removenode = cache.removeFirst();
            map.remove(removenode.key);
            size--;
        }
        cache.addLast(node);
        map.put(node.key,node);
        size++;
    }
    public void print(){
        cache.print();
        /**
         * 对HashMap的遍历
         * */
        //1. for each 遍历map的entrySet()，也可以遍历map的keySet()和values()
        //或者map的forEach()一行解决：map.forEach((k,v)->System.out.println("Key=" + k + "Value=" + v));
        for(Map.Entry<Integer,Node> entry : map.entrySet()){
            System.out.println("Key="+entry.getKey()+"->"+"Value="+entry.getValue()+";");
        }

        //2. iterator
        // 注意：iterator在map.entrySet()下
//        Iterator<Map.Entry<Integer, Node>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<Integer, Node> entry = it.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
    }
}
