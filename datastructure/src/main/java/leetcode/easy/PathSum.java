package leetcode.easy;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that
 * adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        return helper(root, sum, 0);
    }

    public boolean helper(TreeNode node, int sum, int curSum){
        if(node.left == null && node.right == null){
            if(sum == curSum + node.val) return true;
            else return false;
        }
        boolean f = false;
        if(node.left != null){
            f |= helper(node.left, sum, curSum + node.val);
        }
        if(node.right != null && !f){
            f |=  helper(node.right, sum, curSum + node.val);
        }
        return f;
    }

//    public static void main(String[] args) {
//        PathSum p =new PathSum();
//        System.out.println(p.hasPathSum());
//    }
}
