package N211;

public class leetcode211 {
    public static void main(String[] args){
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad"); // return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True

    }
}

/**
 * 递归的思路：
 * 如果匹配到'.'，就把26个children都遍历一遍，大问题变成小问题，递归解决，
 * 否则，就进入children继续遍历。
 * 最后还需要判断是否为一个单词
 * 这题也可以用java.util.regex包中的正则表达式做
 * */
class WordDictionary {

    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        char[] arr = word.toCharArray();
        TrieNode node = root;
        for(char c : arr){
            if(node.children[c-'a']==null){
                node.children[c-'a']=new TrieNode();
            }
            node = node.children[c-'a'];
        }
        node.flag=true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word, root);

    }

    private boolean helper(String word, TrieNode root){
        TrieNode node = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(c=='.'){
                for(int j=0;j<26;j++){
                    if(node.children[j]!=null){
                        if(helper(word.substring(i+1),node.children[j])) return true;
                    }
                }
                return false;
            }
            if(node.children[c-'a']==null){
                return false;
            }
            node = node.children[c-'a'];
        }

        return node.flag;
    }

    class TrieNode{
        TrieNode[] children;
        boolean flag;
        public TrieNode(){
            children = new TrieNode[26];
            flag=false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */