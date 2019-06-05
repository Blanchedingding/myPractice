package leetcode.medium;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class SearchinRotatedSortedArray {

    public int search(int[] nums, int target) {
        int n = nums.length;
        if(n <= 0) return -1;
        return helper(nums, target, 0, n- 1);
    }

    public int helper(int[] nums, int target, int left, int right){
        if(nums[left] == target) return left;
        if(left >= right) return -1;
        if(nums[right] == target) return right;
        int mid = (left + right) / 2;
        if(nums[mid] == target) return mid;
        //断层在左边
        if(nums[left] > nums[mid]){
            if(nums[mid] < target && nums[right] > target) {
                return helper(nums, target, mid + 1, right);
            } else {
                return helper(nums, target, left, mid - 1);
            }
        } else{//断层在右边
            if(nums[left] < target && nums[mid] > target){
                return helper(nums, target, left +1, mid - 1);
            } else {
                return helper(nums,target, mid +1, right);
            }
        }
    }

    public static void main(String[] args) {
        SearchinRotatedSortedArray s = new SearchinRotatedSortedArray();
        System.out.println(s.search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(s.search(new int[]{4,5,6,7,0,1,2}, 3));
    }
}
