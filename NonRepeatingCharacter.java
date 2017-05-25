import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class NonRepeatingCharacter {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        		//code
		Scanner sc = new Scanner(System.in);
		int test_cases = sc.nextInt();
		int i = 0;
		while(i < test_cases){
		    
		    String word = sc.next();
		    char output = nonRepeatingCharacter(word);
		    if(output == '1'){
		        System.out.println(-1);
		    }
		    else{
		        System.out.println(output);
		    }
		    i++;
		}
    }
    
    	public static char nonRepeatingCharacter(String word){
	    char result = '1';
            
	    LinkedHashMap<Character,Integer> map = new LinkedHashMap<Character,Integer>();
	    
	    for(int i=0; i <word.length(); i++){
	        if(!map.containsKey(word.charAt(i))){
	            map.put(word.charAt(i),1);
	        }
	        else{
                int value = map.get(word.charAt(i)) + 1;
	            map.put(word.charAt(i),value);
	        }
	    }
	    
	    for(Map.Entry<Character,Integer> entry : map.entrySet()){
	        
	        if(entry.getValue() == 1){
	            result = entry.getKey();
                break;
	        }
	    }
	    
	    return result;
	}
}
