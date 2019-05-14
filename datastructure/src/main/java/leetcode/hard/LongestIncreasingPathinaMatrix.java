package leetcode.hard;

/**
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down.
 * You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 * Input: nums =
 * [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * Input: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathinaMatrix {

    int max = 0;
    int[][] t;
    int n, m;

    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null) return 0;
        n = matrix.length;
        if(n <= 0) return 0;
        m = matrix[0].length;
        if(m <= 0) return 0;
        t = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int h = helper(matrix, i,j, Integer.MIN_VALUE);
                max = Math.max(h, max);
            }
        }
        return max;
    }

    public int helper(int[][] matrix,  int i, int j, int lastEle){
        if( i < 0 || i >= n || j <0 || j>= m || lastEle >= matrix[i][j] ) {
            return 0;
        } else if(t[i][j] > 0){
            return t[i][j];
        }
        int a1 = helper(matrix,  i + 1, j, matrix[i][j]);
        int a2 = helper(matrix, i - 1, j, matrix[i][j]);
        int a3 = helper(matrix,  i, j +1, matrix[i][j]);
        int a4 = helper(matrix,  i, j -1, matrix[i][j]);
        t[i][j] = Math.max(Math.max(a1, a2), Math.max(a3, a4)) +1;
        return t[i][j];
    }

    public static void main(String[] args) {
        LongestIncreasingPathinaMatrix l = new LongestIncreasingPathinaMatrix();
        System.out.println(l.longestIncreasingPath(new int[][]{
                {3,4,5},
                {3,2,6},
                {2,2,1}
        }));
        System.out.println(l.longestIncreasingPath(new int[][]{
                {9,9,4},
                {6,6,8},
                {2,1,1}
        }));
    }
}
