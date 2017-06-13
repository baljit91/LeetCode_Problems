/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 //vertical tree traversal with Map
public class Solution {
    Map<Integer, ArrayList<Integer>> map = new TreeMap<Integer, ArrayList<Integer>>();
    
    public ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode node) {
       
       ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        
        verticalOrderUtil(node,0);
        for(Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()){
            ArrayList<Integer> current = entry.getValue();
            result.add(current);
        }
        
        return result;
    }
    
        public void verticalOrderUtil(TreeNode node,int col){
        if(node == null)
            return;
        
        if(map.containsKey(col)){
            ArrayList<Integer> current = map.get(col);
            current.add(node.val);
        }
        else{
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(node.val);
            map.put(col,list);
        }
        
        verticalOrderUtil(node.left,col - 1);
        verticalOrderUtil(node.right,col +1);
    }
}
