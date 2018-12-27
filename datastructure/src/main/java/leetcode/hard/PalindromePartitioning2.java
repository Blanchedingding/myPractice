package leetcode.hard;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example:
 * Input: "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class PalindromePartitioning2 {

    public int minCut(String s) {
        int n = s.length();
        if(n <= 1){
            return 0;
        }
        boolean[][] res = new boolean[n][n];
        int[] cut = new int[n];
        for(int i = 0; i < n; i++){
            int min = i;
            for(int j = 0; j <= i; j++){
                if(s.charAt(i) == s.charAt(j) && (j+ 1 > i -1 || res[j+1][i-1])){
                    res[j][i] = true;
                    min = j == 0 ? 0: Math.min(min, cut[j - 1] + 1);
                }
            }
            cut[i] = min;
        }
        return cut[n-1];
    }



    public static void main(String[] args) {
        PalindromePartitioning2 p = new PalindromePartitioning2();
        System.out.println(p.minCut("apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctziz"));
    }
}
