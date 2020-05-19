package N146;

import java.util.LinkedHashMap;
import java.util.Map;

public class leetcode146 {
}





class LRUCache extends LinkedHashMap<Integer,Integer> {//注意继承
    private int capacity;
    public LRUCache(int capacity) {
        /**
         * 1. super要写在第一句话
         * 2. 构造函数直接是super：
         *      1) super(); 访问父类中的无参构造函数
         *      2) super (paras…); 访问父类中的成员函数
         * 3. 非构造函数：
         *      1) super.xxx; 访问父类中的成员变量
         *      2) super.yyy(paras…); 访问父类中的成员函数
         * */
        /**
         * Constructs an empty LinkedHashMap instance with the specified initial capacity, load factor (loadFactor是Map的负载因子) and ordering mode(accessOrder 访问排序).
         * */
        super(capacity,0.75F,true);
        this.capacity=capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key,-1);
    }

    public void put(int key, int value) {
        super.put(key,value);
    }

    /**
     * 注解的好处：
     * 可读性提高
     * 编译器会校验写的方法在父类中是否存在
     * */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest){
        /**
         * 源码中直接返回false，表示不修改LinkedHashMap，让LinkedHashMap看起来像个Map一样，可以一直put而不用考虑删除最老的元素
         */
        return size()>capacity;
    }
}