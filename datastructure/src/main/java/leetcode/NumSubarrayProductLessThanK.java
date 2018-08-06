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


    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int begin = 0, count = 0,end = 0, mul = 1;
        if(nums.length < 0) return 0;
        while(begin < nums.length){
            System.out.println("begin:" + begin + " end:" + end);
            if(end >= nums.length){
                System.out.println("end:" + end + " end >= nums.length");
                begin ++;
                end = begin;
                mul = 1;
                System.out.println("mpdify:" + "end=begin=" + begin);
                System.out.println("continue...");
                continue;
            }
            mul *= nums[end];
            System.out.println("mul=" + mul);
            if(mul < k){
                count ++;
                end ++;
                System.out.println("mul<k: " + " count=" + count + " end=" + end);
            } else {
                mul /= nums[begin];
                if(mul < k){
                    count += Math.pow(2,end - begin)-1;
                    begin++;
                    end ++;

                    System.out.println("mul/begin < k: count+" + (Math.pow(2,end - begin)-1) + "=" + count);
                    System.out.println("begin:" + begin + " end:" + end);
                } else {
                    mul = 1;
                    begin ++;
                    end = begin;
                    System.out.println("mul>=k: " + " mul=1 begin=end=" + end);
                }

            }
        }
        return count;
    }


    public static void main(String[] args) {
        NumSubarrayProductLessThanK n = new NumSubarrayProductLessThanK();
        int[] nums = new int[]{10, 5, 2, 6};
        System.out.println(n.numSubarrayProductLessThanK(nums,100));
    }
}
