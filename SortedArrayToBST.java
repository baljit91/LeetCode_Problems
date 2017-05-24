/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SortedArrayToBST {
    public TreeNode sortedArrayToBST(int[] nums) {
        return util(nums,0,nums.length-1);
    }
    
    public TreeNode util(int[] nums,int first, int last){
        
        if(first > last){
            return null;
        }
        
        int mid = (first + last)/2;
        
        TreeNode head = new TreeNode(nums[mid]);
        head.left = util(nums,first,mid -1);
        head.right = util(nums,mid+1,last);

        return head;
    }
}
