package dynamicProgramming;

public class MaxSubArray {

    //动归
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        if(n <= 0) return 0;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with nums[i];
        dp[0] = nums[0];
        int max  = nums[0];
        for(int i = 1; i < n; i++){
            dp[i] = Math.max(nums[i], dp[i-1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(sum <= nums[i]){
                sum = nums[i];
            }
            if(sum > max){
                max = sum;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray m  = new MaxSubArray();
        System.out.println(m.maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
