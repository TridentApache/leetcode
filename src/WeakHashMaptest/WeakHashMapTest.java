package WeakHashMaptest;

import java.util.WeakHashMap;

/**
 * WeakHashMap演示缓存的例子
 *
 * objectMap.put方法执行的时候i会被封装为Integer类型的，Integer保留了(high-low+1)大小的缓存在cache里，具体看Integer源码：
 * cache = new Integer[(high - low) + 1];
 * 但是对于int来说范围大很多，Integer new的是强引用，不会被回收，超出Integer一开始缓存设定的范围后，剩下的都是弱引用，将会被回收，因此最后的尺寸总是会稳定在128-i 左右
 * */
public class WeakHashMapTest {
    public static void main(String[] args){
        WeakHashMap<Object,Object> objectMap = new WeakHashMap<>();
        for(int i=-10;i<1000;i++){
            objectMap.put(i, new Object());
            System.gc();
            System.out.println("Map size :" + objectMap.size());
        }
    }
}
