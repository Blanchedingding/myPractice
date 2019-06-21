package leetcode.medium;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        if(head.next == null || k <= 0) return head;
        int n = 1;
        ListNode p = head;
        while(p.next != null){
            n ++;
            p = p.next;
        }
        k = (n - k % n) % n;
        if(k == 0) return head;
        ListNode q = head, t = head;
        int index = 0;
        while(q.next != null){
            index ++;
            ListNode u = q.next;
            if(index == k){
                head = u;
                q.next = null;
            }
            q = u;
        }
        q.next = t;
        return head;
    }

    public static void main(String[] args) {
        RotateList r = new RotateList();
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;
        ListNode e = r.rotateRight(l1, 4);
        while(e != null){
            System.out.println(e.val);
            e = e.next;
        }
    }
}
