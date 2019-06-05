package leetcode.hard;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 * Note:
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 */
public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        helper(board);
    }

    public boolean helper(char[][] board){
        for(int i = 0; i < 9 ; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    for(int k = 1; k <= 9; k++){
                        if(canPlace(board, i, j, (char)(48+k))){
                            board[i][j] = (char)(48+k);
                            if(helper(board)){
                                return true;
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public boolean canPlace(char[][] board, int i, int j, char x){
        for(int p = 0; p < 9; p++){
            if(board[i][p] == x || board[p][j] == x) return false;
            if(board[3 * (i / 3) + p / 3][3 * (j / 3) + p % 3] == x) return false; //check 3*3 block
        }
        return true;
    }

    public static void main(String[] args) {
        SudokuSolver s = new SudokuSolver();
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.','.' },
                {'6', '.', '.', '1', '9', '5', '.', '.','.' },
                {'.', '9', '8', '.', '.', '.', '.', '6','.' },
                {'8', '.', '.', '.', '6', '.', '.', '.','3' },
                {'4', '.', '.', '8', '.', '3', '.', '.','1' },
                {'7', '.', '.', '.', '2', '.', '.', '.','6' },
                {'.', '6', '.', '.', '.', '.', '2', '8','.' },
                {'.', '.', '.', '4', '1', '9', '.', '.','5' },
                {'.', '.', '.', '.', '8', '.', '.', '7','9' },
        };
//        System.out.println((char)(48+1));
        s.solveSudoku(board);
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-----------------------------------");
        }
    }


}
