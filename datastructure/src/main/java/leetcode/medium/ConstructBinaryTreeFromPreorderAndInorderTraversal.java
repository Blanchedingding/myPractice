package leetcode.medium;

/***
 * 根据先序和后序重建二叉树
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * For example, given
 *
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, preorder, inorder, 0, preorder.length -1);
    }

    private TreeNode helper(int preIndex, int[] preorder, int[] inorder, int leftIndex, int rightIndex){
        if(leftIndex > rightIndex || preIndex >= preorder.length) return null;
        int r = -1;
        int val = preorder[preIndex];
        TreeNode node = new TreeNode(val);
        for(int i = leftIndex; i <= rightIndex; i++){
            if(inorder[i] == val){
                r = i;
                break;
            }
        }
        node.left = helper(preIndex +1, preorder, inorder, leftIndex, r -1);
        //重点在于计算完左子树后如何得知处理到了preorder的哪个元素
        //其实处理完左子树，指针后移的个数就是左子树的元素数
        node.right = helper(preIndex +(r - leftIndex)+ 1, preorder, inorder,r + 1, rightIndex);
        return node;
    }

    private void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal c =new ConstructBinaryTreeFromPreorderAndInorderTraversal();
//        c.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7});
        c.inorder(c.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
    }

}
