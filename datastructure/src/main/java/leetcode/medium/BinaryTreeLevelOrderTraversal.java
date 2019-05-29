package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null){
            q.offer(root);
            q.offer(null);
            List<Integer> l = new ArrayList<>();
            while(!q.isEmpty()){
                TreeNode t = q.poll();
                if(t == null){
                    r.add(new ArrayList<>(l));
                    l.clear();
                    if(!q.isEmpty()) q.offer(null);
                } else {
                    l.add(t.val);
                    if(t.left != null) q.offer(t.left);
                    if(t.right != null) q.offer(t.right);
                }
            }
        }
        return r;
    }
}

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
