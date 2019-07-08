package leetcode.easy;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 *
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 */
public class RangeSumQueryImmutable {

//    public NumArray(int[] nums) {
//
//    }

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * int param_1 = obj.sumRange(i,j);
     */
    int[] dp ;
    public RangeSumQueryImmutable(int[] nums) {
        int n = nums.length;
        dp = new int[n];
        dp[0] = nums[0];
        for(int i = 1; i < n; i++){
           dp[i] = dp[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if(i > j) return 0;
        if(i == 0){
            return dp[j];
        } else {
            return dp[j] - dp[i - 1];
        }
    }


    public static void main(String[] args) {
        RangeSumQueryImmutable r = new RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(r.sumRange(0,2));
        System.out.println(r.sumRange(2,5));
        System.out.println(r.sumRange(0,5));
    }

}

