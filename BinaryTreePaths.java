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
    ArrayList<String> result = new ArrayList<String>();
    ArrayList<Integer> list = new ArrayList<Integer>();
    
    public List<String> binaryTreePaths(TreeNode root) {
        util(root);
        return result;
    }
    
    public void util(TreeNode root){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            list.add(root.val);
            //add
            addlist(list);
            list.remove(list.size() - 1);
            return;
        }
        
        list.add(root.val);
        util(root.left);
        util(root.right);
        
        list.remove(list.size() - 1);
        
        return;
        
    }
    
    public void addlist(ArrayList<Integer> list){
        String dummy = "" + list.get(0);
        for(int i=1; i < list.size(); i++){
             dummy = dummy + "->" + list.get(i);
        }
        result.add(dummy);
    }
}
