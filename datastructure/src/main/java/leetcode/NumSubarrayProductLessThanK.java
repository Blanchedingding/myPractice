package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 */
public class NumSubarrayProductLessThanK {
    int count;

    public int numSubarrayProductLessThanK(int[] nums, int k) {
       for(int i = 1; i <= nums.length; i++){
            helper(i, nums, k, 1,0 );
       }
    }

    public void helper(int leftNum, int[] nums, int k, int mul, int beginIndex){
       if(leftNum == 0) {
           if(mul < k) count ++;
       } else {
           for(int j = beginIndex; j < nums.length; j++){
               if(mul * nums[j] < k ) count++;
               else break;
           }
           for()
       }
    }

    public static void main(String[] args) {
        NumSubarrayProductLessThanK n = new NumSubarrayProductLessThanK();
        int[] nums = new int[]{10, 5, 2, 6};
        System.out.println(n.numSubarrayProductLessThanK(nums,100));
    }
}
