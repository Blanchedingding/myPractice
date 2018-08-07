package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 *
 * Note: The solution set must not contain duplicate triplets.
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i ++){
            if(i == 0 || (i > 0 && nums[i] != nums[i -1] )) {
                int low = i + 1;
                int high = nums.length - 1;
                while(low < high){
                    int t = nums[i] + nums[low] + nums[high];
                    if(t == 0){
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while(low < high && nums[low] == nums[low+1]) low ++;
                        while(low < high && nums[high] == nums[high - 1]) high--;
                        low ++;
                        high --;
                    } else if(t < 0){
                        low ++;
                    } else {
                        high --;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n = new int[]{-1, 0, 1, 2, -1, -4};
        ThreeSum s = new ThreeSum();
        List<List<Integer>> r = s.threeSum(n);
        System.out.println(r);
    }
}
