package leetcode.medium;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed. All houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, adjacent houses have security system connected
 * and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 *              because they are adjacent houses.
 *
 * Example 2:
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 *              Total amount you can rob = 1 + 3 = 4.
 */
public class HouseRobber2 {

    public int rob(int[] nums) {
        int n = nums.length;
        if(n <= 0) return 0;
        if(n == 1) return nums[0];
        return Math.max(helper(nums, 0, n -2), helper(nums, 1, n - 1));
    }

    public int helper(int[] nums, int low, int high) {
        int rob = 0, notRob = 0;
        for(int i = low; i <= high; i++){
            int t = notRob;
            notRob = Math.max(rob, notRob);
            rob = t + nums[i];
        }
        return Math.max(rob, notRob);
    }

    public static void main(String[] args) {
        HouseRobber2 h = new HouseRobber2();
        int[] nums = new int[]{2,3,2};
        System.out.println(h.rob(nums));
    }
}
