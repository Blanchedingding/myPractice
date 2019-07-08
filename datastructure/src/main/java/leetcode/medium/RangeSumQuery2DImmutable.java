package leetcode.medium;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 *
 * Range Sum Query 2D
 * The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3),
 * which contains sum = 8.
 *
 * Example:
 * Given matrix = [
 *   [3, 0, 1, 4, 2],
 *   [5, 6, 3, 2, 1],
 *   [1, 2, 0, 1, 5],
 *   [4, 1, 0, 1, 7],
 *   [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2DImmutable {


    /**
     * Your NumMatrix object will be instantiated and called as such:
     * NumMatrix obj = new NumMatrix(matrix);
     * int param_1 = obj.sumRegion(row1,col1,row2,col2);
     */
    int[][] r;
    public RangeSumQuery2DImmutable(int[][] matrix) {
        if(matrix == null || matrix.length <= 0 ) return;
        int n = matrix.length;
        int m = matrix[0].length;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int p = i > 0? matrix[i - 1][j]: 0;
                int q = j > 0? matrix[i][j - 1]: 0;
                int k = (i > 0 && j > 0)? matrix[i - 1][j - 1] : 0;
                matrix[i][j] = matrix[i][j] + p + q - k;
            }
        }
        this.r = matrix;
//        for(int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(r[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int p = col1 >0 ? r[row2][col1 - 1]: 0;
        int q = row1 > 0? r[row1 - 1][col2]: 0;
        int k = (col1 > 0 && row1>0)? r[row1 - 1][col1 - 1]: 0;
//        System.out.println("p=" + p + " q=" + q);
        return r[row2][col2] - p - q + k;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        RangeSumQuery2DImmutable r = new RangeSumQuery2DImmutable(matrix);
        System.out.println(r.sumRegion(2,1,4,3));
        System.out.println(r.sumRegion(1,1,2,2));
        System.out.println(r.sumRegion(1,2,2,4));
    }
}
