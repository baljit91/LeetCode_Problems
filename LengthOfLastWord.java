public class LengthOfLastWord {
	public int lengthOfLastWord(final String a) {
	    int stringLength = a.length();
	    int itr = stringLength - 1;
	    int output = 0;
	    
	    while(itr>= 0 && a.charAt(itr) == ' ')
	        itr--;
	    
	    while(itr>= 0 && a.charAt(itr) != ' '){
	        output++;
	        itr--;
	    }
	    
	    return output;
	}
}
