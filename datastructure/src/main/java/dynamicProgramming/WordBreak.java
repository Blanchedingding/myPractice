package dynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }


    //超时！！！！
    public boolean wordBreak2(String s, List<String> wordDict) {
        return helper(s, 0, wordDict);
    }

    public boolean helper(String s, int begin, List<String> wordDict){
        if(begin >= s.length()){
            return true;
        }
        for(String k: wordDict){
            if(s.length() >= begin + k.length() && s.substring(begin, begin + k.length()).equals(k)){
                boolean flag = helper(s, begin+k.length(), wordDict);
                if(flag) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        System.out.println(wb.wordBreak("applepenapple", Arrays.asList(new String[]{"apple", "pen"})));
        System.out.println(wb.wordBreak("catsandog", Arrays.asList(new String[]{"cats", "dog", "sand", "and", "cat"})));
    }
}
