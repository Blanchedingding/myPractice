package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int beginIndex = 0;
        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(map.get(c) != null){
                beginIndex = Math.max(beginIndex, map.get(c) + 1);
            }
            map.put(c, i);
            max = Math.max(max, i-beginIndex +1);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
//        System.out.println(l.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(l.lengthOfLongestSubstring("bbbbbbb"));
//        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
        System.out.println(l.lengthOfLongestSubstring("abba"));
    }
}
