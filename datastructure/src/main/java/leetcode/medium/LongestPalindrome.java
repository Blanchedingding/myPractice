package leetcode.medium;

import java.util.ArrayList;

/**
 * 最长回文子序列
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class LongestPalindrome {

    //动态规划
    public String longestPalindrome2(String s) {
        int n = s.length();
        if(n <= 1) return s;

        int[][] res = new int[n][n];
        for(int p = 0; p < n; p++){
            res[p][p] = 1;
            if(p+1 < n && s.charAt(p) == s.charAt(p+1)) res[p][p+1] = 2;
        }
        for(int len = 3; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && res[i + 1][j -1] > 0){
                    res[i][j] = len;
                }
            }
        }
        int begin = -1, end = -1, max = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(res[i][j] > max){
                    begin = i;
                    end = j;
                    max = res[i][j];
                }
            }
        }
        return s.substring(begin, end +1);
    }

    //Manncher算法 ，o（n）
    public String longestPalindrome(String s) {
       int length = s.length();
       if(length <= 1) return s;
        //Manncher算法 ，o（n）
        String str = preProcess(s);
        int n = str.length(), id = 0, mx = 0;
        int[] p = new int[n];
        for(int i = 1; i < n - 1; i++) {
            p[i] = mx > i ? Math.min(p[2*id-i], mx-i) : 1;
            while(str.charAt(i+p[i]) == str.charAt(i-p[i]))p[i]++;
            if(i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
        }

        //遍历p，寻找最大回文长度
        int maxLen = 0, index = 0;
        for(int i = 1; i < n-1; i++){
            if(p[i] > maxLen) {
                maxLen = p[i];
                index = i;
            }
        }

        return s.substring((index - maxLen)/2, maxLen-1);
    }

    /**
     * 预处理
     */
    public String preProcess(String s) {
        int n = s.length();
        StringBuilder res = new StringBuilder();
        res.append('$');//把$放到字符串头部
        res.append('#');//以#作为原来字符串中每个字符的间隔
        for(int i = 0; i < n; i++) {
            res.append(s.charAt(i));
            res.append('#');
        }
        res.append('^');//以^作为字符串的结尾
        return res.toString();
    }

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        System.out.println(l.longestPalindrome2("ababd"));
    }
}
