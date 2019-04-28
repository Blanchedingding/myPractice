package leetcode.hard;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*',
 * which can be treated as one of the numbers from 1 to 9.
 *
 * Given the encoded message containing digits and the character '*',
 * return the total number of ways to decode it.
 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 *
 * Note:
 * The length of the input string will fit in range [1, 105].
 * The input string will only contain the character '*' and digits '0' - '9'.
 */
public class DecodeWays2 {



    public int numDecodings(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        long[] dp = new long[n+1];
        if(c[0] == '0') return 0;
        dp[0] = 1;
        dp[1] = c[0] == '*'? 9:1;
        for(int i = 1; i < n; i++){
            char pre = c[i-1];
            char cur = c[i];
            if(cur == '*'){
                dp[i + 1] += 9 * dp[i];
            } else if(cur > '0'){
                dp[i +1] += dp[i];
            }

            if(pre == '*'){
                if(cur == '*'){
                    dp[i+1] += dp[i-1] * 15;
                } else if(cur <= '6'){
                    dp[i + 1] += dp[i-1] * 2;
                } else {
                    dp[i+1] += dp[i -1];
                }
            } else if(pre == '1' || pre == '2') {
                if(cur == '*'){
                    if(pre == '1'){
                        dp[i + 1] += dp[i-1] * 9;
                    } else {
                        dp[i + 1] += dp[i-1] * 6;
                    }
                } else {
                    int t = (pre-'0')* 10 + (cur - '0');
                    if(t <= 26){
                        dp[i+1] += dp[i - 1];
                    }
                }
            }
            dp[i+1] %= 1000000007;
        }
         return (int)dp[n];
    }

    public static void main(String[] args) {
        DecodeWays2 d= new DecodeWays2();
        System.out.println(d.numDecodings("**********1111111111"));
    }
}
