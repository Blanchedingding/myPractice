package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 *
 * Note:
 *
 * The solution set must not contain duplicate quadruplets.
 *
 * Example:
 *
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 *
 * A solution set is:
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int length = nums.length;

        for(int i = 0; i < length - 3; i ++){
            if(i == 0 || (i > 0 && nums[i] != nums[i - 1])){
                for(int j = i + 1; j < length - 2; j++){
                    if(j == i + 1 || ((j > (i + 1)) && nums[j] != nums[j -1])){
                        int low = j + 1, high = length - 1;
                        while(low < high){
                            int t = nums[i] + nums[j] + nums[low] + nums[high];
                            if(t == target){
                                result.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                                while(low < high && nums[low + 1] == nums[low]) low++;
                                while(low < high && nums[high - 1] == nums[high]) high--;
                                low ++;
                                high --;
                            } else if(t < target){
                                low ++;
                            } else {
                                high --;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] n = new int[]{-1, 0, 1, 2, -1, -4};
        FourSum s = new FourSum();
        List<List<Integer>> r = s.fourSum(n,-1);
        System.out.println(r);
    }
}
