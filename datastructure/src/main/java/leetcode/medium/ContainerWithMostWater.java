package leetcode.medium;

/**
 * Given n non-negative integers a1, a2, ..., an ,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * Example:
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 */
public class ContainerWithMostWater {

    //从最外面两条线开始计算，比较小的线对应指针向中间挪一格，
    // 因为比较小的线是容器的实际高度，它和其它线组成的容器不可能大于当前计算的最外面的容器了
    public int maxArea2(int[] height) {
        int n = height.length;
        int max = 0;
        int i = 0, j = n - 1;
        while(i < j){
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if(height[i] < height[j]){
                i ++;
            } else {
                j --;
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        int n = height.length;
        int max = 0;
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                int h = Math.min(height[i], height[j]);
                if( h * (i - j) > max){
                    max = h * (i - j);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ContainerWithMostWater c = new ContainerWithMostWater();
        System.out.println(c.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
