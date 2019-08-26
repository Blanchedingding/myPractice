package leetcode.hard;

import java.util.Arrays;

/**
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 *
 * Example 2:
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {
    //从最后开始一个一个看是不是回文序列，不是就舍弃最后一个字符
    public String shortestPalindrome(String s) {
        int i = 0, end = s.length() - 1, j = end;
        char[] arr = s.toCharArray();
        while (i < j) {
            if (arr[i] == arr[j]) {
                ++i; --j;
            } else {
                i = 0; --end; j = end;
            }
        }
        return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
    }

    /**
     * 我们使用双指针来找出字符串s的最长回文前缀的大概范围，指针i和j分别指向s串的开头和末尾，若 s[i] 和 s[j] 相等，则i自增1，j自减1，否则只有j自减1。这样遍历一遍后，最长回文前缀就在范围 [0, i) 中，但不保证这个本身就是最大回文前缀，我们只能确定后面剩余的部分肯定不属于，此时我们提取出剩下的字符，翻转一下加到最前面，而对范围 [0, i) 内的子串再次递归调用本函数，这样，在子函数最终会组成最短的回文串，从而使得整个的回文串就是最短的
     * https://www.cnblogs.com/grandyang/p/4523624.html
     */
    public String shortestPalindrome2(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        System.out.println("j="+j);
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        System.out.println(suffix);
        return new StringBuffer(suffix).reverse().toString() +
                shortestPalindrome(s.substring(0, j)) + suffix;
    }

    public String shortestPalindrome3(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        if(n <= 1) return s;
        if( n%2 == 0){
            int i = n / 2 ;
            int p = 0;
            while(p < i && chars[i-1-p] == chars[i+p]){
                p++;
            }
            if(p != 0 && p == i) return s;
        }
        int i = n % 2 == 0 ?  n/2 -1 : (n-1)/2;
        for(; i > 0; i--){
            int j = 1;
            while( i-j >= 0 && chars[i-j] == chars[i+j]){
                j ++;
            }
            if(j != 1 && j == i+1) return addString(s, 2 * i + 1);
            int p = 0;
            while(p < i && chars[i-1-p] == chars[i+p]){
                p++;
            }
            if(p != 0 && p == i) return addString(s,2 * i);
        }
        return addString(s, 1);
    }


    private String addString(String s, int i){
        return new StringBuffer(s.substring(i)).reverse().toString() + s;
    }

    public static void main(String[] args) {
        ShortestPalindrome s = new ShortestPalindrome();
//        System.out.println(s.shortestPalindrome("abcd"));
//        System.out.println(s.shortestPalindrome("aacecaaa"));
        System.out.println(s.shortestPalindrome2("bddcba"));

    }
}
