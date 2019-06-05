package leetcode.medium;

import java.util.*;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        if(root == null) return r;

        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        q.offer(root);
        q.offer(null);
        boolean right = true;
        while(!q.isEmpty()){
            TreeNode n = q.poll();
            if(n == null ){
                r.add(new LinkedList<>(list));
                list.clear();
                right = right? false: true;
                if(!q.isEmpty())q.offer(null);
            } else {
                if(right) list.add(n.val);
                else list.add(0, n.val);
                if(n.left!= null) q.offer(n.left);
                if(n.right != null) q.offer(n.right);
            }
        }
        return r;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(9);
        TreeNode n3 = new TreeNode(20);
        n1.left = n2;
        n1.right = n3;
        BinaryTreeZigzagLevelOrderTraversal b = new BinaryTreeZigzagLevelOrderTraversal();
        System.out.println(b.zigzagLevelOrder(n1));

    }

}
