/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        
        if(head == null)
            return null;
        
        ListNode prev = null;
        ListNode next = null;
        ListNode current = head;
        
        
        while(current.next != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            
        }
        
        head = current;
        head.next = prev;
        
        return head;
    }
}
