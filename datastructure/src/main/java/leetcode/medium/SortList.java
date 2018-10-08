package leetcode.medium;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 */
public class SortList {

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

       ListNode l1 = head,l2 = head;
       ListNode prev = null;
       //分成两半，前一半要把next设成null!!!!!
       while(l2 != null && l2.next != null){
           prev = l1;
           l1 = l1.next;
           l2 = l2.next.next;
       }
       prev.next = null;
       ListNode s1 = sortList(head);
       ListNode s2 = sortList(l1);
       return merge(s1, s2);
    }

    public ListNode merge(ListNode s1, ListNode s2) {
        ListNode t = new ListNode(0);
        ListNode p = t;
        while(s1 != null && s2 != null){
            if(s1.val <= s2.val){
                p.next = s1;
                s1 = s1.next;
            } else {
                p.next = s2;
                s2 = s2.next;
            }
            p = p.next;
        }
        if(s1 != null ) p.next = s1;
        if(s2 != null) p.next = s2;
        return t.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(4);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(3);
        l3.next = l4;
        l2.next = l3;
        l1.next = l2;

//        do{
//            System.out.println(l1.val);
//            l1 = l1.next;
//        } while(null != l1);

        SortList s = new SortList();
        ListNode o = s.sortList(l1);
        do{
            System.out.println(o.val);
            o = o.next;
        } while(null != o);
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
 }
