package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum,
 * find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> r = new ArrayList<>();
        if(root == null) return r;
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        helper(root, sum, root.val, r, list);
        return r;
    }

    public void helper(TreeNode root, int sum, int curSum, List<List<Integer>> r, List<Integer> list){
        if(root.left == null && root.right == null){
            if(curSum == sum) {
                r.add(new ArrayList<>(list));
            }
            return;
        }
        if(root.left != null){
            list.add(root.left.val);
            helper(root.left, sum, curSum + root.left.val, r, list);
            list.remove(list.size() - 1);
        }
        if(root.right != null){
            list.add(root.right.val);
            helper(root.right, sum, curSum + root.right.val, r, list);
            list.remove(list.size() - 1);
        }
    }

//    public static void main(String[] args) {
//
//    }
}
