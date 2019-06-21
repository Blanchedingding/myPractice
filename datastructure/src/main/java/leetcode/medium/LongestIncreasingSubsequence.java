package leetcode.medium;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 *
 * Note:
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n^2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) {
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    public int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        if(n <= 0) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        for(int i = 1; i < n; i++){
            int k = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    k = Math.max(dp[j] + 1, k);
                }
            }
            dp[i] = k;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        System.out.println(l.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
