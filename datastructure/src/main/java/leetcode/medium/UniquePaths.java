package leetcode.medium;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 *
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0) return 0;
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i <= n; i++){
            dp[1][i] = 1;
        }
        for(int i = 1; i <= m; i++){
            dp[i][1] = 1;
        }
        for(int i = 2; i <= m; i++){
            for(int j = 2; j <= n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
//        Arrays.stream(dp).forEach(a ->System.out.println(Arrays.toString(a)));
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n) {
        int a = (m - 1) + ( n -1 );//一共要走多少步
        int b = n-1;//其中有几步是向下的；
        //C(a, b)
        BigInteger res = new BigInteger("1");
        for(int i = a; i > a-b; i--){
            res = res.multiply(new BigInteger(i+""));
        }
//        System.out.println(res);
        for(int j = b; j >= 2; j--){
            res = res.divide(new BigInteger(j + ""));
        }
        return res.intValue();
    }

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        System.out.println(u.uniquePaths(7, 3));
    }
}
