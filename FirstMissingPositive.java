/**Given an unsorted integer array, find the first missing positive integer.

Example:

Given [1,2,0] return 3,

[3,4,-1,1] return 2,

[-8, -7, -6] returns 1

Your algorithm should run in O(n) time and use constant space.**/

public class Solution {
	public int firstMissingPositive(ArrayList<Integer> a) {

	    HashSet<Integer> map = new HashSet<Integer>();
	    
	    for(int i=0; i < a.size(); i++){
	        if(a.get(i) > 0){
	            map.add(a.get(i));
	        }
	    }
	    
	    
	    int iterator = 1;
	    
	    while(iterator <= a.size()){
	        if(!map.contains(iterator)){
	            return iterator;
	        }
	        iterator++;
	    }
	    
	    return iterator;
	}
}
