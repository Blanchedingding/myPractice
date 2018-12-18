package leetcode.medium;

/**
 * You are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays
 * where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class NumSubarrayProductLessThanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(nums.length < 0) return 0;
        int begin = 0, count = 0,end = 0, mul = 1;
        for(;end < nums.length; end ++){
            mul *= nums[end];
            while(begin <= end && mul >= k){
                mul /= nums[begin];
                begin ++;
            }
            count += end - begin + 1;
        }
        return count;
    }


    public static void main(String[] args) {
        NumSubarrayProductLessThanK n = new NumSubarrayProductLessThanK();
        int[] nums = new int[]{10, 5, 2, 6};
        System.out.println(n.numSubarrayProductLessThanK(nums,100));
    }
}
