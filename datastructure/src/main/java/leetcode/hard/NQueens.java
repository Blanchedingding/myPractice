package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * Example:
 *
 * Input: 4
 * Output: [
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
 * Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */
public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<String> next = new ArrayList<>();
        helper(result, n, 0, next);
        return result;
    }

    private void helper(List<List<String>> result, int n, int row, List<String> next){
        if(row == n) {
            result.add(new ArrayList<>(next));
            return;
        }
        for(int j = 0; j < n; j++){
            boolean conflict = false;
            for(int k = row - 1; k >= 0; k--){
                if(next.get(k).charAt(j) == 'Q' || (j - (row - k) >=0 && next.get(k).charAt(j - (row - k)) == 'Q') ||
                        (j + (row - k) < n && next.get(k).charAt(j + (row - k)) == 'Q')){
                    conflict = true;
                }
            }
            if(!conflict){
                StringBuilder sb = new StringBuilder();
                for(int p = 0; p < n - 1; p++)sb.append('.');
                sb.insert(j, 'Q');
                next.add(sb.toString());
                helper(result, n, row + 1, next);
                next.remove(sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        NQueens n = new NQueens();
        n.solveNQueens(4).stream().forEach(e -> {
            e.stream().forEach(System.out::println);
            System.out.println();
        });
    }
}
