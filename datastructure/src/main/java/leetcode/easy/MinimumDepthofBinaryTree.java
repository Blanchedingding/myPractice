package leetcode.easy;

/**
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its minimum depth = 2.
 */
public class MinimumDepthofBinaryTree {

    int min = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        helper(root, 1);
        return min;
    }

    public void helper(TreeNode node, int level){
        if(node.left == null && node.right == null){
            min = Math.min(min, level);
            return;
        }
        if(node.left != null){
            helper(node.left, level + 1);
        }
        if(node.right != null){
            helper(node.right, level + 1);
        }
    }
}
