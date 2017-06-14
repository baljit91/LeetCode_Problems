/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	public int isSymmetric(TreeNode root) {
	    if(root == null){
	        return 1;
	    }
	    
	    return isSymmetricUtil(root.left,root.right);
	}
	
	
	public int isSymmetricUtil(TreeNode leftSub,TreeNode rightSub){
	    if(leftSub ==null &&  rightSub == null){
	        return 1;
	    }
	    
	    if(leftSub ==null ||  rightSub == null){
	        return 0;
	    }
	    
	    if(leftSub.val == rightSub.val && isSymmetricUtil(leftSub.left,rightSub.right) == 1 && 
	    isSymmetricUtil(leftSub.right,rightSub.left) == 1){
	        return 1;
	    }
	    
	    return 0;
	    
	}
}
