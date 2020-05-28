package N133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * */
public class leetcode133 {
    public static void main(String[] args){
        Solution s = new Solution();
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        Node node4 = new Node(4, new ArrayList<>());
        node1.neighbors.add(node2);node1.neighbors.add(node4);
        node2.neighbors.add(node1);node2.neighbors.add(node3);
        node3.neighbors.add(node2);node3.neighbors.add(node4);
        node4.neighbors.add(node1);node4.neighbors.add(node3);
        s.cloneGraph(node1);
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    HashMap<Node,Node> map=new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node==null) return node;

        if(map.containsKey(node)){
            return map.get(node);
        }

        Node res = new Node(node.val,new ArrayList<>());
        map.put(node,res);

        for(Node n : node.neighbors){
            res.neighbors.add(cloneGraph(n));
        }

        return res;
    }
}