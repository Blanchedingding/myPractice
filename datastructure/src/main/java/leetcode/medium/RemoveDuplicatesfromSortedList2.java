package leetcode.medium;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * Example 1:
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 *
 * Example 2:
 * Input: 1->1->1->2->3
 * Output: 2->3
 */
public class RemoveDuplicatesfromSortedList2 {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null ) return head;
        ListNode fake = new ListNode(-1);
        fake.next = head;
        ListNode pre = fake, cur = head;
        while(cur != null){
            while(cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if(pre.next == cur){
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return fake.next;
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedList2 r = new RemoveDuplicatesfromSortedList2();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(4);
        ListNode l6 = new ListNode(4);
        ListNode l7 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        ListNode e = r.deleteDuplicates(l1);
        while(e != null){
            System.out.println(e.val);
            e = e.next;
        }
    }
}
