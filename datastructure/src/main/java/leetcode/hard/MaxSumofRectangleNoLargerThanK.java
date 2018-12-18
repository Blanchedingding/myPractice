package leetcode.hard;


import java.util.Set;
import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k,
 * find the max sum of a rectangle in the matrix such that its sum is no larger than k.
 *
 * Example:
 * Input: matrix = [[1,0,1],[0,-2,3]], k = 2
 * Output: 2
 *
 * Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
 *              and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?
 */
public class MaxSumofRectangleNoLargerThanK {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int max = Integer.MIN_VALUE;
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        for(int i = 0; i < colNum; i ++){
            int[] sum = new int[rowNum];
            //看第i至j列
           for(int j = i; j < colNum; j++){
               //累加i至j列每行和，因为每次加一列，所以直接使用i至j-1列的每行和
               for(int p = 0; p < rowNum; p++){
                   sum[p] += matrix[p][j];
               }
               Set<Integer> accSet = new TreeSet<>();
               accSet.add(0);
               int curSum = 0, curMax = Integer.MIN_VALUE;
               //现在有rowNum个数组成的数组，求最大的小于K的subarray
               for(int s: sum){
                   curSum += s;
                   if(((TreeSet<Integer>) accSet).ceiling(curSum - k) != null){
                       int it = ((TreeSet<Integer>) accSet).ceiling(curSum - k);
                       curMax = Math.max(curMax, curSum - it);
                   }
                   accSet.add(curSum);
               }
               max = Math.max(max, curMax);
           }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSumofRectangleNoLargerThanK m = new MaxSumofRectangleNoLargerThanK();
        int[][] matrix = new int[][]{
                {2,2,-1}
        };
//        int[][] matrix = new int[][]{
//                {1,0,1},
//                {0,-2,3}
//        };
        System.out.println(m.maxSumSubmatrix(matrix, 3));
    }
}
