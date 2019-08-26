package leetcode.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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

        while(l != null){
            System.out.println("result: " + l.val);
            l = l.next;
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> q = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        int n = lists.length;
        ListNode head = new ListNode(0);
        ListNode res = head;
        for(ListNode ln: lists){
            q.offer(ln);
        }
        while(!q.isEmpty()){
            ListNode t = q.poll();
            head.next = t;
            head = head.next;
            if(t.next != null){
                q.offer(t.next);
            }
        }
        return res.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0) return null;
        int n = lists.length;
        ListNode head ;
        int c = Integer.MAX_VALUE, u = -1;
        for (int i = 0; i < lists.length; i++) {
            if( lists[i] != null && lists[i].val < c) {
                c = lists[i].val;
                u = i;
            }
        }
        if (u == -1) return null;
        head = lists[u];
        lists[u] = lists[u].next;
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
            for (int i = 0; i < index.size(); i++) {
                t.next = lists[index.get(i)];
                t = t.next;
                lists[index.get(i)] = lists[index.get(i)].next;
            }
            if(flag >= n-1) break;
        }
        return head;
    }


    public void print(ListNode r){
        ListNode e = r;
        while(e != null){
            System.out.println("== " + e.val);
            e = e.next;
        }
    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
