package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

    List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String s) {
        int n = s.length();
        if(n <= 1){
            List<String> e = new ArrayList<>();
            e.add(s);
            result.add(e);
            return result;
        }
        int[][] res = new int[n][n];
        for(int p = 0; p < n; p++){
            res[p][p] = 1;
            if(p+1 < n && s.charAt(p) == s.charAt(p+1)) res[p][p+1] = 2;
        }
        for(int len = 3; len <= n; len++){
            for(int i = 0; i <= n - len; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j) && res[i + 1][j -1] > 0){
                    res[i][j] = len;
                }
            }
        }
        helper(res, 0 , n, new ArrayList<String>(), s);
        return result;
    }

    private void helper(int[][] res,int leftIndex, int n, List<String> cur, String s){
        if(leftIndex > n - 1) {
            result.add(new ArrayList<>(cur));
            return;
        }
        for(int i = leftIndex; i < n; i++){
            if(res[leftIndex][i] > 0){
                String temp = s.substring(leftIndex, i + 1);
                cur.add(temp);
                helper(res, i+1, n, cur, s);
                //一定要删除最后一项，不要删除temp，可能有相同项
                cur.remove(cur.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning p = new PalindromePartitioning();
        List<List<String>> r = p.partition("aab");
        System.out.println(r.toString());
//        r.stream().forEach(a -> System.out.println(a.toString()));
    }
}
