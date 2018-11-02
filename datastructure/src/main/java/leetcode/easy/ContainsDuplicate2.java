package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k,
 * find out whether there are two distinct indices i and j in the array
 * such that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 *
 * Example 1:
 * Input: nums = [1,2,3,1], k = 3
 * Output: true
 *
 * Example 2:
 * Input: nums = [1,0,1,1], k = 1
 * Output: true
 *
 * Example 3:
 * Input: nums = [1,2,3,1,2,3], k = 2
 * Output: false
 */
public class ContainsDuplicate2 {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            System.out.println(map);
            if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k){
                return true;
            }
            map.put(nums[i], i);
        }
        System.out.println(map);
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicate2 c = new ContainsDuplicate2();
        int[] n = new int[]{1,0,1,1};
        System.out.println(c.containsNearbyDuplicate(n, 2));
    }


}
