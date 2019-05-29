package leetcode.hard;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Example 1:
 *
 * Input: [1,3,null,null,2]
 *
 *    1
 *   /
 *  3
 *   \
 *    2
 *
 * Output: [3,1,null,null,2]
 *
 *    3
 *   /
 *  1
 *   \
 *    2
 * Example 2:
 *
 * Input: [3,1,4,null,null,2]
 *
 *   3
 *  / \
 * 1   4
 *    /
 *   2
 *
 * Output: [2,1,4,null,null,3]
 *
 *   2
 *  / \
 * 1   4
 *    /
 *   3
 * Follow up:
 *
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
 */
public class RecoverBinarySearchTree {

    TreeNode a=null,b = null,pre = null;

    public void recoverTree(TreeNode root) {
        helper(root);
        if(a != null && b != null){
            int t = a.val;
            a.val = b.val;
            b.val = t;
        }
    }

    public void helper(TreeNode root){
        if(root == null) return;
        helper(root.left);
        if(pre != null && root.val < pre.val){
            if(a == null){
                a = pre;
                b = root;
            } else {
                b = root;
            }
        }
        pre = root;
        helper(root.right);
    }

    public static void main(String[] args) {
//        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(3);
//        TreeNode n4 = new TreeNode(4);
//        n3.left = n1;
//        n3.right = n4;
//        n4.left = n2;
//        RecoverBinarySearchTree r= new RecoverBinarySearchTree();
//        r.recoverTree(n3);
//        r.printTree(n3);

        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n3;
        n3.right = n2;
        RecoverBinarySearchTree r= new RecoverBinarySearchTree();
        r.recoverTree(n1);
        r.printTree(n1);
    }

    public void printTree(TreeNode r){
        if(r == null) return ;
        printTree(r.left);
        System.out.println(r.val);
        printTree(r.right);
    }
}


class TreeNode {
    int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}