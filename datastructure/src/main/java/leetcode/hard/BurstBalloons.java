package leetcode.hard;

import java.util.Arrays;

/**
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i.
 * After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {

   //https://www.cnblogs.com/njufl/p/LeetCode.html
    public int maxCoins(int[] nums) {
        int n = nums.length;
        if( n <= 0) return 0;
        int[] newNums = new int[n + 2];
        int index = 0;
        newNums[index ++] = 1;
        for(int num:nums){
            newNums[index ++] = num;
        }
        newNums[index] = 1;
        System.out.println(Arrays.toString(newNums));

        int[][] dp = new int[n + 2][n + 2];
        for (int k = 2; k < n + 2; ++k){
            for (int left = 0; left + k < n + 2; ++left){
                int right = left + k;
                for (int i = left + 1; i < right; ++i ){//最后推j
                    dp[left][right] = Math.max(dp[left][right], dp[left][i] + dp[i][right] + newNums[i] * newNums[left] * newNums[right]);
                }
            }
        }
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        BurstBalloons b = new BurstBalloons();
        System.out.println(b.maxCoins(new int[]{3,1,5,8}));
    }
}
