package leetcode.easy;

import java.util.Stack;

/**
 * On a N * N grid, we place some 1 * 1 * 1 cubes.
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
 * Return the total surface area of the resulting shapes.
 *
 * Example 1:
 * Input: [[2]]
 * Output: 10
 *
 * Example 2:
 * Input: [[1,2],[3,4]]
 * Output: 34
 *
 * Example 3:
 * Input: [[1,0],[0,2]]
 * Output: 16
 *
 * Example 4:
 * Input: [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 32
 *
 * Example 5:
 * Input: [[2,2,2],[2,1,2],[2,2,2]]
 * Output: 46
 *
 * Note:
 * 1 <= N <= 50
 * 0 <= grid[i][j] <= 50
 */
public class SurfaceAreaof3DShapes {

    public int surfaceArea(int[][] grid) {
        int num = 0;
        int overlap = 0;
        for(int i = 0; i < grid.length; i++){
            int[] a = grid[i];
            for(int j = 0; j < a.length; j++){
                num += grid[i][j];
                //只算下面左边与内部重叠
                if(grid[i][j] > 1){
                    overlap += grid[i][j] - 1;
                }
                if(j - 1 >= 0) {
                    overlap += Math.min(grid[i][j], grid[i][j - 1]);
                }
                if(i - 1 >= 0 && grid[i - 1].length > j) {
                    overlap += Math.min(grid[i][j], grid[i - 1][j]);
                }
            }
        }
        return 6 * num - 2 * overlap;
    }

    public static void main(String[] args) {
        SurfaceAreaof3DShapes s = new SurfaceAreaof3DShapes();
        System.out.println(s.surfaceArea(new int[][]{{1,1,1},{1,0,1},{1,1,1}}));
        System.out.println(s.surfaceArea(new int[][]{{2,2,2},{2,1,2},{2,2,2}}));
        System.out.println(s.surfaceArea(new int[][]{{1,2},{3,4}}));
    }
}
