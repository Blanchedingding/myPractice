package leetcode.easy;

/**
 * 从N个里面拿X个， 0 < x < N and N % x == 0，Alice先拿，最后不能拿的人输
 * Alice and Bob take turns playing a game, with Alice starting first.
 *
 * Initially, there is a number N on the chalkboard.
 * On each player's turn,that player makes a move consisting of:
 *
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 *
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 *
 * Example 1:
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 *
 * Example 2:
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 *
 *
 * Note:
 * 1 <= N <= 1000
 */
public class DivisorGame {

    //1. if Alice will lose for N, then Alice will must win for N+1, since Alice can first just make N decrease 1.
    //2. for any odd number N, it only has odd factor, so after the first move, it will be an even number
    //let's check the inference
    //fisrt N = 1, Alice lose. then Alice will must win for 2.
    //if N = 3, since all even number(2) smaller than 3 will leads Alice win, so Alice will lose for 3
    //3 lose -> 4 win
    //all even number(2,4) smaller than 5 will leads Alice win, so Alice will lose for 5
    //...
    public boolean divisorGame(int N) {
        return N % 2 == 0;
    }

    public boolean divisorGame2(int N) {
        boolean[] b = new boolean[N + 1];
        if(N < 1) return false;
        if(N == 1) return false;
        if(N ==2) return true;
        b[2] = true;
        for(int i = 3; i <= N; i++){
            boolean g = false;
            for(int j = 1; j < i; j++){
                if(i % j == 0 && !b[i-j]){
                    g = true;
                    break;
                }
            }
            b[i] = g;
        }
        return b[N];
    }

    public static void main(String[] args) {
        DivisorGame d = new DivisorGame();
        System.out.println(d.divisorGame(2));
        System.out.println(d.divisorGame(3));
        System.out.println(d.divisorGame(4));
    }

}
