package leetcode.medium;

/**
 * Sort a linked list using insertion sort.
 *
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class InsertionSortList {

    public Node insertionSortList(Node head) {
        if(head == null) return null;
        Node result = new Node(head.val);
        head = head.next;
        while(head != null){
            Node next = head.next;
            if(result.val >= head.val){
                head.next = result;
                result = head;
            } else {
                Node temp = result;
                while(temp.next != null && temp.next.val < head.val){
                    temp = temp.next;
                }
                head.next = temp.next;
                temp.next = head;
            }
            head = next;
        }
        return result;
    }

    public static void main(String[] args) {
        Node a1 = new Node(4);
        Node a2 = new Node(2);
        Node a3 = new Node(1);
        Node a4 = new Node(3);
        a3.next = a4;
        a2.next = a3;
        a1.next = a2;
        InsertionSortList i = new InsertionSortList();
        Node n = i.insertionSortList(a1);
        while(n != null){
            System.out.println(n.val);
            n = n.next;
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

class Node {
    int val;
    Node next;
    Node(int x) { val = x; }
}



