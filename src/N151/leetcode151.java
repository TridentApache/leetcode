package N151;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class leetcode151 {
    public static void main(String[] args){
        Solution1 s = new Solution1();
        System.out.println(s.reverseWords("  hello world!  "));
    }
}
class Solution {
    String[] ss;
    public String reverseWords(String s) {
        if(s.equals("")||s.equals(" ")) return "";
        ss = s.split(" ");
        int len = ss.length;
        if(len==0) return "";
        String res="";
        for(int i=len-1;i>=0;i--){
            if(ss[i].equals("")||ss[i].equals(" ")){
                continue;
            }
            res=res+" "+ss[i];//这里会造成第一个有个空格
        }
        return res.substring(1,res.length());
    }
}
/**
 * 官方基于API的解答
 * */
class Solution1 {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+")); //\\s表示空格,回车,换行等空白符, +号表示一个或多个的意思
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
