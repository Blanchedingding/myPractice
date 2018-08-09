package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class mergeKLists {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(4);
        ListNode g = new ListNode(2);
        ListNode h = new ListNode(6);
        a.next = b;
        b.next = c;
        d.next = e;
        e.next = f;
        g.next = h;
        mergeKLists m = new mergeKLists();
        ListNode l = m.mergeKLists(new ListNode[]{a,d,g});
//        while(a != null){
//            System.out.println(a.val);
//            a = a.next;
//        }

        while(l != null){
            System.out.println(l.val);
            l = l.next;
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        int n = lists.length;
        ListNode head  = lists[0];
        for (int i = 1; i < lists.length; i++) {
            if(lists[i].val < head.val) {
                head = lists[i];
                lists[i] = lists[i].next;
            }
        }

        ListNode t = head;
        while(true){
            int flag = 0;
            int min = Integer.MAX_VALUE;
            List<Integer> index = new ArrayList<>();
            for(int i = 0; i < lists.length; i++){
                if(lists[i] == null){
                    flag ++;
                } else if(lists[i].val < min ){
                    index.clear();
                    index.add(i);
                    min = lists[i].val;
                } else if(lists[i].val == min ){
                    index.add(i);
                }
            }
            System.out.println(flag);
            System.out.println(index);
            for (int i = 0; i < index.size(); i++) {
                t.next = lists[index.get(i)];
                t = t.next;
                lists[index.get(i)] = lists[index.get(i)].next;
                System.out.println("----" + lists[index.get(i)].next.val);
            }
            if(flag >= n - 1) break;
        }

        return head;
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
