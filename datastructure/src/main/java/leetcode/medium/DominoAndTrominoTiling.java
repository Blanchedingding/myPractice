package leetcode.medium;

/**
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.
 *
 * XX  <- domino
 *
 * XX  <- "L" tromino
 * X
 * Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.
 *
 * (In a tiling, every square must be covered by a tile.
 * Two tilings are different if and only if there are two 4-directionally adjacent cells on the board
 * such that exactly one of the tilings has both squares occupied by a tile.)
 *
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * The five different ways are listed below, different letters indicates different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 *
 * Note:
 * N  will be in range [1, 1000].
 */
public class DominoAndTrominoTiling {

    public int numTilings(int N) {
        long mod=1000000007;
        if(N == 0) return 0;
        else if(N == 1) return 1;
        else if(N == 2) return 2;
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= N; i++){
            dp[i] = 2 * dp[i - 1] + dp[i - 3];
            dp[i] %= mod;
        }
        return (int)dp[N];
    }

    public static void main(String[] args) {
        DominoAndTrominoTiling d = new DominoAndTrominoTiling();
        System.out.println(d.numTilings(30));
    }
}
