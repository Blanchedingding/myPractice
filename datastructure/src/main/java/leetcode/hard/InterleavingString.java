package leetcode.hard;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if(n3 != (n1 + n2)) return false;
        if( (n1 == n3 && s1.equals(s3)) || (n2 == n3 && s2.equals(s3))) return true;
        boolean[][] dp = new boolean[n1 + 1][n2 + 1];
        dp[0][0] = true;
        for(int i = 0 ;i < n1; i++){
            dp[i+1][0] = dp[i][0] && s1.charAt(i)== s3.charAt(i);
        }
        for(int i = 0 ;i < n2; i++){
            dp[0][i+1] = dp[0][i] && s2.charAt(i) == s3.charAt(i);
        }
        for(int i = 0; i < n1; i++){
            for(int j = 0; j < n2; j++){
                boolean flag = false;
                if(s3.charAt(i + j + 1) == s1.charAt(i)){
                    flag = dp[i][j + 1];
                }
                if( flag == false && s3.charAt(i + j + 1) == s2.charAt(j)){
                    flag = dp[i + 1][j];
                }
                dp[i + 1][j + 1] = flag;
            }
        }

        return dp[n1][n2];
    }

    public static void main(String[] args) {
        InterleavingString i = new InterleavingString();
        System.out.println(i.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(i.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }
}
