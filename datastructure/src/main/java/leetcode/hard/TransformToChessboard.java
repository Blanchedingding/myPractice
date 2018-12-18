package leetcode.hard;

/**
 * An N x N board contains only 0s and 1s.
 * In each move, you can swap any 2 rows with each other, or any 2 columns with each other.
 *
 * What is the minimum number of moves to transform the board into a "chessboard" -
 * a board where no 0s and no 1s are 4-directionally adjacent? If the task is impossible, return -1.
 *
 * Examples:
 * Input: board = [
 * [0,1,1,0],
 * [0,1,1,0],
 * [1,0,0,1],
 * [1,0,0,1]]
 * Output: 2
 * Explanation:
 * One potential sequence of moves is shown below, from left to right:
 *
 * 0110     1010     1010
 * 0110 --> 1010 --> 0101
 * 1001     0101     1010
 * 1001     0101     0101
 *
 * The first move swaps the first and second column.
 * The second move swaps the second and third row.
 *
 *
 * Input: board = [
 * [0, 1],
 * [1, 0]]
 * Output: 0
 * Explanation:
 * Also note that the board with 0 in the top left corner,
 * 01
 * 10
 *
 * is also a valid chessboard.
 *
 * Input: board = [
 * [1, 0],
 * [1, 0]]
 * Output: -1
 * Explanation:
 * No matter what sequence of moves you make, you cannot end with a valid chessboard.
 *
 * Note:
 * board will have the same number of rows and columns, a number in the range [2, 30].
 * board[i][j] will be only 0s or 1s.
 */
public class TransformToChessboard {

    /**
     * 只要计算交换首行和首列的次数即可。
     * https://www.cnblogs.com/grandyang/p/9053705.html
     */
    public int movesToChessboard(int[][] board) {
        int n = board.length, rowSum = 0, colSum = 0, rowDiff = 0, colDiff = 0;
        //每个矩形四个顶点：
        // 1：全为0；
        // 2：全为1；
        // 3：两个1，两0
        //所以加起来会是偶数
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if ((board[0][0] + board[i][0] + board[0][j] + board[i][j]) % 2 ==  1) return -1;
            }
        }
        for (int i = 0; i < n; ++i) {
            rowSum += board[0][i];
            colSum += board[i][0];
            rowDiff += (board[i][0] == i % 2 ? 1 : 0);
            colDiff += (board[0][i] == i % 2 ? 1 : 0);
        }
        if (n / 2 > rowSum || rowSum > (n + 1) / 2) return -1;
        if (n / 2 > colSum || colSum > (n + 1) / 2) return -1;
        if (n % 2 == 1) {
            if (rowDiff % 2 == 1) rowDiff = n - rowDiff;
            if (colDiff % 2 == 1) colDiff = n - colDiff;
        } else {
            rowDiff = Math.min(n - rowDiff, rowDiff);
            colDiff = Math.min(n - colDiff, colDiff);
        }
        return (rowDiff + colDiff) / 2;
    }

    public static void main(String[] args) {
        TransformToChessboard t = new TransformToChessboard();
        int[][] b = new int[][]{
                {0,1,1,0},
                {0,1,1,0},
                {1,0,0,1},
                {1,0,0,1}
        };
        System.out.println(t.movesToChessboard(b));
    }
}
