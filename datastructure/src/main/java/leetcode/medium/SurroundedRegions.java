package leetcode.medium;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * Example:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 *
 * After running your function, the board should be:
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 *
 * Explanation:
 * Surrounded regions shouldn’t be on the border,
 * which means that any 'O' on the border of the board are not flipped to 'X'.
 * Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'.
 * Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        if(board == null || board.length <= 0) return;
        int n = board.length, m = board[0].length;
        boolean[][] b = new boolean[n][m];
        for(int i = 1; i < m - 1; i++){
            if(board[0][i] == 'O'){
                helper(board, 0, i, b, n, m);
            }
            if(board[n - 1][i] == 'O'){
                helper(board, n - 1, i, b, n, m);
            }
        }

        for(int i = 1; i < n - 1; i++){
            if(board[i][0] == 'O'){
                helper(board, i, 0, b, n, m);
            }
            if(board[i][m - 1] == 'O'){
                helper(board, i, m-1, b, n, m);
            }
        }

        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < m-1; j++){
                if(board[i][j] == 'O' && !b[i][j]){
                    board[i][j] = 'X';
                }
            }
        }

    }

    public void helper(char[][] board, int i, int j, boolean[][] b, int n, int m){
        if(board[i][j] == 'O' && b[i][j] == false){
            b[i][j] = true;
            if(i>1)
                helper(board,i-1,j,b,n,m);
            if(j>1)
                helper(board,i,j - 1,b,n,m);
            if(i+1<n)
                helper(board,i+1,j,b,n,m);
            if(j+1<m)
                helper(board,i,j+1,b,n,m);
        }
    }

    public static void main(String[] args) {
        SurroundedRegions s = new SurroundedRegions();
//        char[][] c = new char[][]{
//                {'X','X','X', 'X'},
//                {'X','O','O', 'X'},
//                {'X','X','O', 'X'},
//                {'X','O','X', 'X'}
//        };
        char[][] c = new char[][]{
                {'O','X','X','O','X'},
                {'X','O','O','X','O'},
                {'X','O','X','O','X'},
                {'O','X','O','O','O'},
                {'X','X','O','X','O'},
        };
        s.solve(c);
        for(int i = 0; i < c.length; i++){
            System.out.println(c[i]);;
        }
    }
}
