package leetcode.hard;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 *
 * A subsequence of a string is a new string which is formed from the original string
 * by deleting some (can be none) of the characters
 * without disturbing the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 *
 * Example 1:
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 *
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 *
 * Example 2:
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 *
 * Explanation:
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 */
public class DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n+1][m+1];
        for(int i = 0; i < n; i++){
            dp[i][0] = 1;
        }
        for(int i = 0 ;i < n; i++){
            for(int j = 0; j < m; j++){
                if(s.charAt(i) == t.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j] + dp[i][j + 1];
                } else {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
            }
        }
        return dp[n][m];
    }

    public int numDistinct2(String s, String t) {
        return helper(s,t,0,0);
    }

    public int helper(String s, String t, int index1, int index2){
        if(index2 > t.length() - 1){
            return 1;
        }
        int count  = 0;
        for(int i = index1; i < s.length(); i++){
            if(s.charAt(i) == t.charAt(index2)){
                count += helper(s, t, i + 1, index2 +1);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        DistinctSubsequences d = new DistinctSubsequences();
        System.out.println(d.numDistinct("rabbbit", "rabbit"));
        System.out.println(d.numDistinct("babgbag", "bag"));
    }
}
