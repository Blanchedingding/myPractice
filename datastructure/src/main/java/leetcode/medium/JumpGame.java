package leetcode.medium;

/**
 * 数组中每个元素都是在这个index可以向后跳的最多的步数，问能不能从第一个元素跳到最后一个元素
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 */
public class JumpGame {

    //work backwards from the last index.
    // Keep track of the smallest index that can "jump" to the last index.
    // Check whether the current index can jump to this smallest index.
    //从倒数第二个开始检查，看能不能跳到第last位上，能的话last位更新
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int last = n-1,i;
        for(i = n - 2;i >= 0;i--){
            if(i + nums[i] >=last) last = i;
        }
        return last <= 0;
    }

    /////////////////////////////////////////////////////////////////

    int[] temp;
    public boolean canJump(int[] nums) {
        temp = new int[nums.length];
        return helper(nums, 0);
    }

    private boolean helper(int[] nums, int index){
        if(index == nums.length - 1) return true;
        if(index >= nums.length) return false;
        if(temp[index] == 0) {
            for(int i = 1; i <= nums[index]; i++){
                boolean f = helper(nums, index + i);
                if(f) {
                    temp[index] = 1;
                    break;
                } else {
                    temp[index] = 2;
                }
            }
        }
        return temp[index] == 1? true:false;
    }

    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        System.out.println(j.canJump(new int[]{2,0}));
    }
}
