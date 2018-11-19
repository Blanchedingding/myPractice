package leetcode.medium;

import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers nums and a positive integer k,
 * find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
 *
 * Example 1:
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 * Note:
 *
 * 1 <= k <= len(nums) <= 16.
 * 0 < nums[i] < 10000.
 */
public class PartitionToKEqualSumSubsets {

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int n: nums){
            sum += n;
        }
        if(sum % k != 0) return false;
        int part = sum / k;
        boolean[] visited = new boolean[nums.length];
        System.out.println(Arrays.toString(nums));
        return helper(nums, k, 0, visited, 0, part);
    }

    private boolean helper(int[] nums, int time, int index, boolean[] visited, int curSum, int part){
        if(time == 1) return true;
        if(curSum == part) {
            System.out.println(Arrays.toString(visited));
            return helper(nums, time - 1, 0, visited, 0, part);
        }
        for(int i = index; i < nums.length; i++){
            if(visited[i]) continue;
            visited[i] = true;
            if(helper(nums, time, i + 1, visited, curSum + nums[i], part)){
                return true;
            }
            visited[i] = false;
        }
        return false;
    }

    public static void main(String[] args) {
        PartitionToKEqualSumSubsets p = new PartitionToKEqualSumSubsets();
        System.out.println(p.canPartitionKSubsets(new int[]{4,3,2,3,5,2,1},4));
    }
}
