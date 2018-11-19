package leetcode.hard;

import java.util.Arrays;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * Example:
 * Input: 4
 * Output: 2
 * Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 */
public class NQueens2 {

    public int totalNQueens(int n) {
        boolean[][] next = new boolean[n][n];
        return helper(next, 0, 0, n);
    }

    private int helper( boolean[][] next, int sum, int row, int n){
        if(row == n) {
            sum ++;
            return sum;
        } else {
            int total = 0;
            for(int i = 0; i < n; i++){
                boolean conflict = false;
                for(int j = row - 1; j >=0; j--){
                    if(next[j][i] == true || (i-(row - j) >=0 && next[j][i-(row - j)] == true)
                            || (i + (row - j) < n && next[j][i+(row - j)] == true)){
                        conflict = true;
                    }
                }
                if(!conflict){
                    next[row][i] = true;
                    total += helper(next, sum, row + 1, n);
                    next[row][i] = false;
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        NQueens2 n = new NQueens2();
        System.out.println(n.totalNQueens(4));
    }
}
