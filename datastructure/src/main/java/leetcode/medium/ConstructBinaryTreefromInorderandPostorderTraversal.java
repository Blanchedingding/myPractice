package leetcode.medium;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreefromInorderandPostorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length - 1, postorder, inorder, 0, postorder.length -1);
    }

    private TreeNode helper(int postIndex, int[] postorder, int[] inorder, int leftIndex, int rightIndex){
        if(leftIndex > rightIndex || postIndex < 0) return null;
        int r = -1;
        int val = postorder[postIndex];
        TreeNode node = new TreeNode(val);
        for(int i = leftIndex; i <= rightIndex; i++){
            if(inorder[i] == val){
                r = i;
                break;
            }
        }
        node.right = helper(postIndex - 1, postorder, inorder,r + 1, rightIndex);
        node.left = helper(postIndex -(rightIndex - r) - 1,  postorder, inorder, leftIndex, r -1);
        return node;
    }

    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    public static void main(String[] args) {
        ConstructBinaryTreefromInorderandPostorderTraversal c = new ConstructBinaryTreefromInorderandPostorderTraversal();
        c.inorder(c.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}));
    }

}
