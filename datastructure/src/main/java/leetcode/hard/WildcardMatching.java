package leetcode.hard;

import java.util.Arrays;

/**
 * Given an input string (s) and a pattern (p),
 * implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 * Example 3:
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 *
 * Example 4:
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 *
 *  Example 5:
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildcardMatching {

    public boolean isMatch(String str, String pattern) {
        int m = str.length(), n = pattern.length();
        boolean[][] dp = new boolean[m +1][n + 1];
        dp[0][0] = true;
        for(int i = 1; i <= n; i++){
            dp[0][i] = dp[0][i - 1] && pattern.charAt(i - 1) == '*';
        }
        System.out.println(Arrays.toString(dp[0]));
        for(int i = 1; i <= m ; i++){
            for(int j = 1; j <= n; j++){
                if(str.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(pattern.charAt(j - 1) == '*'){
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatch2(String str, String pattern) {
        int s = 0, p = 0, match = 0, starIdx = -1;
        while (s < str.length()){
            // advancing both pointers
            if (p < pattern.length()  && (pattern.charAt(p) == '?' || str.charAt(s) == pattern.charAt(p))){
                s++;
                p++;
            }
            // * found, only advancing pattern pointer
            else if (p < pattern.length() && pattern.charAt(p) == '*'){
                starIdx = p;
                match = s;
                p++;
            }
            // last pattern pointer was *, advancing string pointer
            else if (starIdx != -1){
                p = starIdx + 1;
                match++;
                s = match;
            }
            //current pattern pointer is not star, last patter pointer was not *
            //characters do not match
            else return false;
        }

        //check for remaining characters in pattern
        while (p < pattern.length() && pattern.charAt(p) == '*')
            p++;

        return p == pattern.length();
    }



    public static void main(String[] args) {
        WildcardMatching w = new WildcardMatching();
        System.out.println(w.isMatch("aa", "a"));
        System.out.println(w.isMatch("aa", "*"));
        System.out.println(w.isMatch("cb", "?a"));
        System.out.println(w.isMatch("adceb", "*a*b"));
        System.out.println(w.isMatch("acdcb", "a*c?b"));
    }
}
