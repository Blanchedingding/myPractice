package leetcode.easy;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted array: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedArraytoBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        return helper(nums, 0, n - 1);
    }

    public TreeNode helper(int[] nums, int l, int r){
        if(l > r) return null;
        if(l == r) {
            return new TreeNode(nums[l]);
        } else {
            int mid = (l + r)/2;
            TreeNode t = new TreeNode(nums[mid]);
            t.left = helper(nums, l, mid - 1);
            t.right = helper(nums, mid +1, r);
            return t;
        }
    }

    public static void main(String[] args) {
        ConvertSortedArraytoBinarySearchTree c = new ConvertSortedArraytoBinarySearchTree();

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
