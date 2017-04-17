/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int length_A = 0;
        int length_B = 0;
        
        ListNode curr_A = headA;
        ListNode curr_B = headB;
        
        while(curr_A != null){
            curr_A = curr_A.next;
            length_A++;
        }
        
        while(curr_B != null){
            curr_B = curr_B.next;
            length_B++;
        }
        
        int step = 0;
        if(length_A > length_B)
            step = length_A - length_B;
            
            
        else if(length_B > length_A){
            step = length_B - length_A;
            ListNode temp = headA;
            headA = headB;
            headB = temp;
        }
        
        int itr = 0;
        while(itr < step){
            headA = headA.next;
            itr++;
        }
        
        while(headA != null && headB != null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        
        return null;
    }
}
