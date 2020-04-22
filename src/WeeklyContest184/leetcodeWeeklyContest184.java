package WeeklyContest184;

import java.util.*;

public class leetcodeWeeklyContest184 {
    public static void main(String[] args){
//        Solution1 s1 = new Solution1();
//        String[] words = new String[]{"xu","xxu","xdea","exxusn","cs","bkcs"};
//
//        List<String> res = s1.stringMatching(words);
//        for(int i=0;i<res.size();i++){
//            System.out.println(res.get(i));
//        }
//


//        Solution2 s2 = new Solution2();
//        int[] queries = new int[]{7,5,5,8,3};
//        int m=8;
//        System.out.println(Arrays.toString(s2.processQueries(queries,m)));

        Solution3 s3 = new Solution3();
        String s = "leetcode.com&frasl;problemset&frasl;all";
        System.out.println(s3.entityParser(s));

    }
}

class Solution1 {
    public List<String> stringMatching(String[] words) {
        int len = words.length;
        List<String> res = new ArrayList<String>();
        Arrays.sort(words,new MyStringLength());
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if(words[i].indexOf(words[j])!=-1){
                    if(res.size()==0 || !res.contains(words[j]))
                        res.add(words[j]);
                }
            }
        }
        return res;
    }
}

class MyStringLength implements Comparator {
    public int compare(Object obj1, Object obj2) {
        String str1 = (String) obj1;
        String str2 = (String) obj2;
        int num = -str1.length() + str2.length();
        if (num == 0) {
            return str1.compareTo(str2);
        }
        return num;
    }
}



class Solution2 {

    public int[] processQueries(int[] queries, int m) {
        int[] P = new int[m];
        int[] res = new int[queries.length];
          for(int i=0;i<m;i++){
              P[i]=i;
          }

          for(int i=0;i<queries.length;i++){
              int key = queries[i]; //3
              int pos = P[key-1]; //3的位置是2
              res[i]=pos;
              for(int j=0;j<P.length;j++){
                  if(P[j]<pos){
                    P[j]=P[j]+1;
                  }
              }
              P[key-1] = 0; //3的位置为初始位
          }

          return res;
    }
}
/**
 * 「HTML 实体解析器」 是一种特殊的解析器，它将 HTML 代码作为输入，并用字符本身替换掉所有这些特殊的字符实体。
 *
 * HTML 里这些特殊字符和它们对应的字符实体包括：
 *
 * 双引号：字符实体为 &quot; ，对应的字符是 " 。
 * 单引号：字符实体为 &apos; ，对应的字符是 ' 。
 * 与符号：字符实体为 &amp; ，对应对的字符是 & 。
 * 大于号：字符实体为 &gt; ，对应的字符是 > 。
 * 小于号：字符实体为 &lt; ，对应的字符是 < 。
 * 斜线号：字符实体为 &frasl; ，对应的字符是 / 。
 * 给你输入字符串 text ，请你实现一个 HTML 实体解析器，返回解析器解析后的结果。
 * */
class Solution3 {
    public String entityParser(String t) {
        Map<String, String> map = new HashMap<>();
        map.put("&quot;", "\"");//6
        map.put("&apos;", "\'");//6
        map.put("&amp;", "&");//4
        map.put("&gt;", ">");//4
        map.put("&lt;", "<");//4
        map.put("&frasl;", "/");//7

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(idx < t.length()){
            char temp = t.charAt(idx);
            if(temp != '&'){
                sb.append(temp);
            }else{
                if(idx + 4 <= t.length() && map.containsKey(t.substring(idx, idx + 4))){
                    sb.append(map.get(t.substring(idx, idx + 4)));
                    idx += 3;
                }
                else if(idx + 5 <= t.length() && map.containsKey(t.substring(idx, idx + 5))){
                    sb.append(map.get(t.substring(idx, idx + 5)));
                    idx += 4;
                }
                else if(idx + 6 <= t.length() && map.containsKey(t.substring(idx, idx + 6))){
                    sb.append(map.get(t.substring(idx, idx + 6)));
                    idx += 5;
                }else if(idx + 7 <= t.length() && map.containsKey(t.substring(idx, idx + 7))){
                    sb.append(map.get(t.substring(idx, idx + 7)));
                    idx += 6;
                }else{
                    sb.append("&");
                }
            }
            idx++;
        }
        return sb.toString();
    }
}