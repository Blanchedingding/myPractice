package dynamicProgramming;

public class MaxProduct {

    /**
     * Given an integer array nums, find the contiguous subarray within an array
     * (containing at least one number) which has the largest product.
     *
     * Example 1:
     * Input: [2,3,-2,4]
     * Output: 6
     * Explanation: [2,3] has the largest product 6.
     *
     * Example 2:
     * Input: [-2,0,-1]
     * Output: 0
     * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int[] dp1 = new int[n];//dp1存以i结尾的最大的积，dp2存i结尾的最小的积
        int[] dp2 = new int[n];
        dp1[0] = nums[0];
        dp2[0] = nums[0];
        for(int i = 1; i < n; i++){
            if(nums[i] > 0){
                dp1[i] = Math.max(dp1[i - 1] * nums[i], nums[i]);
                dp2[i] = Math.min(dp2[i - 1] * nums[i], nums[i]);
            } else {
                dp1[i] = Math.max(dp2[i - 1] * nums[i], nums[i]);
                dp2[i] = Math.min(dp1[i - 1] * nums[i], nums[i]);
            }
            max = Math.max(max, dp1[i]);
        }
        return max;
    }

    /**
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.
     *
     * Example 1:
     * Input: [1,2,3]
     * Output: 6
     *
     * Example 2:
     * Input: [1,2,3,4]
     * Output: 24
     */
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        if(n < 3) return 0;
        int max = nums[0] * nums[1] * nums[2];
        int tMax = Math.max(nums[0] * nums[2], Math.max(nums[0] * nums[1], nums[1] * nums[2]));
        int tMin = Math.min(nums[0] * nums[2], Math.min(nums[0] * nums[1], nums[1] * nums[2]));
        int iMax = Math.max(nums[2], Math.max(nums[0], nums[1]));
        int iMin = Math.min(nums[2], Math.min(nums[0], nums[1]));
        for(int i = 3; i < n; i++){
            if(nums[i] > 0){
                max = Math.max(max, tMax * nums[i]);
                tMax = Math.max(tMax, iMax * nums[i]);
                tMin = Math.min(tMin, iMin * nums[i]);
            } else {
                max = Math.max(max, tMin * nums[i]);
                tMax = Math.max(tMax, iMin * nums[i]);
                tMin = Math.min(tMin, iMax * nums[i]);

            }
            iMax = Math.max(iMax, nums[i]);
            iMin = Math.min(iMin, nums[i]);
        }
        return max;
    }


    public static void main(String[] args) {
        MaxProduct m = new MaxProduct();
//        System.out.println(m.maxProduct(new int[]{0,2}));
        System.out.println(m.maximumProduct(new int[]{1,2,3,4}));
    }
}
