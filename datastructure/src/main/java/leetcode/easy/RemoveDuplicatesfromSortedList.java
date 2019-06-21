package leetcode.easy;

import swordOffer.ArraySearch;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesfromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if(head == null) return head;
        int preval = head.val;
        ListNode cur = head.next, pre = head;
        while(cur != null){
            if(cur.val == preval){
                pre.next = cur.next;
            } else {
                pre = cur;
                preval = cur.val;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicatesfromSortedList r = new RemoveDuplicatesfromSortedList();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        ListNode l4 = new ListNode(3);
        ListNode l5 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        ListNode e = r.deleteDuplicates(l1);
        while(e != null){
            System.out.println(e.val);
            e = e.next;
        }
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}