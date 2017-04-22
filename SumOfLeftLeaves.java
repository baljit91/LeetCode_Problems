/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = helper(root, 1);
        
        return sum;
    }
    
    public int helper(TreeNode root,int index){
        int sum = 0;
        
        if(root == null)
            return 0;
        if(isLeaf(root, index)){
            sum += root.val;
            return sum;
        } 
        sum = helper(root.left,0) + helper(root.right,1);
        return sum;
    }
    
    public boolean isLeaf(TreeNode root, int index){
        if(root.left == null && root.right == null && index == 0){
            return true;
        }
        else{
            return false;
        }
    }
}
