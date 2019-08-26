package leetcode.medium;

/**
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * Example:
 *
 *              1
 *            /  \
 *           2    3
 *          / \    \
 *         4  5     7
 *
 *           1 -> null
 *         /  \
 *        2 -> 3 -> null
 *       / \    \
 *      4-> 5 -> 7->null
 *
 * Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":null,"next":null,"right":{"$id":"6","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
 *
 * Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":null,"right":null,"val":7},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"6","left":null,"next":null,"right":{"$ref":"5"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"6"},"val":1}
 *
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 *
 *
 * Note:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersinEachNode2 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Node head = root;
        while (root != null){
            Node tempChild = new Node();
            Node currentChild = tempChild;
            while (root!=null){
                if(root.left != null) {
                    currentChild.next = root.left;
                    currentChild = currentChild.next;
                }
                if(root.right != null) {
                    currentChild.next = root.right;
                    currentChild = currentChild.next;
                }
                root = root.next;
            }
            root = tempChild.next;
        }
        return head;
//        if(root == null) return root;
//        Node pre = root;
//        Node cur = pre.left != null? pre.left: pre.right;
//        while(cur != null){
//            Node t = cur;
//            while(pre != null){
//                if(t != pre.right && pre.right != null){
//                    t.next = pre.right;
//                    t = t.next;
//                }
//                while(pre.next != null && pre.next.left == null && pre.next.right == null){
//                    pre = pre.next;
//                }
//                if(pre.next != null && pre.next.left != null){
//                    t.next = pre.next.left;
//                } else if(pre.next != null){
//                    t.next = pre.next.right;
//                }
//                t = t.next;
//                pre = pre.next;
//            }
//            pre = cur;
//            while(pre != null && pre.left == null && pre.right == null){
//                pre = pre.next;
//            }
//            if(pre == null) break;
//            cur = pre.left != null? pre.left: pre.right;
//        }
//
//        return root;
    }
}
