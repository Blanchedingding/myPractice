package leetcode.medium;

/**
 * Given a 2d grid map of '1's (land) and '0's (water),count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally
 * or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */
public class NumberofIslands {


    public int numIslands(char[][] grid) {
        if(grid == null || grid.length <= 0 || grid[0].length <= 0) return 0;
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        boolean[][] p = new boolean[n][m];
        for(int i = 0; i < n; i++){
            for(int j= 0 ;j < m; j++){
                if(grid[i][j] == '1' && !p[i][j]) {
                    count ++;
                    helper(grid, p, n, m, i, j);
                }
            }
        }
        return count;
    }

    public void helper(char[][] grid, boolean[][] p, int n, int m, int i, int j){
        if(i <0 || i >= n || j < 0 || j >= m || p[i][j]) return;
        if(grid[i][j] == '1'){
            p[i][j] = true;
            helper(grid, p, n, m, i + 1, j);
            helper(grid, p, n, m, i - 1, j);
            helper(grid, p, n, m, i,  j + 1);
            helper(grid, p, n, m, i, j - 1);
        }
    }

    public static void main(String[] args) {
        NumberofIslands n = new NumberofIslands();
        char[][] c = new char[][]{
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'},
        };
        System.out.println(n.numIslands(c));
    }
}
