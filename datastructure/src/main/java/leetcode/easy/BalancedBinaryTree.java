package leetcode.easy;

/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 */
public class BalancedBinaryTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }


//    public boolean isBalanced(TreeNode root) {
//        if(root == null) return true;
//        int leftHeight = getDepth(root.left);
//        int rightHeight = getDepth(root.right);
//        return Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
//    }
//
//    private int getDepth(TreeNode node){
//        if(node == null) return 0;
//        return Math.max(getDepth(node.left), getDepth(node.right)) +1;
//    }


    boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        getDepth(root);
        return flag;
    }

    private int getDepth(TreeNode node){
        if(node == null) return 0;
        int leftHeight = getDepth(node.left);
        int rightHeight = getDepth(node.right);
        if(Math.abs(leftHeight - rightHeight) > 1) flag = false;
        return Math.max(leftHeight, rightHeight) +1;
    }

}
