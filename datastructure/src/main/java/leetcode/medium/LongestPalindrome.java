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

    public String longestPalindrome(String s) {
       int length = s.length();
       if(length <= 1) return s;
        //Manncher算法 ，o（n）
//        String str = preProcess(s);
        String str = s;
        int n = str.length(), id = 0, mx = 0;
        int[] p = new int[n];
        for(int i = 0; i < n; i++) {
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

//    public ArrayList<Character> preProcess(String s) {
//        int n = s.length();
//        char res;
//        res.push_back('$');//把$放到字符串头部
//        res.push_back('#');//以#作为原来字符串中每个字符的间隔
//        for(int i = 0; i < n; i++) {
//            res.push_back(s[i]);
//            res.push_back('#');
//        }
//        res.push_back('^');//以^作为字符串的结尾
//        return res;
//    }

    public static void main(String[] args) {
        LongestPalindrome l = new LongestPalindrome();
        System.out.println(l.longestPalindrome("ababd"));
    }
}
