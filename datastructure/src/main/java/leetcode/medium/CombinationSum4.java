package leetcode.medium;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given an integer array with all positive numbers and no duplicates,
 * find the number of possible combinations that add up to a positive integer target.
 *
 * Example:
 * nums = [1, 2, 3]
 * target = 4
 *
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 *
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 *
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 */
public class CombinationSum4 {


    public int combinationSum4(int[] nums, int target) {
        Arrays.sort(nums);
        if(nums.length <= 0) return 0;
        int[] map = new int[target + 1];
        map[0] = 1;
        for(int i = 1; i <= target; i++){
            int sum = 0;
            for(int t: nums){
                if( i - t >= 0) sum += map[i - t];
            }
            map[i] =  sum;
        }
        System.out.println(Arrays.toString(map));
        return map[target];
    }




    public static void main(String[] args) {
        int[] n = new int[]{1, 2, 3};
        CombinationSum4 c = new CombinationSum4();
        System.out.println(c.combinationSum4(n,4));
    }
}
