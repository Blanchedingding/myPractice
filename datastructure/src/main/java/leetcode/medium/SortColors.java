package leetcode.medium;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 *
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 *
 * Note: You are not suppose to use the library's sort function for this problem.
 *
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 *
 * Follow up:
 * A rather straight forward solution is a two-pass algorithm using counting sort.
 * First, iterate the array counting number of 0's, 1's, and 2's,
 * then overwrite array with total number of 0's,then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int n = nums.length;
        int end0 = -1,  end1 = -1;
        int count1 = 0, count2 = 0;
        for(int i = 0; i < n; i++){
            if(nums[i] == 0){
                end0 ++;
                end1 ++;
                nums[end0] = 0;
                if(count1 > 0){
                    nums[end0 + count1] = 1;
                }
                if(count2 > 0){
                    nums[end1 + count2] = 2;
                }
            } else if(nums[i] == 1){
                count1 ++;
                end1 ++;
                nums[end0 + count1] = 1;
                if(count2 > 0){
                    nums[end1 + count2] = 2;
                }
            } else {
                count2 ++;
                nums[end1 + count2] = 2;
            }
//            System.out.println("i=" + i + "  nums[i]=" + nums[i]);
//            System.out.println(Arrays.toString(nums));
//            System.out.println();
        }
    }

    public static void main(String[] args) {
        SortColors s = new SortColors();
        int[] in = new int[]{2,0,2,1,1,0};
        s.sortColors(in);
        System.out.println(Arrays.toString(in));
    }
}
