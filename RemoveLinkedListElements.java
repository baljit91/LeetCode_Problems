/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode prev = head;
        ListNode current = head;
        
        while(current != null){
            
            //handle if node to be removed at head
            if(current.val == val && current == head){
                current = current.next;
                head = current;
                prev = head;
            }
            //handle case when node to be removed not at head
            else if(current.val == val){
                current = current.next;
                prev.next = current;
            }
            //just simply iterating when current node not be removed
            else{
                 prev = current;
                 current = current.next;
            }
        }
        
        return head;
    }
}
