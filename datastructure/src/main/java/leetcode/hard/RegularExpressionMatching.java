package leetcode.hard;

/**
 * Given an input string (s) and a pattern (p),
 * implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
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
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 *
 * Example 3:
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 *
 * Example 4:
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 *
 * Example 5:
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        for (int i = 0 ; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '.') {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == s.charAt(i)) {
                    dp[i+1][j+1] = dp[i][j];
                }
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
                        dp[i+1][j+1] = dp[i+1][j-1];
                    } else {
                        dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean isMatch2(String s, String p) {
        int a = 0, b = 0;
        char last = ' ';
        while(a < s.length() && b < p.length()){
            if(s.charAt(a) == p.charAt(b)){
                last = s.charAt(a);
                a++;
                b++;
            } else if(p.charAt(b) == '.'){
                last = '.';
                a++;
                b++;
            } else if(p.charAt(b) == '*'){
                if(s.charAt(a) == last || last == '.'){
                    a ++;
                }else {
                    b++;
                }
            } else if(b+1 < p.length() && p.charAt(b+1) == '*'){
                b += 2;
            } else {
                return false;
            }
//            System.out.println("a="+a+" b=" + b);
        }
        if(a == s.length()){
            if(b == p.length() - 1 && p.charAt(b)!= '*'){
                return false;
            }
            for(int i = b+1; i < p.length();i+=2){
                if(p.charAt(i) != '*') return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.isMatch("aa", "a"));
        System.out.println(r.isMatch("aa", "a*"));
        System.out.println(r.isMatch("ab", ".*"));
        System.out.println(r.isMatch("aab", "c*a*b"));
        System.out.println(r.isMatch("mississippi", "mis*is*p*."));
        System.out.println(r.isMatch("aaa", "aaaa"));
    }
}
