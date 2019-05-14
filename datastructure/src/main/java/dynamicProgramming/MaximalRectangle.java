package dynamicProgramming;

import java.util.Arrays;

/**
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing only 1's and return its area.
 *
 * Example:
 * Input:
 * [
 *   ["1","0","1","0","0"],
 *   ["1","0","1","1","1"],
 *   ["1","1","1","1","1"],
 *   ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if(matrix == null) return 0;
        int m = matrix.length;
        if(m <= 0) return 0;
        int n = matrix[0].length;
        if(n <= 0) return 0;
        int[] height = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, Integer.MAX_VALUE);
        int max = 0;
        for(int i = 0; i < m; i++){
            int cur_left = 0, cur_right = n - 1;
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1'){
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }

            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1'){
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = -1;
                    cur_left = j + 1;
                }
            }

            for(int j = n - 1; j >= 0; j--){
                if(matrix[i][j] == '1'){
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = Integer.MAX_VALUE;
                    cur_right = j - 1;
                }
            }

            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '1'){
                    max = Math.max(max, height[j] * (right[j]- left[j] + 1));
                }
            }
//            System.out.println("---height---");
//            Arrays.stream(height).forEach(e ->System.out.print(e+" "));
//            System.out.println();
//
//            System.out.println("---left---");
//            Arrays.stream(left).forEach(e ->System.out.print(e+" "));
//            System.out.println();
//
//            System.out.println("---right---");
//            Arrays.stream(right).forEach(e ->System.out.print(e+" "));
//            System.out.println();
//            System.out.println("max=" + max);
//            System.out.println();
        }
        return max;
    }

    public static void main(String[] args) {
        MaximalRectangle m = new MaximalRectangle();
        System.out.println(m.maximalRectangle(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}
