package leetcode.easy;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed,
 * the only constraint stopping you from robbing each of them is that adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 *
 * Example 2:
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 *              Total amount you can rob = 2 + 9 + 1 = 12.
 */
public class HouseRobber1 {

    public int rob2(int[] nums) {
        int n = nums.length;
        if(n <= 0) return 0;
        int rob = 0, notRob = 0;
        for(int i: nums){
            int t = notRob;
            notRob = Math.max(rob, notRob);
            rob = t + i;
        }
        return Math.max(rob, notRob);
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if(n <= 0) return 0;
        if(n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for(int i = 2; i < n; i++){
            dp[i] = dp[i - 1];
            for(int j = 0; j < i - 1; j++){
                dp[i] =( dp[j] + nums[i] > dp[i] )? dp[j] + nums[i] : dp[i];
            }
        }
//        System.out.println(Arrays.toString(dp));
        return dp[n - 1] > dp[n-2] ? dp[n - 1]:dp[n-2];
    }

    public static void main(String[] args) {
        HouseRobber1 h = new HouseRobber1();
        int[] nums = new int[]{2, 1, 1, 2};
        System.out.println(h.rob2(nums));
    }
}
