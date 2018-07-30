package leetcode;

/**
 * Given several boxes with different colors represented by different positive numbers.
 You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with the same color (composed of k boxes, k >= 1), remove them and get k*k points.
 Find the maximum points you can get.

 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 Output:
 23
 Explanation:
 [1, 3, 2, 2, 2, 3, 4, 3, 1]
 ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
 ----> [1, 3, 3, 3, 1] (1*1=1 points)
 ----> [1, 1] (3*3=9 points)
 ----> [] (2*2=4 points)
 Note: The number of boxes n would not exceed 100.
 */
public class RemoveBoxes {

    int[][][] m;

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        m = new int[n][n][n];
        return helper(boxes, 0, n-1, 0);
    }

    public int helper(int[] boxes, int left, int right, int count){
        if(left > right) return 0;
        if(m[left][right][count] > 0) return m[left][right][count];
        while(right > left && boxes[right-1] == boxes[right]){
            count ++;
            right --;
        }
        //先算出left-right的基础points，即每次都从最后开始推连续的箱子
        m[left][right][count] = helper(boxes, left, right-1, 0) + (count+1)*(count+1);
        for(int i = left; i < right; i++){
            //再从头开始找和最后一样数字的箱子，假设先把它们之间的箱子推掉，合并它们，再推，看两种方法哪种points高
            if(boxes[i] == boxes[right]){
                m[left][right][count] = Math.max(m[left][right][count], helper(boxes, left, i, count+1) + helper(boxes, i + 1, right-1, 0));
            }
        }
        return m[left][right][count];
    }
}
