package leetcode.medium;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 *
 * Note: m and n will be at most 100.
 *
 * Example 1:
 * Input:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePaths2 {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if(m <= 0) return 0;
        int n = obstacleGrid[0].length;
        if(n <= 0) return 0;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            //有柱子在左边挡着，后面直接为0
            if(obstacleGrid[i][0] == 1) break;
            else dp[i][0] = 1;
        }
        for(int i = 0; i < n; i++){
            //有柱子在上面挡着，后面直接为0
            if(obstacleGrid[0][i] == 1) break;
            else dp[0][i] = 1;
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                //如果这里是柱子，直接为0
                if(obstacleGrid[i][j] == 1) continue;
                if(obstacleGrid[i-1][j] == 0 && obstacleGrid[i][j-1] == 0){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                } else if(obstacleGrid[i-1][j] == 0){
                    dp[i][j] = dp[i-1][j];
                } else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePaths2 u = new UniquePaths2();
        int[][] o = new int[][]{{1,0}};
        System.out.println(u.uniquePathsWithObstacles(o));
    }
}
