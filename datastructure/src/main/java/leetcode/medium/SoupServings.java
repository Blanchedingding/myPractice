package leetcode.medium;

/**
 * There are two types of soup: type A and type B.
 * Initially we have N ml of each type of soup. There are four kinds of operations:
 *
 * Serve 100 ml of soup A and 0 ml of soup B
 * Serve 75 ml of soup A and 25 ml of soup B
 * Serve 50 ml of soup A and 50 ml of soup B
 * Serve 25 ml of soup A and 75 ml of soup B
 * When we serve some soup, we give it to someone and we no longer have it.
 * Each turn, we will choose from the four operations with equal probability 0.25.
 * If the remaining volume of soup is not enough to complete the operation,
 * we will serve as much as we can.
 * We stop once we no longer have some quantity of both types of soup.
 *
 * Note that we do not have the operation where all 100 ml's of soup B are used first.
 *
 * Return the probability that soup A will be empty first,
 * plus half the probability that A and B become empty at the same time.
 *
 * Example:
 * Input: N = 50
 * Output: 0.625
 * Explanation:
 * If we choose the first two operations, A will become empty first.
 * For the third operation, A and B will become empty at the same time.
 * For the fourth operation, B will become empty first. So the total probability of A becoming empty first
 * plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
 *
 * Notes:
 * 0 <= N <= 10^9.
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class SoupServings {

    public double soupServings(int N) {
        //由于当N足够大时，最后的概率会逼近1，所以当N>4000时，我们直接返回1即可（已经满足精度要求了）。
        if(N>4000) return 1.0;
        double[][] mem = new double[200][200];
        return helper((N+24)/25, (N+24)/25, mem);
    }

    public double helper(int a, int b, double[][] mem){
        if(a <= 0 && b <= 0) return 0.5;
        if(a <= 0) return 1;
        if(b <= 0) return 0;
        if(mem[a][b] >0) return mem[a][b];
        mem[a][b] = 0.25*(helper(a-4,b, mem) + helper(a-3,b-1, mem) +
                helper(a-2,b-2, mem) + helper(a-1,b-3, mem));
        return mem[a][b];
    }

    public static void main(String[] args) {
        SoupServings s = new SoupServings();
        System.out.println(s.soupServings(50));
    }
}
