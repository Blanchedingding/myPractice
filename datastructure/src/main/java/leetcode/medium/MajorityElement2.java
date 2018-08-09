package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElement2 {
    public static void main(String[] args) {
        MajorityElement2 m = new MajorityElement2();
        System.out.println(m.majorityElement(new int[]{3,2,3}));
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> r = new ArrayList<>();
        if(nums.length == 0) return r;
        int c1 = nums[0], c2 = nums[0];
        int c1count = 0, c2count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == c1){
                c1count++;
            } else if(nums[i] == c2){
                c2count ++;
            } else if(c1count == 0){
                c1 = nums[i];
                c1count = 1;
            } else if(c2count == 0){
                c2 = nums[i];
                c2count = 1;
            } else {
                c1count --;
                c2count --;
            }
        }
        System.out.println(c1 + " " + c1count + " " + c2 + " " + c2count);
        c1count = 0;
        c2count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == c1) c1count ++;
            else if(nums[i] == c2) c2count ++;
        }
        System.out.println(c1 + " " + c1count + " " + c2 + " " + c2count);
        if(c1count > nums.length / 3) r.add(c1);
        if(c2count > nums.length / 3) r.add(c2);
        return r;
    }

}
