package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral（螺旋） order.
 *
 * Example 1:
 *
 * Input:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 * Input:
 * [
 *   [1, 2, 3, 4],
 *   [5, 6, 7, 8],
 *   [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return result;
        int n = matrix[0].length;
        if(n == 0) return result;
        int row = 0, column = 0;
        int direction = 0;//0:right; 1:left; 2: down; 3:up
        int[][] visit = new int[m][n];
        while(result.size() < m * n){
            result.add(matrix[row][column]);
            visit[row][column] = 1;
            switch (direction){
                case 0:{
                    if(column + 1 >= n || visit[row][column + 1] == 1){
                        direction = 2;
                        row ++;
                    } else {
                        column ++;
                    }
                    break;
                }
                case 1:{
                    if(column - 1 < 0 || visit[row][column - 1] == 1){
                        direction = 3;
                        row --;
                    } else {
                        column --;
                    }
                    break;
                }
                case 2:{
                    if(row + 1 >= m || visit[row + 1][column] == 1){
                        direction = 1;
                        column --;
                    } else {
                        row ++;
                    }
                    break;
                }
                case 3:{
                    if(row - 1 < 0 || visit[row - 1][column] == 1){
                        direction = 0;
                        column ++;
                    } else {
                        row --;
                    }
                    break;
                }
                default:
                    System.out.println("something wrong.");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SpiralMatrix sm = new SpiralMatrix();
        int[][] m = new int[][]{
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        System.out.println(sm.spiralOrder(m));
    }
}
