package leetcode.medium;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 *
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example:
 *
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesinPairs {

    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode t1 = head.next, t2 = t1;
        while(t1 != null){
            ListNode t3 = t1.next;
            t1.next = head;
            if(t3 != null){
                if(t3.next != null){
                    head.next = t3.next;
                } else {
                    head.next = t3;
                }
                head = t3;
                t1 = head.next;
            } else {
                head.next = null;
                break;
            }
        }
        return t2;
    }

    public static void main(String[] args) {
        SwapNodesinPairs s = new SwapNodesinPairs();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        ListNode l = s.swapPairs(l1);
        while(l != null){
            System.out.println(l.val);
            l = l.next;
        }
    }
}
