package leetcode.hard;

import java.util.Stack;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 * In this case, 6 units of rain water (blue section) are being trapped.
 * Thanks Marcos for contributing this image!
 *
 * Example:
 * Input: [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 */
public class TrappingRainWater {

    public int trap(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1, maxLeft = 0, maxRight = 0;
        int sum = 0;
        while(left <= right){
            if(height[left] <= height[right]){
                if(height[left] >= maxLeft) {
                    maxLeft = height[left];
                }
                else {
                    sum += maxLeft - height[left];
                }
                left ++;
            } else {
                if(height[right] >= maxRight) {
                    maxRight = height[right];
                }
                else {
                    sum += maxRight - height[right];
                }
                right --;
            }
        }
        return sum;
    }

    public int trap1(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, sum = 0;
        int n = height.length;
        while(i < n){
            if(stack.isEmpty() || height[i] <= height[stack.peek()]){
                stack.push(i);
                i++;
            } else {
                int t = stack.pop();
                if(stack.isEmpty()) continue;
                sum += (Math.min(height[stack.peek()], height[i]) - height[t]) * (i - stack.peek() - 1);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        System.out.println(t.trap1(new int[]{2,0,2}));
        System.out.println(t.trap1(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
