package leetcode;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target. Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.

 Example:

 Given array nums = [-1, 2, 1, -4], and target = 1.

 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest p = new ThreeSumClosest();
        int[] nums = new int[]{1,1, 1, 0};
        System.out.println(p. threeSumClosest(nums, -100));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
       int minSum = Integer.MIN_VALUE;
       int maxSum = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int low = i + 1;
            int high = nums.length - 1;
            while(low < high){
                int s = nums[i] + nums[low] + nums[high];
                if ( s <= target){
                    minSum = Math.max(s, minSum);
                    low ++;
                }  else {
                    maxSum = Math.min(s, maxSum);
                    high --;
                }
            }
        }

        if(minSum == Integer.MIN_VALUE) return maxSum;
        if(maxSum == Integer.MAX_VALUE) return minSum;
        if(Math.abs(target - minSum) < Math.abs(maxSum - target)){
            return minSum;
        } else {
            return maxSum;
        }

    }


}
