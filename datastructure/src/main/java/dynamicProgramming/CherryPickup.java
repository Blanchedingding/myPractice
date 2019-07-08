package dynamicProgramming;

/**
 * In a N x N grid representing a field of cherries, each cell is one of three possible integers.
 *
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass through;
 * -1 means the cell contains a thorn that blocks your way.
 *
 * Your task is to collect maximum number of cherries possible by following the rules below:
 *
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 *
 * Example 1:
 *
 * Input: grid =
 * [[0, 1, -1],
 *  [1, 0, -1],
 *  [1, 1,  1]]
 * Output: 5
 *
 * Explanation:
 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 *
 * Note:
 * grid is an N by N 2D array, with 1 <= N <= 50.
 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
 *
 * https://blog.csdn.net/luke2834/article/details/79365645
 */
public class CherryPickup {

    public int cherryPickup(int[][] grid) {
        int N = grid.length, M = (N << 1) - 1;
        int[][] dp = new int[N][N];
        dp[0][0] = grid[0][0];//定义dp[i][j]表示从(0, 0)开始的两条长为k的路径上;而这两条长为k的路径的最终点为(i, k - i)和(j, k - j)

        for (int n = 1; n < M; n++) {
            for (int i = N - 1; i >= 0; i--) {
                for (int p = N - 1; p >= 0; p--) {
                    int j = n - i, q = n - p;

                    if (j < 0 || j >= N || q < 0 || q >= N || grid[i][j] < 0 || grid[p][q] < 0) {
                        dp[i][p] = -1;
                        continue;
                    }

                    if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
                    if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
                    if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);

                    if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
                }
            }
        }

        return Math.max(dp[N - 1][N - 1], 0);
    }

    public static void main(String[] args) {
        CherryPickup c= new CherryPickup();
        System.out.println(c.cherryPickup(new int[][]{
                {1,1,1,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,1},
                {1,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,1,1,1,1}
        }));
    }

//    public int cherryPickup(int[][] grid) {
//        int p = helper(grid);
////        for(int[] i:grid){
////            for(int j: i){
////                System.out.print(j + " ");
////            }
////            System.out.println();
////        }
//        int n = grid.length;
//        int[][] grid2 = new int[n][n];
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                grid2[i][j] = grid[n-1-j][n-1-i];
//            }
//        }
////        for(int[] i:grid2){
////            for(int j: i){
////                System.out.print(j + " ");
////            }
////            System.out.println();
////        }
//        p += helper(grid2);
//        return p;
//    }
//
//    public int helper(int[][] grid){
//        int n = grid.length;
//        int[][] dp = new int[n][n];
//        char[][] t = new char[n][n];
//        dp[0][0] = grid[0][0];
//        int i = 1;
//        for(; i < n; i++){
//            if(grid[0][i] == -1) break;
//            dp[0][i] = dp[0][i - 1] + grid[0][i];
//            t[0][i] = '-';
//        }
//        while(i < n){
//            dp[0][i] = -1;
//            t[0][i] = '*';
//            i++;
//        }
//        i = 1;
//        for(; i < n; i++){
//            if(grid[i][0] == -1) break;
//            dp[i][0] = dp[i -1][0] + grid[i][0];
//            t[i][0] = '|';
//        }
//        while(i < n){
//            dp[i][0] = -1;
//            t[i][0] = '*';
//            i++;
//        }
//
//        for (int j = 1; j < n; j++) {
//            for(int k = 1; k < n; k++){
//                if(grid[j][k] == -1){
//                    t[j][k] = '*';
//                    dp[j][k] = -1;
//                } else if(dp[j][k - 1] == -1 &&  dp[j - 1][k] == -1) {
//                    t[j][k] = '*';
//                    dp[j][k] = -1;
//                } else if(dp[j][k - 1] > dp[j - 1][k]){
//                    t[j][k] = '-';
//                    dp[j][k] = dp[j][k-1] + grid[j][k];
//                } else {
//                    t[j][k] = '|';
//                    dp[j][k] = dp[j - 1][k] + grid[j][k];
//                }
//            }
//        }
//
//        if(dp[n-1][n-1] == -1) return 0;
//
//        int j = n-1, k = n -1;
//        while(j >= 0 && k >= 0){
//            grid[j][k] = 0;
//            if(t[j][k] == '|'){
//                j -= 1;
//            } else {
//                k -= 1;
//            }
//        }
//        grid[0][0] = 0;
//        return dp[n-1][n-1];
//    }


}
