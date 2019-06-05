package leetcode.hard;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list.
 * If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
public class ReverseNodesinkGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null || head.next ==null || k==1)
            return head;
        ListNode dummyhead = new ListNode(-1);
        dummyhead.next = head;
        ListNode begin = dummyhead;
        int i=0;
        while (head != null){
            i++;
            ListNode temp = head.next;
            if (i%k == 0) {
                begin = reverse(begin, head.next);
            }
            head = temp;
        }
        return dummyhead.next;
    }

    public ListNode reverse(ListNode begin, ListNode end){
        ListNode pre = begin, curr = begin.next, first = curr, next;
        while(curr != end){
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        begin.next = pre;
        first.next = curr;
        return first;
    }

    public static void main(String[] args) {
        ReverseNodesinkGroup r = new ReverseNodesinkGroup();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode l = r.reverseKGroup(l1, 2);
        while(l != null){
            System.out.println(l.val);
            l = l.next;
        }
    }
}
