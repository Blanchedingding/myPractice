package leetcode.medium;

import java.util.Arrays;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example:
 *
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int temp = 1;
        int columnLeft = 0, columnRight = n - 1, rowTop = 0, rowBottom = n -1;
        int row = 0, column = 0;
        while(true){
            //right
            while(column >= columnLeft && column <= columnRight){
                result[row][column] = temp;
                column ++;
                temp ++;
            }
            if(temp > n * n) break;
            rowTop ++;
            row ++;
            column --;
            //down
            while(row >= rowTop && row <= rowBottom){
                result[row][column] = temp;
                row ++;
                temp ++;
            }
            if(temp > n * n) break;
            columnRight --;
            column --;
            row --;
            //left
            while(column >= columnLeft && column <= columnRight){
                result[row][column] = temp;
                column --;
                temp ++;
            }
            if(temp > n * n) break;
            rowBottom --;
            row --;
            column ++;
            //up
            while(row >= rowTop && row <= rowBottom){
                result[row][column] = temp;
                row --;
                temp ++;
            }
            if(temp > n * n) break;
            columnLeft ++;
            column ++;
            row ++;
        }
        return result;
    }

    public void print(int[][] m){
        for(int i = 0; i < m.length; i ++){
            for(int j = 0; j < m[0].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SpiralMatrix2 sm = new SpiralMatrix2();
        int[][] m = sm.generateMatrix(3);
        sm.print(m);
    }
}
