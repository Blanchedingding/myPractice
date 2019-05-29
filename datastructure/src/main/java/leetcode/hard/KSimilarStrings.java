package leetcode.hard;

/**
 * Strings A and B are K-similar (for some non-negative integer K)
 * if we can swap the positions of two letters in A exactly K times
 * so that the resulting string equals B.
 *
 * Given two anagrams A and B, return the smallest K for which A and B are K-similar.
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: 1
 *
 * Example 2:
 * Input: A = "abc", B = "bca"
 * Output: 2
 *
 * Example 3:
 * Input: A = "abac", B = "baca"
 * Output: 2
 *
 * Example 4:
 * Input: A = "aabc", B = "abca"
 * Output: 2
 *
 * Note:
 * 1 <= A.length == B.length <= 20
 * A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class KSimilarStrings {

    public int kSimilarity(String A, String B) {
        int m = A.length(), n = B.length();
        char[] a = A.toCharArray(), b = B.toCharArray();

    }

    public static void main(String[] args) {
        KSimilarStrings k = new KSimilarStrings();
        System.out.println(k.kSimilarity("aabc", "abca"));
    }
}
