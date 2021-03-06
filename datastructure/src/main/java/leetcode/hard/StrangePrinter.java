package leetcode.hard;

/**
 * There is a strange printer with the following two special requirements:

 The printer can only print a sequence of the same character each time.
 At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.

 Example 1:
 Input: "aaabbb"
 Output: 2
 Explanation: Print "aaa" first and then print "bbb".

 Example 2:
 Input: "aba"
 Output: 2
 Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.

 Hint: Length of the given string will not exceed 100.
 */
public class StrangePrinter {

    public static void main(String[] args) {
        StrangePrinter strangePrinter = new StrangePrinter();
        System.out.println(strangePrinter.strangePrinter("aaba"));
        System.out.println(strangePrinter.strangePrinter("aaabbb"));
    }

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for(int j = 0; j < n; j++){
            for(int i = j - 1; i >= 0; i--){
                dp[i][j] = dp[i][j - 1] + 1;
                for(int k = j - 1; k >= i; k--){
                    if(s.charAt(k) == s.charAt(j)){
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j - 1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public int strangePrinter2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                dp[i][j] = dp[i+1][j] + 1;
                for(int k = i + 1; k <= j; k ++) {
                    if(s.charAt(k) == s.charAt(i))
                        //先用同一个字母从i打印到k，再打印i+1到k-1之间的
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k][j]);
                }
            }
        }
        return n == 0? 0 : dp[0][n-1];
    }

}


