package Alibaba0420;

import java.util.*;

public class ali {
    public static void main(String[] args) {
        // 红包
//        Solution s = new Solution();
//        //单位是分
//        System.out.println(s.splitRedPackets(100, 10));

        // 计算器ii
        Solution2 s2 = new Solution2();
        System.out.println(s2.calculate("(-150+2)*3"));

    }
}

class Solution2 {
    private static HashMap<String,Integer> opMap = new HashMap<>();
    static {
        opMap.put("+",1);
        opMap.put("-",1);
        opMap.put("*",2);
        opMap.put("/",2);
        opMap.put("(",0);
        opMap.put(")",0);
    }
    public int calculate(String s) {
        if (s.length()<=1){
            //            return Long.valueOf(s).intValue();
            return Integer.valueOf(s);
        }
        StringBuffer stringBuffer = new StringBuffer(s);
        //处理负数
        for(int i = 0;i<stringBuffer.length();i++) {
            if(( stringBuffer.charAt(i) == '-' || stringBuffer.charAt(i) == '+' ) && (i==0 || stringBuffer.charAt(i-1) == '(')){
                stringBuffer.insert(i, "0");
                i--;
            }
        }

        List<String> reversePolish = reversePolish(stringBuffer.toString());
        Stack<Long> stack = new Stack<>();
        for (int i = 0;i<reversePolish.size();i++){
            String n = reversePolish.get(i);
            if (opMap.containsKey(n)){
                if ("+".equals(n)){
                    stack.push(stack.pop()+stack.pop());
                }else if ("-".equals(n)){
                    long b = stack.pop();
                    long a = stack.pop();
                    stack.push(a-b);
                }else if ("*".equals(n)){
                    long b = stack.pop();
                    long a = stack.pop();
                    stack.push(a*b);
                }else if ("/".equals(n)){
                    long b = stack.pop();
                    long a = stack.pop();
                    stack.push(a/b);
                }
            }else {
                stack.push(Long.valueOf(n));
            }

        }

        return stack.pop().intValue();
    }

    private List<String> reversePolish(String s) {
        Stack<Character> opStack = new Stack<>();
        List<String> str = new LinkedList<>();
        boolean bigger = false;
        for (int i = 0; i< s.length();i++){
            Character c = s.charAt(i);
            if (c == ' '){
                continue;
            }
            if (!opMap.containsKey(String.valueOf(c))){
                if (!bigger) {
                    str.add(String.valueOf(c));
                    bigger = true;
                }else {
                    str.set(str.size()-1,str.get(str.size()-1)+String.valueOf(c));
                }
            }else {
                bigger = false;
                if (c == '('){
                    opStack.push(c);
                }else if(c==')'){
                    Character o = opStack.pop();
                    while (o != '('){
                        str.add(String.valueOf(o));
                        o = opStack.pop();
                    }
                }else{
                    if (opStack.isEmpty()){
                        opStack.push(c);
                        continue;
                    }
                    Character o = opStack.peek();
                    while (opMap.get(String.valueOf(o))>=opMap.get(String.valueOf(c))){
                        str.add(String.valueOf(opStack.pop()));
                        if (opStack.isEmpty()){
                            break;
                        }
                        o = opStack.peek();
                    }
                    opStack.push(c);
                }
            }
        }
        while (!opStack.isEmpty()){
            str.add(String.valueOf(opStack.pop()));
        }
        return str;
    }
}



class Solution1 {
    public int calculate(String s) {
        return eval(s, 0, s.length());
    }

    // 计算[start,end)之间的值
    public int eval(String s, int start, int end) {
        int val = 0;
        for(int i = start; i < end; ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                val = val*10 + (int)(c-'0');
            } else if (c == '+' || c == '-') {
                int end2 = findExpEnd(s, i);
                int val2 = eval(s, i+1, end2);
                val = (c == '+') ? val + val2 : val - val2;
                i = end2 - 1;
            } else if (c == '*' || c == '/') {
                int end2 = findNumEnd(s, i);
                int val2 = eval(s, i+1, end2);
                val = (c == '*') ? val * val2 : val / val2;
                i = end2 - 1;
            }
        }
        return val;
    }

    // 找到 包含乘除法的 表达式的结束index，一直搜索到加减号或字符串结束
    public int findExpEnd(String s, int start) {
        for(int i = start+1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                return i;
            }
        }
        return s.length();
    }

    // 找到数字的结束index，可能包含空格，一直搜索到出现加减乘除符号或字符串结束
    public int findNumEnd(String s, int start) {
        for(int i = start+1; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                return i;
            }
        }
        return s.length();
    }
}


/**
 * @author Meng Ji
 *
 */
class Solution {
    /**
     * 1.总金额MAXMONEY单位是分
     * 2.每个都要有钱，最低不能低于1分，最大金额不能超过90%总额
     */
    private int MINMONEY =1;
    private int MAXMONEY;//总额
    private int count;
    /**
     * 这里为了避免某一个占用大量资金，我们需要设定非最后一个的最大金额，我们把他设置为金额平均值的N倍；
     */
    private double TIMES;
    /**
     * 拆分
     * @param money ：总金额（元）
     * @param count ：个数
     * @return
     */
    public List<Double> splitRedPackets(int money,int count){

        this.MAXMONEY = money*100; //元转化为分
        this.count = count;
        this.TIMES = 0.9*count;

        // 合法性校验
        if(!isRight(money*100,count)){
            return null;
        }

        //列表
        List<Double> list =new ArrayList<Double>();

        //每个最大的金额为平均金额的Times倍，单位（分）
        int max =(int)(money*100*TIMES/count);
        max = max>MAXMONEY ? MAXMONEY : max;

        //分配
        int moneycent = money*100;
        for (int i = 0; i < count; i++) {
            int one = randomRedPacket(moneycent,MINMONEY,max,count-i);//单位（分）
            list.add(one/100.0);
            moneycent -=one;
        }
        return list;
    }
    /**
     * 随机分配一个
     * @param money : 总金额（元）
     * @param minS : 最小金额
     * @param maxS ：最大金额(每个的默认Times倍最大值)
     * @param count
     * @return
     */
    private int randomRedPacket(int money, int minS, int maxS, int count) {
        //若是只有一个，直接返回
        if(count==1){
            return money;
        }
        //若是最小金额等于最大金额， 直接返回最小金额
        if(minS ==maxS){
            return minS;
        }
        //最大值和money中选取上界
        int max = maxS>money ? money : maxS;

        //随机一个= 随机一个数* (金额-最小)+最小
        int one =((int)Math.rint(Math.random()*(max-minS)+minS));
        //剩下的金额
        int moneyOther =money-one;
        //校验这种随机方案是否可行，不合法的话，就要重新分配方案
        if(isRight(moneyOther, count-1)){
            return one;
        }else{
            //重新分配
            double avg =moneyOther /(count-1);
            //本次过大，导致下次的过小；如果过大，下次就随机一个小值到本次金额的一个
            if(avg<MINMONEY){
                //递归调用，修改最大金额
                return randomRedPacket(money, minS, one, count);

            }else if(avg>MAXMONEY){
                //递归调用，修改最小金额
                return randomRedPacket(money, one, maxS, count);
            }
        }
        return one;
    }

    /**
     * 合法性校验
     * @param money (单位：分)
     * @param count
     * @return
     */
    private boolean isRight(int money, int count) {
        double avg =money/count;
        //小于最小金额
        if(avg<MINMONEY){
            return false;
            //大于最大金额
        }else if(avg>MAXMONEY){
            return false;
        }
        return true;
    }

}


