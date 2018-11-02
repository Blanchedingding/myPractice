package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 *
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 *
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
 *
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */
public class FindNumberOfLIS {

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        int result = 0;
        int[] len = new int[n];//以i结尾的LIS的长度
        int[] count = new int[n];//以i结尾的LIS的数量
        for(int i = 0; i < n; i++){
            len[i] = count[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                  if(len[i] < len[j] + 1){
                      len[i] = len[j] + 1;
                      count[i] = count[j];
                  } else if(len[i] == len[j] + 1){
                      count[i] += count[j];
                  }
                }
            }
            if(maxLength < len[i]) {
                maxLength = len[i];
                result = count[i];
            }
            else if(maxLength == len[i]) {
                result += count[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindNumberOfLIS f = new FindNumberOfLIS();
        int[] nums = new int[]{1,3,5,4,7};
        System.out.println(f.findNumberOfLIS(nums));
    }
}


