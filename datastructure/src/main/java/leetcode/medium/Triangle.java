package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[2][triangle.size()];
        for(int i = 0; i < triangle.size(); i++){
            for(int j = 0; j <= i; j++){
                if(j == 0){
                    dp[1][0] = dp[0][0] + triangle.get(i).get(0);
                } else if(j == i){
                    dp[1][j] = dp[0][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[1][j] = Math.min(dp[0][j - 1], dp[0][j]) + triangle.get(i).get(j);
                }
            }
            dp[0] = dp[1].clone();
            System.out.println(Arrays.toString(dp[0]));
            dp[1] = new int[triangle.size()];
        }
        for(int i: dp[0]){
            if(i < min) min = i;
        }
        return min;
    }

    public static void main(String[] args) {
        Triangle t = new Triangle();
        List<List<Integer>> triangle = new ArrayList<>();
        List<Integer> l1 = new ArrayList<>();
        l1.add(2);
        List<Integer> l2 = new ArrayList<>();
        l2.add(3);
        l2.add(4);
        List<Integer> l3 = new ArrayList<>();
        l3.add(6);
        l3.add(5);
        l3.add(7);
        List<Integer> l4 = new ArrayList<>();
        l4.add(4);
        l4.add(1);
        l4.add(8);
        l4.add(3);
        triangle.add(l1);
        triangle.add(l2);
        triangle.add(l3);
        triangle.add(l4);
        System.out.println(t.minimumTotal(triangle));
    }
}
