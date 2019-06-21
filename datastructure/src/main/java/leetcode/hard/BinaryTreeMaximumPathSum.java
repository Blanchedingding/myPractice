package leetcode.hard;

/**
 * 求二叉树哪两个节点之间路径的和最大
 *
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to
 * any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 *
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Output: 42
 */
public class BinaryTreeMaximumPathSum {

    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    public int helper(TreeNode node){
        if(node == null) return Integer.MIN_VALUE;
        int a = Math.max(0, helper(node.left));
        int b = Math.max(0, helper(node.right));
        max = Math.max(max, node.val + a + b);
        return Math.max(a, b) + node.val;

    }

}
