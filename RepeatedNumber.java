public class RepeatedNumber {
	// DO NOT MODIFY THE LIST
	public int repeatedNumber(final List<Integer> a) {
	    
	    HashSet<Integer> map = new HashSet<Integer>();
	    
	    for(int i=0; i< a.size(); i++){
	        if(!map.contains(a.get(i))){
	            map.add(a.get(i));
	        }
	        else{
	            return a.get(i);
	        }
	    }
	    return -1;
	}
}
