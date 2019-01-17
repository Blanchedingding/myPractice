package leetcode.hard;

import java.util.Arrays;

/**
 * 数组中每个元素都是在这个index可以向后跳的最多的步数，问能不能从第一个元素跳到最后一个元素,求最小跳的次数
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame2 {
    //每走一步保留走得最远的方案
    public int jump2(int[] nums) {
        int n = nums.length;
        int cur = 0;
        int total = 0;
        int i = 0;
        int pre = 0;
        while(cur < n - 1){
            total ++;
            pre = cur;
            for(; i <= pre; i++){
                cur = Math.max(cur, nums[i] + i);
            }
            //不能往后移动，说明无解
            if(pre == cur) return -1;
        }
        return total;
    }

    public int jump(int[] nums) {
        int n = nums.length;
        int[] h = new int[n];
        for(int i = 0; i < n; i++){
            h[i] = -1;
        }
        for(int i = n - 2; i >= 0; i--){
            for(int k = 1; k <= nums[i] && i + k < n; k ++){
                h[i + k] = i;
            }
        }
        int j = n -1;
        int total = 0;
        while(true){
            if(h[j] == -1 && j != 0){
                return -1;
            } else if(j == 0){
                return total;
            } else {
                j = h[j];
                total ++;
            }
        }
    }

    public static void main(String[] args) {
        JumpGame2 j = new JumpGame2();
        System.out.println(j.jump(new int[]{2,3,1,1,4}));
    }
}
