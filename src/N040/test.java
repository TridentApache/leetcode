package N040;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test {
    public static void main(String[] args){
        List<List<Integer>> ls = new ArrayList<>();
        List<Integer> l = new ArrayList<>();
        l.add(2);
//        ls.add( new ArrayList<>(2)); //这句代码是初始化一个initial capacity为2的ArrayList再add到ls中
        ls.add(l);
        Iterator it = ls.iterator(); //也可以用listIterator(), listIterator()比iterator()功能多
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}

