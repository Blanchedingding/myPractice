package leetcode.medium;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 *
 * Follow up:
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode t0 = new ListNode(0);
        t0.next = head;
        ListNode t1 = t0, t2 = t0;
        for (int i = 1; i <= n ; i++) {
            t1 = t1.next;
        }
        while(t1.next != null){
            t1 = t1.next;
            t2 = t2.next;
        }
        t2.next = t2.next.next;

        return t0.next;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndofList r = new RemoveNthNodeFromEndofList();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode l = r.removeNthFromEnd(l1, 2);
        while(l != null){
            System.out.println(l.val);
            l = l.next;
        }
    }
}
