/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 /**
Logic -- for every node calulate the it left chid node height and it right child node height and t
take a sum of that.
return the max value 
 */
 
 
 
public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null)
            return 0;
            
        int currentDiameter = height(root.left) + height(root.right);
        int maxLeftRight = Math.max(diameterOfBinaryTree(root.left),diameterOfBinaryTree(root.right));
        return Math.max(currentDiameter,maxLeftRight) ;
    }
    
    public int height(TreeNode root){
        if(root == null)
            return 0;
            
        return Math.max(height(root.left),height(root.right)) + 1;
    }
}
