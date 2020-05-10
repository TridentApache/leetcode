package ComputerPrinciple.CacheReplacementPolicies;

public class test {
    public static void main(String[] args){
        FIFOCache cache = new FIFOCache(new Node(0,0),3);//初始Node(0,0),capacity=3
        cache.put(new Node(1,1));
        cache.print();

        System.out.println(cache.get(1));

        cache.put(new Node(2,2));
        cache.print();

        cache.put(new Node(3,3));
        cache.print();

        cache.put(new Node(4,4));
        cache.print();

        System.out.println(cache.get(1));
        /**
        DoubleList dl = new DoubleList(new Node(0,0));
        dl.print();

        dl.addLast(new Node(1,1));
        dl.print();

        Node n2 = new Node(2,2);
        dl.print();

        dl.addLast(n2);
        dl.print();

        dl.addLast(new Node(3,3));
        dl.print();

        dl.removeFirst();
        dl.print();

        dl.remove(n2);
        dl.print();

        dl.addLast(new Node(2,2));
        dl.print();

        dl.removeLast();
        dl.print();

        dl.addLast(new Node(2,2));
        dl.print();

        dl.addFirst(new Node(4,4));
        dl.print();

        dl.removeLast();
        dl.print();

        dl.removeLast();
        dl.print();

        dl.removeLast();
        dl.print();

        dl.removeLast();
        dl.print();

        dl.removeLast();
        dl.print();
         */
    }
}
