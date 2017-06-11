/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if(root == null)
            return;
            
        Queue<TreeLinkNode> que = new LinkedList<TreeLinkNode>();
        
        que.offer(root);
        que.offer(null);
        
        while(!que.isEmpty()){
            TreeLinkNode current = que.poll();
            if(current != null){
                if(current.left != null){
                    que.offer(current.left);
                }
                if(current.right != null){
                    que.offer(current.right);
                }
                
                current.next = que.peek();
            }
            
            else{
                if(!que.isEmpty()){
                   que.offer(null); 
                }
            }
        }
    }
}
