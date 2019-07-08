package leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from
 * the root node down to the farthest leaf node.
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
 * return its depth = 3.
 */
public class MaximumDepthofBinaryTree {

    int max = 0;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        helper(root, 1);
        return max;
    }

    public void helper(TreeNode node, int level){
        if(node.left == null && node.right == null){
            max = Math.max(max, level);
            return;
        }
        if(node.left != null){
            helper(node.left, level + 1);
        }
        if(node.right != null){
            helper(node.right, level + 1);
        }
    }



    public int maxDepth2(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        int depth = 0;
        while(!q.isEmpty()){
            TreeNode t = q.poll();
            if(t == null){
                depth ++;
                if(q.isEmpty()) break;
                else {
                    q.offer(null);
                }
            } else {
                if(t.left != null){
                    q.offer(t.left);
                }
                if(t.right != null){
                    q.offer(t.right);
                }
            }
        }
        return depth;
    }


}
