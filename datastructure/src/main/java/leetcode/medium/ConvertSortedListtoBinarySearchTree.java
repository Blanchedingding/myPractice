package leetcode.medium;

import java.util.LinkedList;

/**
 * Given a singly linked list where elements are sorted in ascending order,
 * convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example:
 *
 * Given the sorted linked list: [-10,-3,0,5,9],
 *
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 */
public class ConvertSortedListtoBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) return null;
        return toBST(head,null);
    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) return null;

        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }

    public TreeNode sortedListToBST2(ListNode head) {
        LinkedList<TreeNode> list = new LinkedList<>();
        while(head != null){
            list.add(new TreeNode(head.val));
            head = head.next;
        }
        return helper(0, list.size() - 1, list);
    }

    public TreeNode helper(int begin, int end, LinkedList<TreeNode> list){
        if(begin == end) return list.get(begin);
        if(begin > end) return null;
        int mid = (begin+end)/2;
        TreeNode node = list.get(mid);
        node.left = helper(begin, mid - 1, list);
        node.right = helper(mid + 1, end, list);
        return node;
    }

    public void inorder(TreeNode root){
        if(root == null) return;
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }

    public static void main(String[] args) {
        ConvertSortedListtoBinarySearchTree c = new ConvertSortedListtoBinarySearchTree();
        ListNode l1 = new ListNode(-10);
        ListNode l2 = new ListNode(-3);
        ListNode l3 = new ListNode(0);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        TreeNode t = c.sortedListToBST(l1);
        c.inorder(t);
    }

}
