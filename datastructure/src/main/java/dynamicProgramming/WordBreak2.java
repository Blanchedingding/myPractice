package dynamicProgramming;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 *
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreak2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    List<String> DFS(String s, List<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s)){
            return map.get(s);
        }
        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String> sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }


    //超时！！！！
    List<String> result = new ArrayList<>();

    public List<String> wordBreak2(String s, List<String> wordDict) {
        helper(s, wordDict, "");
        return result;
    }

    public void helper(String s, List<String> wordDict, String temp){
        if(s.length() == 0){
            result.add(temp);
            return ;
        }
        for(String t: wordDict){
            if(s.startsWith(t)){
                helper(s.substring(t.length()), wordDict, (temp == ""? "" : temp + " ")+ t);
            }
        }
    }

    public static void main(String[] args) {
        WordBreak2 wb = new WordBreak2();
        System.out.println(wb.wordBreak("catsanddog", Arrays.asList(new String[]{"cat", "cats", "and", "sand", "dog"})));
    }
}
