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
    static int postIndex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inStart = 0;
        int inEnd = inorder.length -1;
        postIndex = postorder.length - 1;
        return buildTreeUtil(postorder, inorder, inStart, inEnd);
    }
    
    
        public TreeNode buildTreeUtil(int[] postorder, int[] inorder,int inStart,int inEnd) {
        TreeNode root = null;
        //initiate node with null
        if(inStart <= inEnd && postIndex >= 0){
            
            System.out.println(postIndex);
            root = new TreeNode(postorder[postIndex]);
            postIndex--;
            int indexIn = search(inorder, root.val);
            
            root.right = buildTreeUtil(postorder, inorder, indexIn + 1, inEnd);
            root.left = buildTreeUtil(postorder, inorder, inStart, indexIn -1);
            
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
