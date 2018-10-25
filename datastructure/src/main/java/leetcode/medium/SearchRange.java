package leetcode.medium;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order,
 * find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRange {

    public int[] searchRange(int[] nums, int target) {
        return helper(nums, target, 0, nums.length-1);
    }

    public int[] helper(int[] nums, int target, int left, int right){
        if(left > right) return  new int[]{-1, -1};
        if(left == right){
            if(nums[left] == target) return new int[]{left, right};
            else return new int[]{-1, -1};
        }
        int mid = (left + right) / 2;
        if(nums[mid] > target) {
            return helper(nums, target, left, mid);
        } else if(nums[mid] < target){
            return helper(nums, target, mid + 1, right);
        } else {
            int t1 = mid, t2 = mid;
            //要验证t1t2的界线
            while(t1 >= 0 && nums[t1] == target) t1--;
            while(t2 < nums.length && nums[t2] == target) t2 ++;
            return new int[]{t1 + 1, t2 -1};
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1};
        SearchRange s = new SearchRange();
        System.out.println(Arrays.toString(s.searchRange(nums, 1)));
    }
}
