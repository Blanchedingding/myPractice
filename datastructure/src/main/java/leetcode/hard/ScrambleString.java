package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at",
 * it produces a scrambled string "rgtae".
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Example 1:
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "abcde", s2 = "caebd"
 * Output: false
 */
public class ScrambleString {

    public boolean isScramble(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 != len2) return false;
        if (len1 == 0) return true;
        // pruning
        if (s1.equals(s2)) return true;
        int[] count = new int[256];
        for (int i = 0; i < len1; i++){
            count[s1.charAt(i)]++;
            count[s2.charAt(i)]--;
        }
        for (int i = 0; i < len1; i++){
            if (count[s1.charAt(i)] != 0) return false;
        }
        for (int i = 1; i < len1; i++){
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(len1 - i)) && isScramble(s1.substring(i), s2.substring(0, len1 - i)))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ScrambleString s = new ScrambleString();
        System.out.println(s.isScramble("abcde", "caebd"));
    }
}
