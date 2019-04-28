package leetcode.medium;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {


    public int numDecodings(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int[] dp = new int[n +1];
        dp[0] = 1;
        for(int i = 0; i < n; i++){
            if(c[i] < '0' || c[i] > '9'){
                return 0;
            }
            if(c[i] != '0'){
                dp[i + 1] += dp[i];
            }
            if(i - 1 >= 0){
                int t = (c[i - 1] - '0')*10 + (c[i] - '0');
                if(t >= 10 && t <= 26){
                    dp[i + 1] += dp[i - 1];
                }
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodings("12"));
    }
}
