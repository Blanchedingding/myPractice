package dynamicProgramming;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 *
 * Example:
 *
 * Input: [2,1,5,6,2,3]
 * Output: 10
 */
public class LargestRectangleinHistogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int max = 0;
        while(i < n ){
            if(stack.empty() || heights[i] >= heights[stack.peek()]){
                stack.push(i);
                i ++;
            } else {
                int endIndex = stack.pop();
                int area = heights[endIndex] * (stack.empty()? i: i - 1 - stack.peek());
                max = Math.max(max, area);
            }
        }
        while(!stack.empty()){
            int endIndex = stack.pop();
            int area = heights[endIndex] * (stack.empty()? i: i - 1 - stack.peek());
            max = Math.max(max, area);
        }
        return max;
    }

    public static void main(String[] args) {
        LargestRectangleinHistogram l = new LargestRectangleinHistogram();
        System.out.println(l.largestRectangleArea(new int[]{2,1,5,6,2,3}));
    }
}
