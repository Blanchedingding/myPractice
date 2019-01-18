package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreePostorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        List<Integer> r = new ArrayList<>();
        while (!stack.empty()){
            TreeNode n = stack.peek();
            if (n != null){
                if(n.left == null && n.right == null){
                    stack.pop();
                    r.add(n.val);
                } else if(n.right == null){
                    stack.push(null);
                    stack.push(n.left);
                } else if(n.left == null){
                    stack.push(null);
                    stack.push(n.right);
                } else {
                    stack.push(null);
                    stack.push(n.right);
                    stack.push(n.left);
                }
            } else {
                stack.pop();
                if(! stack.empty()){
                    TreeNode n2 = stack.pop();
                    r.add(n2.val);
                }
            }
        }
        return r;
    }

}
