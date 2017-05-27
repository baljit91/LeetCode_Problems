/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class PalindromeList {
    public boolean isPalindrome(ListNode head) {
        
        if(head == null || head.next == null){
            return true;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //in case of odd length
        if(fast != null){
            slow = slow.next;
        }
        
        ListNode midReversed = reverseList(slow);
        ListNode currentNode = head;
        
        while(midReversed != null){
            
            if(midReversed.val != currentNode.val){
                return false;
            }
            midReversed = midReversed.next;
            currentNode = currentNode.next;
        }
        
        return true;
    }
    
    
    public ListNode reverseList(ListNode head){
        ListNode next = null;
        ListNode prev = null;
        ListNode currentNode = head;
        
        while(currentNode != null){
            next = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = next;
        }
        head = prev;
        return head;
    }
}
