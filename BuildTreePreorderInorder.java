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
    int preIndex = 0;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int inStart = 0;
        int inEnd = inorder.length -1;
        return buildTreeUtil(preorder, inorder, inStart, inEnd);
    }
    
    
    public TreeNode buildTreeUtil(int[] preorder, int[] inorder,int inStart,int inEnd) {
        TreeNode root = null;
        //initiate node with null
        if(inStart <= inEnd){
            //get the first value from preorder make a new node and then search the same value
            // in inorder array. and the left half of the inorder will be left sub tree and right half will be right subtree.
            root = new TreeNode(preorder[preIndex++]);
            int indexIn = search(inorder,root.val);
            
            root.left = buildTreeUtil(preorder,inorder,inStart,indexIn -1);
            root.right = buildTreeUtil(preorder, inorder,indexIn + 1, inEnd);
        }
        
        return root;
    }
    
    public int search(int[] array,int value){
        for(int i=0; i<array.length; i++){
            if(array[i] == value){
                return i;
            }
        }
        return -1;
    }
}
